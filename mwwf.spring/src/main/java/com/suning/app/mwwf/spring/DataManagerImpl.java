package com.suning.app.mwwf.spring;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.suning.app.mwwf.core.AbstractDataManager;
import com.suning.app.mwwf.helper.StreamHelper;

/**
 * 数据管理中心,有以下功能
 * 
 * <pre>
 * 1.存放业务模型
 * 2.存储，获取工作流数据
 * </pre>
 * 
 * @author 14061798 2015年1月3日 上午10:33:48
 */
public class DataManagerImpl extends AbstractDataManager {

	@Autowired
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
