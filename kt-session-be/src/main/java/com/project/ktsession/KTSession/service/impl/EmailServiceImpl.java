package com.project.ktsession.KTSession.service.impl;

import com.project.ktsession.KTSession.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Value("${brevo.api.key}")
    private String apiKey;

    @Value("${brevo.sender.email}")
    private String senderEmail;

    @Value("${brevo.sender.name}")
    private String senderName;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendRegistrationEmail(String email, String fullName) {

        String url = "https://api.brevo.com/v3/smtp/email";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);

        String htmlContent = """
                <html>
                <body style="font-family: Arial, sans-serif; color: #333333;">

                    <h2 style="color:#0d6efd;">Welcome to KT Session!</h2>

                    <p>Dear <b>%s</b>,</p>


                    <p>
                        Your account has been created successfully, and we're excited to have you as part of our learning community.
                    </p>

                    <p>
                        You can now log in and start exploring high-quality technical sessions, courses, and resources designed to enhance your skills and accelerate your career.
                    </p>

                    <p>
                        We're committed to providing you with an engaging and seamless learning experience.
                    </p>

                    <p>
                        If you have any questions or need assistance, feel free to reach out to our support team.
                    </p>

                    <br>

                    <p>
                        Happy Learning!
                    </p>

                    <br>

                    <p>
                        Best Regards,
                    </p>

                    <b>KT Session Team</b>

                    <p>
                        Empowering Learning, One Session at a Time.
                    </p>

                </body>
                </html>
                """.formatted(fullName);

        Map<String, Object> body = Map.of(
                "sender", Map.of(
                        "name", senderName,
                        "email", senderEmail
                ),
                "to", List.of(
                        Map.of("email", email)
                ),
                "subject", "Welcome to KT Session – Registration Successful",
                "htmlContent", htmlContent
        );

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response =
                    restTemplate.postForEntity(url, request, String.class);

            log.info("Email sent successfully: {}", response.getBody());

        } catch (Exception e) {
            log.error("Failed to send email", e);
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }
}
//    private final JavaMailSender mailSender;
//
//    @Override
//    public void sendRegistrationEmail(String email, String fullName) {
//
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setTo(email);
//        message.setSubject("Welcome to KT Session – Registration Successful");
//        log.info("EmailService: Sent mail");
//        message.setText(
//                "Dear " + fullName + ",\n\n" +
//                        "Welcome to KT Session!\n\n" +
//                        "Your account has been created successfully, and we're excited to have you as part of our learning community.\n\n" +
//                        "You can now log in and start exploring high-quality technical sessions, courses, and resources designed to enhance your skills and accelerate your career.\n\n" +
//                        "We're committed to providing you with an engaging and seamless learning experience.\n\n" +
//                        "If you have any questions or need assistance, feel free to reach out to our support team.\n\n" +
//                        "Happy Learning!\n\n" +
//                        "Best Regards,\n\n" +
//                        "KT Session Team\n" +
//                        "Empowering Learning, One Session at a Time."
//        );
//
//        mailSender.send(message);
//    }
