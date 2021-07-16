package mall.spring.project.domain;

public class CartVO {
	private int cartNo;
	private String customerId;
	private String cartImage;
	private String cartName;
	private int cartPrice;
	private int cartAmount;
	private int cartTotalprice;
	private String productNo;
	private String category;
	
	public CartVO() {}

	public CartVO(int cartNo, String customerId, String cartImage, String cartName, int cartPrice, int cartAmount,
			int cartTotalprice, String productNo, String category) {
		super();
		this.cartNo = cartNo;
		this.customerId = customerId;
		this.cartImage = cartImage;
		this.cartName = cartName;
		this.cartPrice = cartPrice;
		this.cartAmount = cartAmount;
		this.cartTotalprice = cartTotalprice;
		this.productNo = productNo;
		this.category = category;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCartImage() {
		return cartImage;
	}

	public void setCartImage(String cartImage) {
		this.cartImage = cartImage;
	}

	public String getCartName() {
		return cartName;
	}

	public void setCartName(String cartName) {
		this.cartName = cartName;
	}

	public int getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(int cartPrice) {
		this.cartPrice = cartPrice;
	}

	public int getCartAmount() {
		return cartAmount;
	}

	public void setCartAmount(int cartAmount) {
		this.cartAmount = cartAmount;
	}

	public int getCartTotalprice() {
		return cartTotalprice;
	}

	public void setCartTotalprice(int cartTotalprice) {
		this.cartTotalprice = cartTotalprice;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "CartVO [cartNo=" + cartNo + ", customerId=" + customerId + ", cartImage=" + cartImage + ", cartName="
				+ cartName + ", cartPrice=" + cartPrice + ", cartAmount=" + cartAmount + ", cartTotalprice="
				+ cartTotalprice + ", productNo=" + productNo + ", category=" + category + "]";
	}
}
