/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpus.dialog;

import com.mycompany.perpus.model.Mahasiswa;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class MahasiswaFormDialog {

    private TextField nimField = new TextField();
    private TextField namaField = new TextField();
    private TextField prodiField = new TextField();
    private TextField angkatanField = new TextField();

    public Dialog<Mahasiswa> getDialog(Mahasiswa existing) {
        Dialog<Mahasiswa> dialog = new Dialog<>();
        dialog.setTitle(existing == null ? "Tambah Mahasiswa" : "Edit Mahasiswa");

        ButtonType okButton = new ButtonType("Simpan", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        nimField.setPromptText("NIM");
        namaField.setPromptText("Nama");
        prodiField.setPromptText("Prodi");
        angkatanField.setPromptText("Angkatan");

        grid.add(new Label("NIM:"), 0, 0);
        grid.add(nimField, 1, 0);
        grid.add(new Label("Nama:"), 0, 1);
        grid.add(namaField, 1, 1);
        grid.add(new Label("Prodi:"), 0, 2);
        grid.add(prodiField, 1, 2);
        grid.add(new Label("Angkatan:"), 0, 3);
        grid.add(angkatanField, 1, 3);

        if (existing != null) {
            nimField.setText(existing.getNim());
            nimField.setDisable(true); // NIM tidak bisa diubah saat edit
            namaField.setText(existing.getNama());
            prodiField.setText(existing.getProdi());
            angkatanField.setText(existing.getAngkatan());
        }

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButton) {
                return new Mahasiswa(
                    nimField.getText().trim(),
                    namaField.getText().trim(),
                    prodiField.getText().trim(),
                    angkatanField.getText().trim()
                );
            }
            return null;
        });

        return dialog;
    }
}
