package com.mycompany.perpus.dao;

import com.mycompany.perpus.model.Mahasiswa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDAO {

    public boolean exists(String nim) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM mahasiswa WHERE nim=?")) {
            stmt.setString(1, nim);
            return stmt.executeQuery().next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insert(Mahasiswa m) {
        String sql = "INSERT INTO mahasiswa (nim, nama, prodi, angkatan) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, m.getNim());
            stmt.setString(2, m.getNama());
            stmt.setString(3, m.getProdi());
            stmt.setString(4, m.getAngkatan());
            return stmt.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(Mahasiswa m) {
        String sql = "UPDATE mahasiswa SET nama = ?, prodi = ?, angkatan = ? WHERE nim = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getNama());
            stmt.setString(2, m.getProdi());
            stmt.setString(3, m.getAngkatan());
            stmt.setString(4, m.getNim());
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean delete(String nim) {
    String sql = "DELETE FROM mahasiswa WHERE nim = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, nim);
        int affected = stmt.executeUpdate();
        return affected > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
        }
    }


    public List<Mahasiswa> getAll() {
        List<Mahasiswa> list = new ArrayList<>();
        String sql = "SELECT * FROM mahasiswa ORDER BY nama ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nim = rs.getString("nim");
                String nama = rs.getString("nama");
                String prodi = rs.getString("prodi");
                String angkatan = rs.getString("angkatan");

                list.add(new Mahasiswa(nim, nama, prodi, angkatan));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public String getNamaByNim(String nim) {
        String sql = "SELECT nama FROM mahasiswa WHERE nim = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nim);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nama");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
