package fr.insa.mas.AideManager;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		SpringApplication.run(AideManagerApplication.class, args);
	}




}
