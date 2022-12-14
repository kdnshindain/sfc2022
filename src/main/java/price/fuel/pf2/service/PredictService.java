package price.fuel.pf2.service;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import price.fuel.pf2.dao.PredictDao;



@Service("predictService")
public class PredictService {
	
	private static final Logger logger = LoggerFactory.getLogger(PredictService.class);
	
	@Autowired
	private PredictDao predictDao;

	/**
	 * select (조회함수)
	 * input  : Map (as_of_date:기준일, pred_date:예측일)
	 * output : ServiceMap (list, page)  
	 */
	public List<HashMap> select(HashMap entity) {
		
		
		// 1. 조회조건 정리
		String asOfDate = (String) entity.get("asOfDate");
		String fromDate = (String) entity.get("fromDate");
		String category = (String) entity.get("category");
		logger.info(asOfDate);
		logger.info(fromDate);
		logger.info(category);
		
		// 2. 예측값 조회 실행
		HashMap pMap = new HashMap();
		pMap.put("asOfDate", asOfDate);
		pMap.put("fromDate", fromDate);
		
		logger.info("pMap", pMap);
		
		List<HashMap> pList1 = predictDao.selectList(pMap);
		List<HashMap> pList2 = new ArrayList<HashMap>();
		
		// 3. category 선별 & NaN 제거
		for(int i=0 ; i<pList1.size() ; i++){
			Map map1 = pList1.get(i);
			Map map2 = new HashMap<String, Object>();
			
			if (map1.get(category)==null || Double.isNaN((double) map1.get(category))  || "NaN".equals(map1.get(category)) ) continue;

			map2.put(category, map1.get(category));
			map2.put("as_of_date", map1.get("as_of_date"));
			map2.put("pred_date", map1.get("pred_date"));
			map2.put("pred_step", map1.get("pred_step"));
			
			pList2.add((HashMap) map2);
		}
		
		logger.info("pList1 size:" + pList1.size());
		logger.info("pList2 size:" + pList2.size());
		

		return pList2;
			
	}
	
	public HashMap  detail(HashMap entity){
		return null;
	}





}
