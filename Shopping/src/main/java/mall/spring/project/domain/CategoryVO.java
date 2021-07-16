package mall.spring.project.domain;

public class CategoryVO {
	private String categoryCode;
	private String categoryNo;
	
	public CategoryVO() {}

	public CategoryVO(String categoryCode, String categoryNo) {
		super();
		this.categoryCode = categoryCode;
		this.categoryNo = categoryNo;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	@Override
	public String toString() {
		return "CategoryVO [categoryCode=" + categoryCode + ", categoryNo=" + categoryNo + "]";
	}
	
}
