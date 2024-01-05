package fr.insa.mas.AideManager.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.mas.AideManager.Model.Aide;

@RestController
@RequestMapping("/aide")
public class AideController {
	@Autowired
	Connection connection = null;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/{id}")
	public Aide getAideById(@PathVariable("id") int id) {
        String sql = "SELECT * FROM Aide WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String type = rs.getString("type");
                String status = rs.getString("status");
                String motif = rs.getString("motif_rejet");
                int benevolId = rs.getInt("benevol_id");
                Aide aide = new Aide(id,type, status, motif, benevolId);
                return aide;
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
	
	@PostMapping("/add")
	public String addAide(String type, String status, String motif, int benevolId) {
		String sql = "INSERT INTO Aide(type, status, motif_rejet, benevol_id) VALUES(?,?,?,?)";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, type);
			pstmt.setString(2, status);
			pstmt.setString(3, motif);
			pstmt.setInt(4, benevolId);
			pstmt.executeUpdate();
			return "Aide ajoutée";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "Erreur lors de l'ajout de l'aide";
		}
	}
	
	@PostMapping("/update")
	public String updateAide(int id, String type, String status, String motif, int benevolId) {
		String sql = "UPDATE Aide SET type = ?, status = ?, motif_rejet = ?, benevol_id = ? WHERE id = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, type);
			pstmt.setString(2, status);
			pstmt.setString(3, motif);
			pstmt.setInt(4, benevolId);
			pstmt.setInt(5, id);
			pstmt.executeUpdate();
			return "Aide modifiée";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "Erreur lors de la modification de l'aide";
		}
	}
	
	@DeleteMapping("/{id}")
	public String deleteAide(@PathVariable int id) {
		String sql = "DELETE FROM Aide WHERE id = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			return "Aide supprimée";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "Erreur lors de la suppression de l'aide";
		}
	}
	
	@GetMapping("/getAll")	
	public List<Aide> getAllAide() {
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
					Aide aide = new Aide(id,type, status, motif, benevolId);
	                aides.add(aide);
	            }
	            return aides;
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            return null;
	        }
	}
	
	
	

}
