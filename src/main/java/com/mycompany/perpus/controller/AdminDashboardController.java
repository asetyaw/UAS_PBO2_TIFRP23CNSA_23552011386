/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpus.controller;

import com.mycompany.perpus.dao.AbsensiDAO;
import com.mycompany.perpus.dao.MahasiswaDAO;
import com.mycompany.perpus.model.Absensi;
import com.mycompany.perpus.model.Mahasiswa;
import com.mycompany.perpus.dialog.MahasiswaFormDialog;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AdminDashboardController {

    @FXML private ComboBox<String> filterCombo;

    @FXML private TableView<Absensi> absensiTable;
    @FXML private TableColumn<Absensi, String> nimColumn;
    @FXML private TableColumn<Absensi, String> namaColumn;
    @FXML private TableColumn<Absensi, String> waktuColumn;

    @FXML private TableView<Mahasiswa> mahasiswaTable;
    @FXML private TextField searchField;
    @FXML private TableColumn<Mahasiswa, String> mhsNimColumn;
    @FXML private TableColumn<Mahasiswa, String> mhsNamaColumn;
    @FXML private TableColumn<Mahasiswa, String> mhsProdiColumn;
    @FXML private TableColumn<Mahasiswa, String> mhsAngkatanColumn;

    private final AbsensiDAO absensiDAO = new AbsensiDAO();
    private final MahasiswaDAO mahasiswaDAO = new MahasiswaDAO();

    @FXML
    private void initialize() {
        // Inisialisasi kolom Absensi
        nimColumn.setCellValueFactory(data -> data.getValue().nimProperty());
        namaColumn.setCellValueFactory(data -> data.getValue().namaProperty());
        waktuColumn.setCellValueFactory(data -> data.getValue().waktuProperty());

        // Inisialisasi kolom Mahasiswa
        mhsNimColumn.setCellValueFactory(data -> data.getValue().nimProperty());
        mhsNamaColumn.setCellValueFactory(data -> data.getValue().namaProperty());
        mhsProdiColumn.setCellValueFactory(data -> data.getValue().prodiProperty());
        mhsAngkatanColumn.setCellValueFactory(data -> data.getValue().angkatanProperty());

        // Tambahkan item filter
        filterCombo.setItems(FXCollections.observableArrayList(
            "Hari Ini",
            "Minggu Ini",
            "Bulan Ini",
            "Semua"
        ));
        filterCombo.getSelectionModel().selectFirst(); // Pilih default "Hari Ini"

        loadMahasiswa();
        handleFilter();
    }

    @FXML
    private void handleFilter() {
        String filter = filterCombo.getValue();
        if (filter == null) return;

        LocalDate now = LocalDate.now();
        List<Absensi> hasil;

        switch (filter) {
            case "Hari Ini":
                hasil = absensiDAO.getAbsensiByDate(now);
                break;
            case "Minggu Ini":
                hasil = absensiDAO.getAbsensiByWeek(now);
                break;
            case "Bulan Ini":
                hasil = absensiDAO.getAbsensiByMonth(now);
                break;
            default:
                hasil = absensiDAO.getAllAbsensi();
                break;
        }

        absensiTable.setItems(FXCollections.observableArrayList(hasil));
    }

    private void loadMahasiswa() {
        List<Mahasiswa> list = mahasiswaDAO.getAll();
        mahasiswaTable.setItems(FXCollections.observableArrayList(list));
    }
    @FXML
    private void handleSearchMahasiswa() {
        String keyword = searchField.getText().toLowerCase();

        List<Mahasiswa> semuaMahasiswa = mahasiswaDAO.getAll(); // ambil semua data
        List<Mahasiswa> hasilFilter = semuaMahasiswa.stream()
            .filter(m -> m.getNim().toLowerCase().contains(keyword) ||
                         m.getNama().toLowerCase().contains(keyword))
            .collect(Collectors.toList());

        mahasiswaTable.setItems(FXCollections.observableArrayList(hasilFilter));
    }
    
    @FXML
    private void handleTambahMahasiswa() {
        MahasiswaFormDialog form = new MahasiswaFormDialog();
        Dialog<Mahasiswa> dialog = form.getDialog(null);

        dialog.showAndWait().ifPresent(mahasiswa -> {
            boolean success = mahasiswaDAO.insert(mahasiswa);
            if (success) {
                loadMahasiswa();
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Mahasiswa berhasil ditambahkan.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Gagal", "Gagal menambahkan mahasiswa (mungkin NIM sudah ada).");
            }
        });
    }


    @FXML
    private void handleEditMahasiswa() {
        Mahasiswa selected = mahasiswaTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Pilih Mahasiswa", "Pilih mahasiswa yang ingin diedit.");
            return;
        }

        MahasiswaFormDialog form = new MahasiswaFormDialog();
        Dialog<Mahasiswa> dialog = form.getDialog(selected);

        dialog.showAndWait().ifPresent(updated -> {
            boolean success = mahasiswaDAO.update(updated);
            if (success) {
                loadMahasiswa();
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data mahasiswa berhasil diubah.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Gagal", "Gagal mengubah data mahasiswa.");
            }
        });
    }
    
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }



    @FXML
    private void handleHapusMahasiswa() {
    Mahasiswa selected = mahasiswaTable.getSelectionModel().getSelectedItem();

    if (selected != null) {
        Alert konfirmasi = new Alert(Alert.AlertType.CONFIRMATION);
        konfirmasi.setTitle("Konfirmasi Hapus");
        konfirmasi.setHeaderText("Hapus Mahasiswa");
        konfirmasi.setContentText("Apakah kamu yakin ingin menghapus mahasiswa dengan NIM: " + selected.getNim() + "?");

        konfirmasi.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                boolean berhasil = mahasiswaDAO.delete(selected.getNim());

                if (berhasil) {
                    mahasiswaTable.getItems().remove(selected);
                    Alert info = new Alert(Alert.AlertType.INFORMATION);
                    info.setTitle("Sukses");
                    info.setHeaderText(null);
                    info.setContentText("Mahasiswa berhasil dihapus.");
                    info.showAndWait();
                } else {
                    Alert gagal = new Alert(Alert.AlertType.ERROR);
                    gagal.setTitle("Gagal");
                    gagal.setHeaderText(null);
                    gagal.setContentText("Gagal menghapus mahasiswa dari database.");
                    gagal.showAndWait();
                }
            }
        });
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Tidak Ada Mahasiswa Dipilih");
        alert.setHeaderText(null);
        alert.setContentText("Silakan pilih mahasiswa yang ingin dihapus.");
        alert.showAndWait();
        }
    }



}
