package price.fuel.pf2.service;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import price.fuel.pf2.dao.FactorDao;
import price.fuel.pf2.dao.PredictDao;



@Service("factorService")
public class FactorService {
	
	private static final Logger logger = LoggerFactory.getLogger(FactorService.class);
	
	@Autowired
	private FactorDao factorDao;

	/**
	 * select (조회함수)
	 * input  : Map (as_of_date:기준일, pred_date:예측일)
	 * output : ServiceMap (list, page)  
	 */
	public List<HashMap> select(HashMap entity) {
		
		
		// 1. 조회조건 정리
		String asOfDate = (String) entity.get("asOfDate");
		String predDate = (String) entity.get("predDate");
		String category = (String) entity.get("category");
		String categoryNo = "290";
		
		if("gci".equals(category)) categoryNo = "290";
		else if("ici1".equals(category)) categoryNo = "147";
		else if("ici2".equals(category)) categoryNo = "148";
		else if("ici3".equals(category)) categoryNo = "149";
		else if("ici4".equals(category)) categoryNo = "150";
			
		logger.info(asOfDate);
		logger.info(predDate);
		logger.info(category);
		
		// 2. 예측값 조회 실행
		HashMap fMap = new HashMap();
		fMap.put("asOfDate", asOfDate);
		fMap.put("predDate", predDate);
		fMap.put("categoryNo", categoryNo);
		
		logger.info("pMap", fMap);
		
		List<HashMap> fList1 = factorDao.selectList(fMap);
			
		logger.info("fList1 size:" + fList1.size());

		return fList1;
			
	}
	
	public HashMap  detail(HashMap entity){
		return null;
	}





}
