package mall.spring.project.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mall.spring.project.domain.CartVO;

@Repository 
public class CartDAOImple implements CartDAO{
	private Logger logger = LoggerFactory.getLogger(CoupangDAOImple.class);
	private String NAMESPACE = "mall.spring.project.cartMapper";	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public CartVO select_cart_check(String customerId, String cartName) {
		logger.info("daoImple의 select_cart_check() 호출");
		logger.info("customerId : " + customerId);
		logger.info("cartName : " + cartName);
		Map<String, String> args = new HashMap<String, String>();
		args.put("customerId", customerId);
		args.put("cartName", cartName);
		return sqlSession.selectOne(NAMESPACE+".select_cart_check", args);
	}
	
	@Override
	public int insert_cart(CartVO cartvo) {
		logger.info("daoImple의 insert_cart() 호출");
		logger.info("cartvo : " + cartvo);
		return sqlSession.insert(NAMESPACE+".insert_cart", cartvo);
	}


	@Override
	public int update_cart(CartVO vo) {
		logger.info("daoImple의 update_cart() 호출");
		logger.info("vo : " + vo.toString());
		return sqlSession.update(NAMESPACE+".update_cart", vo);
	}

	@Override
	public List<CartVO> select_cart(String customerId) {
		logger.info("daoImple의 select_cart() 호출");
		logger.info("customerId" + customerId);
		return sqlSession.selectList(NAMESPACE+".select_cart", customerId);
	}

	@Override
	public int delete_cart(String customerId, String name) {
		logger.info("daoImple의 delete_cart() 호출");
		logger.info("customerId : " + customerId);
		logger.info("cartName : " + name);
		
		Map<String, String> args = new HashMap<String, String>();
		args.put("customerId", customerId);
		args.put("cartName", name);
		
		return sqlSession.delete(NAMESPACE+".delete_one", args);
	}

	@Override
	public int delete_cart_all(String customerId) {
		logger.info("daoImple의 delete_cart_all() 호출");
		logger.info("customerId : " + customerId);
		
		return sqlSession.delete(NAMESPACE+".delete_cart_all", customerId);
	}

	@Override
	public CartVO select_cart_No(String cartNo) {
		logger.info("daoImple의 select_cart_No() 호출");
		logger.info("cartNo : " + cartNo);
		return  sqlSession.selectOne(NAMESPACE+".select_cart_no", cartNo);
	}

	@Override
	public int insert_and_select_seq(CartVO vo) {
		logger.info("daoImple의 insert_and_select_seq() 호출");
		logger.info("vo : " + vo);
		return sqlSession.insert(NAMESPACE+".insert_and_select_seq", vo);
	}

	@Override
	public void delete_cart_by_Pno(String customerId, int productNo) {
		logger.info("daoImple의 delete_cart_by_Pno() 호출");
		logger.info("customerId : " + customerId);
		logger.info("productNo : " + productNo);
		
		Map<String, String> args = new HashMap<String, String>();
		args.put("customerId", customerId);
		args.put("productNo", productNo+"");
		
		
		sqlSession.delete(NAMESPACE+".delete_cart_by_Pno", args);
	}
}
