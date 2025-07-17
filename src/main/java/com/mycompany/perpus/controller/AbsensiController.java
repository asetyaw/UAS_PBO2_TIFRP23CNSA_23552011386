/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpus.controller;

import com.mycompany.perpus.dao.MahasiswaDAO;
import com.mycompany.perpus.dao.AbsensiDAO;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

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
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class AbsensiController {
    @FXML private TextField nimField;
    @FXML private Label statusLabel;
    @FXML private Label welcomeLabel;
    @FXML private ImageView logoView;
    @FXML private Button loginAdminButton;

    private MahasiswaDAO mahasiswaDAO = new MahasiswaDAO();
    private AbsensiDAO absensiDAO = new AbsensiDAO();

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
            String nama = mahasiswaDAO.getNamaByNim(nim);
            String pesan = "Selamat datang, " + nama + "!";

            playTypingAnimation(pesan);

            AudioClip clip = new AudioClip(getClass().getResource("/sounds/selamat_datang.wav").toString());
            clip.play();

            statusLabel.setText("Absensi berhasil!");
            nimField.clear();
        } else {
            statusLabel.setText("Gagal absensi.");
        }
    }

    private void playTypingAnimation(String fullText) {
        welcomeLabel.setText("");
        final int[] index = {0};

        Timeline timeline = new Timeline();
        timeline.setCycleCount(fullText.length());

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(35), event -> {
            if (index[0] < fullText.length()) {
                welcomeLabel.setText(welcomeLabel.getText() + fullText.charAt(index[0]));
                index[0]++;
            }
        }));

        timeline.play();
    }

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

    @FXML
    public void initialize() {
        Image logo = new Image(getClass().getResource("/images/Logo_utb.png").toExternalForm());
        logoView.setImage(logo);
        welcomeLabel.setText("");
        loginAdminButton.setVisible(false); // Sembunyikan saat awal

        Platform.runLater(() -> {
            Scene scene = logoView.getScene();
            scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                if (event.isControlDown() && event.getCode() == KeyCode.A) {
                    loginAdminButton.setVisible(true);
                }
            });
        });
    }
}
