package mall.spring.project.service;

import java.util.List;

import mall.spring.project.domain.CartVO;
import mall.spring.project.domain.ProductVO;
import mall.spring.project.pageutil.PageCriteria;

public interface CoupangService {
	public abstract List<ProductVO> read(String productName, String categoryCode, PageCriteria criteria, String sort);
	public abstract int read_count(String productName, String categoryCode);
	public abstract ProductVO read_No(int productNo);

}
