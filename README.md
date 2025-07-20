# 📚 Aplikasi Absensi Perpustakaan Berbasis JavaFX

**Aplikasi Absensi Perpustakaan** adalah sistem berbasis JavaFX yang digunakan untuk mencatat kehadiran mahasiswa secara efisien. Didesain dengan prinsip OOP (Object-Oriented Programming), aplikasi ini mendukung autentikasi admin, pendaftaran mahasiswa baru, serta ekspor data absensi ke CSV untuk keperluan pelaporan.

![JavaFX Banner](https://img.shields.io/badge/JavaFX-UI-orange)
![MySQL Badge](https://img.shields.io/badge/MySQL-Database-blue)
![Maven Badge](https://img.shields.io/badge/Maven-Build%20Tool-red)
![License](https://img.shields.io/badge/License-MIT-green)

---

# Final Proyek Pemrograman Berorientasi Obyek 2
<ul>
  <li>Mata Kuliah: Pemrograman Berorientasi Obyek 2</li>
  <li>Dosen Pengampu: <a href="https://github.com/Muhammad-Ikhwan-Fathulloh">Muhammad Ikhwan Fathulloh</a></li>
</ul>

---

## Profil

<ul>
  <li>Nama: {Aa Setya Wibawa}</li>
  <li>NIM: {23552011386}</li>
  <li>Studi Kasus: {Aplikasi Absensi Perpustakaan Berbasis JavaFX}</li>
</ul>

---

## Judul Studi Kasus
<p>Aplikasi Absensi Perpustakaan di Universitas Teknologi Bandung</p>


---

## Penjelasan Studi Kasus

<p>
  Perpustakaan di Universitas Teknologi Bandung masih menggunakan sistem manual untuk mencatat kehadiran mahasiswa yang berkunjung. Proses ini dilakukan dengan menuliskan NIM di buku absensi, yang sangat rentan terhadap kesalahan pencatatan, duplikasi data, serta tidak efisien dalam pembuatan laporan kehadiran.
</p>
<p>
  Studi kasus ini berfokus pada pengembangan dan implementasi **Aplikasi Absensi Perpustakaan** berbasis GUI menggunakan JavaFX dan NetBeans IDE. Aplikasi ini dirancang untuk menggantikan sistem pencatatan manual dengan antarmuka modern, suara notifikasi selamat datang, serta kemampuan ekspor data ke CSV. Tujuan utamanya adalah meningkatkan efisiensi pencatatan, mempermudah admin dalam melihat rekap data, dan memberikan pengalaman digital yang ramah bagi pengguna (mahasiswa).
</p>

---

## Penjelasan 4 Pilar OOP dalam Studi Kasus
1. **Enkapsulasi**  
   Setiap entitas seperti `Mahasiswa`, `Absensi`, dan `Admin` diwakili oleh kelas dengan atribut dan metode yang dibungkus secara rapi. Data sensitif seperti password admin dienkapsulasi agar tidak diakses langsung.
   ```java
   public class Mahasiswa {
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
  
3. **Abstraksi**  
   Implementasi koneksi database (DAO) disembunyikan dari komponen GUI, hanya menyediakan metode seperti `simpanAbsensi()`, `getMahasiswaByNIM()`, dll.
      ```java
      public class MahasiswaDAO {

    public boolean exists(String nim) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM mahasiswa WHERE nim=?")) {
            stmt.setString(1, nim);
            return stmt.executeQuery().next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
      }
    }


5. **Inheritance (Pewarisan)**  
   Kelas turunan dapat dibuat dari kelas dasar jika dibutuhkan — misalnya jika ingin mengembangkan role pengguna lebih lanjut (admin, operator, pustakawan).
   ```java
   public class App extends Application


7. **Polimorfisme**  
   Penggunaan method overriding di dalam controller yang menangani aksi-aksi pada tombol atau event tertentu, tergantung konteks UI yang aktif.
      ```java
      @Override
      public void start(Stage primaryStage) throws Exception


---

## Demo Proyek

<ul>
  <li>Github: <a href="https://github.com/asetyaw/UAS_PBO2_TIFRP23CNSA_23552011386">Github</a></li>
  <li>Youtube: <a href="https://youtu.be/E1q_mcis1yw">Youtube</a></li>
</ul>

---

## 🚀 Cara Menjalankan

### Prasyarat:
- Java JDK 17+ (rekomendasi JDK 21)
- Maven
- MySQL Server
- NetBeans atau IDE lain yang mendukung JavaFX

### Setup Database
```sq
CREATE DATABASE perpustakaan;

USE perpustakaan;

CREATE TABLE `mahasiswa` (
  `nim` VARCHAR(20) NOT NULL,
  `nama` VARCHAR(100) DEFAULT NULL,
  `prodi` VARCHAR(100) DEFAULT NULL,
  `angkatan` VARCHAR(10) DEFAULT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`nim`)
);

CREATE TABLE `absensi` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nim` VARCHAR(20) DEFAULT NULL,
  `waktu` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`nim`) REFERENCES `mahasiswa`(`nim`)
);

CREATE TABLE `admin` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
);


