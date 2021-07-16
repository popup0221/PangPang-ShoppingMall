package mall.spring.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mall.spring.project.domain.AddressVO;
import mall.spring.project.domain.BuyVO;
import mall.spring.project.domain.CartVO;
import mall.spring.project.domain.CustomerVO;
import mall.spring.project.domain.ProductVO;
import mall.spring.project.service.BuyService;
import mall.spring.project.service.CartService;
import mall.spring.project.service.CoupangService;
import mall.spring.project.service.LoginService;

@Controller
@RequestMapping(value = "/main")
public class BuyController {
   private static final Logger logger = 
         LoggerFactory.getLogger(BuyController.class);

   @Autowired
   private BuyService buyService;
   @Autowired
   private LoginService loginService;
   @Autowired
   private CartService cartService;
   @Autowired
   private CoupangService coupangService;


   @GetMapping("/buy") 
   public void buyGET(Model model, HttpServletRequest request, @RequestParam(required=false) String[] ArrayNo, String ProductNo, String Amount){

      logger.info("buyGET() 호출");
      
      // 로그인 유저 배송지 정보(사용자 정보 model 저장) 
      HttpSession session = request.getSession(); 
      String customerId = (String)session.getAttribute("customerId");
      CustomerVO Cvo = loginService.read(customerId);
      
      model.addAttribute("Cvo", Cvo);

      try {
         
         logger.info("ArrayNo = " + ArrayNo);
         logger.info("ProductNo = " + ProductNo);
         logger.info("Amount = " + Amount);
         
         if(ArrayNo != null) {
            List<CartVO> buyList = new ArrayList<CartVO>();
            for (int i = 0; i < ArrayNo.length; i++) {
               logger.info(ArrayNo[i] + "");
               buyList.add(cartService.read_cart_No(ArrayNo[i]));
            }
            logger.info(buyList+"");
            model.addAttribute("buyList", buyList);
         }
         
         if(ProductNo != null && Amount  != null) {
            ProductVO buyvo = coupangService.read_No(Integer.parseInt(ProductNo));
            logger.info(buyvo.toString());
            model.addAttribute("buyvo", buyvo);
            model.addAttribute("Amount", Amount);
            
         }
      } catch (NumberFormatException e) {
         e.printStackTrace();
      }
   }
   
   @PostMapping("/buy")
      public ResponseEntity<String> buyPOST(HttpServletResponse response, @RequestBody BuyVO param) throws IOException {
         logger.info("buyPOST() 실행");
         
         String buyId = "";     
         for(BuyVO vo : param.getBuyList()) {
            logger.info("vo : " + vo);
            buyId = vo.getBuyId();
            buyService.create_buy(vo);
         }
         logger.info("buyId : " + buyId);
         return new ResponseEntity<String>(buyId, HttpStatus.OK);

      }
   
   @GetMapping("/chooseAddr")
   public void chooseAddrGET(HttpServletRequest request, Model model) {
      logger.info("chooseAddrGET() 실행");
      HttpSession session = request.getSession();
      String customerId = (String)session.getAttribute("customerId");
      List<AddressVO> list = loginService.read_List(customerId);
      model.addAttribute("list", list);
      String uri = request.getRequestURI();
      String[] uriSplit = uri.split("/", 3);
      logger.info("uriSplit : " + uriSplit);
      String u = uriSplit[1] + "/" + uriSplit[2];
      logger.info(uriSplit[1] + "/" + uriSplit[2]);
      session.setAttribute("uri", u);
   }
   
   @GetMapping("/buyComplete")
   public void buyCompleteGET(String buyId, Model model) {
      logger.info("buyCompleteGET() 실행");
      
      List<BuyVO> buyList = buyService.read_buy(buyId);
      
      
      
      String buyDate = buyList.get(0).getBuyDate();
      int addressNo = buyList.get(0).getAddressNo();
      
      int buyTotalPrice = 0;
      
      for (int i = 0; i < buyList.size(); i++) {
         buyTotalPrice += buyList.get(i).getBuyTotalPrice();
      }
      
      AddressVO avo = buyService.read_address(addressNo);
      
      model.addAttribute("buyDate", buyDate);
      model.addAttribute("buyTotalPrice", buyTotalPrice);
      model.addAttribute("avo", avo);
      
   }
}