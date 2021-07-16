package mall.spring.project.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mall.spring.project.domain.AddressVO;
import mall.spring.project.domain.BuyVO;

@Repository 
public class BuyDAOImple implements BuyDAO{
	private Logger logger = LoggerFactory.getLogger(BuyDAOImple.class);
	private String NAMESPACE = "mall.spring.project.BuyMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert_buy(BuyVO vo) {
		logger.info("daoImple의 insert_buy() 호출");
		logger.info("buyvo : " + vo);
		return sqlSession.insert(NAMESPACE + ".insert_buy", vo);
	}

	@Override
	public AddressVO select_address(int addressNo) {
		logger.info("daoImple의 select_address() 호출");
		logger.info("addressNo : " + addressNo);
		return sqlSession.selectOne(NAMESPACE + ".select_address", addressNo);
	}

	@Override
	public List<BuyVO> select_buy(String buyId) {
		logger.info("daoImple의 select_buy() 호출");
		logger.info("addressNo : " + buyId);
		return sqlSession.selectList(NAMESPACE + ".select_buy", buyId);
	}
	
	@Override
	public List<BuyVO> select_order(String customerId) {
		logger.info("daoImple의 select_order() 호출");
		logger.info("customerId : " + customerId);
		return sqlSession.selectList(NAMESPACE + ".select_order", customerId);
	}

	@Override
	public int delete_buy_all(String customerId) {
		logger.info("daoImple의 delete_buy_all() 호출");
		logger.info("customerId : " + customerId);
		return sqlSession.delete(NAMESPACE + ".delete_buy_all", customerId);
	}
}
