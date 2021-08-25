package com.example.sub.subscribe;

import com.example.common.EmpDTO;
import com.example.sub.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmpSubscriber {
    @Autowired
    EmpService empService;

    @KafkaListener(topics = "${emp.topic.name}", containerFactory = "empKafkaListenerContainerFactory")
    public void empListener(EmpDTO empDTO) {
        try {
            System.out.println("▶▶▶▶▶▶▶▶ RECEIVED DATA FROM KAFKA: " + empDTO.toString());
            empService.insertEmp(empDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
