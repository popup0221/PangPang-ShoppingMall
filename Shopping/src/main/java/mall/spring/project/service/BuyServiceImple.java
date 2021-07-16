package mall.spring.project.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mall.spring.project.domain.AddressVO;
import mall.spring.project.domain.BuyVO;
import mall.spring.project.persistence.BuyDAO;
import mall.spring.project.persistence.CartDAO;

@Service
public class BuyServiceImple implements BuyService{
	private static final Logger logger = 
			LoggerFactory.getLogger(BuyServiceImple.class);
	
	@Autowired
	private BuyDAO buyDAO;
	
	@Autowired
	private CartDAO cartDAO;
	
	
	@Transactional()
	@Override
	public int create_buy(BuyVO vo) {
		logger.info("buyvo : " + vo.toString());
		
		String customerId = vo.getCustomerId();
		int productNo = vo.getProductNo();
		
		cartDAO.delete_cart_by_Pno(customerId, productNo);
		
		return buyDAO.insert_buy(vo);
	}

	@Override
	public AddressVO read_address(int addressNo) {
		logger.info("ServiceImple의 read_address() 호출");
		logger.info("addressNo : " + addressNo);
		return buyDAO.select_address(addressNo);
	}

	@Override
	public List<BuyVO> read_buy(String buyId) {
		logger.info("ServiceImple의 read_buy() 호출");
		logger.info("addressNo : " + buyId);
		return buyDAO.select_buy(buyId);
	}

	@Override
	public List<BuyVO> read_order(String customerId) {
		logger.info("ServiceImple의 read_order() 호출");
		logger.info("customerId : " + customerId);
		return buyDAO.select_order(customerId);
	}
}
