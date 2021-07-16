package mall.spring.project.domain;

public class AddressVO {
	private String customerId;
	private int addressNo;
	private String addressName;
	private String addressDestination;
	private String addressDetail;
	private String addressPhone;
	private String addressRequest;
	
	public AddressVO() {}

	public AddressVO(String customerId, int addressNo, String addressName, String addressDestination,
			String addressDetail, String addressPhone, String addressRequest) {
		super();
		this.customerId = customerId;
		this.addressNo = addressNo;
		this.addressName = addressName;
		this.addressDestination = addressDestination;
		this.addressDetail = addressDetail;
		this.addressPhone = addressPhone;
		this.addressRequest = addressRequest;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getAddressNo() {
		return addressNo;
	}

	public void setAddressNo(int addressNo) {
		this.addressNo = addressNo;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressDestination() {
		return addressDestination;
	}

	public void setAddressDestination(String addressDestination) {
		this.addressDestination = addressDestination;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getAddressPhone() {
		return addressPhone;
	}

	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}

	public String getAddressRequest() {
		return addressRequest;
	}

	public void setAddressRequest(String addressRequest) {
		this.addressRequest = addressRequest;
	}

	@Override
	public String toString() {
		return "AddressVO [customerId=" + customerId + ", addressNo=" + addressNo + ", addressName=" + addressName
				+ ", addressDestination=" + addressDestination + ", addressDetail=" + addressDetail + ", addressPhone="
				+ addressPhone + ", addressRequest=" + addressRequest + "]";
	}
}
