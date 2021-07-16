package mall.spring.project.service;

import java.io.PrintWriter;
import java.util.List;

import javax.mail.Address;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mall.spring.project.domain.AddressVO;
import mall.spring.project.domain.CartVO;
import mall.spring.project.domain.CustomerVO;
import mall.spring.project.persistence.BuyDAO;
import mall.spring.project.persistence.CartDAO;
import mall.spring.project.persistence.LoginDAO;

@Service // @Component
// 스프링 프레임워크에게 서비스 계층(Service/Business layer)의
// 컴퍼넌트임을 알려주는 어노테이션
public class LoginServiceImple implements LoginService{
	private static final Logger logger =
			LoggerFactory.getLogger(LoginServiceImple.class);

	@Autowired
	private LoginDAO loginDAO;
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private BuyDAO buyDAO;
	

	// SpringSecurity 단방향 암호화
	@Autowired
	PasswordEncoder Encoding;
	
	@Override
	public int create(CustomerVO vo) {
		logger.info("create() 호출 : vo = " + vo.toString());
		return loginDAO.insert(vo);
	}

	@Override
	public CustomerVO read(String customerId) {
		logger.info("read() 호출 ");
		logger.info("customerId = " + customerId);
		return loginDAO.select(customerId);
	}

	@Transactional()
	@Override
	public int read_check(String customerId, String customerPw, String non_Member) {
		logger.info("read_check() 호출 ");
		logger.info("customerId = " + customerId);
		logger.info("customerPw = " + customerPw);
		
		int result = loginDAO.select(customerId, customerPw);
		
		logger.info("result : " + result);
		
		// 비회원 장바구니 조회 
		logger.info("non_Member : " + non_Member);
		List<CartVO> NM_list = cartDAO.select_cart(non_Member);
		logger.info("NM_list: " + NM_list);
		
		// 비회원 장바구니 + 회원 장바구니 (중복검사)
		for (int i = 0; i < NM_list.size(); i++) {
			if(NM_list != null) {
				String cartName = NM_list.get(i).getCartName();
				logger.info(cartName);
				CartVO overlap = cartDAO.select_cart_check(customerId, cartName);
				logger.info("overlap : " + overlap);
				
				if (overlap == null) {
					logger.info("중복 X -> insert");
					NM_list.get(i).setCustomerId(customerId);
					cartDAO.insert_cart(NM_list.get(i));
				} else {
					logger.info("중복 O -> update");
					
					int addAmount = overlap.getCartAmount() + NM_list.get(i).getCartAmount();
					int totalPrice = overlap.getCartPrice() * addAmount;
					overlap.setCartAmount(addAmount);
					overlap.setCartTotalprice(totalPrice);
					
					cartDAO.update_cart(overlap);
				}
			}
			cartDAO.delete_cart_all(non_Member);
		}
		return result;
	}

	@Override
	public int read_check_Id(String customerId) {
		logger.info("read_check_id() 호출 ");
		logger.info("customerId = " + customerId);
		return loginDAO.select_check_Id(customerId);
	}

	@Override
	public int read_check_phone(String customerPhone) {
		logger.info("read_check_phone() 호출 ");
		logger.info("customerPhone = " + customerPhone);
		return loginDAO.select_check_phone(customerPhone);
	}

	@Override
	public int read_check_pw(String customerId, String customerPw) {
		logger.info("read_check_password() 호출 ");
		logger.info("customerId = " + customerId);
		logger.info("customerPw = " + customerPw);
		return loginDAO.select_check_password(customerId, customerPw);
	}

	@Override
	public String read_find_Id(String customerName, String customerPhone) {
		logger.info("read_find_id() 호출 ");
		logger.info("customerName = " + customerName);
		logger.info("customerPhone = " + customerPhone);
		return loginDAO.select_find_Id(customerName, customerPhone);
	}
	
	////////////////////////////////////////////////////////
	
