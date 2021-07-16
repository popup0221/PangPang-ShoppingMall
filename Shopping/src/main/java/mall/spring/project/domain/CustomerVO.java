package mall.spring.project.domain;

public class CustomerVO {
	private String customerId;
	private String customerPw;
	private String customerName;
	private String customerPhone;
	
	public CustomerVO() {}

	public CustomerVO(String customerId, String customerPw, String customerName, String customerPhone) {
		super();
		this.customerId = customerId;
		this.customerPw = customerPw;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerPw() {
		return customerPw;
	}

	public void setCustomerPw(String customerPw) {
		this.customerPw = customerPw;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	@Override
	public String toString() {
		return "CustomerVO [customerId=" + customerId + ", customerPw=" + customerPw + ", customerName=" + customerName
				+ ", customerPhone=" + customerPhone + "]";
	}

}
