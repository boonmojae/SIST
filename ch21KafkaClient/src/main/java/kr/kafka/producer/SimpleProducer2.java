 package kr.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SimpleProducer2 {
	private final static Logger logger = 
			LoggerFactory.getLogger(SimpleProducer2.class);
	//프로듀서가 생성한 데이터를 보낼 토픽 지정
	private final static String TOPIC_NAME = "test";
	//전송하고자 하는 카프카 클러스터 서버의 host와 ip를 지정
	private final static String BOOTSTRAP_SERVERS = "192.168.10.72:9092";
	
	public static void main(String[] args) {
		//Properties에는 KafkaProducer 인스턴스를 생성하기 위한
		//프로듀서 옵션들을 key/value 로 선언
		Properties configs = new Properties();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		//String 객체를 전송하기 위해 String을 직렬화하는 클래스인 카프카 라이브러리의 StringSerializer를 사용
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		//Properties를 kafkaProducer의 생성 파라미터로 추가하여 인스턴스 생성
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(configs);
		//카프카 브로커로 데이터를 보내기 위해 ProducerRecod를 생성
		//메시지 키는 따로 선언하지 않았으므로 null로 설정되어 전송
		//메시지 키를 가진 데이터를 전송하는 프로듀서
		ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, "seoul","30");
		producer.send(record);
		logger.info("{}",record);
		
		producer.flush();
		producer.close();
	
	}
}
