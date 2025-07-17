/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpus.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import com.mycompany.perpus.dao.AdminDAO;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.scene.media.AudioClip;


public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    private AdminDAO adminDAO = new AdminDAO();

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (adminDAO.authenticate(username, password)) {
            try {
                String welcomeMessage = "Selamat datang, " + username + "!";
                statusLabel.setText(welcomeMessage);

                // Putar suara selamat datang
                //AudioClip audioClip = new AudioClip(getClass().getResource("/sounds/selamat_datang.wav").toExternalForm());
                //audioClip.play();

                // Load dashboard setelah sedikit delay agar suara sempat terdengar
                PauseTransition delay = new PauseTransition(Duration.seconds(2)); // delay 2 detik
                delay.setOnFinished(e -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin_dashboard.fxml"));
                        Parent root = loader.load();

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Dashboard Admin");
                        stage.show();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        statusLabel.setText("Gagal membuka dashboard.");
                    }
                });
                delay.play();

            } catch (Exception e) {
                e.printStackTrace();
                statusLabel.setText("Gagal menampilkan ucapan selamat datang.");
            }
        } else {
            statusLabel.setText("Username atau password salah.");
        }
    }
}