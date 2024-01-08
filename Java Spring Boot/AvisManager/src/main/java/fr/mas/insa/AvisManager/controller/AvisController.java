package fr.mas.insa.AvisManager.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.mas.insa.AvisManager.Model.Aide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import fr.mas.insa.AvisManager.Model.Avis;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/avis")
public class AvisController {

    @Autowired
    Connection connection = null;

    @Autowired
    private RestTemplate restTemplate;




    @GetMapping("/getAll")
    public List<Avis> getAllAvis() {
        String sql = "SELECT * FROM Avis";
        List<Avis> Avis = new ArrayList<Avis>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Avis avis = new Avis(rs.getInt("Note"), rs.getString("Commentaire"), rs.getInt("Aide_reference"),rs.getInt("Benevol_reference"));
                Avis.add(avis);
            }
            return Avis;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/get/{id}")
    public Avis getAvis(@PathVariable int id) {
        String sql = "SELECT * FROM Avis WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Avis avis = new Avis(rs.getInt("Note"), rs.getString("Commentaire"), rs.getInt("Aide_reference"),rs.getInt("Benevol_reference"));
                return avis;
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    @GetMapping("/getByAide/{id}")
    public Avis getAvisByAide(@PathVariable int id) {
        String sql = "SELECT * FROM Avis WHERE aide_reference=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Avis avis = new Avis(rs.getInt("Note"), rs.getString("Commentaire"), rs.getInt("Aide_reference"),rs.getInt("Benevol_reference"));
                return avis;
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/getByBenevol/{id}")
    public List<Avis> getAvisByBenevol(@PathVariable int id) {
        String sql = "SELECT * FROM Avis WHERE benevol_reference=?";
        List<Avis> Avis = new ArrayList<Avis>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Avis avis = new Avis(rs.getInt("Note"), rs.getString("Commentaire"), rs.getInt("Aide_reference"),rs.getInt("Benevol_reference"));
                Avis.add(avis);
            }
            return Avis;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    @DeleteMapping("/delete/{id}")
    public void deleteAvis(@PathVariable int id) {
        String sql = "DELETE FROM Avis WHERE aide_reference=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    @PostMapping(path="/add", consumes = "application/json")
    public void addAvis(@RequestBody Avis avis) {
        Aide aide = this.restTemplate.getForObject("http://AideManagerService/aide/get/"+avis.getAide_reference(), Aide.class);
        String sql = "INSERT INTO Avis(note,commentaire,aide_reference,benevol_reference) VALUES(?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, avis.getNote());
            pstmt.setString(2, avis.getCommentaire());
            pstmt.setInt(3, avis.getAide_reference());
            pstmt.setInt(4, aide.getBenevol_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
