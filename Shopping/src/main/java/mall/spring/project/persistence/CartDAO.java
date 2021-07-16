package mall.spring.project.persistence;

import java.util.List;

import mall.spring.project.domain.CartVO;

public interface CartDAO {
	public abstract int insert_cart(CartVO cartvo);
	public abstract CartVO select_cart_check(String customerId, String cartName);
	public abstract int update_cart(CartVO vo);
	public abstract List<CartVO> select_cart(String customerId);
	public abstract int delete_cart(String customerId, String name);
	public abstract int delete_cart_all(String customerId);
	public abstract CartVO select_cart_No(String cartNo);
	public abstract int insert_and_select_seq(CartVO vo);
	public abstract void delete_cart_by_Pno(String customerId, int productNo);
}
