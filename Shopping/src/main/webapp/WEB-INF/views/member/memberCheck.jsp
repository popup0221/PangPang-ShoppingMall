<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
/* table, th, td { 
  border-style: solid;
  border-width: 1px;
  text-align: center;
}

ul {
  list-style-type: none;
}

li {
  display: inline-block;
} */
.find-id-table, .find-password-table, .find-password-reset-table, .usermodify-table, .pincode-input__table {
    width: 500px;
    border-top: 2px solid #969696;
    border-collapse: collapse;
}
table {
    border-collapse: collapse;
    border-spacing: 0;
    display: table;
    border-collapse: separate;
    box-sizing: border-box;
    text-indent: initial;
    border-spacing: 2px;
    border-color: grey;
    margin-bottom: 7px;
}
tbody {
    display: table-row-group;
    vertical-align: middle;
    border-color: inherit;
}
.find-id-table th, .find-password-table th, .find-password-reset-table th, .usermodify-table th, .pincode-input__table th {
    background-color: #eef1f8;
    text-align: left;
    white-space: nowrap;
}
.find-id-table th, .find-password-table th, .find-password-reset-table th, .usermodify-table th, .pincode-input__table th, .find-id-table td, .find-password-table td, .find-password-reset-table td, .usermodify-table td, .pincode-input__table td {
    padding: 14px 25px;
    border-bottom: 1px solid #ddd;
}
tr {
    display: table-row;
    vertical-align: inherit;
    border-color: inherit;
}
.usermodify-auth-password-tf {
    width: 200px;
}
.login-tf, .find-id-name-tf, .find-id-phone-tf, .find-id-auth-tf, .find-password-name-tf, .find-password-email-tf, .find-password-temp-password-tf, .find-password-new-password-tf, .find-password-new-password-confirm-tf, .find-password-birthday-confirm-tf, .find-password-phonenumber-confirm-tf, .secession-validate-user input[type="text"], .secession-validate-user input[type="password"], .usermodify-auth-password-tf, .usermodify-email-tf, .usermodify-email-change-tf, .usermodify-phone-tf, .usermodify-phone-auth-tf, .usermodify-password-currentpassword-tf, .usermodify-password-newpassword-tf, .usermodify-password-newpassword-confirm-tf {
    height: 22px;
    padding: 2px 5px;
    line-height: 22px;
}
.login-tf, .find-id-name-tf, .find-id-phone-tf, .find-id-auth-tf, .find-password-name-tf, .find-password-email-tf, .find-password-temp-password-tf, .find-password-new-password-tf, .find-password-new-password-confirm-tf, .find-password-birthday-confirm-tf, .find-password-phonenumber-confirm-tf, .secession-validate-user input[type="text"], .secession-validate-user input[type="password"], .usermodify-auth-password-tf, .usermodify-email-tf, .usermodify-email-change-tf, .usermodify-phone-tf, .usermodify-phone-auth-tf, .usermodify-password-currentpassword-tf, .usermodify-password-newpassword-tf, .usermodify-password-newpassword-confirm-tf, .find-id-phone-select, .usermodify-phone-select {
    border: 1px solid #ccc;
    vertical-align: middle;
}
</style>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>회원정보확인</title>
</head>
<body>
   <h1>
      <a href="../main/Coupang"><img src="../resources/image/main.png"></a>
   </h1>
   
   <h2 style="margin-bottom: 3px;">회원정보확인</h2>
   <p style="color: blue; display : inline-block;">
   ${customerId}
   </p>
   님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인합니다.
   <br>
   <form action="memberCheck" method="post" style="display: inline-block; float: left;">
   <table class="usermodify-table">
      <tbody>
         <tr>
            <th>아이디(이메일)</th>
            <td>
            <p style="margin: 0px;"> ${customerId} </p> 
            </td>
         </tr>
         <tr>
            <th>비밀번호</th>
            <td>
            <input type="password" name="customerPw" required class="usermodify-auth-password-tf member__input-border">
            <c:if test="${param.Fail eq 'Fail'}">
               <p style="font-size: 10px; color: red">
               현재 비밀번호가 일치하지 않습니다.
               </p>
               </c:if>
            </td>
         </tr>
      </tbody>
   </table>

   <input type="submit" value="확인">

   <a href="../main/myPage">
      <input type="button" value="취소">
   </a>
   </form>
</body>
</html>