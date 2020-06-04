-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 04 Jun 2020 pada 08.17
-- Versi server: 10.4.11-MariaDB
-- Versi PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restoran`
--

DELIMITER $$
--
-- Prosedur
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_menu` (`nmmn` VARCHAR(100), `hrg` INT(4))  BEGIN
INSERT INTO menu (namamenu, harga)
VALUES (nmmn, hrg);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `add_pelanggan` (IN `nmpl` VARCHAR(30), IN `jk` VARCHAR(20), IN `hp` VARCHAR(13), IN `alamt` VARCHAR(30))  Begin
insert into pelanggan (namapelanggan, jeniskelamin, nohp, alamat)
values (nmpl, jk, hp, alamt);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `count_pesanan` (OUT `c` VARCHAR(30))  begin
select count(*) from v_pesanan;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `parse_pelanggan` (IN `p` VARCHAR(45), OUT `res` VARCHAR(45))  SELECT pelanggan.idpelanggan INTO res FROM pelanggan WHERE pelanggan.namapelanggan = p$$

--
-- Fungsi
--
CREATE DEFINER=`root`@`localhost` FUNCTION `status_tx` (`stts` TINYINT) RETURNS INT(11) begin
declare stats int;
select count(*) as tx_status into stats from transaksi
where status = stts;
return stats;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `menu`
--

