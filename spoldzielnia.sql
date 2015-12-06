-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 06 Gru 2015, 14:05
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
(1, 'Chmieleniec', '1', 'Krakow', 'Stonka');

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
(2, 1, 2, 1, 66, 2, 22);

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
(1, 'Krzysztof', 'Pezi', 93103336358, 'Chmieleniec 1', 'Krakow', 'Prezes', 0, 666777888, 'Stonka');

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
(93103336358, 1);

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
  ADD CONSTRAINT `mieszkanie_ibfk_1` FOREIGN KEY (`id_bloku`) REFERENCES `blok` (`id_bloku`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `oplaty`
--
ALTER TABLE `oplaty`
  ADD CONSTRAINT `oplaty_mieszkanie_fk` FOREIGN KEY (`id_mieszkania`) REFERENCES `mieszkanie` (`id_mieszkania`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  ADD CONSTRAINT `pracownik_spoldzielnia_fk` FOREIGN KEY (`nazwa_spoldzielni_fk`) REFERENCES `spoldzielnia_mieszkaniowa` (`nazwa_spoldzielni`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `wlasciciel_mieszkanie`
--
ALTER TABLE `wlasciciel_mieszkanie`
  ADD CONSTRAINT `wlasciciel_mieszkanie_ibfk_1` FOREIGN KEY (`mieszkanie_id`) REFERENCES `mieszkanie` (`id_mieszkania`),
  ADD CONSTRAINT `wlasciciel_mieszkanie_ibfk_2` FOREIGN KEY (`wlasciciel_pesel`) REFERENCES `wlasciciel` (`pesel`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
