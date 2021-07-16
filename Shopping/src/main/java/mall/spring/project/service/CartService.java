package mall.spring.project.service;

import java.util.List;

import mall.spring.project.domain.CartVO;

public interface CartService {
	public abstract CartVO read_cart_check(String customerId, String cartName);
	public abstract int create_cart(CartVO cartvo);
	public abstract int update_cart(CartVO vo);
	public abstract List<CartVO> read_cart(String customerId);
	public abstract CartVO read_cart_No(String cartNo);
	public abstract int delete_cart(String customerId, String name);
	public abstract int delete_cart_all(String customerId);
	public abstract int create_and_select_seq(CartVO vo);	
}
