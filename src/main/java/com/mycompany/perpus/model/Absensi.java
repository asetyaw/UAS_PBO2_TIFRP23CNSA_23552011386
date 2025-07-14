/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpus.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Absensi {
    private StringProperty nim;
    private StringProperty nama;
    private StringProperty waktu;

    public Absensi(String nim, String nama, String waktu) {
        this.nim = new SimpleStringProperty(nim);
        this.nama = new SimpleStringProperty(nama);
        this.waktu = new SimpleStringProperty(waktu);
    }

    public StringProperty nimProperty() { return nim; }
    public StringProperty namaProperty() { return nama; }
    public StringProperty waktuProperty() { return waktu; }

    public String getNim() { return nim.get(); }
    public String getNama() { return nama.get(); }
    public String getWaktu() { return waktu.get(); }
}
