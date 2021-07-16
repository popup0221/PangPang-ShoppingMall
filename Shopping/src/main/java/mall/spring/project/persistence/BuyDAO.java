package mall.spring.project.persistence;

import java.util.List;

import mall.spring.project.domain.AddressVO;
import mall.spring.project.domain.BuyVO;

public interface BuyDAO {
	public abstract int insert_buy(BuyVO vo);
	public abstract AddressVO select_address(int addressNo);
	public abstract List<BuyVO> select_buy(String buyId);
	public abstract List<BuyVO> select_order(String customerId);
	public abstract int delete_buy_all(String customerId);
} 