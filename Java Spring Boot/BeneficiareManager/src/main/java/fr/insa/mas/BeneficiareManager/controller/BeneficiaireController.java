 package fr.insa.mas.BeneficiareManager.controller;

import fr.insa.mas.BeneficiareManager.Model.Beneficiaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/beneficiaire")
public class BeneficiaireController {
    @Autowired
    Connection connection = null;

    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/nbOf")
    public int getNumberBenevol() {
        String sql = "SELECT COUNT(*) FROM Beneficiaire";
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
    
    @PostMapping(path = "/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void ajouterBeneficiaire(@RequestBody Beneficiaire beneficiaire) {
        String sql = "INSERT INTO Beneficiaire(Nom,Prenom) VALUES(?,?)";
        //(IDUSERS,LOGIN)
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, beneficiaire.getNom());
            pstmt.setString(2, beneficiaire.getPrenom());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public List<Beneficiaire> getAllBeneficiaire() {
        String sql = "SELECT * FROM Beneficiaire";
        List<Beneficiaire> Beneficiaire = new ArrayList<Beneficiaire>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                Beneficiaire beneficiaire = new Beneficiaire(id, nom, prenom);
                Beneficiaire.add(beneficiaire);
            }
            return Beneficiaire;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/{id}")
    public Beneficiaire getBeneficiaireById(@PathVariable("id") int id) {
        String sql = "SELECT * FROM Beneficiaire WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                return new Beneficiaire(id, nom, prenom);
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteBeneficiaire(@PathVariable int id) {
        String sql = "DELETE FROM Beneficiaire WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return "Beneficiaire supprimé";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "Erreur lors de la suppression du Beneficiaire";
        }
    }
}
