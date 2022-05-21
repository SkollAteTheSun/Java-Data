-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3307
-- Время создания: Авг 21 2020 г., 15:10
-- Версия сервера: 5.6.41
-- Версия PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `cars`
--

-- --------------------------------------------------------

--
-- Структура таблицы `cars`
--

CREATE TABLE `cars` (
  `ID` int(11) NOT NULL,
  `Brand` varchar(255) NOT NULL,
  `Model` varchar(255) NOT NULL,
  `Color` varchar(255) NOT NULL,
  `Plate` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `SecondName` varchar(255) NOT NULL,
  `MiddleName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `cars`
--

INSERT INTO `cars` (`ID`, `Brand`, `Model`, `Color`, `Plate`, `FirstName`, `SecondName`, `MiddleName`) VALUES
(1, 'Toyota', 'Corolla', 'Yellow', 't444tt32', 'Maxim', 'Pakhomov', 'Victorovich'),
(5, 'Hammer', '33', 'Silver', 'x777xx77', 'Alexander', 'ivaon', 'Spichka'),
(28, 'BMW', 'X5', 'Black', 'x898xx40', 'Alexander', 'Ivanov', 'Kirillovich'),
(29, 'Audi', 'A6', 'White', 'x777xx99', 'Fedor', 'Jukov', 'Eliseev'),
(30, 'Ferrari', '430', 'Red', 'e555ee32', 'Mikhail', 'Red', 'Sun'),
(31, 'Toyota', 'Corolla', 'Yellow', 't444tt32', 'Maxim', 'Pakhomov', 'Victorovich'),
(32, 'Hammer', '33', 'Silver', 'x777xx77', 'Alexander', 'ivaon', 'Spichka'),
(33, 'BMW', 'X5', 'Black', 'x898xx40', 'Alexander', 'Ivanov', 'Kirillovich'),
(34, 'Audi', 'A6', 'White', 'x777xx99', 'Fedor', 'Jukov', 'Eliseev'),
(35, 'Ferrari', '430', 'Red', 'e555ee32', 'Mikhail', 'Red', 'Sun'),
(36, 'Toyota', 'Corolla', 'Yellow', 't444tt32', 'Maxim', 'Pakhomov', 'Victorovich'),
(37, 'Hammer', '33', 'Silver', 'x777xx77', 'Alexander', 'ivaon', 'Spichka');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `cars`
--
ALTER TABLE `cars`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
