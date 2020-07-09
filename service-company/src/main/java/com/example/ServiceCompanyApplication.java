package com.example;

import com.example.entities.Company;
import com.example.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.stream.Stream;

@EnableDiscoveryClient
@SpringBootApplication
@Configuration
public class ServiceCompanyApplication implements CommandLineRunner {

    @Autowired
    CompanyRepository companyRepository;

    public static void main(String[] args) {
        SpringApplication.run(ServiceCompanyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("A","B","C").forEach(name -> {
            companyRepository.save(new Company(null,name,100+ Math.random()*900));
        });
        companyRepository.findAll().forEach(System.out::println);
    }

    @Bean
    public HttpTraceRepository httpTraceRepository()
    {
        return new InMemoryHttpTraceRepository();
    }


}
