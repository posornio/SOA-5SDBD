package com.insa.soap;
import java.net.MalformedURLException;
import javax.xml.ws.Endpoint;

public class PersonApplication {
	public static String host = "localhost";
	public static short port = 8090;
	public void demarrerService() {
		String url = "http://"+host+":"+port+"/";
		Endpoint.publish(url, new PersonWS());
	}
	public static void main(String [] args) throws MalformedURLException {
		new PersonApplication().demarrerService();
		System.out.println("Service demarré");
	}
	
}
