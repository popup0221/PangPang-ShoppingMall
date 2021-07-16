<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../resources/css/detail.css" rel="stylesheet"
  type="text/css" />
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../resources/js/detail.js"></script>
<title>상품 상세 페이지</title>
</head>
<body>
  <div class="center">
    
      <!-- 재 검색 -->
      <jsp:include page="Coupang.jsp"></jsp:include>

      <!-- 이미지, 이름, 평점, 가격, 날짜, 판매자, 택배사, 갯수, 장바구니,구매-->
      <div class="prod-atf">
        <div class="prod-image">
          <input type="hidden" class="productImage"
            value="${vo.productImage}"> <img
            src="${vo.productImage }">
        </div>

        <div class="prod-buy">
          <div>
            <input type="hidden" class="productName"
              value="${vo.productName}">
            <h2>${vo.productName}</h2>
            <span class="stars"></span>
          </div>

          <br>

          <div>
            <input type="hidden" class="Base_productPrice"
              value="${vo.productPrice}"> <strong><span
              class="productPrice" id="productPrice">${vo.productPrice}원</span></strong>
          </div>

          <br>

          <div>
            <span class="deldate"></span>
          </div>

          <br>

          <div>
            <c:if test="${not empty vo.productSeller}">
              <span>판매자 : ${vo.productSeller}</span>
            </c:if>
            <br>
            <c:if test="${not empty vo.productCourier}">
              <span>택배사 : ${vo.productCourier}</span>
            </c:if>
          </div>

          <div class="prod-buy-quantity-and-footer">

            <div class="prod-buy-quantity">
              <div class="prod-quantity__form">
                <input class="productAmount" type="text"
                  id="productAmount" value="1">
                <div style="display: table-cell; vertical-align: top;">
                  <button class="btn_plus" id="btn_plus">∧</button>
                  <button class="btn_minus" id="btn_minus">∨</button>
                </div>
              </div>
            </div>

            <div class="prod-buy-footer ">
              <div class="prod-onetime-order ">
                <button class="btn_cart" id="btn_cart">장바구니 담기</button>
                <button class="btn_buy" id="btn_buy">구매하기</button>
              </div>
            </div>

          </div>
        </div>
      </div>

      <!-- 상품 상세, 제품평(리뷰), 상품문의 -->
      <div id="btfTab" class="tab">
        <ul class="tab-titles">
          <li name="detail" class="active">상품상세</li>
          <li name="review">상품평 <span
            class="product-tab-review-count">(99)</span>
          </li>
          <li name="qna">상품문의</li>
          <li name="etc">배송/교환/반품 안내</li>
        </ul>

        <ul class="tab-contents">
          <li class="product-detail">
            <div class="prod-selling-point" style="display:none;">
              <div class="prod-selling-point__title">특장점</div>
              <div class="prod-selling-point__content"></div>
            </div>
            <br>
            <div>
              <div id="itemBrief" class="product-essential-info">
                <div class="product-item__table">
                 <p class="table-title">필수 표기정보</p>
                 <table class="prod-delivery-return-policy-table essential-info-table">
                  <colgroup>
                    <col width="150px">
                    <col width="340px">
                    <col width="150px">
                    <col width="*">
                  </colgroup>
                  <tbody>
                    <tr>
                      <th>제품명</th>
                      <td>${vo.productName }</td>
                      <th>카테고리</th>
                      <td>${vo.categoryCode }</td>
                    </tr>
                    <tr>
                      <th>개 당 가격</th>
                      <td>${vo.productPrice}</td>
                      <th>배송정보</th>
                      <td><span class="deldate"></span></td>
                    </tr>
                    <tr>
                      <th>판매자</th>
                      <td>
                        <c:if test="${empty vo.productSeller}">정보없음</c:if>
                        <c:if test="${not empty vo.productSeller}">${vo.productSeller}</c:if>  
                      </td>
                      <th>배송사</th>
                      <td> 
                        <c:if test="${empty vo.productCourier}">정보없음</c:if>
                        <c:if test="${not empty vo.productCourier  }">${vo.productCourier}</c:if> 
                      </td>
                    </tr>
                  </tbody>
                 </table>
                </div>
              </div>
            </div>
          </li>
          <li class="product-review">
            <div class="sdp-review">
            
            </div>
          </li>
          <li class="product-qna"></li>
          <li class="product-etc"></li>
        </ul>
      </div>

      
      <input type="hidden" value="${vo.productNo}" class="hid_no">
      <input type="hidden" value="${vo.categoryCode}" class="hid_category">
  </div>
</body>
</html>