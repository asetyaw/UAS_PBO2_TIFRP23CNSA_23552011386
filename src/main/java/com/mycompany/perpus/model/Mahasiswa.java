/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpus.model;
public class Mahasiswa {
    private String nim;
    private String nama;
    private String prodi;
    private String angkatan;

    public Mahasiswa(String nim, String nama, String prodi, String angkatan) {
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
        this.angkatan = angkatan;
    }
    // Getter & setter
    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getProdi() { return prodi; }
    public void setProdi(String prodi) { this.prodi = prodi; }

    public String getAngkatan() { return angkatan; }
    public void setAngkatan(String angkatan) { this.angkatan = angkatan; }
}