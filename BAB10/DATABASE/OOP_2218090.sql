-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 18, 2023 at 12:23 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `OOP_2218090`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_mahasiswa`
--

CREATE TABLE `tb_mahasiswa` (
  `id` int(11) NOT NULL,
  `nim` varchar(7) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jk` varchar(1) NOT NULL,
  `prodi` varchar(50) NOT NULL,
  `th_angkatan` varchar(4) NOT NULL,
  `alamat` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_mahasiswa`
--

INSERT INTO `tb_mahasiswa` (`id`, `nim`, `nama`, `jk`, `prodi`, `th_angkatan`, `alamat`) VALUES
(1, '2218090', 'Muhammad Zulfan', 'L', 'Teknik Informatika', '2022', 'Jl.Suhat'),
(2, '2218091', 'Malik Abdullah', 'L', 'Teknik Kimim', '2022', 'Jl.Bojonegoro, Bondowoso'),
(3, '2218092', 'Bintang Brillian', 'L', 'Teknik Industri', '2020', 'JL.Dampit'),
(4, '1818087', 'Abbashalom Radja', 'L', 'Teknik Ancaman', '2018', 'Bulungan');

-- --------------------------------------------------------

--
-- Table structure for table `tb_matkul`
--

CREATE TABLE `tb_matkul` (
  `id` int(11) NOT NULL,
  `kode_mk` varchar(10) NOT NULL,
  `matakuliah` varchar(100) NOT NULL,
  `dosenpengajar` varchar(100) NOT NULL,
  `jmlsks` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_matkul`
--

INSERT INTO `tb_matkul` (`id`, `kode_mk`, `matakuliah`, `dosenpengajar`, `jmlsks`) VALUES
(1, 'IF322', 'OOP', 'Yosep Agus P', 4),
(3, 'IF224', 'PCD', 'Nur Laily ', 4);

-- --------------------------------------------------------

--
-- Table structure for table `tb_nilai`
--

CREATE TABLE `tb_nilai` (
  `id` int(11) NOT NULL,
  `Nim` varchar(7) NOT NULL,
  `kd_mk` varchar(6) NOT NULL,
  `NP1` int(3) NOT NULL,
  `NP2` int(3) NOT NULL,
  `UTS` int(3) NOT NULL,
  `NP3` int(3) NOT NULL,
  `prak` int(3) NOT NULL,
  `UAS` int(3) NOT NULL,
  `NA` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_RentalMotor`
--

CREATE TABLE `tb_RentalMotor` (
  `ID` int(2) NOT NULL,
  `Nama` varchar(25) DEFAULT NULL,
  `Merk` varchar(25) DEFAULT NULL,
  `Sewa` int(5) DEFAULT NULL,
  `Harga Sewa/Hari` int(10) DEFAULT NULL,
  `Total Pembayaran` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_RentalMotor`
--

INSERT INTO `tb_RentalMotor` (`ID`, `Nama`, `Merk`, `Sewa`, `Harga Sewa/Hari`, `Total Pembayaran`) VALUES
(1, 'Zulfan', 'Nmax', 5, 40000, 200000),
(2, 'Alek', 'Vario', 3, 35000, 105000);

-- --------------------------------------------------------

--
-- Table structure for table `tb_RentalMotor1`
--

CREATE TABLE `tb_RentalMotor1` (
  `ID` int(11) NOT NULL,
  `Nama` varchar(255) DEFAULT NULL,
  `Merk` varchar(255) DEFAULT NULL,
  `Sewa` varchar(255) DEFAULT NULL,
  `Harga` varchar(255) DEFAULT NULL,
  `Total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_RentalMotor1`
--

INSERT INTO `tb_RentalMotor1` (`ID`, `Nama`, `Merk`, `Sewa`, `Harga`, `Total`) VALUES
(1, 'Zulfan', 'Nmax', '5', '40000', 200000),
(2, 'Alek', 'Vario', '3', '35000', 105000);

-- --------------------------------------------------------

--
-- Table structure for table `tb_RentalMotor2`
--

CREATE TABLE `tb_RentalMotor2` (
  `ID` int(11) NOT NULL,
  `Nama` varchar(255) DEFAULT NULL,
  `Merk` varchar(255) DEFAULT NULL,
  `Sewa` varchar(255) DEFAULT NULL,
  `Harga` varchar(255) DEFAULT NULL,
  `KodePromo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_RentalMotor2`
--

INSERT INTO `tb_RentalMotor2` (`ID`, `Nama`, `Merk`, `Sewa`, `Harga`, `KodePromo`) VALUES
(1, 'Zulfan', 'Nmax', '4', '45000', 'N0260'),
(3, 'Alek', 'Vario', '5', '40000', 'V0320'),
(7, 'Abdi', 'Vario', '4', '40000', 'V0320');

-- --------------------------------------------------------

--
-- Table structure for table `tb_RentalMotor_1`
--

CREATE TABLE `tb_RentalMotor_1` (
  `ID` int(1) NOT NULL,
  `Nama` varchar(25) DEFAULT NULL,
  `Merk` varchar(15) DEFAULT NULL,
  `Sewa` int(5) DEFAULT NULL,
  `Harga` varchar(15) DEFAULT NULL,
  `KodePromo` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_RentalMotor_1`
--

INSERT INTO `tb_RentalMotor_1` (`ID`, `Nama`, `Merk`, `Sewa`, `Harga`, `KodePromo`) VALUES
(1, 'Zulfan', 'Vario', 4, '35000', 'V0320'),
(2, 'Alek', 'Scoopy', 5, '30000', 'S0120');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_mahasiswa`
--
ALTER TABLE `tb_mahasiswa`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_matkul`
--
ALTER TABLE `tb_matkul`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_nilai`
--
ALTER TABLE `tb_nilai`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_RentalMotor`
--
ALTER TABLE `tb_RentalMotor`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tb_RentalMotor1`
--
ALTER TABLE `tb_RentalMotor1`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tb_RentalMotor2`
--
ALTER TABLE `tb_RentalMotor2`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tb_RentalMotor_1`
--
ALTER TABLE `tb_RentalMotor_1`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_mahasiswa`
--
ALTER TABLE `tb_mahasiswa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tb_matkul`
--
ALTER TABLE `tb_matkul`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tb_nilai`
--
ALTER TABLE `tb_nilai`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_RentalMotor1`
--
ALTER TABLE `tb_RentalMotor1`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tb_RentalMotor2`
--
ALTER TABLE `tb_RentalMotor2`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
