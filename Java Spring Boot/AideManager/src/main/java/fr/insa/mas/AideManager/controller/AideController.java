package fr.insa.mas.AideManager.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.insa.mas.AideManager.Model.Avis;
import fr.insa.mas.AideManager.Model.DbInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import fr.insa.mas.AideManager.Model.Aide;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/aide")
public class AideController {
	@Autowired
	Connection connection = null;

	@Autowired
	private RestTemplate restTemplate;

@GetMapping("/nbOf")
	public int getNumberAide() {
		String sql = "SELECT COUNT(*) FROM Aide";
		try (Statement stmt = connection.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				int nbOf = rs.getInt(1);
				return nbOf;
			}
			return 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	@GetMapping("/get/{id}")
	public Aide getAide(@PathVariable int id) {
		String sql = "SELECT * FROM Aide WHERE id = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int idAide = rs.getInt("id");
				String type = rs.getString("type");
				String status = rs.getString("status");
				String motif = rs.getString("motif_rejet");
				int benevolId = rs.getInt("benevol_id");
				int traitePar = rs.getInt("traite_par");
				int demandePar = rs.getInt("demande_par");
				String avisUrl = "http://AvisManagerService/avis/getByAide/"+id;
				Avis avis = restTemplate.getForObject(avisUrl, Avis.class);

				Aide aide = new Aide(idAide,type, status, motif, benevolId,traitePar,demandePar, avis);
				return aide;
			}
			return null;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@GetMapping("/getByBenevol/{id}")
	public List<Aide> getAideByBenevol(@PathVariable int id) {
		String sql = "SELECT * FROM Aide WHERE benevol_id = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			List<Aide> aides = new ArrayList<Aide>();
			while (rs.next()) {
				int idAide = rs.getInt("id");
				String type = rs.getString("type");
				String status = rs.getString("status");
				String motif = rs.getString("motif_rejet");
				int benevolId = rs.getInt("benevol_id");
				int traitePar = rs.getInt("traite_par");
				int demandePar = rs.getInt("demande_par");
				String avisUrl = "http://AvisManagerService/avis/getByAide/"+id;
				Avis avis = restTemplate.getForObject(avisUrl, Avis.class);
				Aide aide = new Aide(idAide,type, status, motif, benevolId,traitePar,demandePar, avis);
				aides.add(aide);
			}
			return aides;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@GetMapping("/getByDemande/{id}")
	public List<Aide> getAideByDemande(@PathVariable int id) {
		String sql = "SELECT * FROM Aide WHERE demande_par = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			List<Aide> aides = new ArrayList<Aide>();
			while (rs.next()) {
				int idAide = rs.getInt("id");
				String type = rs.getString("type");
				String status = rs.getString("status");
				String motif = rs.getString("motif_rejet");
				int benevolId = rs.getInt("benevol_id");
				int traitePar = rs.getInt("traite_par");
				int demandePar = rs.getInt("demande_par");
				String avisUrl = "http://AvisManagerService/avis/getByAide/"+id;
				Avis avis = restTemplate.getForObject(avisUrl, Avis.class);
				Aide aide = new Aide(idAide,type, status, motif, benevolId,traitePar,demandePar, avis);
				aides.add(aide);
			}
			return aides;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@GetMapping("/getByStatus/{status}")
	public List<Aide> getAideByStatus(@PathVariable String status) {
		String sql = "SELECT * FROM Aide WHERE status = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, status);
			ResultSet rs = pstmt.executeQuery();
			List<Aide> aides = new ArrayList<Aide>();
			while (rs.next()) {
				int idAide = rs.getInt("id");
				String type = rs.getString("type");
				String statusAide = rs.getString("status");
				String motif = rs.getString("motif_rejet");
				int benevolId = rs.getInt("benevol_id");
				int traitePar = rs.getInt("traite_par");
				int demandePar = rs.getInt("demande_par");
				String avisUrl = "http://AvisManagerService/avis/getByAide/"+idAide;
				Avis avis = restTemplate.getForObject(avisUrl, Avis.class);
				Aide aide = new Aide(idAide,type, statusAide, motif, benevolId,traitePar,demandePar, avis);
				aides.add(aide);
			}
			return aides;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@GetMapping("/getByType/{type}")
	public List<Aide> getAideByType(@PathVariable String type) {
		String sql = "SELECT * FROM Aide WHERE type = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, type);
			ResultSet rs = pstmt.executeQuery();
			List<Aide> aides = new ArrayList<Aide>();
			while (rs.next()) {
				int idAide = rs.getInt("id");
				String typeAide = rs.getString("type");
				String status = rs.getString("status");
				String motif = rs.getString("motif_rejet");
				int benevolId = rs.getInt("benevol_id");
				int traitePar = rs.getInt("traite_par");
				int demandePar = rs.getInt("demande_par");
				String avisUrl = "http://AvisManagerService/avis/getByAide/"+idAide;
				Avis avis = restTemplate.getForObject(avisUrl, Avis.class);
				Aide aide = new Aide(idAide,typeAide, status, motif, benevolId,traitePar,demandePar, avis);
				aides.add(aide);
			}
			return aides;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@GetMapping("/getByManager/{id}")
	public List<Aide> getAideByManager(@PathVariable int id) {
		String sql = "SELECT * FROM Aide WHERE traite_par = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			List<Aide> aides = new ArrayList<Aide>();
			while (rs.next()) {
				int idAide = rs.getInt("id");
				String type = rs.getString("type");
				String status = rs.getString("status");
				String motif = rs.getString("motif_rejet");
				int benevolId = rs.getInt("benevol_id");
				int traitePar = rs.getInt("traite_par");
				int demandePar = rs.getInt("demande_par");
				String avisUrl = "http://AvisManagerService/avis/getByAide/"+idAide;
				Avis avis = restTemplate.getForObject(avisUrl, Avis.class);
				Aide aide = new Aide(idAide,type, status, motif, benevolId,traitePar,demandePar, avis);
				aides.add(aide);
			}
			return aides;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}


	@PostMapping(path="/add", consumes = "application/json")
	public String addAide(@RequestBody Aide aide) {
		String sql = "INSERT INTO Aide(type, status, motif_rejet, benevol_id,traite_par,demande_par) VALUES(?,?,?,?,?,?)";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, aide.getType());
			pstmt.setString(2, aide.getStatus());
			pstmt.setString(3, aide.getMotif_rejet());
			pstmt.setInt(4, aide.getBenevol_id());
			pstmt.setInt(5, aide.getTraite_par());
			pstmt.setInt(6, aide.getDemande_par());
			pstmt.executeUpdate();
			return "Aide ajoutée";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "Erreur lors de l'ajout de l'aide";
		}
	}

	@PatchMapping("/update/{id}")
	public String updateAide(@PathVariable(value = "id") int aideId, @RequestBody Aide aide) {
		String sql = "UPDATE Aide SET type = ?, status = ?, motif_rejet = ?, benevol_id = ? WHERE id = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, aide.getType());
			pstmt.setString(2, aide.getStatus());
			pstmt.setString(3, aide.getMotif_rejet());
			pstmt.setInt(4, aide.getBenevol_id());
			pstmt.setInt(5, aideId);
			pstmt.executeUpdate();
			return "Aide modifiée";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "Erreur lors de la modification de l'aide";
		}
	}

	@DeleteMapping("/delete/{id}")
	public String deleteAide(@PathVariable int id) {
		String sql = "DELETE FROM Aide WHERE id = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			restTemplate.delete("http://AvisManagerService/avis/delete/"+id);
			return "Aide supprimée";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "Erreur lors de la suppression de l'aide";
		}
	}

	@GetMapping("/getAll")
	public List<Aide> getAllAide() {
		System.out.println("getAllAide");
        String sql = "SELECT * FROM Aide";

		 List<Aide> aides = new ArrayList<Aide>();
	        try (Statement stmt = connection.createStatement()) {
	            ResultSet rs = stmt.executeQuery(sql);
	            while (rs.next()) {
	            	int id = rs.getInt("id");
					String type = rs.getString("type");
					String status = rs.getString("status");
					String motif = rs.getString("motif_rejet");
					int benevolId = rs.getInt("benevol_id");
					int traitePar = rs.getInt("traite_par");
					int demandePar = rs.getInt("demande_par");
					String avisUrl = "http://AvisManagerService/avis/getByAide/"+id;
					Avis avis = restTemplate.getForObject(avisUrl, Avis.class);
					Aide aide = new Aide(id,type, status, motif, benevolId,traitePar,demandePar, avis);
	                aides.add(aide);
	            }
	            return aides;
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            return null;
	        }
	}

	@PostMapping(path= "/valider", consumes = "application/json")
	public String validerAide(@RequestBody Aide aide) {
		System.out.println("validerAide");
		System.out.println(aide.getId());
		String sql = "UPDATE Aide SET status = ? , traite_par = ?  WHERE id = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, "Valide");
			pstmt.setInt(2, aide.getTraite_par());
			pstmt.setInt(3, aide.getId());
			pstmt.executeUpdate();
			return "Aide validée";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "Erreur lors de la validation de l'aide";
		}
	}

