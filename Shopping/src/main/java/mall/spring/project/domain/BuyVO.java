package mall.spring.project.domain;

import java.util.List;

public class BuyVO {
   private int buyNo;
   private String buyId;
   private String buyProducts;
   private String buyDate;
   private String buyDelDate;
   private String buyDelCheck;
   private int buyAmount;
   private int buyTotalPrice;
   private String buyImage;
   private int addressNo;
   private String customerId;
   private int productNo;
   
   private List<BuyVO> buyList;
   
   public List<BuyVO> getBuyList() {
	   return buyList;
   }

   public void setBuyList(List<BuyVO> buyList) {
	   this.buyList = buyList;
   }

public BuyVO() {}

   public BuyVO(int buyNo, String buyId, String buyProducts, String buyDate, String buyDelDate, String buyDelCheck,
         int buyAmount, int buyTotalPrice, String buyImage, int addressNo, String customerId, int productNo) {
      super();
      this.buyNo = buyNo;
      this.buyId = buyId;
      this.buyProducts = buyProducts;
      this.buyDate = buyDate;
      this.buyDelDate = buyDelDate;
      this.buyDelCheck = buyDelCheck;
      this.buyAmount = buyAmount;
      this.buyTotalPrice = buyTotalPrice;
      this.buyImage = buyImage;
      this.addressNo = addressNo;
      this.customerId = customerId;
      this.productNo = productNo;
   }

   public int getBuyNo() {
      return buyNo;
   }

   public void setBuyNo(int buyNo) {
      this.buyNo = buyNo;
   }

   public String getBuyId() {
      return buyId;
   }

   public void setBuyId(String buyId) {
      this.buyId = buyId;
   }

   public String getBuyProducts() {
      return buyProducts;
   }

   public void setBuyProducts(String buyProducts) {
      this.buyProducts = buyProducts;
   }

   public String getBuyDate() {
      return buyDate;
   }

   public void setBuyDate(String buyDate) {
      this.buyDate = buyDate;
   }

   public String getBuyDelDate() {
      return buyDelDate;
   }

   public void setBuyDelDate(String buyDelDate) {
      this.buyDelDate = buyDelDate;
   }

   public String getBuyDelCheck() {
      return buyDelCheck;
   }

   public void setBuyDelCheck(String buyDelCheck) {
      this.buyDelCheck = buyDelCheck;
   }

   public int getBuyAmount() {
      return buyAmount;
   }

   public void setBuyAmount(int buyAmount) {
      this.buyAmount = buyAmount;
   }

   public int getBuyTotalPrice() {
      return buyTotalPrice;
   }

   public void setBuyTotalPrice(int buyTotalPrice) {
      this.buyTotalPrice = buyTotalPrice;
   }

   public String getBuyImage() {
      return buyImage;
   }

   public void setBuyImage(String buyImage) {
      this.buyImage = buyImage;
   }

   public int getAddressNo() {
      return addressNo;
   }

   public void setAddressNo(int addressNo) {
      this.addressNo = addressNo;
   }

   public String getCustomerId() {
      return customerId;
   }

   public void setCustomerId(String customerId) {
      this.customerId = customerId;
   }

   public int getProductNo() {
      return productNo;
   }

   public void setProductNo(int productNo) {
      this.productNo = productNo;
   }

   @Override
   public String toString() {
      return "BuyVO [buyNo=" + buyNo + ", buyId=" + buyId + ", buyProducts=" + buyProducts + ", buyDate=" + buyDate
            + ", buyDelDate=" + buyDelDate + ", buyDelCheck=" + buyDelCheck + ", buyAmount=" + buyAmount
            + ", buyTotalPrice=" + buyTotalPrice + ", buyImage=" + buyImage + ", addressNo=" + addressNo
            + ", customerId=" + customerId + ", productNo=" + productNo + "]";
   }
   
}