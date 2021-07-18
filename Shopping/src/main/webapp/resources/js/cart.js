	$(document).ready(function(){
		// 날짜
		var date = new Date();
		var month = date.getMonth()+1;
		var day = date.getDate()+1;
		var week = new Array('(일)', '(월)', '(화)', '(수)', '(목)', '(금)', '(토)');
		var delweek = week[date.getDay() + 1];
		var deldate = month+"/"+day;

		$('.arrive-date-txt').html('내일'); // 내일
		$('.arrive-date-day').html(delweek); // (요일)
		$('.arrive-date-date').html(deldate); // 날짜
		
		// 페이지 새로고침 
		function refresh(){
			location.reload();
		}
		
		function priceSet() {
			// 리스트 총가격 설정
			var totalPrice = 0;
			var total = $('.unit-total-sale-price').text().split('원');
	    	for (var i = 0; i < total.length - 1; i++) {
	    		console.log(parseInt(total[i]));
	    		totalPrice += parseInt(total[i]);
			}
	    	console.log(totalPrice);
	
	    	$('.total-product-price').html(totalPrice);
	    	$('.total-order-price').html(totalPrice);
		}
		
		// 페이지 로드시 가격표시
		priceSet();

		// 전체선택 체크박스
		$('.all-deal-select').click(function(){
			if($('.all-deal-select').is(":checked")){ // 전체 선택시
				$('.dealSelectChk').prop('checked', true);
				priceSet();
			}else{ // 전체 헤제시
				$('.dealSelectChk').prop('checked', false);
				
				var totalPrice = 0;
				$('.total-product-price').html(totalPrice);
		    	$('.total-order-price').html(totalPrice);
			}
		}); // end all-deal-select 

		
		// 개별 체크박스
		$('.dealSelectChk').click(function(){
  			// 개별 체크박스 헤제시 전체선택 체크박스도 해제
			if($('dealSelectChk').is(":checked")){
				$('.all-deal-select').prop('checked', true);
			} else {
				$('.all-deal-select').prop('checked', false);
			}
			
			var check_length = $('input:checkbox[name=SelectChk]').length;
			console.log(check_length);
			if($('input[name="SelectChk"]:checked').length == check_length){
				$('.all-deal-select').prop('checked', true);
			} else {
				$('.all-deal-select').prop('checked', false);
			}
			
			var rowData = new Array();
			var checkbox = $('input:checkbox[name=SelectChk]:checked');
// 			var checkbox = $(this);

			var add = 0;
			
			checkbox.each(function(i) {
				// checkbox.parent() : checkbox의 부모는 <td>이다.
				// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
				var tr = checkbox.parent().parent().eq(i);
				var td = tr.children();
				
				// 체크된 row의 모든 값을 배열에 담는다.
				rowData.push(tr.text());
				
				var total = td.eq(3).find('.unit-total-sale-price').text().replace('원','');				
				console.log(total);
				
				add += parseInt(total);
			}); // checkbox.each(function(i)
					
			console.log(add);
			$('.total-product-price').html(add);
	    	$('.total-order-price').html(add);
		}); // end dealSelectChk
		
		// select 태그 변경 -> 물품 수량 변경시 총가격 변경하기
        $(".quantity-select").change(function() {
        	
        	var rowData = new Array();
//         	var select =$('select[name=quantity-select]');
        	var select =$(this);
        	
        	select.each(function(i) {	

				var tr = select.parent().parent().parent().parent().parent().parent().eq(i);
				var td = tr.children();
							
				// 체크된 row의 모든 값을 배열에 담는다.
				rowData.push(tr.text());

				var amount = td.eq(2).find("select").val();
				var price = td.eq(2).find('.unit-cost').text();
				var totalPrice = amount * price;

				console.log("amount : " + amount);
				console.log("price : " + price);
				console.log(totalPrice);			
		
				td.eq(2).find('.unit-price').html(totalPrice+"원");
				td.eq(3).find('.unit-total-sale-price').html(totalPrice+"원");
				
				var name = td.eq(2).find('.product-name-part').text().trim();
				console.log(name);
				
				var id = td.eq(2).find('.customerID').val();
				console.log(id);
				
				var obj = {
						'name' : name,
						'id' : id,
						'amount' : amount,
						'totalPrice' : totalPrice		
				}
				
				$.ajax({
					type : 'post',
					url : 'cart_update',
					data : obj
				}); // end ajax
				refresh();
         	}); // end select         	
       }); // end quantity-select
       
	   // x 클릭시(a태그) 발생하는 이벤트
       $('.delete-option').click(function(){
    	   
    	   	var rowData = new Array();
       	   	var delete_tag = $(this);
    	   
       	 	delete_tag.each(function(i) {	

			var tr = delete_tag.parent().parent().parent().parent().parent().eq(i);
			var td = tr.children();
			
			console.log("tr"); console.log(tr);
			console.log("td"); 	console.log(td);
						
			// 체크된 row의 모든 값을 배열에 담는다.
			rowData.push(tr.text());
			
			var customerId = td.eq(2).find('.customerID').val();
			console.log(customerId);
			
			var name = td.eq(2).find('.product-name-part').text().trim();
			console.log(name);

			var obj = {
					'customerId' : customerId,	
					'name' : name
			}
			
			$.ajax({
				type : 'post',
				url : 'cart_delete',
				data : obj
			}); // end ajax
			refresh();
       	 }); // end delete_tag    
       }); // end delete-option 
       
       // 전체삭제 버튼 클릭 이벤트
       $('.selected-delete').click(function(){			
			var customerId = $('.customerID').val();
			console.log(customerId);
			
			$.ajax({
				type : 'post',
				url : 'cart_delete_all',
				data : {
					'customerId' : customerId
				}
			}); // end ajax
			refresh();
      }); //  selected-delete
      
      // 구매 버튼 클릭 이벤트
      $('#btnPay').click(function(){
    	  
        // 체크된 버튼의 id 값을 가져옴
  		var rowData = new Array();
  		var ArrayNo = new Array();
  		var checkbox = $('input:checkbox[name=SelectChk]:checked');

    	var buyURL = "buy?";
  		
  		checkbox.each(function(i) {
  			// checkbox.parent() : checkbox의 부모는 <td>이다.
  			// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
  			var tr = checkbox.parent().parent().eq(i);
  			var td = tr.children();
  			
  			// 체크된 row의 모든 값을 배열에 담는다.
  			rowData.push(tr.text());
  			
			var No = td.eq(0).find('.cartNo').val();
			console.log(No);
			
			ArrayNo.push(No);
			buyURL += ('ArrayNo=' + ArrayNo[i] + '&');
  		}); // checkbox.each(function(i)
  		console.log(ArrayNo);
  		
  		if(ArrayNo[0] == null){
  			alert("1개 이상의 상품을 선택해주세요.");
  		} else {
  			$('#btnPay').prop('href', buyURL.slice(0,-1));
  		}
      }); // end btnPay(구매버튼 클릭) 
	}); // end of document
	
