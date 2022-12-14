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

import price.fuel.pf2.service.MonitorService;
import price.fuel.pf2.service.PredictService;





@Controller
public class PredictController {
	
	private static final Logger logger = LoggerFactory.getLogger(PredictController.class);
	

	@Autowired
	private MonitorService monitorService;
	@Autowired
	private PredictService predictService;
	
	@RequestMapping(value={"predict"}, method=RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		
		String asOfDate = (String)request.getParameter("asOfDate");
		String category = (String)request.getParameter("category");
		
		logger.info("asOfDate"+ asOfDate);
		logger.info("category"+ category);
		
		if (asOfDate == null) asOfDate = "20210104";
		if (category == null) category = "gci"; // gci, ici1, ici3, ici4
		
		// 시(asOfDate),종(fromDate = asOfDate+6M) 일자 계산
		String fromDate = "";
		try {
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
			Date date1 = transFormat.parse(asOfDate);	
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date1);
			calendar.add(Calendar.DATE , calendar.get(Calendar.DATE) - 150); // BEFORE 6M (150M)
			
			Date date2 = calendar.getTime();
			fromDate = transFormat.format(date2);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		HashMap entity = new HashMap(); 
		entity.put("asOfDate", asOfDate);
		entity.put("fromDate", fromDate);
		entity.put("category", category);
		
		// 관측값  & 예측값 조회 
		List<HashMap> mList =  monitorService.select(entity);
		List<HashMap> pList =  predictService.select(entity);
		
		model.addAttribute("mList", mList);
		model.addAttribute("pList", pList);
		model.addAttribute("category", category);
		model.addAttribute("asOfDate", asOfDate);
		
		return "predict";
	}
	

}