package price.fuel.pf2.dao;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class PredictDao{
	
	private static final Logger logger = LoggerFactory.getLogger(MonitorDao.class);
    
    @Autowired
    private SqlSession sqlSession; // SqlSessionTemplate DI
    
    public List<HashMap> selectList(HashMap param) {
    	
    	logger.info("param" + param);
    	return sqlSession.selectList("PredictDao.selectList", param);
	}
    
    public HashMap selectOne(HashMap param) {
    	return sqlSession.selectOne("PredictDao.selectOne", param);
    }
    

}
