# Sudoku

This repository is a final project (Java GUI) from Object-Oriented Programming Class, Teknik Informatika Universitas Padjadjaran.

[Challenge Guidelines](challenge-guideline.md)

**Projek ini merupakan projek bahasa pemrograman Java yang mengimplementasikan konsep Object-Oriented Programming dan Java GUI menggunakan JavaFX.
Sudoku merupakan puzzle yang berbasis logika dimana pemain dapat meletakkan angka pada kotak yang kosong. Sudoku terdiri dari 9 daerah 3x3 yang tersusun oleh 9 kotak 3x3 yang lebih kecil, dengan total 81 kotak. Tujuannya adalah pemain harus mengisi angka 1 sampai 9 pada kotak yang kosong dimana pada setiap baris, kolom, dan daerah 3x3 tidak ada angka yang berulang.
**

## Credits

| NPM          | Name              |
| ------------ | ----------------- |
| 140810200005 | Alfadli Maulana S |
| 140810200009 | Wafi Fahruzzaman  |
| 140810200011 | Faiq Muhammad     |

## Change log

- **[Sprint Planning](changelog/sprint-planning.md) - 17 Nov**

  - Pencarinan referensi GUI sudoku di Java
  - Diskusi dengan team tentang Planning Keseluruhan

- **[Sprint 1](changelog/sprint-1.md) - (date from 18 Nov until 22 Nov)**

  - Tampilan grid 9x9 dengan subgrid
  - Implementasi dasar PBO
  - Tampilan Tingkat Kesulitan

- **[Sprint 2](changelog/sprint-2.md) - (date from 23 Nov until 29 Nov)**
  - Tampilan Tombol Restart
  - Tampilan String dan Clue
  - Mengacak Angka
- **[Sprint 3](changelog/sprint-3.md) - (date from 30 until 6 Dec)**
  - Set angka yang tidak bisa diganti
  - Cek input benar/salah
  - Highlight grid yang bernilai sama
  - Perbaikan tampilan
  - Menu Bar

## Running The App

TO;DO with steps

## Classes Used

- sudoku.konstanta.GameState => enum untuk kondisi game (NEW, ACTIVE, COMPLETE)
- sudoku.konstanta.Message => berisi pesan jika terjadi error atau game selesai.
- sudoku.konstanta.Rows => enum dengan value TOP, MIDDLE, BOTTOM yang merepresentasikan grid 3x9.
- sudoku.problemdomain.Coordinates => class untuk mencatat posisi setiap value di grid.
- sudoku.problemdomain.IStorage => interface method yang berhubungan dengan storage.
- sudoku.problemdomain.SudokuGame => menyimpan grid dan kondisi game sementara.
- sudoku.userinterface.IUserInterface => interface bagi method yang berhubungan dengan user interface.
- sudoku.userinterface.SudokuTextField => mirip seperti class Coordinates namun khusus untuk text field.
- sudoku.userinterface.UserInterfaceImpl => class yang membuat seluruh tampilan sudoku dengan mengimplementasi IUserInterface.view
- sudoku.userinterface.logic.ControlLogic => class yang mengimplementasi IUserInterface.EventListener
- sudoku.computationlogic.GameGenerator => class untuk mengenerate grid sudoku baru
- sudoku.computationlogic.GameLogic => class untuk memeriksa apakah game sudah selesai atau belum.
- sudoku.computationlogic.SudokuUtilities => class untuk mengoperasikan array 2 dimensi.
- sudoku.persistence.LocalStorage => class yang mengimplementasi IStorage.
- sudoku.buildlogic.SudokuBuildLogic => memiliki method build untuk memulai game baru.
- sudoku.SudokuApps => Class yang memiliki main method, juga method start milik javafx.

UML image here

## Notable Assumption and Design App Details

TO;DO
