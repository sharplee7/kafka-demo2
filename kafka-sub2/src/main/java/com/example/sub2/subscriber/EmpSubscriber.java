package com.example.sub2.subscriber;

//
import com.example.sub2.domain.EmpDTO2;
import com.example.sub2.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmpSubscriber {
    @Autowired
    EmpService empService;

    @KafkaListener(topics = "${emp.topic.name}", containerFactory = "empKafkaListenerContainerFactory")
    public void empListener(EmpDTO2 empDTO) {
        try {
            System.out.println("▶▶▶▶▶▶▶▶ RECEIVED DATA FROM KAFKA: " + empDTO.toString());
            empService.insertEmp(empDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
