package com.mycompany.perpus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/absensi.fxml"));
        AnchorPane root = loader.load();

        Scene scene = new Scene(root);

        // Tambahkan CSS eksternal
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        // Set judul jendela
        primaryStage.setTitle("Aplikasi Absensi Perpustakaan");

        // ➤ Agar jendela tampil penuh saat awal (masih ada titlebar dan taskbar)
        primaryStage.setMaximized(true);

        // ➤ Jika kamu ingin fullscreen total (tanpa titlebar/taskbar):
        // primaryStage.setFullScreen(true);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
