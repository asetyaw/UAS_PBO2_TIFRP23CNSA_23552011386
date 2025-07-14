/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpus.controller;

import com.mycompany.perpus.dao.MahasiswaDAO;
import com.mycompany.perpus.dao.AbsensiDAO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.media.AudioClip;




import java.io.IOException;

public class AbsensiController {
    @FXML private TextField nimField;
    @FXML private Label statusLabel;
    @FXML private Label welcomeLabel;


    private MahasiswaDAO mahasiswaDAO = new MahasiswaDAO();
    private AbsensiDAO absensiDAO = new AbsensiDAO();

    /**
     * Method untuk melakukan absensi berdasarkan NIM
     */
    @FXML
    private void submitAbsensi() {
        String nim = nimField.getText().trim();

        if (nim.isEmpty()) {
            statusLabel.setText("NIM harus diisi!");
            return;
        }

        if (!mahasiswaDAO.exists(nim)) {
            statusLabel.setText("NIM belum terdaftar.");
        } else if (absensiDAO.insertAbsensi(nim)) {
            String nama = mahasiswaDAO.getNamaByNim(nim); // kamu harus punya method ini
            welcomeLabel.setText("Selamat datang, " + nama + "!");

            AudioClip clip = new AudioClip(getClass().getResource("/sounds/selamat_datang.wav").toString());
            clip.play();

            statusLabel.setText("Absensi berhasil!");
            nimField.clear();
        } else {
            statusLabel.setText("Gagal absensi.");
        }
    }

    /**
     * Method untuk membuka form pendaftaran mahasiswa
     */
    @FXML
    private void openRegisterDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
            Parent dialogRoot = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Pendaftaran Mahasiswa");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(dialogRoot));
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            statusLabel.setText("Gagal membuka dialog pendaftaran.");
        }
    }
    
    @FXML
    
    private void openLoginPage(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent loginRoot = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loginRoot);
        stage.setScene(scene);
        stage.setTitle("Login Admin");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
