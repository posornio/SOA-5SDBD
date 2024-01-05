package fr.insa.mas.BeneficiareManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class BeneficiareManagerApplication {
	@Bean
	public Connection dbinit() throws Exception {
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_064";
		String user = "projet_gei_064";
		String password = "Aepahzu1";

		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url, user, password);

		// Perform database operations here

	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


	public static void main(String[] args) {
		SpringApplication.run(BeneficiareManagerApplication.class, args);
	}

}
