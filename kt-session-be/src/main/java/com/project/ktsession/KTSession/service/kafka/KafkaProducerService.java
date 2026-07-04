package com.project.ktsession.KTSession.service.kafka;

import com.project.ktsession.KTSession.dto.event.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, UserRegisteredEvent> kafkaTemplate;

    private static final String TOPIC = "user-registration";


    public void publish(UserRegisteredEvent event) {
        kafkaTemplate.send(TOPIC, event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Kafka publish failed", ex);
                    } else {
                        log.info("Kafka event published successfully");
                    }
                });   }
}
