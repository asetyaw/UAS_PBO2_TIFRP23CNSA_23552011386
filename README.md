# 📚 Aplikasi Absensi Perpustakaan Berbasis JavaFX

**Aplikasi Absensi Perpustakaan** adalah sistem berbasis JavaFX yang digunakan untuk mencatat kehadiran mahasiswa secara efisien. Didesain dengan prinsip OOP (Object-Oriented Programming), aplikasi ini mendukung autentikasi admin, pendaftaran mahasiswa baru, serta ekspor data absensi ke CSV untuk keperluan pelaporan.

![JavaFX Banner](https://img.shields.io/badge/JavaFX-UI-orange)
![MySQL Badge](https://img.shields.io/badge/MySQL-Database-blue)
![Maven Badge](https://img.shields.io/badge/Maven-Build%20Tool-red)
![License](https://img.shields.io/badge/License-MIT-green)

---

## ✨ Fitur Unggulan

✅ Absensi Mahasiswa berdasarkan NIM  
✅ Suara “Selamat Datang” setelah absensi  
✅ Pendaftaran Mahasiswa Baru  
✅ Login untuk Admin  
✅ Dashboard Admin  
✅ Ekspor Data Absensi ke Format **CSV**  
✅ Interface rapi dengan **FXML + CSS**  
✅ Terhubung ke **MySQL Database**

---


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


