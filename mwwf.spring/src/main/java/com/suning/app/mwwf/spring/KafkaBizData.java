package com.suning.app.mwwf.spring;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.suning.app.mwwf.core.BizDataAbstract;

@Repository
public class KafkaBizData extends BizDataAbstract<KafkaFlowEntity>{
	
	@Autowired
	SqlMapClient sqlMapClient;
	
	/* (non-Javadoc)
	 * @see com.suning.app.mwwf.core.BizDataAbstract#get(java.lang.String)
	 */
	@Override
	public KafkaFlowEntity get(String id) {
		
		KafkaFlowEntity kafkaFlowStageEntity = null;
		try {
			kafkaFlowStageEntity = (KafkaFlowEntity) sqlMapClient.queryForObject("selectKafkaFlowStage",id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kafkaFlowStageEntity;
	}

	@Override
	public String getName() {
		return "kafka";
	}

    public boolean insertKafkaFlowStage(KafkaFlowEntity bizEntity) {
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
    
    public boolean insertKafkaFlow(KafkaFlowEntity bizEntity) {
		try {
	        Integer key = (Integer) sqlMapClient.insert("insertKafkaFlow", bizEntity);
	        if(key == null) {
	        	return false;
	        }
        } catch (SQLException e) {
	        e.printStackTrace();
        }
	    return true;
    }
}
