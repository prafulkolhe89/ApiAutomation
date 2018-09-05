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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.ibm.silverpop.sms.smpp.db.model.SmppSmsMessage;
import com.ibm.silverpop.sms.smpp.db.model.SmsMessageStatus;
import com.ibm.silverpop.sms.smpp.serde.AvroDeserializer;
import com.ibm.silverpop.sms.smpp.serde.AvroSerializer;
import com.sun.prism.impl.Disposer.Record;

public class KafkaConnection {

	private static final Deserializer<String> Keyserde = null;
	private String bootStrapServer;

	public KafkaConnection(String bootStrapServer) {
		this.bootStrapServer = bootStrapServer;
	}

	public void runProducer(String topic, String key, String value) throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		List<SmppSmsMessage> smsList = new ArrayList<SmppSmsMessage>();

		smsList = mapper.readValue(value,
				TypeFactory.defaultInstance().constructCollectionType(List.class, SmppSmsMessage.class));

		Producer<String, SmppSmsMessage> producer = createProducer();

		for (SmppSmsMessage sms : smsList) {
			ProducerRecord<String, SmppSmsMessage> record = new ProducerRecord<String, SmppSmsMessage>(topic, sms);
			Future<RecordMetadata> future = producer.send(record);

			while (!future.isDone()) {
				// Wait till future is done.
				Thread.sleep(100);
			}
			RecordMetadata metadata = future.get();
			System.out.println("Record sent: " + metadata.toString());
		}

	}

	public void runProducerWithOrgKey(String topic, String key, String value) throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		List<SmppSmsMessage> smsList = new ArrayList<SmppSmsMessage>();

		smsList = mapper.readValue(value,
				TypeFactory.defaultInstance().constructCollectionType(List.class, SmppSmsMessage.class));

		Producer<String, SmppSmsMessage> producer = createProducer();

		for (SmppSmsMessage sms : smsList) {
			ProducerRecord<String, SmppSmsMessage> record = new ProducerRecord<String, SmppSmsMessage>(topic,
					sms.getOrgId(), sms);
			Future<RecordMetadata> future = producer.send(record);

			while (!future.isDone()) {
				// Wait till future is done.
				Thread.sleep(100);

			}
			RecordMetadata metadata = future.get();
			System.out.println("Record sent: " + metadata.toString());
		}
	}

	private Producer<String, SmppSmsMessage> createProducer() {
		final Producer<String, SmppSmsMessage> producer = new KafkaProducer(getProducerProperties());
		return producer;
	}

	private Properties getProducerProperties() {
		final Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleConsumer");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class.getName());

		return props;

	}

	/**
	 * This is consumer.
	 * 
	 * @param topic
	 *            - The topic to be consumed.
	 * @param sendResp
	 *            -
	 * @return
	 */
	Map<String, SmppSmsMessage> runConsumer(String topic) {
		final Consumer<String, SmppSmsMessage> consumer = createConsumer(topic);
		final int giveUp = 10;
		int noRecordsCount = 0;
		Map<String, SmppSmsMessage> consumedMap = new HashMap<String, SmppSmsMessage>();

		while (true) {
			final ConsumerRecords<String, SmppSmsMessage> consumerRecords = consumer.poll(1000);
			if (consumerRecords.count() == 0) {
				noRecordsCount++;
				if (noRecordsCount > giveUp)
					break;
				else
					continue;
			}

			consumerRecords.forEach(record -> {
				consumedMap.put(record.key(), record.value());
			});
			consumer.commitAsync();
			break;

		}
		consumer.close();
		return consumedMap;
	}

	/**
	 * This is consumer.
	 * 
	 * @param topic
	 *            - The topic to be consumed.
	 * @param sendResp
	 *            -
	 * @return
	 */

	/**
	 * This is consumer.
	 * 
	 * @param topic
	 *            - The topic to be consumed.
	 * @param sendResp
	 *            -
	 * @return
	 */
	Map<String, SmsMessageStatus> runDBConsumer(String topic) {
		final Consumer<String, SmsMessageStatus> consumer = createDBConsumer(topic);
		final int giveUp = 10;
		int noRecordsCount = 0;
		Map<String, SmsMessageStatus> consumedMap = new HashMap<String, SmsMessageStatus>();

		while (true) {
			final ConsumerRecords<String, SmsMessageStatus> consumerRecords = consumer.poll(1000);
			if (consumerRecords.count() == 0) {
				noRecordsCount++;
				if (noRecordsCount > giveUp)
					break;
				else
					continue;
			}

			consumerRecords.forEach(record -> {
				consumedMap.put(record.key(), record.value());
			});
			consumer.commitAsync();
			break;

		}
		consumer.close();
		return consumedMap;
	}

	List<SmppSmsMessage> runConsumerList(String topic) throws InterruptedException {
		final Consumer<String, SmppSmsMessage> consumer = createConsumer(topic);
		final int giveUp = 10;
		int noRecordsCount = 0;
		List<SmppSmsMessage> consumedMap = new ArrayList<SmppSmsMessage>();

		
	Thread.sleep(10000);
		
		
		while (true) {
			final ConsumerRecords<String, SmppSmsMessage> consumerRecords = consumer.poll(1000);
			// Thread.sleep(100);
			if (consumerRecords.count() == 0) {
				noRecordsCount++;
				if (noRecordsCount > giveUp)
					break;
				else
					continue;
			}

			consumerRecords.forEach(record -> {
				consumedMap.add(record.value());
			});
			consumer.commitAsync();
			break;

		}
		consumer.close();
		return consumedMap;
	}

	List<SmsMessageStatus> runDBConsumerList(String topic) {

		final Consumer<String, SmsMessageStatus> consumer = createDBConsumer(topic);
		final int giveUp = 1000;
		int noRecordsCount = 0;
		List<SmsMessageStatus> consumedMap = new ArrayList<SmsMessageStatus>();

		while (true) {
			final ConsumerRecords<String, SmsMessageStatus> consumerRecords = consumer.poll(Long.MAX_VALUE);
			if (consumerRecords.count() == 0) {
				noRecordsCount++;
				if (noRecordsCount > giveUp)
					break;
				else
					continue;
			}

			consumerRecords.forEach(record -> {
				consumedMap.add(record.value());
			});
			consumer.commitAsync();
			break;

		}
		consumer.close();
		return consumedMap;
	}

	private Consumer<String, SmppSmsMessage> createConsumer(String topic) {
		// Create the consumer using props.
		Consumer<String, SmppSmsMessage> consumer = null;

		AvroDeserializer<SmppSmsMessage> valueserde = new AvroDeserializer<>(SmppSmsMessage.class);
		consumer = new KafkaConsumer<String, SmppSmsMessage>(getConsumerProperties(), Serdes.String().deserializer(),
				valueserde);

		// Subscribe to the topic.
		consumer.subscribe(Collections.singletonList(topic));
		return consumer;
	}

	private Consumer<String, SmsMessageStatus> createDBConsumer(String topic) {
		// Create the consumer using props.
		Consumer<String, SmsMessageStatus> consumer = null;

		AvroDeserializer<SmsMessageStatus> valueserde = new AvroDeserializer<>(SmsMessageStatus.class);
		consumer = new KafkaConsumer<String, SmsMessageStatus>(getConsumerProperties(), Serdes.String().deserializer(),
				valueserde);

		// Subscribe to the topic.
		consumer.subscribe(Collections.singletonList(topic));
		return consumer;
	}

	private Properties getConsumerProperties() {
		final Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleTest12");
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10");

		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");

		return props;
	}

}
