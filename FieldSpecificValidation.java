/*
 * IBM Confidential
 * OCO Source Materials
 * 5725-S87
 * © Copyright IBM Corp. 2018
 * The source code for this program is not published or otherwise
 * divested of its trade secrets, irrespective of what has been
 * deposited with the U.S. Copyright Office.
 */

/**
 * @author praful_kolhe
 *
 */

package com.ibm.silverpop.sms.smpp.processor.fit;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.util.StringUtils;

import com.ibm.silverpop.sms.smpp.db.model.SmppSmsMessage;
import com.ibm.silverpop.sms.smpp.db.model.SmsMessageStatus;

import fit.ColumnFixture;

public class FieldSpecificValidation extends ColumnFixture {

	Properties property;

	public String bootStrapServer;

	public String inputTopic;

	public String outputTopic;

	public String outputTopicDb;

	public String data;

	static String Current_date_time;

	String NewMessage;

	String UniqueKey;

	String receivedSms;

	static String messageReceived;

	static String uniqueIdReceived;

	static String FieldTobeValidated;

	private Properties readConfigProperty() {

		Properties property = null;
		try (FileReader reader = new FileReader(
				"C:\\\\Users\\\\Praful_kolhe\\\\workspace\\\\WcaAutomation\\\\ConfigWca")) {
			property = new Properties();
			property.load(reader);
		} catch (Exception e) {

		}

		return property;
	}

	public String FieldValidationOnOutTopic() throws Exception {

		String testing = System.getProperty("CONFIGURATION");
		String test2 = System.getenv().get("CONFIGURATION");
		
		Properties configProperty = readConfigProperty();
		String bootStrapServer = configProperty.getProperty("bootstrap_server_K8");

		String out_value = "";
		/**
		 * This will generate current date time stamp and would then be concatenated
		 * with a message body in order to generate a unique message every time.
		 */

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:SSS");
		LocalDateTime now = LocalDateTime.now(); //
		System.out.println(dtf.format(now));
		Current_date_time = dtf.format(now);

		NewMessage = "Test message for fit channel" + Current_date_time;
		UniqueKey = "v-clcbco_lbnkgi_inmjep_inmjep_a" + Current_date_time;

		data = data.replace("MessageString", NewMessage);
		data = data.replace("UniqueId", UniqueKey);
		KafkaConnection kafkaConn = new KafkaConnection(bootStrapServer);
		kafkaConn.runProducer(inputTopic, "myKey", data);
		
		Thread.sleep(5000);

		// Once Message is produced, consume the kafka message from the output
		// topic.
		// Map<String, Messages> consumedMap =
		// kafkaConn.runConsumer(outputTopic);
		List<SmppSmsMessage> listOfMessages = new ArrayList<SmppSmsMessage>();

		List<SmppSmsMessage> consumedMap = kafkaConn.runConsumerList(outputTopic);
		for (SmppSmsMessage receivedSms : consumedMap) {

			listOfMessages.add(receivedSms);
			messageReceived = receivedSms.getMessage().toString();
			uniqueIdReceived = receivedSms.getUniqueId().toString();

			if ((messageReceived.equalsIgnoreCase(NewMessage)) || uniqueIdReceived.equalsIgnoreCase(UniqueKey)) {

				if (receivedSms.get(FieldTobeValidated) == null || receivedSms.toString() == "") {
					out_value = "Validation Failure: One or More mandatory fields are null or Message Length "
							+ "exceeds Max Message Length allowed for the message.";
				} else if (!StringUtils.isEmpty(receivedSms.get(FieldTobeValidated))) {
					if (!StringUtils.isEmpty(out_value))
						out_value += ",";
					out_value = out_value + receivedSms.get(FieldTobeValidated).toString();
				}

			}

		}

		return out_value;

	}

	public String Databasestatus() throws Exception {

		String out_DbState = null;

		KafkaConnection kafkaConn = new KafkaConnection(bootStrapServer);
		kafkaConn.runProducer(inputTopic, "myKey", data);

		// Once Message is produced, consume the kafka message from the output
		// topic.
		// Map<String, Messages> consumedMap =
		// kafkaConn.runConsumer(outputTopic);
		List<SmsMessageStatus> listOfMessages = new ArrayList<SmsMessageStatus>();

		Map<String, SmsMessageStatus> consumedMap = kafkaConn.runDBConsumer(outputTopicDb);
		for (Entry<String, SmsMessageStatus> e : consumedMap.entrySet()) {

			SmsMessageStatus receivedSms = e.getValue();
			listOfMessages.add(receivedSms);

			out_DbState = receivedSms.getStatus().getState().toString();

		}

		if (out_DbState == null) {
			out_DbState = null;
		}

		return out_DbState;

	}

	public String TimeStamp() {
		return Current_date_time;
	}

}
