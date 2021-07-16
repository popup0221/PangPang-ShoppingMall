package mall.spring.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mall.spring.project.domain.BuyVO;
import mall.spring.project.domain.ProductVO;
import mall.spring.project.pageutil.PageCriteria;
import mall.spring.project.pageutil.PageMaker;
import mall.spring.project.service.BuyService;
import mall.spring.project.service.CoupangService;

@Controller
@RequestMapping(value = "/main")
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private CoupangService coupangService;
	
	@Autowired
	private BuyService buyService;

	@GetMapping("/Coupang")
	public void CoupangGET() {
		logger.info("CoupangGET() 실행");
	}


	@GetMapping("/list")
	public void ListGET(String productName, String categoryCode, Model model, Integer page, Integer perPage, String sort, String pagesize) {
		logger.info("ListGET() 실행");
		logger.info("키워드 : " + productName);
		logger.info("카테고리 : " + categoryCode);
		
		if(categoryCode == null) {
			categoryCode = "all";
		}
		
		logger.info("page = " + page + ", perPage = " + perPage);

		if (sort == null) {
			sort = "review_rating";
		}
		if (pagesize == null) {
			pagesize = "8";
		}

		perPage = Integer.parseInt(pagesize);

		logger.info(sort);
		logger.info(pagesize);

		// Paging 처리
		PageCriteria criteria = new PageCriteria();

		if (page != null) {
			criteria.setPage(page);
		}
		if (perPage != null) {
			criteria.setNumsPerPage(perPage);
		}

		logger.info("productName: " + productName + ", categoryCode: " + categoryCode);

		List<ProductVO> list = coupangService.read(productName, categoryCode, criteria, sort);
		model.addAttribute("list", list);
		logger.info("vo 목록 : " + list);

		int count = coupangService.read_count(productName, categoryCode);
		model.addAttribute("count", count);
		logger.info("count: " + count);

		// PageMaker 객체 생성후 페이징 데이터 생성
		PageMaker maker = new PageMaker();
		maker.setCriteria(criteria);
		maker.setTotalCount(count);
		maker.setPageData();
		model.addAttribute("pageMaker", maker);

		model.addAttribute("productName", productName);
		model.addAttribute("categoryCode", categoryCode);

		model.addAttribute("sort", sort);
		model.addAttribute("pagesize", pagesize);

		logger.info("==========================================================================");
	}

	@GetMapping("detail")
	public void detailGET(Model model, int productNo, String categoryCode) {
		logger.info("detailGet() 호출");
		logger.info("productNo : " + productNo);
		logger.info("categoryCode : " + categoryCode);

		ProductVO vo = coupangService.read_No(productNo);
		logger.info(vo.toString());

		model.addAttribute("vo", vo);
	}

	@GetMapping("/myPage")
	public void myPageGET(HttpServletRequest request, Model model) {
		logger.info("myPageGET() 실행");
		HttpSession session = request.getSession();
		String customerId = (String) session.getAttribute("customerId");
		List<BuyVO> bvo = buyService.read_order(customerId);
		logger.info("사용자의 buyVO : " + bvo);
		model.addAttribute("bvo", bvo);
	}
	
	@PostMapping("/getCategory")
	@ResponseBody
	public String getCategory(int productNo) throws Exception {
		logger.info("productNo : " + productNo);
		ProductVO vo = coupangService.read_No(productNo);
		String category = vo.getCategoryCode();
		logger.info("category : " + category);
		return category;
	}
}
