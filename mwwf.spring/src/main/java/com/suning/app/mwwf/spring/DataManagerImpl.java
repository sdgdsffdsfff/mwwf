package com.suning.app.mwwf.spring;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.suning.app.mwwf.core.AbstractDataManager;
import com.suning.app.mwwf.helper.StreamHelper;

public class DataManagerImpl extends AbstractDataManager {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void creatStageInfoTable() {
		System.out.println("创建数据库");
		try (Connection conn = sqlSession.getConnection()) {
			ScriptRunner runner = new ScriptRunner(conn);
			runner.runScript(new InputStreamReader(StreamHelper.getStreamFromClasspath(CTEATE_STAGE_TABLE_SQL)));
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
