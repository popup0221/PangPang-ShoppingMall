package mall.spring.project.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mall.spring.project.domain.ProductVO;
import mall.spring.project.pageutil.PageCriteria;
import mall.spring.project.persistence.CoupangDAO;

@Service
public class CoupangServiceImple implements CoupangService{
	private static final Logger logger = 
			LoggerFactory.getLogger(CoupangServiceImple.class);
	
	@Autowired
	private CoupangDAO dao;
	
	@Override
	public List<ProductVO> read(String productName, String categoryCode, PageCriteria criteria, String sort) {
		logger.info("ServiceImple의 read() 호출");
		logger.info("CoupangServiceImple productName: " + productName);
		logger.info("CoupangServiceImple categoryCode: " + categoryCode);
		logger.info("CoupangServiceImple sort: " + sort);
		return dao.select(productName, categoryCode, criteria, sort);
	}

	@Override
	public int read_count(String productName, String categoryCode) {
		logger.info("ServiceImple의 read_count() 호출");
		logger.info("CoupangServiceImple productName: " + productName);
		logger.info("CoupangServiceImple categoryCode: " + categoryCode);

		return dao.select_count(productName, categoryCode);
	}

	@Override
	public ProductVO read_No(int productNo) {
		logger.info("ServiceImple의 read_No() 호출");
		logger.info("productNO : " + productNo);
	
		return dao.select_No(productNo);
	}

}
