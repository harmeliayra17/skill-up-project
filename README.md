# SkillUp
SkillUp adalah aplikasi yang dirancang untuk membantu orang-orang yang mengalami kesulitan dalam menemukan pekerjaan yang sesuai dengan kepribadian dan keterampilan mereka serta sering merasa bingung mengenai arah karir yang ingin diambil dan tidak menyadari potensi penuh yang dimiliki. 
## Group Name : SHELINA 
Nama Anggota: 
1. Harmelia Yuli Rahmatika. A - H071231079
2. Nancy Jiwono - H071231004
3. Sheryl Anastasya Palambang - H071231059
### Tema yang dipilih : Career Development 
### Nama Pendamping : Muh. Adnan Putra Amiruddin 
### Tim Juri : 
1. Muh. Resky Fadil
2. Ahmad Hamsa Pattureni
## Executive Summary 
Dalam era digital, mencari pekerjaan yang sesuai dengan kepribadian dan keterampilan bisa menjadi tantangan. Banyak orang merasa bingung atau tidak yakin dengan arah karir yang ingin diambil, serta tidak menyadari potensi penuh yang dimiliki. Informasi tentang pekerjaan yang cocok pun sering sulit ditemukan. Untuk membantu mengatasi masalah ini, aplikasi Skill Up hadir dengan fitur tes MyersBriggs Type Indicator (MBTI) yang membantu pengguna memahami tipe kepribadian mereka, sehingga lebih mudah menemukan pekerjaan yang sesuai.

Aplikasi Skill Up dirancang untuk memberdayakan pengguna dengan menyediakan artikel pengembangan karir, tes MBTI dengan penjelasan rinci dan rekomendasi pekerjaan yang sesuai dengan kepribadian, serta daftar webinar dan pelatihan untuk meningkatkan keterampilan dan pengetahuan pengguna. Dengan fitur-fitur ini, Skill Up membantu pengguna memahami diri mereka lebih baik, menemukan pekerjaan yang cocok, dan berkembang secara profesional.
## Fitur Aplikasi 
### A. General
1. Splash Screen
Pada saat aplikasi diluncurkan, layar splash akan muncul selama 3 detik. Lalu terdapat pilihan login as admin atau login as user.
2. Login User/Admin
Di scene login user/admin akan diminta untuk memasukkan email dan password, jika login berhasil user/admin akan diarahkan ke homepage masing-masing.
3. Register User/Admin
Di scene register, user/admin akan diminta untuk mengisi nama,  email dan password, lalu user/admin akan diarahkan ke scene login kembali.
### B. User
1. Terdapat fitur HomePage dimana user ditampilkan beberapa fitur utama seperti tes MBTI, daftar artikel, webinar dan pelatihan, serta profil pengguna. Halaman ini dirancang untuk memudahkan navigasi dan memberikan gambaran umum tentang apa yang tersedia dalam aplikasi.
2. Terdapat fitur tes mbti di mana user dapat mengikuti tes yang terdiri dari 36 pertanyaan. Setelah menyelesaikan tes, hasil tesakan muncul. Hasil tes mencakup penjelasan mengenai kepribadian pengguna serta rekomendasi pekerjaan yang sesuai dengan tipe kepribadian mereka.
3. Terdapat fitur daftar artikel, ketika ditekan, akan langsung terbuka di browser. Fitur ini dirancang untuk memberikan rekomendasi kegiatan atau informasi yang bermanfaat untuk meningkatkan keterampilan dan pengetahuan pengguna.
4. Terdapat fitur webinar dan pelatihan, di dalam fitur ini terdapat deskripsi dan poster mengenai event webinar yang tersedia.Juga terdapat tombol ”Daftar” yang jika ditekan, user akan langsung diarahkan ke halaman pendaftaran event yang diminati.
5. Terdapat Fitur profil menampilkan informasi detail tentang pengguna, termasuk nama, email, umur, nomor telepon, dan hasil tes MBTI. Fitur ini juga memungkinkan user untuk mengedit detail profilnya, namun tidak termasuk hasil tes mbti.
### C. Admin 
1. Homepage pada untuk admin memiliki tampilan yang sedikit berbeda dibandingkan dengan user. Pada homepage admin terdapat fitur-fitur yang memungkinkan admin mengakses ke fitur manajemen.
2. Pada Homepage, terdapat fitur daftar-daftar webinar daftardaftar artikel, dengan fitur tersebut admin dapat melihat daftar semua event webinar dan artikel-artikel yang telah ditambahkan untuk memudahkan admin mengelola event webinar dan artikelyang ada.
3. Admin memiliki akses ke tombol tambah webinar, di mana mereka bisa menambahkan poster, nama event webinar, dan deskripsinya. Fitur ini juga memungkinkan admin untuk mengedit dan menghapus webinar yang sudah ada.
4. Admin juga memiliki fitur untuk menambah, mengedit dan menghapus artikel. Dalam fitur tambah artikel, admin bisa menambahkan gambar, nama artikel, juga link.
## Penjelasan Prinsip OOP : 
### A. Config dan Controllers 
1. Dalam folder Config terdapat file DbConfig.java yang memiliki 4 Attribute protected, 1 attribute private yang bersifat final dan 1 method. (Encapsulation)
2. Dalam folder Controllers terdapat 3 file, yaitu UserController.java, WebinarController.java, dan ArticleController.java. Semua class tersebut meng-extends class DbConfig. (Inheritence)
### B. Models 
1. Dalam Folder Models terdapat 4 file, yaitu Model.java, User.java, Webinar.java, Article.java dan 1 folder mbtiPackage. class Model menjadi kelas induk untuk keempat file tersebut, di dalam classModel terdapat 1 attribute protected, constructor beserta settergetternya. Lalu ada class user, Article, dan Webinar, masingmasing kelas meng-extends class Model, di dalam kelas-kelas tersebut terdapat attribute-attribute private, 2 constructor, dan setter getter (Encapsulation dan Inhertance)
2. Terdapat folder mbtiPackage yang di dalamnya terdapat file MBTI.java, ExtraVsIntro.java, SenseVsIntu.java, ThinkVsFeel.java, JudgeVsPercieve.java. Class MBTI adalah kelas abstract yang memiliki 4 attribute private, dan 1 method abstract. Keempat file lainnya meng-extends class MBTI, masing-masing kelas memiliki 2 attribute private, 1 constructor, 3 method yang salah satunya merupakan method override dari kelas MBTI. (Encapsulation dan Abstraction)
### C. Scenes 
Pada bagian scene, untuk penerapan prinsip OOP terdapat pada folder MbtiScene, di dalamnya terdapat MbtiTest1Scene.java, MbtiTest2Scene.java, MbtiTest3Scene.java, MbtiTest4Scene.java, dan MbtiResultScene.java. Tes MBTI terbagi dalam 4 scene yang masing-masing memiliki 9 pertanyaan. Pembagian scene tersebut adalah sebagai berikut:
  1) MbtiTest1Scene: Pertanyaan mengenai extroverted vs introverted.
  2) MbtiTest2Scene: Pertanyaan mengenai sensing vs intuitive.
  3) MbtiTest3Scene: Pertanyaan mengenai thinking vs feeling.
  4) MbtiTest4Scene: Pertanyaan mengenai judging vs perceiving.
