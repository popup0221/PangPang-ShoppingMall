$(document).ready(function() {

	// 배송 날짜
	var date = new Date();
	var month = date.getMonth() + 1;
	var day = date.getDate() + 1;
	var week = new Array('일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일');
	var deldate = week[date.getDay() + 1] + "(내일) " + month + "월 " + day + "일";
	console.log(deldate);
	$('.deldate').html(deldate + '까지 도착');


	// radio 박스 변경에 따른 url값
	var categoryCode_val = $('.categoryCode_val').val();
	var productName_val = $('.productName_val').val();
	var page_val = $('.page_val').val();

	// 정렬 
	$("input[name='sort']").change(function() {
		// 정렬 라디오버튼 값 변경
		$("input:radio[id='" + $(this).attr('id') + "']").prop("checked", true);
		location.href = "list?categoryCode=" + categoryCode_val + "&productName=" + productName_val + "&page=" +  page_val + "&sort=" + $('input[name="sort"]:checked').val() + "&pagesize=" + $('input[name="pagesize"]:checked').val();
	}); // end sort

	// 페이징(페이지당 목록 개수)
	$("input[name='pagesize']").change(function() {
		$("input:radio[id='" + $(this).attr('id') + "']").prop("checked", true);
		location.href = "list?categoryCode=" + categoryCode_val + "&productName=" + productName_val + "&page=" +  page_val + "&sort=" + $('input[name="sort"]:checked').val() + "&pagesize=" + $('input[name="pagesize"]:checked').val();
	}); // end pagesize
	
}); // end document