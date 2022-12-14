package price.fuel.pf2.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import price.fuel.pf2.service.FactorService;
import price.fuel.pf2.service.MonitorService;
import price.fuel.pf2.service.PredictService;





@Controller
public class FactorController {
	
	private static final Logger logger = LoggerFactory.getLogger(FactorController.class);
	

	@Autowired
	private FactorService factorService;
	
	@RequestMapping(value={"factor"}, method=RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		
		String asOfDate = (String)request.getParameter("asOfDate");
		String predDate = (String)request.getParameter("predDate");
		String category = (String)request.getParameter("category");
		
		logger.info("asOfDate"+ asOfDate);
		logger.info("predDate"+ predDate);
		logger.info("category"+ category);
		
		if (asOfDate == null) asOfDate = "20210104";
		if (category == null) category = "gci"; // gci, ici1, ici3, ici4
		
		HashMap entity = new HashMap(); 
		entity.put("asOfDate", asOfDate);
		entity.put("predDate", predDate);
		entity.put("category", category);
		
		// 관측값  & 예측값 조회 
		List<HashMap> fList =  factorService.select(entity);
		
		model.addAttribute("fList", fList);
		model.addAttribute("category", category);
		model.addAttribute("asOfDate", asOfDate);
		model.addAttribute("predDate", predDate);
		
		return "factor";
	}
	

}