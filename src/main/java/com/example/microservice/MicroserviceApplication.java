package com.example.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.SQLException;
import java.util.Random;

@SpringBootApplication
@EnableSwagger2
public class MicroserviceApplication {
	private static Logger _logger = LoggerFactory.getLogger(MicroserviceApplication.class);
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SpringApplication.run(MicroserviceApplication.class, args);

		//StoredProcedure.createStoredProcedure();

	}

	@Bean
	public CommandLineRunner demo(OrderRepository orderRepository, CustomerRepository customerRepository){
		return args ->{
			_logger.info("adding Dummy Customers and their orders");
			for(int i=1;i<1000;i++) {
				Customer customer=new Customer("Name"+i, "State"+i, "City"+i, 102030+i);
				customerRepository.save(customer);
				Order order=new Order(customer,(100*i)%110,(i+1)%7);
				orderRepository.save(order);
				customer.placeOrder(order);
			}

		};
	}

	@Bean
	public Docket userApi(){
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.microservice"))
				.build();
	}

}
