package mall.spring.project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mall.spring.project.domain.AddressVO;
import mall.spring.project.domain.CustomerVO;
import mall.spring.project.service.LoginService;

@Controller
@RequestMapping(value = "/member")
public class LoginController {
   private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

   // SpringSecurity 단방향 암호화
   @Autowired
   PasswordEncoder Encoding;
   
   @Autowired
   private LoginService loginService;

   String non_mem = "";   

   // 로그인 페이지로 넘어가기
   @GetMapping("/login")
   public void loginGet(String rtnUrl, HttpSession session) {
      logger.info("loginGet() 호출");
      
      if(rtnUrl != null) {
         logger.info("장바구니 로그인 클릭 : " + rtnUrl);
         session.setAttribute("rtnUrl", rtnUrl);
      } else {
         session.setAttribute("rtnUrl", "../main/Coupang");
      }
   }

   // 로그인 버튼 클릭
   @PostMapping("/login")
   public void loginPost(String customerId, String customerPw, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
      logger.info("loginPost() 호출");
      logger.info("customerId : " + customerId); // 넘어온 값 확인
      logger.info("customerPw : " + customerPw);
      
      
      if(session.getAttribute("customerId")!=null) {
         // 기존의 세션 값이 존재한다면
         session.removeAttribute("customerId"); // 세션 값 제거
      }

      // 로그인시 비회원 DB + 기존 DB를 처리하기 위함
      Cookie[] cookies = request.getCookies();
      String non_Member = "";
      if (cookies != null) {
         for (int i = 0; i < cookies.length; i++) {
            Cookie c = cookies[i];

            // 쿠키값을 가져온다
            non_Member = c.getValue();
            logger.info("non_Member : " + non_Member);
         }
      }
      
      int check_result = 0;
      
      // 암호화된 비밀번호와 입력한 비밀번호 일치 여부를 확인하는 변수
      boolean dataMatch = false;
      
      try {
         check_result = loginService.read_check(customerId, customerPw, non_Member);
         CustomerVO check_vo = loginService.read(customerId);
         dataMatch = Encoding.matches(customerPw, check_vo.getCustomerPw());
         
         logger.info("check_result : " + check_result);
      } catch (NullPointerException e) {
         customerId = null;
      }

      if (dataMatch == true) {
         session = request.getSession();
         session.setAttribute("customerId", customerId);
         String session_success = (String)session.getAttribute("customerId");
         logger.info("세션 생성 성공 : " + session_success);
         logger.info("로그인 성공");
         
         
         String destination = (String) session.getAttribute("destination");
         if(destination != null) {
            response.sendRedirect(destination);
         } else {
            String rtnUrl = (String) session.getAttribute("rtnUrl");
            logger.info(rtnUrl);
            
            if(rtnUrl != null) {
               response.sendRedirect(rtnUrl);
            }
         }
      } else {
         logger.info("로그인 실패");
         response.sendRedirect("login?loginFail=loginFail");
      }
   }

   // 회원가입 페이지로 넘어가기
   @GetMapping("/register")
   public void registerGet() {
      logger.info("registerGet() 호출");
   }

   // 회원가입 버튼 클릭
   @PostMapping("/register")
   public String registerPost(CustomerVO vo, RedirectAttributes reAttr) {
      logger.info("registerPost() 호출");
      logger.info(vo.toString());
      
      // 비밀번호 암호화
      String input_pw = vo.getCustomerPw();
	  String encode_pw = Encoding.encode(input_pw);
	  vo.setCustomerPw(encode_pw);
	  
      int result = loginService.create(vo);
      logger.info(result + "행 삽입");
      if (result == 1) {
         // "insert_result"의 키이름을 가진 데이터 전송
         reAttr.addFlashAttribute("insert_result", "success");
         return "member/login"; // /member/login 경로로 이동. get방식
      } else {
         // "insert_result"의 키이름을 가진 데이터 전송
         reAttr.addFlashAttribute("insert_result", "fail");
         return "member/register"; // /member/register 경로로 이동. get방식
      }
   }
   
   //    @ResponseBody -> json을 jsp에 보내줄때 사용하는 어노테이션 자동으로 parsing 까지 해서 보내줌
   //    @RequestBody -> jsp -> controller로 json을 받아올때 사용하는 어노테이션
   // 회원가입 이메일(아이디) 중복 체크
   @PostMapping("/check_id")
   @ResponseBody
   public int checkId(String customerId) throws Exception{
      logger.info(customerId);
      int result = loginService.read_check_Id(customerId);
      logger.info("result : " + result);
      return result;
   }
   
   // 회원가입 전화번호 중복 체크
   @PostMapping("/check_tel")
   @ResponseBody
   public int checkTel(String customerPhone) throws Exception{
      logger.info(customerPhone);
      int result = loginService.read_check_phone(customerPhone);
      logger.info("result : " + result);
      return result;
   }
   
