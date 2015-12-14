-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 14 Gru 2015, 16:19
-- Wersja serwera: 10.1.8-MariaDB
-- Wersja PHP: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `spoldzielnia`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `blok`
--

CREATE TABLE `blok` (
  `id_bloku` int(10) NOT NULL,
  `ulica` varchar(255) NOT NULL,
  `ulica_nr` varchar(10) NOT NULL,
  `miejscowosc` varchar(255) NOT NULL,
  `nazwa_spoldzielni` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `blok`
--

INSERT INTO `blok` (`id_bloku`, `ulica`, `ulica_nr`, `miejscowosc`, `nazwa_spoldzielni`) VALUES
(1, 'Chmieleniec', '1', 'Krakow', 'Stonka'),
(2, 'Pilsudskiego', '18', 'Skawina', 'Stonka'),
(3, 'Sloneczna', '3', 'Krakow', 'Stonka');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `mieszkanie`
--

CREATE TABLE `mieszkanie` (
  `id_mieszkania` int(10) NOT NULL,
  `id_bloku` int(10) NOT NULL,
  `nr_mieszkania` int(3) NOT NULL,
  `pietro` int(3) NOT NULL,
  `powierzchnia` float NOT NULL,
  `ilosc_pokoi` int(2) NOT NULL,
  `piwnica_powierzchnia` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `mieszkanie`
--

INSERT INTO `mieszkanie` (`id_mieszkania`, `id_bloku`, `nr_mieszkania`, `pietro`, `powierzchnia`, `ilosc_pokoi`, `piwnica_powierzchnia`) VALUES
(1, 1, 1, 1, 50, 3, 14),
(2, 1, 2, 1, 66, 2, 22),
(3, 1, 2, 1, 40, 2, 12),
(4, 1, 2, 1, 40, 2, 12),
(5, 1, 3, 2, 44, 2, 12),
(6, 1, 2, 1, 40, 2, 12),
(7, 1, 3, 2, 44, 2, 12),
(8, 1, 4, 2, 40, 2, 12),
(9, 1, 5, 3, 44, 2, 12),
(10, 1, 6, 3, 40, 2, 12),
(11, 2, 2, 1, 50, 3, 9),
(12, 2, 3, 2, 54, 3, 9),
(13, 2, 4, 2, 50, 3, 9),
(14, 2, 5, 3, 54, 3, 9),
(15, 2, 6, 3, 50, 3, 9),
(16, 3, 2, 1, 30, 2, 5),
(17, 3, 3, 2, 34, 2, 5),
(18, 3, 4, 2, 30, 2, 5),
(19, 3, 5, 3, 34, 2, 5),
(20, 3, 6, 3, 30, 2, 5);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `oplaty`
--

CREATE TABLE `oplaty` (
  `id_mieszkania` int(10) NOT NULL,
  `czynsz` float NOT NULL,
  `prad` float NOT NULL,
  `woda` float NOT NULL,
  `gaz` float NOT NULL,
  `remontowe` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `oplaty`
--

INSERT INTO `oplaty` (`id_mieszkania`, `czynsz`, `prad`, `woda`, `gaz`, `remontowe`) VALUES
(1, 500, 122, 88, 52, 23),
(1, 500, 122, 88, 52, 23),
(2, 500, 122, 88, 52, 23),
(3, 500, 122, 88, 52, 23),
(4, 500, 122, 88, 52, 23),
(5, 500, 122, 88, 52, 23),
(6, 500, 122, 88, 52, 23),
(7, 500, 122, 88, 52, 23),
(8, 500, 122, 88, 52, 23),
(9, 500, 122, 88, 52, 23),
(10, 500, 122, 88, 52, 23),
(11, 500, 122, 88, 52, 23),
(12, 500, 122, 88, 52, 23),
(13, 500, 122, 88, 52, 23),
(14, 500, 122, 88, 52, 23),
(15, 500, 122, 88, 52, 23),
(16, 500, 122, 88, 52, 23),
(17, 500, 122, 88, 52, 23),
(18, 500, 122, 88, 52, 23),
(19, 500, 122, 88, 52, 23),
(20, 500, 122, 88, 52, 23);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracownik`
--

CREATE TABLE `pracownik` (
  `id` int(10) NOT NULL,
  `imie` varchar(25) NOT NULL,
  `nazwisko` varchar(25) NOT NULL,
  `pesel` bigint(11) NOT NULL,
  `ulica` varchar(255) NOT NULL,
  `miejscowosc` varchar(25) NOT NULL,
  `stanowisko` varchar(255) NOT NULL,
  `nr_konta` bigint(26) NOT NULL,
  `nr_telefonu` bigint(13) NOT NULL,
  `nazwa_spoldzielni_fk` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `pracownik`
--

INSERT INTO `pracownik` (`id`, `imie`, `nazwisko`, `pesel`, `ulica`, `miejscowosc`, `stanowisko`, `nr_konta`, `nr_telefonu`, `nazwa_spoldzielni_fk`) VALUES
(1, 'Krzysztof', 'Pezi', 93103336358, 'Chmieleniec 1', 'Krakow', 'Prezes', 0, 666777888, 'Stonka'),
(2, 'Mateusz', 'Fajny', 93661236258, 'Warszawska 1', 'Krakow', 'Vice Prezes', 22, 456789123, 'Stonka'),
(6, 'Katarzyna', 'Grzyb', 89141236258, 'Morska 11', 'Krakow', 'Sekretarka', 9223372036854775807, 996789123, 'Stonka'),
(7, 'Dominik', 'Kowalski', 92011236258, 'Komorowskiego 21', 'Krakow', 'Maanager', 12000034000060022, 147258369, 'Stonka');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `remont`
--

CREATE TABLE `remont` (
  `opis` varchar(255) NOT NULL,
  `kwota` float NOT NULL,
  `id_bloku` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `spoldzielnia_mieszkaniowa`
--

CREATE TABLE `spoldzielnia_mieszkaniowa` (
  `nazwa_spoldzielni` varchar(255) NOT NULL,
  `adres_spoldzielni` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `spoldzielnia_mieszkaniowa`
--

INSERT INTO `spoldzielnia_mieszkaniowa` (`nazwa_spoldzielni`, `adres_spoldzielni`) VALUES
('Stonka', 'ul. Pilsudskiego 1, Krakow 30-000');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wlasciciel`
--

CREATE TABLE `wlasciciel` (
  `pesel` bigint(11) NOT NULL,
  `imie` varchar(25) NOT NULL,
  `nazwisko` varchar(25) NOT NULL,
  `ulica` varchar(25) NOT NULL,
  `miejscowosc` varchar(25) NOT NULL,
  `nr_telefonu` int(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `wlasciciel`
--

INSERT INTO `wlasciciel` (`pesel`, `imie`, `nazwisko`, `ulica`, `miejscowosc`, `nr_telefonu`) VALUES
(89551415963, 'Alicja', 'Zlopek', 'Straszna 1', 'Krakow', 458729963),
(92551415000, 'Kali', 'Brow', 'Zycia 2', 'Krakow', 123789963),
(92551415963, 'Anna', 'Popek', 'Moneta 18', 'Krakow', 456789963),
(92551488963, 'Tadeusz', 'Paluch', 'Patologa 8', 'Krakow', 455555963),
(92889415963, 'Alibaba', 'Rozbujnik', 'Dzina 1', 'Krakow', 969789963),
(93103336358, 'Mateusz', 'Skocz', 'Pilsudskiego 1', 'Skawina', 666777888);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wlasciciel_mieszkanie`
--

CREATE TABLE `wlasciciel_mieszkanie` (
  `wlasciciel_pesel` bigint(11) NOT NULL,
  `mieszkanie_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `wlasciciel_mieszkanie`
--

INSERT INTO `wlasciciel_mieszkanie` (`wlasciciel_pesel`, `mieszkanie_id`) VALUES
(93103336358, 1),
(89551415963, 5),
(92551415000, 6),
(89551415963, 5),
(92551415000, 6),
(92551415963, 10),
(92551488963, 7),
(92551415963, 10),
(92551488963, 7),
(92889415963, 16),
(93103336358, 17),
(92889415963, 16),
(93103336358, 17);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `blok`
--
ALTER TABLE `blok`
  ADD PRIMARY KEY (`id_bloku`),
  ADD UNIQUE KEY `id_bloku` (`id_bloku`),
  ADD KEY `nazwa_spoldzielni` (`nazwa_spoldzielni`),
  ADD KEY `nazwa_spoldzielni_2` (`nazwa_spoldzielni`);

--
-- Indexes for table `mieszkanie`
--
ALTER TABLE `mieszkanie`
  ADD PRIMARY KEY (`id_mieszkania`),
  ADD UNIQUE KEY `id_mieszkania` (`id_mieszkania`),
  ADD KEY `id_bloku` (`id_bloku`);

--
-- Indexes for table `oplaty`
--
ALTER TABLE `oplaty`
  ADD KEY `id_mieszkania` (`id_mieszkania`);

--
-- Indexes for table `pracownik`
--
ALTER TABLE `pracownik`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `pesel` (`pesel`),
  ADD KEY `nazwa_spoldzielni_fk` (`nazwa_spoldzielni_fk`),
  ADD KEY `pesel_2` (`pesel`);

--
-- Indexes for table `remont`
--
ALTER TABLE `remont`
  ADD KEY `id_bloku` (`id_bloku`);

--
-- Indexes for table `spoldzielnia_mieszkaniowa`
--
ALTER TABLE `spoldzielnia_mieszkaniowa`
  ADD PRIMARY KEY (`nazwa_spoldzielni`),
  ADD UNIQUE KEY `nazwa_spoldzielni` (`nazwa_spoldzielni`),
  ADD KEY `nazwa_spoldzielni_2` (`nazwa_spoldzielni`);

--
-- Indexes for table `wlasciciel`
--
ALTER TABLE `wlasciciel`
  ADD PRIMARY KEY (`pesel`);

--
-- Indexes for table `wlasciciel_mieszkanie`
--
ALTER TABLE `wlasciciel_mieszkanie`
  ADD KEY `wlasciciel_pesel` (`wlasciciel_pesel`),
  ADD KEY `mieszkanie_id` (`mieszkanie_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `blok`
--
ALTER TABLE `blok`
  MODIFY `id_bloku` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `mieszkanie`
--
ALTER TABLE `mieszkanie`
  MODIFY `id_mieszkania` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `blok`
--
ALTER TABLE `blok`
  ADD CONSTRAINT `blok_spoldzielnia_fk` FOREIGN KEY (`nazwa_spoldzielni`) REFERENCES `spoldzielnia_mieszkaniowa` (`nazwa_spoldzielni`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `mieszkanie`
--
ALTER TABLE `mieszkanie`
  ADD CONSTRAINT `mieszkanie_blok_fk` FOREIGN KEY (`id_bloku`) REFERENCES `blok` (`id_bloku`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `oplaty`
--
ALTER TABLE `oplaty`
  ADD CONSTRAINT `oplaty_mieszkanie_fk` FOREIGN KEY (`id_mieszkania`) REFERENCES `mieszkanie` (`id_mieszkania`);

--
-- Ograniczenia dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  ADD CONSTRAINT `pracownik_spoldzielnia_fk` FOREIGN KEY (`nazwa_spoldzielni_fk`) REFERENCES `spoldzielnia_mieszkaniowa` (`nazwa_spoldzielni`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `remont`
--
ALTER TABLE `remont`
  ADD CONSTRAINT `remont_blok_fk` FOREIGN KEY (`id_bloku`) REFERENCES `blok` (`id_bloku`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `wlasciciel_mieszkanie`
--
ALTER TABLE `wlasciciel_mieszkanie`
  ADD CONSTRAINT `mieszkanie_wl-wl_fk` FOREIGN KEY (`wlasciciel_pesel`) REFERENCES `wlasciciel` (`pesel`),
  ADD CONSTRAINT `mieszkanie_wlasciciel_miesz_fk` FOREIGN KEY (`mieszkanie_id`) REFERENCES `mieszkanie` (`id_mieszkania`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
