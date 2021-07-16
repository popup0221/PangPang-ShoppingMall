package mall.spring.project.domain;

public class ProductVO {
	private int productNo;
	private String productName;
	private int productPrice;
	private String productSeller;
	private String productCourier;
	private String productImage;
	private int reviewRating;
	private String categoryCode;
	private String salesRate;
	
	public ProductVO() {
		// TODO Auto-generated constructor stub
	}

	public ProductVO(int productNo, String productName, int productPrice, String productSeller, String productCourier,
			String productImage, int reviewRating, String categoryCode, String salesRate) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productSeller = productSeller;
		this.productCourier = productCourier;
		this.productImage = productImage;
		this.reviewRating = reviewRating;
		this.categoryCode = categoryCode;
		this.salesRate = salesRate;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductSeller() {
		return productSeller;
	}

	public void setProductSeller(String productSeller) {
		this.productSeller = productSeller;
	}

	public String getProductCourier() {
		return productCourier;
	}

	public void setProductCourier(String productCourier) {
		this.productCourier = productCourier;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public int getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getSalesRate() {
		return salesRate;
	}

	public void setSalesRate(String salesRate) {
		this.salesRate = salesRate;
	}

	@Override
	public String toString() {
		return "ProductVO [productNo=" + productNo + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productSeller=" + productSeller + ", productCourier=" + productCourier + ", productImage="
				+ productImage + ", reviewRating=" + reviewRating + ", categoryCode=" + categoryCode + ", salesRate="
				+ salesRate + "]";
	}
}
