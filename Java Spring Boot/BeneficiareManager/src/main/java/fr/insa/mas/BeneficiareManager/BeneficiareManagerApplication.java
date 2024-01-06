package fr.insa.mas.BeneficiareManager;

import fr.insa.mas.BeneficiareManager.Model.DbInfo;
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
		DbInfo dbInfo = this.restTemplate().getForObject("http://localhost:8087/dbInfo", DbInfo.class);

		Class.forName("com.mysql.jdbc.Driver");
		System.out.println(dbInfo);
		return DriverManager.getConnection(dbInfo.getUrl(), dbInfo.getUser(), dbInfo.getPassword());


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