	//비밀번호 찾기 이메일발송
	@Override
	public void sendEmail(CustomerVO vo, String div) throws Exception {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com"; //네이버 이용시 smtp.naver.com
		String hostSMTPid = "sayd3041@gmail.com";
		String hostSMTPpwd = "dbehd20513";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "sayd3041@gmail.com";
		String fromName = "Coupang_Clone";
		String subject = "";
		String msg = "";

		if(div.equals("findpw")) {
			subject = "쿠팡클론 임시 비밀번호 입니다.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += vo.getCustomerId() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
			msg += "<p>임시 비밀번호 : ";
			msg += vo.getCustomerPw() + "</p></div>";
		}

		// 받는 사람 E-Mail 주소
		String mail = vo.getCustomerId();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			
			if(mail.contains("gmail")) {				
				email.setSmtpPort(465); // gmail -> 465
			} else if(mail.contains("naver")) {
				email.setSmtpPort(587); // naver -> 587
			}

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
	}

	//비밀번호찾기
	@Override
	public void findPw(HttpServletResponse response, CustomerVO vo) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 가입된 아이디가 없으면
		if(loginDAO.select_check_name_id(vo.getCustomerName(), vo.getCustomerId()) == 0) {
			out.print("등록되지 않은 사용자입니다."); // 아이디 -> 사용자
			out.close();
		}
		else {
			// 임시 비밀번호 생성
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			
			// 암호화된 비밀번호
			String encode_pw = Encoding.encode(pw);
			
			// 비밀번호 변경
			vo.setCustomerPw(encode_pw);
			loginDAO.updatePw(vo);
			
			
			// 비밀번호 변경 메일 발송
			vo.setCustomerPw(pw); // 사용자에게는 암호화 전 비밀번호로 전송
			sendEmail(vo, "findpw");

			out.print("이메일로 임시 비밀번호를 발송하였습니다.");
			out.close();
		}
	}

	@Transactional()
	@Override
	public int delete(String customerId) {
		logger.info("delete() 호출 : customerId = " + customerId);
		
		// 회원 탈퇴
		int result = loginDAO.delete(customerId);
		
		// 탈퇴하는 회원의 주소정보 삭제
		List<AddressVO> list = loginDAO.selectList(customerId);
		for (int i = 0; i < list.size(); i++) {
			loginDAO.deleteAddress(list.get(i).getAddressNo());
		}		
		// 탈퇴하는 회원의 장바구니 정보 삭제
		cartDAO.delete_cart_all(customerId);
		// 탈퇴하는 회원의 구매 정보 삭제
		buyDAO.delete_buy_all(customerId);
		
		return result;
	}

	@Override
	public int update_Name(String updateName, String customerId) {
		logger.info("update_Name() 호출 ");
		logger.info("updateName = " + updateName);
		logger.info("customerId = " + customerId);
		return loginDAO.updateName(updateName, customerId);
	}

	@Override
	public int update_Phone(String updatePhone, String customerId) {
		logger.info("update_Phone() 호출 ");
		logger.info("updatePhone = " + updatePhone);
		logger.info("customerId = " + customerId);
		return loginDAO.updatePhone(updatePhone, customerId);
	}

	@Override
	public int update_Password(String updatePw, String customerId) {
		logger.info("update_Password() 호출 ");
		logger.info("updatePw = " + updatePw);
		logger.info("customerId = " + customerId);
		return loginDAO.updatePassword(updatePw, customerId);
	}

	@Override
	public int create(AddressVO vo) {
		logger.info("create() 호출 : vo = " + vo.toString());
		return loginDAO.insert(vo);
	}

	@Override
	public List<AddressVO> read_List(String customerId) {
		logger.info("read_list() 호출, customerId : " + customerId);
		return loginDAO.selectList(customerId);
	}

	@Override
	public int update_Address(AddressVO vo) {
		logger.info("update_Address() 호출 : vo = " + vo.toString());
		return loginDAO.updateAddress(vo);
	}

	@Override
	public int delete_Address(int addressNo) {
		logger.info("delete_Address() 호출, addressNo = " + addressNo);
		return loginDAO.deleteAddress(addressNo);
	}

}
