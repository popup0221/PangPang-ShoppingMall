package mall.spring.project.service;

import java.util.List;

import mall.spring.project.domain.AddressVO;
import mall.spring.project.domain.BuyVO;

public interface BuyService {
	public abstract int create_buy(BuyVO vo);
	public abstract AddressVO read_address(int addressNo);
	public abstract List<BuyVO> read_buy(String buyId);
	public abstract List<BuyVO> read_order(String customerId);
}
