<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../resources/css/buy.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../resources/js/buy.js"></script>
<title>구매하기</title>
</head>
<body>
    <div id="body">
    
   <h1>
      <a href="Coupang"><img src="../resources/image/main.png"></a>
    </h1>

    <hr>

    <div class="middle">
       <h1>주문/결제</h1>
       
       <hr class="thick">
    
       <div>
          <h2 style="display: inline;">구매자정보</h2>
          <table class="design">
             <tbody>
                <tr class="tr">
                   <th class="th" scope="row" style="text-align: left;">이름</th>
                   <td class="td">
                      ${Cvo.customerName}
                   </td>
                </tr>
                <tr class="tr">
                   <th class="th" scope="row" style="text-align: left;">이메일</th>
                   <td class="td">
                      <span class="customerId">${Cvo.customerId}</span> 
                   </td>
                </tr>
                <tr class="tr">
                   <th class="th" scope="row" style="text-align: left;">휴대폰번호</th>
                   <td class="td">
                      ${Cvo.customerPhone}
                   </td>
                </tr>
             </tbody>
          </table>
       </div>
       
       <div>
          <h2 style="display: inline;">받는사람정보</h2> 
          &nbsp; 
          <input type="button" onclick="chooseAddr()" value="배송지 선택"> <br>
          
          <table id="addressTbl" style="display: none;" class="design">
             <tbody>
                <tr class="tr">
                   <th class="th" scope="row">이름</th>
                   <td class="td">
                      <span id="addressName">.</span>
                   </td>
                </tr>
                <tr class="tr">
                   <th class="th" scope="row">배송주소</th>
                   <td class="td" id="address">
                      <span id="addressDestination"></span>, <span id="addressDetail"></span>
                   </td>
                </tr>
                <tr class="tr">
                   <th class="th" scope="row">연락처</th>
                   <td class="td">
                      <span id="addressPhone"></span>
                   </td>
                </tr>
                <tr class="tr">
                   <th class="th" scope="row">배송 요청사항</th>
                   <td class="td">
                      <span id="addressRequest"></span>
                   </td>
                </tr>
             </tbody>
          </table>
       </div>
      
       <div class="bundle-info__retail ">
          <div class="bundle-info__pdd-group-title">배송정보</div>
          <div class="bundle-info__pdd-group-box">
         <c:if test="${not empty buyList}">
          <c:forEach var="vo" items="${buyList}">
              <div class="totalPrice" style="display: none;">
              <span class="Unit_totalPrice">${vo.cartTotalprice}</span>원
              </div>
              
               <input type="hidden" class="productNo" value="${vo.productNo}">
               <input type="hidden" class="imagedata" value="${vo.cartImage}">
                   
            <div class="bundle-info__item-list ">
                  <div class="bundle-info__vendor-item-box ">
                    <div class="bundle-info__vendor-item">
                      <p class="ProductName"> 
                      <span class="bundle-info__vendor-item__offer-condition"></span>
                      ${vo.cartName }</p>
                      <input type="hidden" id="cartName" value="${vo.cartName }">
                    </div>
                    <div class="bundle-info__item-description">
                      수량 : <span class="Amount">${vo.cartAmount}</span>개
                    </div>
                    <span>무료배송</span>
                  </div>
               </div>
         </c:forEach>
         </c:if>
           
            <c:if test="${empty buyList}">
              <div class="totalPrice" style="display: none;">
              <span class="Unit_totalPrice">${buyvo.productPrice*Amount}</span>원
              </div>
              
               <input type="hidden" class="productNo" value="${buyvo.productNo}">
               <input type="hidden" class="imagedata" value="${buyvo.productImage}">
               
               <div class="bundle-info__item-list ">
                  <div class="bundle-info__vendor-item-box ">
                    <div class="bundle-info__vendor-item">
                      <p> 
                      <span class="bundle-info__vendor-item__offer-condition"></span>
                      ${buyvo.productName}</p>
                    <input type="hidden" id="cartName" value="${buyvo.productName }">
                    </div>
                    <div class="bundle-info__item-description">
                    수량 : <span class="Amount">${Amount}</span>개
                    </div>
                    <span>무료배송</span>
                  </div>
               </div>
           </c:if>
           
         </div>
       </div>
      
      <div>
          <h2>결제정보</h2> 
         
         <table class="design">
            <tbody>
               <tr class="tr">
                  <th class="th" scope="row" style="text-align: left;">총상품가격</th>
                  <td class="td">
                      <span><span class="price"></span>원</span>
                  </td>
               </tr>
            </tbody>
         </table>
      </div>
      
        <form action="buy" method="post" name="buy">
          <input type="hidden" name="buyId" id="buyId">
          <input type="hidden" name="addressNo" id="addressNo">
          
          <button id="check_module" type="button">구매하기</button>
        </form>
        
    </div>
   </div>
</body>
</html>