package fr.insa.soa.Rest;
import fr.insa.soa.Rest.Etudiant;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.UriInfo;

@Path("etudiant")
public class EtudiantRessource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Etudiant getEtudiant(@Context UriInfo uriInfo) {
		Etudiant etudiant = new Etudiant();
		etudiant.setNom("Lemmers");
		etudiant.setPrenom("Jean");
		etudiant.setId(01);
		etudiant.addLink(getUriInfoSelf(uriInfo,etudiant),"self","GET");
		etudiant.addLink(getUriInfoStage(uriInfo),"stage","GET");
		return etudiant;
	}

	private String getUriInfoStage(UriInfo uriInfo) {
		String uri = uriInfo.getBaseUriBuilder().path(StageRessource.class)
				.build().toString();
		return uri;
	}
	
	private String getUriInfoSelf(UriInfo uriInfo, Etudiant etudiant) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(EtudiantRessource.class)
				.path(Long.toString(etudiant.getId())).build()
				.toString();
		return uri;
	}
	
	@POST
	@Path("/{idEtudiant}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addEtudiant(Etudiant etudiant) {
		System.out.println("Etudiant ajouté : " + etudiant.getNom());
		System.out.println("Binome : " + etudiant.getBinome().getNom());
	}

}
