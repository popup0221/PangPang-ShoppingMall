  $(document).ready(function () {
  	  $('#btn_review').click(function(){
  	  	var buyNo = $(this).prevAll('.data_buyNo').val();
  	  	
  	  	console.log(buyNo);
  	  	
  	  	$.ajax({
  	  		type : "get",
  	  		url : "review/" + buyNo,
  	  		headers : {
				'X-HTTP-Method-Override' : 'GET'
			}
  	  	});// end ajax
  	  
  	  }); // end btn_review click
  
      $('.btn_cart').click(function(){
        console.log(this);
        var buyImage = $(this).prevAll('#buyImage').val();
        var buyProducts = $(this).prevAll('#buyProducts').val();
        var buyTotalPrice = parseInt($(this).prevAll('#buyTotalPrice').val());
        var buyAmount = parseInt($(this).prevAll('#buyAmount').val());
        var price = buyTotalPrice/buyAmount;
        var productNo = $(this).prevAll('#productNo').val();
        
        $.ajax({
          type : "post",
          url : "getCategory",
          data : {
            productNo : productNo
          },
          success : function (category) {
            console.log(category);
            cart(buyImage,buyProducts,buyTotalPrice,buyAmount,price,productNo,category);
          }
        });
          }); // end btn_cart
          
          function cart(buyImage,buyProducts,buyTotalPrice,buyAmount,price,productNo,category) {
               console.log(productNo);
               console.log(category);
               
               var obj = {
                     'cartImage' : buyImage,
                     'cartName' : buyProducts,
                     'cartPrice' : price,
                     'cartAmount' : buyAmount,
                     'cartTotalprice' : buyTotalPrice,
                     'productNo' : productNo,
                     'category' : category
               }
               
               console.log(obj);
               var JSONObj = JSON.stringify(obj);

               $.ajax({
                  type : 'post',
                  url : 'cart?judge=cart',            
                    headers : {
                       'Content-Type' : 'application/json',
                       'X-HTTP-Method-Override' : 'POST'
                    },
                  data : JSONObj,
                  success : function(result) {
                     if(result == 1){
                        alert('장바구니 등록 성공!'); 
                     } else {                        
                        alert('장바구니 등록 실패!'); 
                     }
                  }
               }); // end ajax
      } // end cart
      
      // "각각의 id를 구분"하여 태그 안의 value를 비교한 뒤 delCheck라는 id의 innerHTML을 변경해줘야함  
      var now = new Date();
      var year = now.getFullYear();
      var month = now.getMonth()+1;
      var day = now.getDate();
      console.log(now);
      console.log(year+"."+month+"."+day);
      var cnt = $('input[name=delDate]').length;
      console.log(cnt);
      
      for (var o = 0; o < cnt; o++) {
        var DelDate = $('input[name=delDate]').eq(o).val();
            console.log(o + "-" + DelDate);
        var dateSplit = DelDate.split('.');
        for ( var i in dateSplit ) {
              console.log(i + "-" + dateSplit[i]);
          }
        if(year>=dateSplit[0]){
          console.log(year+","+dateSplit[0]);
          console.log(month+","+dateSplit[1]);
          console.log(day+","+dateSplit[2]);
          if(month>=dateSplit[1]){
            if(day>=dateSplit[2]){
              $('span[id=delCheck]').eq(o).html("배송완료");
            }
          }
        }
      }
//      console.log("tlqkf " +  DelDate);
//      for(var d in DelDate){
//            console.log(d + "-" + DelDate[d]);
//      }
    }); // end document