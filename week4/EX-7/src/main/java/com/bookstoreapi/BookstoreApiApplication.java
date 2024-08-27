package com.bookstoreapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApiApplication.class, args);
        System.out.println("It's working Fine");
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
