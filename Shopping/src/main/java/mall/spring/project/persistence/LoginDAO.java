package mall.spring.project.persistence;

import java.util.List;

import mall.spring.project.domain.AddressVO;
import mall.spring.project.domain.CustomerVO;

public interface LoginDAO {
	public abstract int insert(CustomerVO vo);
	public abstract int insert(AddressVO vo);
	public abstract CustomerVO select(String customerId);
	public abstract int select(String customerId, String customerPw);
	public abstract int select_check_Id(String customerId);
	public abstract int select_check_phone(String customerPhone);
	public abstract int select_check_name_id(String customerName, String customerId);
	public abstract int select_check_password(String customerId, String customerPw);
	public abstract String select_find_Id(String customerName, String customerPhone);
	public abstract int updateName(String updateName, String customerId);
	public abstract int updatePhone(String updatePhone, String customerId);
	public abstract int updatePassword(String updatePw, String customerId);
	public abstract int updateAddress(AddressVO vo);
	public abstract int deleteAddress(int addressNo);
	
	// 임시비밀번호 변경
	public abstract int updatePw(CustomerVO vo) throws Exception;
	
	public abstract int delete(String customerId);
	
	public List<AddressVO> selectList(String customerId);
}
