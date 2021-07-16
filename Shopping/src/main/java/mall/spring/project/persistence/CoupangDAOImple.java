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
import mall.spring.project.domain.ProductVO;
import mall.spring.project.pageutil.PageCriteria;

@Repository 
public class CoupangDAOImple implements CoupangDAO{
	private Logger logger = LoggerFactory.getLogger(CoupangDAOImple.class);
	private String NAMESPACE = "mall.spring.project.ShopMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ProductVO> select(String productName, String categoryCode, PageCriteria criteria, String sort) {
		logger.info("daoImple의 select() 호출");
		String product = "%"+productName+"%"; // 상품이름 like 처리
		String start = criteria.getStart()+""; 
		String end = criteria.getEnd()+""; 
		
		Map<String, String> args = new HashMap<String, String>();
		args.put("productName", product);
		args.put("categoryCode", categoryCode);	
		args.put("start", start);	
		args.put("end", end);	
		args.put("sort", sort);
	
		
		logger.info("CoupangDAOImple -> productName: " + product);
		logger.info("CoupangDAOImple -> categoryCode: " + categoryCode);
		logger.info("CoupangDAOImple -> start: " + start + ", end: " + end);
		logger.info("CoupangDAOImple -> sort: " + sort);
		
		return sqlSession.selectList(NAMESPACE+".select_search", args);
	}

	@Override
	public int select_count(String productName, String categoryCode) {
		logger.info("daoImple의 select_count() 호출");
		String product = "%"+productName+"%"; // 상품이름 like 처리
		Map<String, String> args = new HashMap<String, String>();
		args.put("productName", product);
		args.put("categoryCode", categoryCode);	
		return sqlSession.selectOne(NAMESPACE+".select_count", args);
	}

	@Override
	public ProductVO select_No(int productNo) {
		logger.info("daoImple의 select_No() 호출");
		logger.info("productNo : " + productNo);
		return sqlSession.selectOne(NAMESPACE+".select_no", productNo);
	}
}
