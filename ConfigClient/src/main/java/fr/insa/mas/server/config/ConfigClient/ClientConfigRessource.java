package fr.insa.mas.server.config.ConfigClient;

import fr.insa.mas.server.config.ConfigClient.Model.DbInfo;
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
	@Value("${db.url}")
	private String dbUrl;
	@Value("${db.user}")
	private String dbUser;
	@Value("${db.password}")
	private String dbPassword;
	
	@GetMapping("/dbHost")
	public String getDbHost() {
		System.out.println("dbHost: "+dbHost);
		return dbHost;
	}
	
	@GetMapping("/dbPort")
	public String getDbPort() {
		System.out.println("dbPort: " + dbPort);
		return dbPort;
	}
	
	@GetMapping("/dbUrl")
	public String getDbUrl() {
		System.out.println("dbUrl: " + dbUrl);
		return dbUrl;
	}
	
	@GetMapping("/dbUser")
	public String getDbUser() {
		System.out.println("dbUser: " + dbUser);
		return dbUser;
	}
	
	@GetMapping("/dbPassword")
	public String getDbPassword() {
		System.out.println("dbPassword: " + dbPassword);
		return dbPassword;
	}

	@GetMapping("/dbInfo")
	public DbInfo getDbInfo() {
		System.out.println("dbInfo: " + new DbInfo(dbUrl, dbUser, dbPassword));
		return new DbInfo(dbUrl, dbUser, dbPassword);
	}

	

}
