package com.example.sub.config;

//import com.example.common.EmpDTO;
import com.example.sub.domain.EmpDTO2;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    public ConsumerFactory<String, EmpDTO2> empConsumerFactory() {
        // Pub에서 보낸 DTO의 패키지 구조에 상관 없이 전송 받을 수 있도록 TrustedPackage 설정
        JsonDeserializer<EmpDTO2> deserializer = new JsonDeserializer<>(EmpDTO2.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "emp");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EmpDTO2> empKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, EmpDTO2> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(empConsumerFactory());
        // 필터 사용 시
        // factory.setRecordFilterStrategy(record -> record.value().contains("World"));
        return factory;
    }

}