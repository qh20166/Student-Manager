package com.hcmut.student_management;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {
    static {
        // Luôn nạp file .env từ thư mục src
        Dotenv dotenv = Dotenv.configure()
                .directory("./src")
                .ignoreIfMissing()
                .load();

        // Đưa các biến vào System để Spring Boot bốc được
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
    }
}