<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../resources/css/cart.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../resources/js/cart.js"></script>
<title>장바구니 페이지</title>
</head>
  
<body>
<div class="center">
  <h1>
    <a href="Coupang"><img src="../resources/image/main.png"></a>
  </h1> 
  
  <div>
    <h2>장바구니</h2>
    <table class="cartTable">
      <colgroup>
        <col width="50">
        <col width="80">
        <col width="*">
        <col width="90">
        <col width="90">
      </colgroup>
      <thead>
        <c:if test="${not empty list}">
          <tr>
            <th scope="col" class="all-select-event">
              <label>
                <input type="checkbox" class="all-deal-select" checked="checked"> 
                <span>&nbsp;&nbsp;전체선택</span>
              </label>
            </th>
            <th scope="colgroup" id="th-product-box" colspan="2">상품정보</th>
            <th scope="col" id="th-unit-total-price">상품금액</th>
            <th scope="col" id="th-delivery-fee">배송비</th>
          </tr>
        </c:if>
      </thead>
      
      
      <tbody>
        <tr>
          <td colspan="5" class="cart_bundle-title">
            <hr> <!-- thead 스타일 작업후 지우기 -->
            <h2 class="title bundle">판매 상품</h2>
          </td>
        </tr>

        <c:if test = "${empty NotLogin && empty list}">
        <tr>
            <td colspan="5" class="cart-bundle-title">장바구니에 담은 상품이 없습니다.</td>
          <tr>
        </c:if>

        <c:forEach var="vo" items="${list}">
          <tr class="cart-deal-item count0 cart-sc-items last-cart-sc-items">

            <td class="product-select-event">
              <input type="checkbox" class="dealSelectChk" checked="checked" name="SelectChk">
              <input type="hidden" class="cartNo" value="${vo.cartNo}">
            </td>
            
            <td>
              <a href="detail?productNo=${vo.productNo}&categoryCode=${vo.category}" class="moveProduct">
                <img src="${vo.cartImage }" width="100" height="100" class="product-img img"> 
                <input type="hidden" value="${vo.cartImage }" class="ImgVal">
              </a>
            </td>

            <td class="product-box">
              <input type="hidden" class="customerID" value="${vo.customerId}">
              <div class="product-name-part">
                <a href="detail?productNo=${vo.productNo}&categoryCode=${vo.category}">${vo.cartName }</a>
              </div>
              <div class="option-item modify-float">
                <div class="option-item-info"> 
                  <div class="option-name">
                    <span class="option-item-name" style="display: none;">${vo.cartName }</span>
                  </div>
                  <div class="delivery-date partner modify-padding" style="visibility: visible;">
                    <span class="arrive-date" style="display: inline-block;"> 
                      <span class="arrive-date-txt"></span> 
                      <span class="arrive-date-day"></span> 
                      <span class="arrive-date-date"></span>
                      도착 예정
                    </span>
                  </div>
                  <div class="option-price part">
                    <span class="unit-cost">${vo.cartPrice }</span>원
                    <span class="select-select"> 
                    <select class="quantity-select" name="quantity-select">
                        <option value="1" <c:if test="${vo.cartAmount == 1}">selected</c:if>>1</option>
                        <option value="2" <c:if test="${vo.cartAmount == 2}">selected</c:if>>2</option>
                        <option value="3" <c:if test="${vo.cartAmount == 3}">selected</c:if>>3</option>
                        <option value="4" <c:if test="${vo.cartAmount == 4}">selected</c:if>>4</option>
                        <option value="5" <c:if test="${vo.cartAmount == 5}">selected</c:if>>5</option>
                        <option value="6" <c:if test="${vo.cartAmount == 6}">selected</c:if>>6</option>
                        <option value="7" <c:if test="${vo.cartAmount == 7}">selected</c:if>>7</option>
                        <option value="8" <c:if test="${vo.cartAmount == 8}">selected</c:if>>8</option>
                        <option value="9" <c:if test="${vo.cartAmount == 9}">selected</c:if>>9</option>
                        <option value="10" <c:if test="${vo.cartAmount == 10}">selected</c:if>>10</option>
                    </select>
                    </span> 
                    <span class="unit-price-area"> 
                    <span class="unit-price"> 
                        <span class="sr-only">구매가격</span>
                        ${vo.cartTotalprice}원
                    </span>
                    </span><a href="cart" class="delete-option"></a>
                  </div>
                </div>
              </div>
            </td>
            
            <td class="unit-total-price" headers="th-unit-total-price">
              <div class="unit-total-sale-price">${vo.cartTotalprice}원</div>
            </td>
            
            <td class="delivery-fee" rowspan="1" headers="th-delivery-fee">무료</td>
         
          </tr>
        </c:forEach>
        
        <tr class="bundle-price-total">
          <td colspan="5">
            <h3 class="sr-only">주문금액 정보</h3>
            상품 가격
            <span class="total-product-price number">0</span>원
            <span class="coupon-area">
              <span class="symbol symbol-plus">
                <span class="sr-only">더하기</span>
              </span>
              배송비
              <span class="delivery-charge number">0</span>원
              <span class="symbol symbol-equal">
                <span class="sr-only">결과는</span>
              </span>
              주문금액
              <span class="total-order-price number">0</span>원
            </span>
          </td>
        </tr>
      </tbody>
    </table>
    
    <c:if test="${not empty list}">
    <div class="order-table-foot">
      <span class="all-select-product">
        <a href="cart" class="selected-delete">전체 삭제</a>
      </span>
    </div>
    
      
    <div class="order-buttons">
      <!-- 메인페이지 -->
      <a id="continueShoopingBtn" class="goShopping logging" href="Coupang">계속 쇼핑하기</a>
      <!-- 구매하기 -->
      <a href="javascript:void(0)" class="goPayment narrow" id="btnPay">구매하기</a>
      <div class="item-disabled" style="display: none;"></div>
    </div>
    </c:if>
  </div>
  
  <c:if test = "${not empty NotLogin && empty list}">
  <div class="cart-no-item">   
     <p class="txt">장구니에 담은 상품이 없습니다.</p>
     
     <p class="login-area">
        <span>로그인을 하시면, 장바구니에 보관된 상품을 확인하실 수 있습니다.</span>
        <a href="../member/login?rtnUrl=../main/cart" class="login"></a>
     </p>
  </div>
  </c:if>
</div>
</body>
</html>











