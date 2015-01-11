package com.suning.app.mwwf.spring;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suning.app.mwwf.dao.StageInfoDao;
import com.suning.app.mwwf.entity.StageInfoEntity;

@Repository
public class StageInfoDaoImpl implements StageInfoDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
    public Integer updateStageInfo(StageInfoEntity stageInfoEntity) {
		StageInfoDao stageInfoDao = sqlSession.getMapper(StageInfoDao.class);
	    return stageInfoDao.updateStageInfo(stageInfoEntity);
    }

	@Override
    public Integer insertStageInfo(StageInfoEntity stageInfoEntity) {
		StageInfoDao stageInfoDao = sqlSession.getMapper(StageInfoDao.class);
	    return stageInfoDao.insertStageInfo(stageInfoEntity);
    }
	
	@Override
	public StageInfoEntity selectStageInfo(String flowInsId) {
		StageInfoDao stageInfoDao = sqlSession.getMapper(StageInfoDao.class);
		return stageInfoDao.selectStageInfo(flowInsId);
	}
}