Untuk MbtiResultScene akan memperlihatkan hasil dari tes, di mana terdapat deskripsi dan gambar terkait dengan tipe kepribadian pengguna. Dalam scene mbtiTest terdapat implementasi konsep polymorphism. Dalam konteks ini, class MBTI digunakan sebagai tipe data referensi, kemudian dibuat objek baru dari subclass-nya (ExtraVsIntro, SenseVsIntu, ThinkVsFeel, dan JudgePercieve) yang memungkinkan penggunaan method(mbtiResult()) yang didefinisikan dalam kelas MBTI pada objek subclass-nya. (Polymerphism)
### Mentoring :
- [Muh. Adnan Putra Amiruddin] - [Senin, 13 Mei 2024]
- [Muh. Adnan Putra Amiruddin] - [Rabu, 22 Mei 2024]
- [Muh. Adnan Putra Amiruddin] - [Kamis, 23 Mei 2024]
- [Muh. Adnan Putra Amiruddin] - [Senin, 27 Mei 2024]
- [Muh. Adnan Putra Amiruddin] - [Jumat, 31 Mei 2024]
## Link Repository GitHub Project
https://github.com/harmeliayra17/skill-up-project
## Screenshots
#### Splash Screen 
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/9364409e-a07e-4064-85c9-4f66f73968fe)
#### Scene Landing 
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/0ea37372-3a67-40e5-ace7-5507bbc85cf7)
#### Scene Login 
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/f2fd4ead-22dd-4b5f-a52a-28cacd4808b1)
#### Scene Register 
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/183b3513-42ff-4c11-99f7-4cd808aa1621)
#### Scene Home (User)
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/3c65c264-79de-409f-bf38-bd185c18f9e3)
#### Scene MBTI 
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/3b6b8121-d3de-4bdb-bc69-3783b1172507)

![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/37d19cbd-7c96-48d5-af9e-de237bc8f092)
#### Scene Result Tes MBTI
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/37755456-51be-42fc-8ca0-7dcfb16c7f55)
#### Scene List Webinar 
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/f8d8afb8-f228-4b72-9e96-fce8abb767b0)
#### Scene Detail Webinar 
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/7245baa1-49ed-4b61-b709-db8f0241eee8)
#### Scene Profile (User)
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/a4af29ce-7984-48c7-b407-9483e7660b9a)
#### Scene Home & Scene List Webinar (Admin)
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/17834d43-706e-4379-b587-3e5a279ddc3a)
#### Scene Tambah Webinar 
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/c8fb06a4-58c4-4aa4-a311-cc4d6e56281a)
#### Scene Edit Webinar 
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/bbd5aae0-bfec-4e60-98d5-f0a8c4f8fa59)
#### Scene List Artikel 
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/59321622-3a79-4402-8d53-4b809cd0ade0)
#### Scene Edit Artikel 
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/f5db5efe-5459-462a-be5a-9c7a9e8c85a7)
#### Scene Tambah Artikel 
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/db5c502d-ac91-449b-84ef-aa138d527d3d)
#### Scene Profile (Admin)
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/dffef76c-3657-4e4f-af2c-bdae57205794)
## Pengujian pada Aplikasi 
![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/b531fa08-a62d-4b19-9a1d-b15ae18ef952)

![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/e9bdf8c9-8239-4d78-b2ac-65a72658d238)

![image](https://github.com/harmeliayra17/skill-up-project/assets/144916838/66721ddd-0c81-48e7-bab7-331e881586f6)
