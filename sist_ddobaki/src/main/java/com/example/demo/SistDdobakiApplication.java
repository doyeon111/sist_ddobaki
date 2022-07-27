package com.example.demo;

import javax.persistence.EntityManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.querydsl.jpa.impl.JPAQueryFactory;

@SpringBootApplication
public class SistDdobakiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistDdobakiApplication.class, args);
	}
	
	@Bean    
    public JPAQueryFactory jpaQueryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }
	
	@Bean(name = "uploadPath")
	public String uploadPath() {
		return "c:/day0720/sist_ddobaki/src/main/resources/static/images/";
	}

}