   // id/pw 찾기 페이지로 넘어가기
   @GetMapping("/accountFind")
   public void accountFindGet() {
      logger.info("accountFindGet() 호출");
   }
   
   // id 검색 클릭
   @PostMapping("/find_id")
   @ResponseBody
   public String findId(String customerName, String customerPhone) throws Exception{
      logger.info("이름 :" + customerName);
      logger.info("전화번호 :" + customerPhone);
      String result = loginService.read_find_Id(customerName, customerPhone);
      logger.info("아이디 :" + result);
      return result;
   }
   
   // id/pw 찾기 페이지에서 pw찾기 페이지로 넘어가기
   @GetMapping("/findPassword")
   public void findPasswordGet() {
      logger.info("findPasswordGet() 호출");
   }
   
   // 비밀번호 찾기
   @RequestMapping(value = "/findpw", method = RequestMethod.POST)
   public void findPwPOST(@ModelAttribute CustomerVO vo, HttpServletResponse response) throws Exception{
      loginService.findPw(response, vo);
   }
   
   // 회원정보확인 페이지(비밀번호인증)열기
   @GetMapping("/memberCheck")
   public void memberCheckGet() {
      logger.info("memberCheckGet() 호출");
   }
   
   //  회원정보확인 비밀번호인증확인 클릭
   @PostMapping("/memberCheck")
   public void memberCheckPost(String customerPw, HttpServletRequest request, HttpServletResponse response) throws IOException {
	  logger.info("memberCheckPost() 호출");
      
	  HttpSession session = request.getSession();
      String customerId = (String)session.getAttribute("customerId");
      logger.info("customerId : " + customerId); // 넘어온 값 확인
      
      logger.info("customerPw : " + customerPw);
      
      int check_result = 0;
      
      // 암호화된 비밀번호와 입력한 비밀번호 일치 여부를 확인하는 변수
      CustomerVO check_vo = loginService.read(customerId);
      boolean dataMatch = Encoding.matches(customerPw, check_vo.getCustomerPw()); 
     
      if (dataMatch == true) {
    	  logger.info("비밀번호 확인완료");
          response.sendRedirect("memberModify");
      } else {
    	  logger.info("비밀번호가 틀림");
          response.sendRedirect("memberCheck?Fail=Fail");
      }
   }
   
   // 회원정보수정 페이지열기
   @GetMapping("/memberModify")
   public void memberModifyGet(Model model, HttpServletRequest request) throws Exception{
      logger.info("memberModifyGet() 호출");
      HttpSession session = request.getSession();
      String customerId = (String)session.getAttribute("customerId");
      CustomerVO vo = loginService.read(customerId);
      model.addAttribute("vo", vo);
   }
   
   // 회원 이름 수정
   @PostMapping("/update_name")
   @ResponseBody
   public String updateName(String updateName, HttpServletRequest request) throws Exception{
      HttpSession session = request.getSession();
      String customerId = (String)session.getAttribute("customerId");
      logger.info("customerId : " + customerId);
      logger.info("updateName : " + updateName);
      loginService.update_Name(updateName, customerId);
      return updateName;
   }
   
   // 회원 전화번호 수정
   @PostMapping("/update_phone")
   @ResponseBody
   public String updatePhone(String updatePhone, HttpServletRequest request) throws Exception{
      HttpSession session = request.getSession();
      String customerId = (String)session.getAttribute("customerId");
      logger.info("customerId : " + customerId);
      logger.info("updatePhone : " + updatePhone);
      loginService.update_Phone(updatePhone, customerId);
      return updatePhone;
   }

   // 회원 비밀번호 수정
   @PostMapping("/update_password")
   @ResponseBody
   public int updatePassword(String customerPw, String updatePw, HttpServletRequest request) throws Exception{
      HttpSession session = request.getSession();
      String customerId = (String)session.getAttribute("customerId");
      logger.info("customerId : " + customerId);
      logger.info("updatePassword : " + updatePw);
      
      // 암호화된 비밀번호와 입력한 비밀번호 일치 여부를 확인하는 변수
      CustomerVO check_vo = loginService.read(customerId);
      boolean dataMatch = Encoding.matches(customerPw, check_vo.getCustomerPw()); 
      
      int result;

      if(dataMatch == true) {
    	 // 바꿀 비밀번호 암호화
         String encode_pw = Encoding.encode(updatePw);
         
         result = loginService.update_Password(encode_pw, customerId); 
      } else {
         result=0;
      }
      logger.info("result : " + result);
      return result;
   }

