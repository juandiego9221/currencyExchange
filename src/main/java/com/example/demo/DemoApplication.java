package com.example.demo;

import com.example.demo.entity.ChangeType;
import com.example.demo.repository.ChangeTypeRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    ChangeTypeRepository changeTypeRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            changeTypeRepository.save(new ChangeType(1, "PEN", 0.28));
            changeTypeRepository.save(new ChangeType(2, "USD", 1));
            changeTypeRepository.save(new ChangeType(3, "EUR", 1.22));
            changeTypeRepository.save(new ChangeType(4, "MEX", 0.049));
        };
    }
}
