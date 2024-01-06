package fr.insa.soa.Rest;

import fr.insa.soa.Rest.Stage;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
@Path("stage")
public class StageRessource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Stage getStage() {
		Stage stage = new Stage();
		stage.setCompetences("Java");
		stage.setEvaluation(5);
		stage.setAnnee(2021);
		return stage;
	}

}
