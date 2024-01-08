package fr.mas.insa.AvisManager;

import fr.mas.insa.AvisManager.Model.DbInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class AvisManagerApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	@Bean
	public Connection dbinit() throws Exception {

		DbInfo dbInfo = this.restTemplate().getForObject("http://localhost:8087/dbInfo", DbInfo.class);

		Class.forName("com.mysql.jdbc.Driver");
		System.out.println(dbInfo);
		return DriverManager.getConnection(dbInfo.getUrl(), dbInfo.getUser(), dbInfo.getPassword());

		// Perform database operations here

	}


	public static void main(String[] args) {
		SpringApplication.run(AvisManagerApplication.class, args);
	}

}
