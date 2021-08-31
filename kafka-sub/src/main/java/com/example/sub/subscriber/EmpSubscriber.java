package com.example.sub.subscriber;

import com.example.common.EmpDTO;
import com.example.sub.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmpSubscriber {
    private final Logger logger = LoggerFactory.getLogger(EmpSubscriber.class);

    @Autowired
    EmpService empService;

    @KafkaListener(topics = "${emp.topic.name}", containerFactory = "empKafkaListenerContainerFactory")
    public void empListener(EmpDTO empDTO) {
        try {
            logger.info("▶▶▶▶▶▶▶▶ RECEIVED DATA FROM KAFKA: " + empDTO.toString());

            empService.insertEmp(empDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
