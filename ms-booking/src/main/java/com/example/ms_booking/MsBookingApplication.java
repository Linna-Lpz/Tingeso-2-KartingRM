package com.example.ms_booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBookingApplication.class, args);
	}

}