	@PostMapping(path= "/rejeter", consumes = "application/json")
	public String rejeterAide(@RequestBody Aide aide) {
		System.out.println("rejeterAide");
		System.out.println(aide.getId());
		System.out.println(aide.getMotif_rejet());
		String sql = "UPDATE Aide SET status = ? , motif_rejet = ? , traite_par = ?  WHERE id = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, "Rejeté");
			pstmt.setString(2, aide.getMotif_rejet());
			pstmt.setInt(3, aide.getTraite_par());
			pstmt.setInt(4, aide.getId());
			pstmt.executeUpdate();
			return "Aide rejetée";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "Erreur lors du rejet de l'aide";
		}

	}

	@PostMapping(path= "/affecter", consumes = "application/json")
	public String affecterAide(@RequestBody Aide aide) {
		System.out.println("affecterAide");
		System.out.println(aide.getId());
		System.out.println(aide.getBenevol_id());
		String sql = "UPDATE Aide SET benevol_id = ?, traite_par = ? WHERE id = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, aide.getBenevol_id());
			pstmt.setInt(2, aide.getTraite_par());
			pstmt.setInt(3, aide.getId());
			pstmt.executeUpdate();
			return "Aide affectée";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "Erreur lors de l'affectation de l'aide";
		}
	}


}
