package com.example.pub.publish;

import com.example.common.EmpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class EmpPublisher {
    @Autowired
    private KafkaTemplate<String, EmpDTO> empKafkaTemplate;

    @Value(value = "${emp.topic.name}")
    private String empTopicName;

    public void publish(EmpDTO empDTO) {
        ListenableFuture<SendResult<String, EmpDTO>> future = empKafkaTemplate.send(empTopicName, empDTO);

        // 아래의 콜백은 옵션 사항
        future.addCallback(new ListenableFutureCallback<SendResult<String, EmpDTO>>() {
            @Override
            public void onSuccess(SendResult<String, EmpDTO> result) {
                EmpDTO empDTO = result.getProducerRecord().value();
                System.out.println("Sent message=[" + empDTO.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println( "Unable to send message=[" + empDTO.toString() + "] due to : " + ex.getMessage());
            }
        });


    }
}
