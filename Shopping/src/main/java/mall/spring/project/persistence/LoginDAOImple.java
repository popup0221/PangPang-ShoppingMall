package mall.spring.project.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mall.spring.project.domain.AddressVO;
import mall.spring.project.domain.CustomerVO;

@Repository // @Component
// - 영속 계층(Persistence Layer)의 DB 관련 기능을 담당
// - Spring Component bean으로 등록함
// - 스프링 프레임워크가 bean을 생성하기 위해서는
//    root-context.xml에 bean으로 등록해야 함
// - <context:component-scan ... />

public class LoginDAOImple implements LoginDAO{
   private static final Logger LOGGER = 
         LoggerFactory.getLogger(LoginDAOImple.class);
   private static final String NAMESPACE = 
         "mall.spring.project.LoginMapper";
   
   // Mybatis의 SqlSession을 사용하기 위해서
   // 스프링 프레임워크가 생성한 bean을 주입(injection)받음
   
   @Autowired
   private SqlSession sqlSession;
   
   @Override
   public int insert(CustomerVO vo) {
      LOGGER.info("insert() 호출");
      return sqlSession.insert(NAMESPACE + ".insert", vo);
   }

	@Override
	public CustomerVO select(String customerId) {
		LOGGER.info("select() 호출");
		return sqlSession.selectOne(NAMESPACE + ".select", customerId);
	}
   
	@Override
	public int select(String customerId, String customerPw) {
		LOGGER.info("select() 호출");
		Map<String, String> args = new HashMap<String,String>(); 
		args.put("customerId", customerId);
		args.put("customerPw", customerPw);
		return sqlSession.selectOne(NAMESPACE + ".select_check", args);
	}

	@Override
	public int select_check_Id(String customerId) {
		LOGGER.info("select_check_id() 호출");
		return sqlSession.selectOne(NAMESPACE + ".select_check_id", customerId);
	}

	@Override
	public int select_check_phone(String customerPhone) {
		LOGGER.info("select_check_phone() 호출");
		return sqlSession.selectOne(NAMESPACE + ".select_check_phone", customerPhone);
	}

	@Override
	public int select_check_password(String customerId, String customerPw) {
		LOGGER.info("select_check_pw() 호출");
		Map<String, String> args = new HashMap<String,String>(); 
		args.put("customerId", customerId);
		args.put("customerPw", customerPw);
		return sqlSession.selectOne(NAMESPACE + ".select_check_pw", args);
	}

	@Override
	public int select_check_name_id(String customerName, String customerId) {
		LOGGER.info("select_check_name_id() 호출");
		Map<String, String> args = new HashMap<String,String>(); 
		args.put("customerName", customerName);
		args.put("customerId", customerId);
		return sqlSession.selectOne(NAMESPACE + ".select_check_name_id", args);
	}

	@Override
	public String select_find_Id(String customerName, String customerPhone) {
		LOGGER.info("select_find_id() 호출");
		Map<String, String> args = new HashMap<String,String>(); 
		args.put("customerName", customerName);
		args.put("customerPhone", customerPhone);
		return sqlSession.selectOne(NAMESPACE + ".select_find_id", args);
	}
	
	//비밀번호변경
	@Override
	public int updatePw(CustomerVO vo) throws Exception {
		return sqlSession.update(NAMESPACE+".updatePw", vo);
	}

	@Override
	public int delete(String customerId) {
		LOGGER.info("delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete", customerId);
	}

	@Override
	public int updateName(String updateName, String customerId) {
		LOGGER.info("update_name() 호출");
		LOGGER.info("updateName : " + updateName);
		LOGGER.info("customerId : " + customerId);
		Map<String, String> args = new HashMap<String,String>(); 
		args.put("updateName", updateName);
		args.put("customerId", customerId);
		return sqlSession.update(NAMESPACE + ".updateName", args);
	}

	@Override
	public int updatePhone(String updatePhone, String customerId) {
		LOGGER.info("update_phone() 호출");
		LOGGER.info("updatePhone : " + updatePhone);
		LOGGER.info("customerId : " + customerId);
		Map<String, String> args = new HashMap<String,String>(); 
		args.put("updatePhone", updatePhone);
		args.put("customerId", customerId);
		return sqlSession.update(NAMESPACE + ".updatePhone", args);
	}

	@Override
	public int updatePassword(String updatePw, String customerId) {
		LOGGER.info("update_password() 호출");
		LOGGER.info("updatePw : " + updatePw);
		LOGGER.info("customerId : " + customerId);
		Map<String, String> args = new HashMap<String,String>(); 
		args.put("updatePw", updatePw);
		args.put("customerId", customerId);
		return sqlSession.update(NAMESPACE + ".updatePw2", args);
	}

	@Override
	public int insert(AddressVO vo) {
		LOGGER.info("insert() 호출");
	    return sqlSession.insert(NAMESPACE + ".insert_address", vo);
	}

	@Override
	public List<AddressVO> selectList(String customerId) {
		LOGGER.info("selectList() 호출");
	    return sqlSession.selectList(NAMESPACE + ".selectList", customerId);
	} 

	@Override
	public int updateAddress(AddressVO vo) {
		LOGGER.info("updateAddress() 호출");
		return sqlSession.update(NAMESPACE + ".updateAddress", vo);
	}

	@Override
	public int deleteAddress(int addressNo) {
		LOGGER.info("deleteAddress() 호출");
		return sqlSession.delete(NAMESPACE + ".deleteAddress", addressNo);
	}

}