CREATE TABLE `menu` (
  `idmenu` int(11) NOT NULL,
  `namamenu` varchar(100) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `menu`
--

INSERT INTO `menu` (`idmenu`, `namamenu`, `harga`) VALUES
(2, 'Jus jeruk', 3000),
(4, 'Ayam goreng', 10000),
(5, 'Es kosong', 1000),
(7, 'menu bau', 1233),
(9, 'es hampir berisi', 5000),
(10, 'jus mangga', 6000),
(12, 'ayam bakar', 8000),
(13, 'jus pokat', 5000),
(14, 'ayam 3', 12000),
(41, 'wewe', 30000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pelanggan`
--

CREATE TABLE `pelanggan` (
  `idpelanggan` int(11) NOT NULL,
  `namapelanggan` varchar(80) DEFAULT NULL,
  `jeniskelamin` varchar(20) DEFAULT NULL,
  `nohp` varchar(13) DEFAULT NULL,
  `alamat` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `pelanggan`
--

INSERT INTO `pelanggan` (`idpelanggan`, `namapelanggan`, `jeniskelamin`, `nohp`, `alamat`) VALUES
(1, 'Umar Hadi Siswanto', 'Laki - Laki', '082254243096', 'Penajam Paser Utara'),
(2, 'Bagus Supriyanto', 'Laki - Laki', '08211133121', 'Riau'),
(3, 'Deni Prasetyo', 'Laki - Laki', '0808808012', 'Sleman'),
(4, 'Andi Apriansah', 'Laki - Laki', '0881231441', 'Lampung'),
(28, 'hahahhaa', 'Laki - Laki', '51341', 'aaw'),
(29, 'marr', 'Perempuan', '02138921', 'sssaaw');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pesanan`
--

CREATE TABLE `pesanan` (
  `idpesanan` int(11) NOT NULL,
  `kodepesanan` varchar(15) DEFAULT NULL,
  `menu_idmenu` int(11) NOT NULL,
  `pelanggan_idpelanggan` int(11) NOT NULL,
  `user_iduser` int(11) NOT NULL,
  `jumlah` tinyint(1) NOT NULL,
  `dibuat` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `pesanan`
--

INSERT INTO `pesanan` (`idpesanan`, `kodepesanan`, `menu_idmenu`, `pelanggan_idpelanggan`, `user_iduser`, `jumlah`, `dibuat`) VALUES
(8, 'AACDDEGGG2FD5F', 2, 1, 2, 3, '2020-04-20'),
(10, 'ABBAEEGABG2D5F', 4, 2, 2, 1, '2020-04-20'),
(11, 'ABBAEEGABG2D5F', 5, 2, 2, 3, '2020-04-20'),
(12, 'ABBBBFEFEEFE42', 9, 3, 2, 1, '2020-04-20'),
(13, 'ABBBBFEFEEFE42', 2, 3, 2, 1, '2020-04-20'),
(14, 'AACAAEBFFDCCE3', 13, 4, 2, 1, '2020-04-20'),
(15, 'AACAAEBFFDCCE3', 7, 4, 2, 1, '2020-04-20');

--
-- Trigger `pesanan`
--
DELIMITER $$
CREATE TRIGGER `before_delete_pesanan` BEFORE DELETE ON `pesanan` FOR EACH ROW UPDATE transaksi SET
transaksi.total = transaksi.total - (SELECT menu.harga * old.jumlah FROM menu WHERE menu.idmenu = old.menu_idmenu)
WHERE transaksi.idtransaksi = old.kodepesanan
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insert_transaksi` AFTER INSERT ON `pesanan` FOR EACH ROW INSERT INTO transaksi SET
transaksi.idtransaksi = new.kodepesanan,
transaksi.total = (SELECT menu.harga * new.jumlah FROM menu WHERE menu.idmenu = new.menu_idmenu)

ON duplicate KEY UPDATE transaksi.total = transaksi.total + (SELECT menu.harga * new.jumlah FROM menu WHERE menu.idmenu = new.menu_idmenu)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `idtransaksi` varchar(15) NOT NULL,
  `total` int(11) DEFAULT NULL,
  `bayar` int(11) DEFAULT 0,
  `kembalian` int(11) NOT NULL DEFAULT 0,
  `status` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`idtransaksi`, `total`, `bayar`, `kembalian`, `status`) VALUES
('AACAAEBFFDCCE3', 6233, 10000, 3767, 1),
('AACDDEGGG2FD5F', 9000, 0, 0, 0),
('ABBAEEGABG2D5F', 13000, 0, 0, 0),
('ABBBBFEFEEFE42', 8000, 0, 0, 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `iduser` int(11) NOT NULL,
  `namauser` varchar(80) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `akses` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`iduser`, `namauser`, `password`, `akses`) VALUES
(2, 'admin', '67862890a9ebe89eefdb55671cae33f4', 1),
(3, 'naruto', '67862890a9ebe89eefdb55671cae33f4', 2),
(4, 'sasuke', '67862890a9ebe89eefdb55671cae33f4', 3),
(5, 'owner', '67862890a9ebe89eefdb55671cae33f4', 4),
(6, 'umar', '67862890a9ebe89eefdb55671cae33f4', 1),
(8, 'mar', '1', 1);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `v_pesanan`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `v_pesanan` (
`idpesanan` int(11)
,`kodepesanan` varchar(15)
,`namapelanggan` varchar(80)
,`namamenu` varchar(100)
,`jumlah` tinyint(1)
,`total` bigint(14)
,`dibuat` date
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `v_transaksi`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `v_transaksi` (
`idtransaksi` varchar(15)
,`total` int(11)
,`bayar` int(11)
,`kembalian` int(11)
,`status` tinyint(1)
);

-- --------------------------------------------------------

--
-- Struktur untuk view `v_pesanan`
--
DROP TABLE IF EXISTS `v_pesanan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_pesanan`  AS  select `pesanan`.`idpesanan` AS `idpesanan`,`pesanan`.`kodepesanan` AS `kodepesanan`,`pelanggan`.`namapelanggan` AS `namapelanggan`,`menu`.`namamenu` AS `namamenu`,`pesanan`.`jumlah` AS `jumlah`,(select `menu`.`harga` * `pesanan`.`jumlah` from `menu` where `menu`.`idmenu` = `pesanan`.`menu_idmenu`) AS `total`,`pesanan`.`dibuat` AS `dibuat` from ((`pesanan` join `pelanggan` on(`pelanggan`.`idpelanggan` = `pesanan`.`pelanggan_idpelanggan`)) join `menu` on(`menu`.`idmenu` = `pesanan`.`menu_idmenu`)) ;

-- --------------------------------------------------------

--
-- Struktur untuk view `v_transaksi`
--
DROP TABLE IF EXISTS `v_transaksi`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_transaksi`  AS  select `transaksi`.`idtransaksi` AS `idtransaksi`,`transaksi`.`total` AS `total`,`transaksi`.`bayar` AS `bayar`,`transaksi`.`kembalian` AS `kembalian`,`transaksi`.`status` AS `status` from `transaksi` where `transaksi`.`status` = 0 ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`idmenu`);

--
-- Indeks untuk tabel `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`idpelanggan`);

--
-- Indeks untuk tabel `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`idpesanan`),
  ADD KEY `fk_pesanan_menu1_idx` (`menu_idmenu`),
  ADD KEY `fk_pesanan_pelanggan1_idx` (`pelanggan_idpelanggan`),
  ADD KEY `user_iduser` (`user_iduser`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`idtransaksi`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`iduser`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `menu`
--
ALTER TABLE `menu`
  MODIFY `idmenu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT untuk tabel `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `idpelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT untuk tabel `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `idpesanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `pesanan`
--
ALTER TABLE `pesanan`
  ADD CONSTRAINT `fk_pesanan_menu1` FOREIGN KEY (`menu_idmenu`) REFERENCES `menu` (`idmenu`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_pesanan_pelanggan1` FOREIGN KEY (`pelanggan_idpelanggan`) REFERENCES `pelanggan` (`idpelanggan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pesanan_ibfk_1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE;

DELIMITER $$
--
-- Event
--
CREATE DEFINER=`root`@`localhost` EVENT `delete_transaksi` ON SCHEDULE EVERY 1 SECOND STARTS '2019-02-19 10:16:15' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM transaksi WHERE transaksi.total <= 0$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
