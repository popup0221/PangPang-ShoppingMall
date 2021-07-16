package mall.spring.project.domain;

import java.util.Date;

public class ReviewVO {
	private int reviewNo;
	private String reviewContent;
	private Date reviewDate;
	private int reviewLike;
	private int reviewUnlike;
	private int reviewRating;
	private int productNo;
	private String customerId;
	
	public ReviewVO() {}

	public ReviewVO(int reviewNo, String reviewContent, Date reviewDate, int reviewLike, int reviewUnlike,
			int reviewRating, int productNo, String customerId) {
		super();
		this.reviewNo = reviewNo;
		this.reviewContent = reviewContent;
		this.reviewDate = reviewDate;
		this.reviewLike = reviewLike;
		this.reviewUnlike = reviewUnlike;
		this.reviewRating = reviewRating;
		this.productNo = productNo;
		this.customerId = customerId;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public int getReviewLike() {
		return reviewLike;
	}

	public void setReviewLike(int reviewLike) {
		this.reviewLike = reviewLike;
	}

	public int getReviewUnlike() {
		return reviewUnlike;
	}

	public void setReviewUnlike(int reviewUnlike) {
		this.reviewUnlike = reviewUnlike;
	}

	public int getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", reviewContent=" + reviewContent + ", reviewDate=" + reviewDate
				+ ", reviewLike=" + reviewLike + ", reviewUnlike=" + reviewUnlike + ", reviewRating=" + reviewRating
				+ ", productNo=" + productNo + ", customerId=" + customerId + "]";
	}
}
