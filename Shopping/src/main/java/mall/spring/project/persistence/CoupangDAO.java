package mall.spring.project.persistence;

import java.util.List;

import mall.spring.project.domain.CartVO;
import mall.spring.project.domain.ProductVO;
import mall.spring.project.pageutil.PageCriteria;

public interface CoupangDAO {
	public abstract List<ProductVO> select(String productName, String categoryCode, PageCriteria criteria, String sort);
	public abstract int select_count(String productName, String categoryCode);
	public abstract ProductVO select_No(int productNo);
} 