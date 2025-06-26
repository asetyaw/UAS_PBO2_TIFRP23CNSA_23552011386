/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpus.dao;

import com.mycompany.perpus.model.Mahasiswa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
        String sql = "INSERT INTO mahasiswa (nim,nama,prodi,angkatan) VALUES(?,?,?,?)";
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
}
