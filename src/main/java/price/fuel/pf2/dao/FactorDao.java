package price.fuel.pf2.dao;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import price.fuel.pf2.common.domain.*;


@Component
public class FactorDao{

	private static final Logger logger = LoggerFactory.getLogger(MonitorDao.class);
    
    @Autowired
    private SqlSession sqlSession; // SqlSessionTemplate DI
    
    public List<HashMap> selectList(HashMap param) {
    	
    	logger.info("param" + param);
    	return sqlSession.selectList("FactorDao.selectList", param);
	}
    
    public HashMap selectOne(HashMap param) {
    	return sqlSession.selectOne("FactorDao.selectOne", param);
    }
    

}
