package com.couchbase.connect.kafka.deleteOldDocumentID;

import java.time.Instant;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.transforms.Transformation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Remove Document ID Transform.
 *
 *transforms=removeDocumentID
 *
 * transforms.removeDocumentID.type=com.couchbase.connect.kafka.deleteOldDocumentID.RemoveDocumentIDTransform
 */
public class RemoveDocumentIDTransform<R extends ConnectRecord<R>> implements Transformation<R>, Runnable  {
	
	//private static final String OP_CONFIG = "op";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RemoveDocumentIDTransform.class);

	
	private static final ConfigDef CONFIG_DEF = new ConfigDef()
		      .define(null,
		          ConfigDef.Type.BOOLEAN,
		          true,
		          null,
		          ConfigDef.Importance.HIGH,
		          "The Custom Remove is used to remove current Document ID and insert new Document ID ");

	  
	@Override
	public void configure(Map<String, ?> arg0) {
		// TODO Auto-generated method stub
		//apply(record);
	}

	@Override
	public R apply(R record) {
		
		System.out.println("=====Inside CustomRemoveDocumentID::: apply()=====Start==1");
		
		R newRecord = null;
		if (record.value() == null) {
		      return record;
		    }
	
		 Instant instant = Instant.now();
		Long timeStampMillis = instant.toEpochMilli();
		
		LOGGER.info("===Logger==Inside CustomRemoveDocumentID::: apply()===record.value()==="+record.value());
		System.out.println("=====Inside CustomRemoveDocumentID::: apply()===record.value()==="+record.value());
		
		newRecord = record.newRecord(record.topic(), record.kafkaPartition(), null, timeStampMillis, null, record.value(), record.timestamp());
		LOGGER.info("===Logger==Inside CustomRemoveDocumentID::: apply()=====end==");
		
		return newRecord;
	}

	

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ConfigDef config() {
		return CONFIG_DEF;
	}

	@Override
	public void run() {
		R record = null;
		apply(record);
	}

}