   // 회원탈퇴 페이지(비밀번호인증)열기
   @GetMapping("/deleteCheck")
   public void deleteCheckGet(HttpServletRequest request, Model model) {
      logger.info("deleteCheckGet() 호출");
      HttpSession session = request.getSession();
      String customerId = (String)session.getAttribute("customerId");
      CustomerVO vo = loginService.read(customerId);
      model.addAttribute("vo", vo);
   }
   
   //  회원탈퇴 비밀번호인증확인 클릭
   @PostMapping("/deleteCheck")
   public void deleteCheckPost(String customerPw, HttpServletRequest request, HttpServletResponse response) throws IOException {
      HttpSession session = request.getSession();
      String customerId = (String)session.getAttribute("customerId");
      logger.info("deleteCheckPost() 호출");
      logger.info("customerId : " + customerId); // 넘어온 값 확인
      logger.info("customerPw : " + customerPw);
      
      // 암호화된 비밀번호와 입력한 비밀번호 일치 여부를 확인하는 변수
      CustomerVO check_vo = loginService.read(customerId);
      boolean dataMatch = Encoding.matches(customerPw, check_vo.getCustomerPw()); 
      
      if (dataMatch == true) {
         logger.info("비밀번호 확인완료");
         loginService.delete(customerId);
         session.removeAttribute("customerId"); // 세션 값 제거
         response.sendRedirect("../main/Coupang");
      } else {
         logger.info("비밀번호가 틀림");
         response.sendRedirect("deleteCheck?Fail=Fail");
      }
   }
   

   // 배송지관리 열기
   @GetMapping("/addressList")
   public void addressListGet(HttpServletRequest request, Model model) {
      logger.info("addressListGet() 호출");
      HttpSession session = request.getSession();
      String customerId = (String)session.getAttribute("customerId");
      List<AddressVO> list = loginService.read_List(customerId);
      model.addAttribute("list", list);
      String uri = request.getRequestURI();
      String[] uriSplit = uri.split("/",3);
      logger.info("uriSplit : " + uriSplit);
      String u = uriSplit[1] + "/" + uriSplit[2];
      logger.info(uriSplit[1] + "/" + uriSplit[2]);
      session.setAttribute("uri", u);
   }
   
   // 배송지추가 열기
   @GetMapping("/addressNew")
   public void addressNewGet() {
      logger.info("addressNewGet() 호출");
   }
      
   // 배송지추가 클릭
   @PostMapping("/addressNew")
   public void addresssNewPost(HttpSession session, AddressVO vo, RedirectAttributes reAttr, HttpServletRequest request, HttpServletResponse response) throws IOException {
      logger.info("addresssNewPost() 호출");
      logger.info(vo.toString());
      int result = loginService.create(vo);
      logger.info(result + "행 삽입");
      session = request.getSession();
      String uri = (String)session.getAttribute("uri");
      response.sendRedirect("../../" + uri);
   }
   
   // 배송지추가 열기
   @GetMapping("/addressEdit")
   public void addressEditGet(String addressNo, String addressName, String addressDestination, String addressDetail, String addressPhone, String addressRequest, Model model) {
      logger.info("addressEditGet() 호출");
      logger.info("addressNo : " + addressNo);
      logger.info("addressName : " + addressName);
      logger.info("addressDestination : " + addressDestination);
      logger.info("addressDetail : " + addressDetail);
      logger.info("addressPhone : " + addressPhone);
      logger.info("addressRequest : " + addressRequest);
      model.addAttribute("addressNo", addressNo);
      model.addAttribute("addressName", addressName);
      model.addAttribute("addressDestination", addressDestination);
      model.addAttribute("addressDetail", addressDetail);
      model.addAttribute("addressPhone", addressPhone);
      model.addAttribute("addressRequest", addressRequest);
   }
   
   // 배송지추가 클릭
   @PostMapping("/addressUpdate")
   public void addressUpdatePost(AddressVO vo, RedirectAttributes reAttr, HttpServletResponse response) throws IOException {
      logger.info("addressUpdatePost() 호출");
      logger.info(vo.toString());
      int result = loginService.update_Address(vo);
      logger.info(result + "행 수정");
      response.sendRedirect("addressList");
   }

   // 배송지삭제 클릭
   @PostMapping("/addressDelete")
   public void addressDeletePost(int addressNo, RedirectAttributes reAttr, HttpServletResponse response) throws IOException {
      logger.info("addressDeletePost() 호출");
      logger.info("addressNo : " + addressNo);
      int result = loginService.delete_Address(addressNo);
      logger.info(result + "행 삭제");
      response.sendRedirect("addressList");
   }
   
   @GetMapping("/logout")
   public String logout(HttpServletRequest request, HttpSession session) {
      logger.info("logout() 호출");
      
      session = request.getSession();
      session.removeAttribute("customerId");
      session.removeAttribute("destination");
      logger.info("customerId, destination 세션제거");
      
      return "redirect:../main/Coupang";
   }

}