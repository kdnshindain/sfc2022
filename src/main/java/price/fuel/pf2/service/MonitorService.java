package price.fuel.pf2.service;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import price.fuel.pf2.dao.MonitorDao;



@Service("monitorService")
public class MonitorService  {
	
	private static final Logger logger = LoggerFactory.getLogger(MonitorService.class);
	
	@Autowired
	private MonitorDao monitorDao;

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
		
		// 2. 관측값 조회 실행
		HashMap mMap = new HashMap();
		mMap.put("asOfDate", asOfDate);
		mMap.put("fromDate", fromDate);
		
		logger.info("mMap", mMap);
		
		List<HashMap> mList1 = monitorDao.selectList(mMap);
		List<HashMap> mList2 = new ArrayList<HashMap>();
		
		// 3. category 선별 & NaN 제거
		for(int i=0 ; i<mList1.size() ; i++){
			Map map1 = mList1.get(i);
			Map map2 = new HashMap<String, Object>();
			
			if (map1.get(category)==null || Double.isNaN((double) map1.get(category))  || "NaN".equals(map1.get(category)) ) continue;

			map2.put(category, map1.get(category));
			map2.put("as_of_date", map1.get("as_of_date"));
			map2.put("pred_date", map1.get("pred_date"));
			map2.put("pred_step", map1.get("pred_step"));
			
			mList2.add((HashMap) map2);
		}

		logger.info("mList1 size:"+ mList1.size());
		logger.info("mList2 size:"+ mList2.size());

		return mList2;
			
	}
	
	public HashMap  detail(HashMap entity){
		return null;
	}





}
