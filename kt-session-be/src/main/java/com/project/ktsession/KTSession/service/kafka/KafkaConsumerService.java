package com.project.ktsession.KTSession.service.kafka;

import com.project.ktsession.KTSession.dto.event.UserRegisteredEvent;
import com.project.ktsession.KTSession.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final EmailService emailService;

    @KafkaListener(
            topics = "user-registration",
            groupId = "kt-session-group"
    )
    public void consume(UserRegisteredEvent event) {

        log.info("Received Event : {}", event);

        emailService.sendRegistrationEmail(
                event.getEmail(),
                event.getFullName()
        );

        log.info("Email sent successfully");
    }
}
