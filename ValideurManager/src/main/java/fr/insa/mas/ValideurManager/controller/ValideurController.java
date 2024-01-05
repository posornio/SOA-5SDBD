 package fr.insa.mas.ValideurManager.controller;

import fr.insa.mas.ValideurManager.Model.Valideur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/valideurs")
public class ValideurController {
    @Autowired
    Connection connection = null;

    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/nbOf")
    public int getNumberValideurs() {
        String sql = "SELECT COUNT(*) FROM Valideurs";
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
    
    @PostMapping("/add")
    public void ajouterValideur( String nom,String prenom) {
        String sql = "INSERT INTO Valideurs(Nom,Prenom) VALUES(?,?)";
        //(IDUSERS,LOGIN)
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, prenom);
            pstmt.setString(2, nom);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public List<Valideur> getAllValideur() {
        String sql = "SELECT * FROM Valideurs";
        List<Valideur> Valideurs = new ArrayList<Valideur>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                Valideur Valideur = new Valideur(id, nom, prenom);
                Valideurs.add(Valideur);
            }
            return Valideurs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/{id}")
    public Valideur getValideurById(@PathVariable("id") int id) {
        String sql = "SELECT * FROM Valideurs WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                return new Valideur(id, nom, prenom);
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}