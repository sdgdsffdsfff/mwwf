package com.suning.app.mwwf.spring;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.suning.app.mwwf.dao.StageInfoDao;
import com.suning.app.mwwf.entity.StageInfoEntity;

@Repository
public class StageInfoDaoImpl implements StageInfoDao {

	@Resource(name = "sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	@Override
    public Integer updateStageInfo(StageInfoEntity stageInfoEntity) {
		//StageInfoDao stageInfoDao = jdbcTemplate.getMapper(StageInfoDao.class);
	    return 1;
    }

	@Override
    public Integer insertStageInfo(StageInfoEntity stageInfoEntity) {
		//StageInfoDao stageInfoDao = jdbcTemplate.getMapper(StageInfoDao.class);
	    return 1;
    }
	
	@Override
	public StageInfoEntity selectStageInfo(String flowInsId) {
		//StageInfoDao stageInfoDao = sqlSession.getMapper(StageInfoDao.class);
		StageInfoEntity stageInfo = null;
		try {
			stageInfo = (StageInfoEntity) sqlMapClient.queryForObject("stageInfo.selectStageInfo",flowInsId,StageInfoEntity.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stageInfo;
	}
}
