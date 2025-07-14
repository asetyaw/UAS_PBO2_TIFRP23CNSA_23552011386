/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpus.model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mahasiswa {
    private final StringProperty nim;
    private final StringProperty nama;
    private final StringProperty prodi;
    private final StringProperty angkatan;

    public Mahasiswa(String nim, String nama, String prodi, String angkatan) {
        this.nim = new SimpleStringProperty(nim);
        this.nama = new SimpleStringProperty(nama);
        this.prodi = new SimpleStringProperty(prodi);
        this.angkatan = new SimpleStringProperty(angkatan);
    }

    public StringProperty nimProperty() { return nim; }
    public StringProperty namaProperty() { return nama; }
    public StringProperty prodiProperty() { return prodi; }
    public StringProperty angkatanProperty() { return angkatan; }

    public String getNim() { return nim.get(); }
    public String getNama() { return nama.get(); }
    public String getProdi() { return prodi.get(); }
    public String getAngkatan() { return angkatan.get(); }

    public void setNim(String value) { nim.set(value); }
    public void setNama(String value) { nama.set(value); }
    public void setProdi(String value) { prodi.set(value); }
    public void setAngkatan(String value) { angkatan.set(value); }
}
