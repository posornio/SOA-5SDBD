package fr.insa.mas.AideManager;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.insa.mas.AideManager.Model.DbInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AideManagerApplication {
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
		SpringApplication.run(AideManagerApplication.class, args);
	}




}
