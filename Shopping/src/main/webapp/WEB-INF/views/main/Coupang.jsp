<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../resources/css/Coupang.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../resources/js/Coupang.js"></script>
<title>PangPang 메인페이지</title>
</head>

<body style="text-align: center;">
  <div id="container" class="renewal home srp-sync srp-sync-brand">
    <div class="renewal-header">
      <header id="header">
        <section>
          <h1>
            <a href="../main/Coupang"><img src="../resources/image/main.png"></a>
          </h1>
       <form action="../main/list" method="get">
          <select name=categoryCode id="categoryCode">
            <option class="all" value="all" selected="selected">전체</option>
            <option value="fashion">패션</option>
            <option value="child">유아</option>
            <option value="food">식품</option>
            <option value="household">가정용품</option>
            <option value="digital">가전디지털</option>
            <option value="car">자동차</option>
            <option value="hobby">취미</option>
            <option value="pet">반려동물</option>
          </select> 
          
          <input type="text" name="productName" class="search_bar" id="search" placeholder="찾고 싶은 상품을 검색해 보세요!"> 
          <input type="submit" class="btn_search" value="검색">       
        
          <button type="button" id="btn_myPage">마이페이지</button>
          <button type="button" id="btn_cart">장바구니</button>
        </form>    
        </section>
      </header>
      
      <article class="top-bar">
        <section>
          <menu id="headerMenu">
            <c:if test="${empty customerId}">
              <button type="button" id="btn_login" class="member_btn">로그인</button>
              <a href="../member/register"><button
                  class="member_btn">회원가입</button></a>
            </c:if>
            <c:if test="${not empty customerId}">
              <button type="button" id="btn_logout" class="member_btn">로그아웃</button>
            </c:if>
          </menu>
        </section>
      </article>
    </div>
    
    <h3><b>"주스"로 검색해주세요</b></h3>
    <hr>
  </div>
</body>
</html>