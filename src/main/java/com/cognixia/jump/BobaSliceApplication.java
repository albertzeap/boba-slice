package com.cognixia.jump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@OpenAPIDefinition(
   info = @Info( title="Boba Slice API", version="1.0",
           description="API that allows Users to view menu items, place an order, and view orders. Allows Admins to create and update menu items.",
           contact=@Contact(name="Boba Slice", email="bobaslice@gmail.com")
))

public class BobaSliceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BobaSliceApplication.class, args);
	}

}
