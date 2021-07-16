<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../resources/css/list.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../resources/js/list.js"></script>
<title>상품 리스트 페이지</title>
</head>

<body style="text-align: center;">
<div class="center">
  <div>
  
  <jsp:include page="Coupang.jsp"></jsp:include>

  <hr>
  
  <div class="search-content search-content-with-feedback ">
   
  </div>
  
  <div> 
   <p class="hit-count"><strong>'${productName}'</strong> 에 대한 <strong>${count}</strong>개의 검색 결과</p>
  </div>


  <div class="search-sorting">
    <div id="searchSortingOrder">
      <ul class="sort_ul">
        <li class="sort_li">
          <input type="radio" name="sort" id="review_rating" value="review_rating" checked="checked">
          <label>높은 평점순</label>
        </li>
        <li class="sort_li">
          <input type="radio" name="sort" id="product_price asc" value="product_price asc"
          <c:if test = "${sort eq 'product_price asc'}">checked="checked"</c:if>
           /> 
          <label>낮은 가격순</label>
        </li>
        <li class="sort_li">
          <input type="radio" name="sort" id="product_price desc" value="product_price desc"
          <c:if test = "${sort eq 'product_price desc'}">checked="checked"</c:if>> 
          <label>높은 가격순</label>
        </li>
        <li class="sort_li">
          <input type="radio" name="sort" id="sales_rate" value="sales_rate"
          <c:if test = "${sort eq 'sales_rate'}">checked="checked"</c:if>> 
          <label>판매량순</label>
        </li>
      </ul>
    </div>

    <div>
      <ul class="listsize_ul">
        <li class="listsize_li">          
          <input type="radio" name="pagesize" id="8" value="8" checked="checked">
          <label>8개씩 보기</label>
        </li>
        <li class="listsize_li">
          <input type="radio" name="pagesize" id="16" value="16" 
          <c:if test = "${pagesize eq '16'}">checked="checked"</c:if>>
          <label>16개씩 보기</label>
        </li>
        <li class="listsize_li">
          <input type="radio" name="pagesize" id="24" value="24"
          <c:if test = "${pagesize eq '24'}">checked="checked"</c:if>> 
          <label>24개씩 보기</label>
        </li>
        <li class="listsize_li">
          <input type="radio" name="pagesize" id="32" value="32"
          <c:if test = "${pagesize eq '32'}">checked="checked"</c:if>>
          <label>32개씩 보기</label>
        </li>
      </ul>
    </div>
  </div>

  <hr>
  <br>

  <ul class="list_ul">
    <c:forEach var="vo" items="${list}">
      <li class="list_li"><a href="../main/detail?productNo=${vo.productNo}&categoryCode=${vo.categoryCode}" target="_blank">
          <dl>
            <dt>
              <img class="img" src="${vo.productImage }">
            </dt>
            <dd>
              <p>${vo.productName }</p>
              <p>
                <strong>${vo.productPrice }원</strong>
              </p>
              <p class="deldate"></p>
              <span>
                 <c:choose>
                  <c:when test="${vo.reviewRating eq '0'}">
                    ☆☆☆☆☆
                  </c:when>
                  <c:when test="${vo.reviewRating eq '1'}">
                    ★☆☆☆☆
                  </c:when>
                  <c:when test="${vo.reviewRating eq '2'}">
                    ★★☆☆☆
                  </c:when>
                  <c:when test="${vo.reviewRating eq '3'}">
                    ★★★☆☆
                  </c:when>
                  <c:when test="${vo.reviewRating eq '4'}">
                    ★★★★☆
                  </c:when>
                  <c:when test="${vo.reviewRating eq '5'}">
                    ★★★★★
                  </c:when>
                </c:choose>
              </span> 
              <span class="rating_count">
                <input type="hidden" class="rating_count">
              </span>
            </dd>
          </dl>
      </a></li>
    </c:forEach>
  </ul>

  <br>

  </div>
</div>

  <div class="paging">
    <ul>
      <!-- 이전 버튼 -->
      <c:if test="${pageMaker.hasPrev}">
        <li><a href="list?categoryCode=${categoryCode}&productName=${productName}&page=${pageMaker.endPageNo - 1}&sort=${sort}&pagesize=${pagesize}">이전</a></li>
      </c:if>

      <c:forEach begin="${pageMaker.startPageNo}" end="${pageMaker.endPageNo}" var="num">
        <li><a href="list?categoryCode=${categoryCode}&productName=${productName}&page=${num}&sort=${sort}&pagesize=${pagesize}">${num}</a></li>
      </c:forEach>

      <!-- 다음 버튼 -->
      <c:if test="${pageMaker.hasNext}">
        <li><a href="list?categoryCode=${categoryCode}&productName=${productName}&page=${pageMaker.endPageNo + 1}&sort=${sort}&pagesize=${pagesize}">다음</a></li>
      </c:if>
    </ul>
  </div>


  
  <input type="hidden" class="categoryCode_val" value="${categoryCode}">
  <input type="hidden" class="productName_val" value="${productName}">
  <input type="hidden" class="page_val" value="${pageMaker.criteria.page}">

</body>
</html>