-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Ноя 01 2022 г., 11:21
-- Версия сервера: 5.7.39
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `UP_ind`
--

-- --------------------------------------------------------

--
-- Структура таблицы `appliances`
--

CREATE TABLE `appliances` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` int(11) NOT NULL,
  `size` varchar(20) NOT NULL,
  `wight` int(11) NOT NULL,
  `provider_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `appliances`
--

INSERT INTO `appliances` (`id`, `name`, `price`, `size`, `wight`, `provider_id`) VALUES
(2, 'Микроволновка', 3000, '100х100см', 10, 1),
(7, 'Плита', 10999, '1.2х1.3м', 30, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(7);

-- --------------------------------------------------------

--
-- Структура таблицы `order`
--

CREATE TABLE `order` (
  `id` bigint(20) NOT NULL,
  `complited_date` datetime(6) NOT NULL,
  `total_coast` int(11) NOT NULL,
  `appliances_id` bigint(20) DEFAULT NULL,
  `point_of_issue_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `point_of_issue`
--

CREATE TABLE `point_of_issue` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `schedule` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `point_of_issue`
--

INSERT INTO `point_of_issue` (`id`, `address`, `schedule`) VALUES
(3, 'ул. Пушкина 21', '9:00 - 16:00');

-- --------------------------------------------------------

--
-- Структура таблицы `post`
--

CREATE TABLE `post` (
  `id` bigint(20) NOT NULL,
  `postname` varchar(255) DEFAULT NULL,
  `salary` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `post`
--

INSERT INTO `post` (`id`, `postname`, `salary`) VALUES
(1, 'Выдовальщик', '20000'),
(2, 'Уборщик', '15000');

-- --------------------------------------------------------

--
-- Структура таблицы `provider`
--

CREATE TABLE `provider` (
  `id` bigint(20) NOT NULL,
  `information_of_boss` varchar(200) NOT NULL,
  `inn` varchar(12) NOT NULL,
  `namecompany` varchar(100) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `ur_address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `provider`
--

INSERT INTO `provider` (`id`, `information_of_boss`, `inn`, `namecompany`, `phone`, `ur_address`) VALUES
(1, '[value-2]', '[value-3]', '[value-4]', '[value-5]', '[value-6]');

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `birthday` date NOT NULL,
  `middle_name` varchar(255) NOT NULL,
  `name` varchar(50) NOT NULL,
  `number_passport` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `serial_passport` int(11) NOT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `active`, `birthday`, `middle_name`, `name`, `number_passport`, `password`, `phone`, `serial_passport`, `surname`, `username`, `post_id`) VALUES
(1, NULL, '2022-10-01', 'Олегович', 'Иван', 123456, 'Qwe1234', '+8(999)999-99-99', 1234, 'Иванов', 'Qwe1234', 1),
(3, b'1', '2022-10-15', 'Амогусович', 'Иван', 0, '$2a$08$xVmpmZi/sGgcfKhdWwbNDulHdg5pg6.ytkkPtyOuHnVQoFn4hEBBq', '+8(999)999-99-99', 0, 'Кринжлоуни', 'qwertyu', NULL),
(4, b'1', '2000-06-07', 'Олегович', 'Иван', 123456, '$2a$08$Qb5XicCa6gOCGYD9YI61VOJ/lYVxwW6ZbStT0FUCGsvFYySDrigiy', '+8(999)999-99-99', 1234, 'Иванов', 'ZxcZxc', NULL),
(5, b'1', '2022-11-03', 'Амогусович', 'Иван', 123456, '$2a$08$yZkJSjfE/7HYTEaSIV.7iurJYe7.OEfEQo5NpnRUsU0L9kBubHfsy', '+8(999)999-99-99', 1234, 'Абобов', 'AsdAsd', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `user_roles`
--

CREATE TABLE `user_roles` (
  `id_user` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_roles`
--

INSERT INTO `user_roles` (`id_user`, `roles`) VALUES
(1, 'EMPLOYEE'),
(3, 'USER'),
(4, 'USER'),
(5, 'USER'),
(5, 'ADMIN'),
(5, 'EMPLOYEE');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `appliances`
--
ALTER TABLE `appliances`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKlke4dq6eq9yarhfafkwjfkd84` (`provider_id`);

--
-- Индексы таблицы `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3ki4nvrygexp48pfqj3rmtqu3` (`appliances_id`),
  ADD KEY `FKlocurqkv1xttylkb0p4wws7qu` (`point_of_issue_id`),
  ADD KEY `FKcpl0mjoeqhxvgeeeq5piwpd3i` (`user_id`);

--
-- Индексы таблицы `point_of_issue`
--
ALTER TABLE `point_of_issue`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `provider`
--
ALTER TABLE `provider`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6pioe1o4vepu5cboesyadgmun` (`post_id`);

--
-- Индексы таблицы `user_roles`
--
ALTER TABLE `user_roles`
  ADD KEY `FKok1v2uejpjcfqg8va888yvy0w` (`id_user`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `appliances`
--
ALTER TABLE `appliances`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT для таблицы `post`
--
ALTER TABLE `post`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `provider`
--
ALTER TABLE `provider`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `appliances`
--
ALTER TABLE `appliances`
  ADD CONSTRAINT `FKlke4dq6eq9yarhfafkwjfkd84` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`id`);

--
-- Ограничения внешнего ключа таблицы `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `FK3ki4nvrygexp48pfqj3rmtqu3` FOREIGN KEY (`appliances_id`) REFERENCES `appliances` (`id`),
  ADD CONSTRAINT `FKcpl0mjoeqhxvgeeeq5piwpd3i` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKlocurqkv1xttylkb0p4wws7qu` FOREIGN KEY (`point_of_issue_id`) REFERENCES `point_of_issue` (`id`);

--
-- Ограничения внешнего ключа таблицы `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK6pioe1o4vepu5cboesyadgmun` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`);

--
-- Ограничения внешнего ключа таблицы `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKok1v2uejpjcfqg8va888yvy0w` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
