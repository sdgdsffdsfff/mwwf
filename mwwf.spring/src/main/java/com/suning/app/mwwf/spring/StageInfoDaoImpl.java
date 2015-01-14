package com.suning.app.mwwf.spring;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.suning.app.mwwf.dao.StageInfoDao;
import com.suning.app.mwwf.entity.StageInfoEntity;

@Repository
public class StageInfoDaoImpl implements StageInfoDao {

	
	@Autowired
	private SqlMapClient sqlMapClient;
	
	@Override
    public Integer updateStageInfo(StageInfoEntity stageInfoEntity) {
		//StageInfoDao stageInfoDao = jdbcTemplate.getMapper(StageInfoDao.class);
		Integer affectRows = 0;
		try {
			affectRows = (Integer) sqlMapClient.insert("updateStageInfo", stageInfoEntity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return affectRows;
    }

	@Override
    public Integer insertStageInfo(StageInfoEntity stageInfoEntity) {
		//StageInfoDao stageInfoDao = jdbcTemplate.getMapper(StageInfoDao.class);
		Integer key = 0;
		try {
			key = (Integer) sqlMapClient.insert("insertStageInfo", stageInfoEntity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return key;
    }
	
	@Override
	public StageInfoEntity selectStageInfo(String flowInsId) {
		//StageInfoDao stageInfoDao = sqlSession.getMapper(StageInfoDao.class);
		StageInfoEntity stageInfo = null;
		try {
			stageInfo = (StageInfoEntity) sqlMapClient.queryForObject("selectStageInfo",flowInsId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stageInfo;
	}
}
