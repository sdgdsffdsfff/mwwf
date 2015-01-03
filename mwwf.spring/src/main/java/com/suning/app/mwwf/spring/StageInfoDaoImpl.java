package com.suning.app.mwwf.spring;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suning.app.mwwf.dao.StageInfoDao;

@Repository
public class StageInfoDaoImpl implements StageInfoDao{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
    public int updateStageInfo() {
	    // TODO Auto-generated method stub
	    return 0;
    }

	@Override
    public int insertStageInfo() {
	    // TODO Auto-generated method stub
	    return 0;
    }
}
