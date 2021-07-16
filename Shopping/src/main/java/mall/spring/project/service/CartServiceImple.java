package mall.spring.project.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mall.spring.project.domain.CartVO;
import mall.spring.project.persistence.CartDAO;

@Service
public class CartServiceImple implements CartService{
	private static final Logger logger = 
			LoggerFactory.getLogger(CoupangServiceImple.class);
	
	@Autowired
	private CartDAO dao;
	
	@Override
	public CartVO read_cart_check(String customerId, String cartName) {
		logger.info("ServiceImple의 read_cart_check() 호출");
		logger.info("customerId : " + customerId);
		logger.info("cartName : " + cartName);
		return dao.select_cart_check(customerId, cartName);
	}

	@Override
	public int create_cart(CartVO cartvo) {
		logger.info("ServiceImple의 create_cart() 호출");
		logger.info("cartvo : " + cartvo.toString());
		return dao.insert_cart(cartvo);
	}
	
	@Override
	public int update_cart(CartVO vo) {
		logger.info("ServiceImple의 update_cart() 호출");
		logger.info("vo : " + vo.toString());
		return dao.update_cart(vo);
	}

	@Override
	public List<CartVO> read_cart(String customerId) {
		logger.info("ServiceImple의 read_cart() 호출");
		logger.info("customerId : " + customerId);
		return dao.select_cart(customerId);
	}

	@Override
	public int delete_cart(String customerId, String name) {
		logger.info("ServiceImple의 delete_cart() 호출");
		logger.info("customerId : " + customerId);
		logger.info("cartName : " + name);
		return dao.delete_cart(customerId, name);
	}

	@Override
	public int delete_cart_all(String customerId) {
		logger.info("ServiceImple의 delete_cart_all() 호출");
		logger.info("customerId : " + customerId);
		return dao.delete_cart_all(customerId);
	}

	@Override
	public CartVO read_cart_No(String cartNo) {
		logger.info("ServiceImple의 read_cart_No() 호출");
		logger.info("cartNo : " + cartNo);
		return dao.select_cart_No(cartNo);
	}

	@Override
	public int create_and_select_seq(CartVO vo) {
		logger.info("ServiceImple의 create_and_select_seq() 호출");
		logger.info("vo : " + vo);
		return dao.insert_and_select_seq(vo);
	}
}
