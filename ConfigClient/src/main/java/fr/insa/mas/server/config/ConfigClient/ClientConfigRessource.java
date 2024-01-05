package fr.insa.mas.server.config.ConfigClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientConfigRessource {
	@Value("${server.port}")
	private String serverPort;
	@Value("${db.connection}")
	private String typeConnexionDeDB;
	@Value("${db.host}")
	private String dbHost;
	@Value("${db.port}")
	private String dbPort;
	
	@GetMapping("/dbHost")
	public String getDbHost() {
		System.out.println("dbHost: "+dbHost);
		return dbHost;
	}
	

}
