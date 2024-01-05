 package fr.insa.mas.BenevolManager.controller;

import fr.insa.mas.BenevolManager.Model.Benevole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/benevoles")
public class BenevoleController {
    @Autowired
    Connection connection = null;

    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/nbOf")
    public int getNumberBenevol() {
        String sql = "SELECT COUNT(*) FROM Benevoles";
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
    public void ajouterBenevol( String nom,String prenom) {
        String sql = "INSERT INTO Benevoles(Nom,Prenom) VALUES(?,?)";
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
    public List<Benevole> getAllBenevole() {
        String sql = "SELECT * FROM Benevoles";
        List<Benevole> benevoles = new ArrayList<Benevole>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                Benevole benevole = new Benevole(id, nom, prenom);
                benevoles.add(benevole);
            }
            return benevoles;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/{id}")
    public Benevole getBenevoleById(@PathVariable("id") int id) {
        String sql = "SELECT * FROM Benevoles WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                Benevole benevole = new Benevole(id, nom, prenom);
                return benevole;
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
