package mall.spring.project.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import mall.spring.project.domain.AddressVO;
import mall.spring.project.domain.CustomerVO;

// CRUD(CREATE, READ, UPDATE, DELETE)
public interface LoginService {
	//
	public abstract int create(CustomerVO vo);
	public abstract int create(AddressVO vo);
	public abstract CustomerVO read(String customerId);
	public abstract int read_check(String customerId, String customerPw, String non_Member);
	public abstract int read_check_Id(String customerId);
	public abstract int read_check_phone(String customerPhone);
	public abstract int read_check_pw(String customerId, String customerPw);
	public abstract String read_find_Id(String customerName, String customerPhone);
	public abstract int update_Name(String updateName, String customerId);
	public abstract int update_Phone(String updatePhone, String customerId);
	public abstract int update_Password(String updatePw, String customerId);
	public abstract int update_Address(AddressVO vo);
	public abstract int delete_Address(int addressNo);
	
	//이메일발송
	public void sendEmail(CustomerVO vo, String div) throws Exception;

	//비밀번호찾기
	public void findPw(HttpServletResponse resp, CustomerVO vo) throws Exception;
	
	public abstract int delete(String customerId);
	
	public List<AddressVO> read_List(String customerId);
}