package com.suning.app.mwwf.spring;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.mybatis.spring.SqlSessionTemplate;

import com.suning.app.mwwf.core.AbstractDataManager;
import com.suning.app.mwwf.helper.StreamHelper;

public class DataManagerImpl extends AbstractDataManager {

	private SqlSessionTemplate sqlSession;

	public void init() {
		creatStageInfoTable();
	}
	
	@Override
	public void creatStageInfoTable() {
		System.out.println("创建数据库");
		Connection conn = null;
		try {
			sqlSession.getConnection();
			ScriptRunner runner = new ScriptRunner(conn);
			runner.runScript(new InputStreamReader(StreamHelper
					.getStreamFromClasspath(CTEATE_STAGE_TABLE_SQL)));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
}
