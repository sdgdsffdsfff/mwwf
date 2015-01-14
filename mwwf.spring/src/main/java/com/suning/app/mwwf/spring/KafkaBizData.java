package com.suning.app.mwwf.spring;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.suning.app.mwwf.core.BizDataAbstract;

@Repository
public class KafkaBizData extends BizDataAbstract<KafkaFlowStageEntity>{
	
	@Autowired
	SqlMapClient sqlMapClient;
	
	/* (non-Javadoc)
	 * @see com.suning.app.mwwf.core.BizDataAbstract#get(java.lang.String)
	 */
	@Override
	public KafkaFlowStageEntity get(String id) {
		
		KafkaFlowStageEntity kafkaFlowStageEntity = null;
		try {
			kafkaFlowStageEntity = (KafkaFlowStageEntity) sqlMapClient.queryForObject("selectKafkaFlowStage",id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kafkaFlowStageEntity;
	}

	@Override
	public String getName() {
		return "kafka";
	}

	@Override
    public boolean set(KafkaFlowStageEntity bizEntity) {
		try {
	        Integer key = (Integer) sqlMapClient.insert("insertKafkaFlowStage", bizEntity);
	        if(key == null) {
	        	return false;
	        }
        } catch (SQLException e) {
	        e.printStackTrace();
        }
	    return true;
    }
}
