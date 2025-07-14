/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpus.dao;

import com.mycompany.perpus.model.Absensi;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AbsensiDAO {

    public boolean insertAbsensi(String nim) {
        String sql = "INSERT INTO absensi (nim) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nim);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Absensi> getAbsensiByDate(LocalDate date) {
        String sql = "SELECT a.nim, m.nama, a.waktu " +
                     "FROM absensi a JOIN mahasiswa m ON a.nim = m.nim " +
                     "WHERE DATE(a.waktu) = ?";
        return getAbsensiList(sql, date.toString(), null, null);
    }

    public List<Absensi> getAbsensiByWeek(LocalDate date) {
        String sql = "SELECT a.nim, m.nama, a.waktu " +
                     "FROM absensi a JOIN mahasiswa m ON a.nim = m.nim " +
                     "WHERE WEEK(a.waktu, 1) = WEEK(?, 1) AND YEAR(a.waktu) = YEAR(?)";
        return getAbsensiList(sql, date.toString(), date.toString(), null);
    }

    public List<Absensi> getAbsensiByMonth(LocalDate date) {
        String sql = "SELECT a.nim, m.nama, a.waktu " +
                     "FROM absensi a JOIN mahasiswa m ON a.nim = m.nim " +
                     "WHERE MONTH(a.waktu) = MONTH(?) AND YEAR(a.waktu) = YEAR(?)";
        return getAbsensiList(sql, date.toString(), date.toString(), null);
    }

    public List<Absensi> getAllAbsensi() {
        String sql = "SELECT a.nim, m.nama, a.waktu " +
                     "FROM absensi a JOIN mahasiswa m ON a.nim = m.nim " +
                     "ORDER BY a.waktu DESC";
        return getAbsensiList(sql, null, null, null);
    }

    private List<Absensi> getAbsensiList(String sql, String param1, String param2, String param3) {
        List<Absensi> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (param1 != null) stmt.setString(1, param1);
            if (param2 != null) stmt.setString(2, param2);
            if (param3 != null) stmt.setString(3, param3);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nim = rs.getString("nim");
                String nama = rs.getString("nama");
                String waktu = rs.getString("waktu");
                list.add(new Absensi(nim, nama, waktu));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
