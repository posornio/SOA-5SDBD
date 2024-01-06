package fr.insa.soa.Rest;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;


public class ClientRest {
	public static void main(String [] args ) {
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:8080/Rest/webapi/comparateur/longueur/Toulouse").request().get();
		System.out.println(response.readEntity(String.class));
	}

}
