<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../resources/css/myPage.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../resources/js/myPage.js"></script>
<title>MyPage</title>
</head>
<body>
  <h1>
    <a href="Coupang"><img src="../resources/image/main.png"></a>
  </h1>

<!--  <h1>MyPage</h1> -->

  <div style="display: flex;">

    <div id="mc-left-menu" class="sc-1liba5b-0 khEJUl">
      <div id="myMenu" class="sc-1liba5b-3 kwYRYy">
        <nav>
          <div class="sc-1liba5b-4 hawZXu">
            <div class="sc-1liba5b-5 bODDJk">MY 쇼핑</div>
            <div class="sc-1liba5b-6 bBDFpz">
              <ul class="sc-1liba5b-7 bPNIBy">
                <li id="mc-order-list-page" class="sc-1liba5b-8 cHASlF selected"><a
                  href="../main/myPage" title="주문목록/배송조회s" target="_self"
                  class="sc-1liba5b-9 cVKVxE">주문목록/배송조회</a></li>
                <li id="mc-cancel-return-page" class="sc-1liba5b-8 cHASlF"><a
                  href="../member/memberCheck" title="개인정보확인/수정"
                  target="_self" class="sc-1liba5b-9 cVKVxE">개인정보확인/수정</a></li>
                <li id="mc-rocket-wow-page" class="sc-1liba5b-8 cHASlF"><a
                  href="../member/addressList" title="배송지 관리"
                  target="_self" class="sc-1liba5b-9 cVKVxE">배송지 관리</a></li>
              </ul>
            </div>
          </div>
        </nav>
      </div>
    </div>
    
    <div class="vv7pzb-0 jBlbmr my-area-body">
      <div class="vv7pzb-1 kLtvvC my-area-contents">
        <div class="sc-1de9moo-0 gnFuzJ">
          <div class="sc-173hyd4-0 UGWtR">
            <span class="sc-1vjn3x9-1 sc-173hyd4-1 jZfxHn csGJyw">주문목록</span>
          </div>
            <c:set var="Date" value="checkDate" />
            <c:forEach var="bvo2" items="${bvo}">
            <c:if test="${Date ne bvo2.buyDate}">
            <div class="qxzqk9-0 fLLyPk">
              <div class="fimazj-0 iNdylH">
                <div class="abukv2-0 fYfgsV">
                  <div class="abukv2-1 fpOWPc">
                    ${bvo2.buyDate} 주문
                  </div>
                </div>
              <c:forEach var="bvo" items="${bvo}">
                <c:if test="${bvo2.buyDate eq bvo.buyDate}">
                <div class="gnmni8-0 fRuZif">
                  <table class="gnmni8-1 fiiJTG">
                    <colgroup>
                      <col width="728.422">
                    </colgroup>
                    <tbody class="gnmni8-2 hjoQeN">
                      <tr class="gnmni8-3 gAcAQo">
                        <td class="gnmni8-5 iujTjm"><div class="ki5ja7-0 gpHCtP">
                            <div class="ki5ja7-1 fbDORO">
                              <input name="delDate" value="${bvo.buyDelDate}" type="hidden">
                              <span id="delCheck" font-weight="bold" color="#111111" style="font-size: 1.25rem" class="sc-755zt3-0 eTBQYg">
                                ${bvo.buyDelCheck}
                              </span>
                              <span size="4" class="sc-13xhsmd-0 oplxI">
                                <span size="4" class="sc-13xhsmd-1 CdZxw"></span>
                              </span>
                              <span
                                font-weight="normal" color="#00891A"
                                class="sc-755zt3-0 lcvccu-0 crvMkb lkVlhg">${bvo.buyDelDate} 도착</span>
                            </div>
                          </div>
                          <div class="fe2r96-0 fMdHQk"></div>
                          <div class="sc-1jiyjbz-0 ctCdtE">
                            <div class="gnmni8-9 bVRKeS">
                              <div class="g8964r-0 kTyDve"></div>
                              <div class="sc-9cwg9-1 cfYxaq">
                                <div class="sc-9cwg9-2 edkEk">
                                  <div class="sc-9cwg9-3 gbHcOJ">
                                    <a class="sc-1uwk3m0-0 EWvgd sc-9cwg9-0 jrBUjS"
                                      href="../main/detail?productNo=${bvo.productNo}"
                                      target="_blank">
                                      <input type="hidden" class="buyImage" value="${bvo.buyImage}">
                                      <input type="hidden" class="buyProducts" value="${bvo.buyProducts}">
                                      <img width="64" height="64"
                                      src="${bvo.buyImage}"
                                      alt="${bvo.buyProducts}"
                                      class="sc-1uwk3m0-1 dVdLWu">
                                    </a>
                                  </div>
                                  <div class="sc-9cwg9-5 hVsoGf">
                                    <div class="sc-9cwg9-6 iFVNgE">
                                      <a class="gnmni8-10 kyxMcC"></a>
                                      <a href="../main/detail?productNo=${bvo.productNo}"
                                        target="_blank"
                                        class="gnmni8-10 sc-8q24ha-0 kyxMcC eMdHXr">
                                        <span color="#111111" class="sc-755zt3-1 sc-8q24ha-1 kGEiDX gJmRLG">
                                          ${bvo.buyProducts}
                                        </span>
                                      </a>
                                      <a class="gnmni8-10 sc-8q24ha-4 kyxMcC iRqUKH">
                                        <div class="sc-8q24ha-2 dlrSjd">
                                          <div class="uaa4l4-0 gkJajJ">
                                            <span font-weight="normal" class="sc-755zt3-0 fvbOfJ">${bvo.buyTotalPrice}원</span>
                                            <span class="sc-13xhsmd-0 hRyoWJ">
                                              <span class="sc-13xhsmd-1 PPRnE"></span>
                                            </span>
                                            <span class="sc-755zt3-0 ctbual">
                                              ${bvo.buyAmount}개
                                            </span>
                                          </div>
                                        </div>
                                        <div class="sc-8q24ha-3 fiBbXs"> 
                                          <input type="hidden" class="data_buyNo" value="${bvo.buyNo}">
                                          <input id="buyImage" value="${bvo.buyImage}" type="hidden">
                                          <input id="buyProducts" value="${bvo.buyProducts}" type="hidden">
                                          <input id="buyTotalPrice" value="${bvo.buyTotalPrice}" type="hidden">
                                          <input id="buyAmount" value="${bvo.buyAmount}" type="hidden">
                                          <input id="productNo" value="${bvo.productNo}" type="hidden">
                                          <button class="btn_cart sc-1k9quwu-0 iGA-dmg xuyxga-0 jYesDa" id="btn_cart">장바구니 담기</button>                                        
                                          <c:if test="${bvo.buyDelCheck eq '배송중'}">
                                            <button class="btn_review sc-1k9quwu-0 iGA-dmg xuyxga-0 jYesDa" id="btn_review">리뷰 작성하기</button>
                                          </c:if>
                                          <div class="xuyxga-1 kDLduL"></div>
                                        </div>
                                        </a>
                                      <div class="fxyxvg-0 jcJyte"></div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                </c:if>
                <c:set var="Date" value="${bvo2.buyDate}" />
              </c:forEach>
              </div>
            </div>
            </c:if>
            </c:forEach>
            
          <c:if test="${empty bvo}">
            <div style="display: flex;">
              <svg>
                <polygon fill-opacity="0" fill="#FFFFFF" points="0 24 24 24 24 0 0 0"></polygon>
                <path d="M12,2 L24,22 L0,22 L12,2 Z M11.9962851,17.6000004 C11.6396269,17.6000004 11.3510849,17.7140976 11.1306504,17.9422955 C10.9102158,18.1704933 10.8000002,18.4577036 10.8000002,18.8039349 C10.8000002,19.1501661 10.9102158,19.4360649 11.1306504,19.6616398 C11.3510849,19.8872147 11.6396269,20.0000005 11.9962851,20.0000005 C12.3578968,20.0000005 12.6489156,19.8872147 12.8693501,19.6616398 C13.0897847,19.4360649 13.2000003,19.1501661 13.2000003,18.8039349 C13.2000003,18.4524577 13.0897847,18.163936 12.8693501,17.9383611 C12.6489156,17.7127861 12.3578968,17.6000004 11.9962851,17.6000004 Z M13.3999996,7.5 L10.6000004,7.5 L11,16 L13,16 L13.3999996,7.5 Z" fill="#FF9600" fill-rule="nonzero">
                </path>
              </svg>
              
              <div height="24">
                <span>
                &nbsp;주문하신 내역이 없습니다.
                </span>
              </div>
            </div>
          </c:if>
        </div>
      </div>
    </div>
  </div>
</body>
</html>