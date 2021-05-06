-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2021 at 04:34 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ds_remote_file_storage_data_node_2`
--

-- --------------------------------------------------------

--
-- Table structure for table `chunks`
--

CREATE TABLE `chunks` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `file_id` bigint(20) UNSIGNED NOT NULL,
  `part` int(10) UNSIGNED NOT NULL,
  `replica` int(10) UNSIGNED NOT NULL,
  `size` bigint(20) UNSIGNED NOT NULL,
  `local_path` varchar(255) NOT NULL,
  `unique_chunk_name` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chunks`
--

INSERT INTO `chunks` (`id`, `file_id`, `part`, `replica`, `size`, `local_path`, `unique_chunk_name`, `created_at`, `updated_at`) VALUES
(543, 125, 1, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_125_part_1_replica_1', 'file_id_125_part_1_replica_1', '2021-05-05 15:16:00', '2021-05-05 15:16:00'),
(544, 125, 2, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_125_part_2_replica_1', 'file_id_125_part_2_replica_1', '2021-05-05 15:16:00', '2021-05-05 15:16:00'),
(545, 125, 3, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_125_part_3_replica_1', 'file_id_125_part_3_replica_1', '2021-05-05 15:16:00', '2021-05-05 15:16:00'),
(546, 125, 4, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_125_part_4_replica_1', 'file_id_125_part_4_replica_1', '2021-05-05 15:16:00', '2021-05-05 15:16:00'),
(547, 125, 5, 1, 360866, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_125_part_5_replica_1', 'file_id_125_part_5_replica_1', '2021-05-05 15:16:00', '2021-05-05 15:16:00'),
(548, 126, 1, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_126_part_1_replica_1', 'file_id_126_part_1_replica_1', '2021-05-05 15:17:12', '2021-05-05 15:17:12'),
(549, 126, 2, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_126_part_2_replica_1', 'file_id_126_part_2_replica_1', '2021-05-05 15:17:12', '2021-05-05 15:17:12'),
(550, 126, 3, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_126_part_3_replica_1', 'file_id_126_part_3_replica_1', '2021-05-05 15:17:12', '2021-05-05 15:17:12'),
(551, 126, 4, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_126_part_4_replica_1', 'file_id_126_part_4_replica_1', '2021-05-05 15:17:12', '2021-05-05 15:17:12'),
(552, 126, 5, 1, 360866, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_126_part_5_replica_1', 'file_id_126_part_5_replica_1', '2021-05-05 15:17:12', '2021-05-05 15:17:12'),
(553, 127, 1, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_127_part_1_replica_1', 'file_id_127_part_1_replica_1', '2021-05-05 15:20:35', '2021-05-05 15:20:35'),
(554, 127, 2, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_127_part_2_replica_1', 'file_id_127_part_2_replica_1', '2021-05-05 15:20:35', '2021-05-05 15:20:35'),
(555, 127, 3, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_127_part_3_replica_1', 'file_id_127_part_3_replica_1', '2021-05-05 15:20:35', '2021-05-05 15:20:35'),
(556, 127, 4, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_127_part_4_replica_1', 'file_id_127_part_4_replica_1', '2021-05-05 15:20:35', '2021-05-05 15:20:35'),
(557, 127, 5, 1, 360866, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_127_part_5_replica_1', 'file_id_127_part_5_replica_1', '2021-05-05 15:20:35', '2021-05-05 15:20:35'),
(558, 128, 1, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_1_replica_1', 'file_id_128_part_1_replica_1', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(559, 128, 3, 2, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_3_replica_2', 'file_id_128_part_3_replica_2', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(560, 128, 4, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_4_replica_1', 'file_id_128_part_4_replica_1', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(561, 128, 6, 2, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_6_replica_2', 'file_id_128_part_6_replica_2', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(562, 128, 7, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_7_replica_1', 'file_id_128_part_7_replica_1', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(563, 128, 9, 2, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_9_replica_2', 'file_id_128_part_9_replica_2', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(564, 128, 10, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_10_replica_1', 'file_id_128_part_10_replica_1', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(565, 128, 12, 2, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_12_replica_2', 'file_id_128_part_12_replica_2', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(566, 128, 13, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_13_replica_1', 'file_id_128_part_13_replica_1', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(567, 128, 15, 2, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_15_replica_2', 'file_id_128_part_15_replica_2', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(568, 128, 16, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_16_replica_1', 'file_id_128_part_16_replica_1', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(569, 128, 18, 2, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_18_replica_2', 'file_id_128_part_18_replica_2', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(570, 128, 19, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_19_replica_1', 'file_id_128_part_19_replica_1', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(571, 128, 21, 2, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_21_replica_2', 'file_id_128_part_21_replica_2', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(572, 128, 22, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_22_replica_1', 'file_id_128_part_22_replica_1', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(573, 128, 24, 2, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_24_replica_2', 'file_id_128_part_24_replica_2', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(574, 128, 25, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_25_replica_1', 'file_id_128_part_25_replica_1', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(575, 128, 27, 2, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_27_replica_2', 'file_id_128_part_27_replica_2', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(576, 128, 28, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_28_replica_1', 'file_id_128_part_28_replica_1', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(577, 128, 30, 2, 158623, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_128_part_30_replica_2', 'file_id_128_part_30_replica_2', '2021-05-05 15:25:52', '2021-05-05 15:25:52'),
(578, 129, 1, 1, 12582912, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_129_part_1_replica_1', 'file_id_129_part_1_replica_1', '2021-05-05 15:30:53', '2021-05-05 15:30:53'),
(579, 129, 3, 2, 5401503, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_129_part_3_replica_2', 'file_id_129_part_3_replica_2', '2021-05-05 15:30:53', '2021-05-05 15:30:53'),
(580, 130, 1, 1, 12582912, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_130_part_1_replica_1', 'file_id_130_part_1_replica_1', '2021-05-05 15:40:42', '2021-05-05 15:40:42'),
(581, 130, 3, 2, 5401503, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_130_part_3_replica_2', 'file_id_130_part_3_replica_2', '2021-05-05 15:40:42', '2021-05-05 15:40:42'),
(582, 131, 1, 1, 12582912, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_131_part_1_replica_1', 'file_id_131_part_1_replica_1', '2021-05-05 15:43:39', '2021-05-05 15:43:39'),
(583, 131, 3, 2, 5401503, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_131_part_3_replica_2', 'file_id_131_part_3_replica_2', '2021-05-05 15:43:39', '2021-05-05 15:43:39'),
(584, 132, 1, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_132_part_1_replica_1', 'file_id_132_part_1_replica_1', '2021-05-05 21:32:35', '2021-05-05 21:32:35'),
(585, 132, 3, 2, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_132_part_3_replica_2', 'file_id_132_part_3_replica_2', '2021-05-05 21:32:35', '2021-05-05 21:32:35'),
(586, 132, 4, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_132_part_4_replica_1', 'file_id_132_part_4_replica_1', '2021-05-05 21:32:35', '2021-05-05 21:32:35'),
(587, 133, 1, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_133_part_1_replica_1', 'file_id_133_part_1_replica_1', '2021-05-05 21:38:26', '2021-05-05 21:38:26'),
(588, 133, 3, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_133_part_3_replica_1', 'file_id_133_part_3_replica_1', '2021-05-05 21:38:26', '2021-05-05 21:38:26'),
(589, 133, 5, 1, 360866, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_133_part_5_replica_1', 'file_id_133_part_5_replica_1', '2021-05-05 21:38:26', '2021-05-05 21:38:26'),
(590, 134, 1, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_134_part_1_replica_1', 'file_id_134_part_1_replica_1', '2021-05-05 21:39:32', '2021-05-05 21:39:32'),
(591, 134, 3, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_134_part_3_replica_1', 'file_id_134_part_3_replica_1', '2021-05-05 21:39:32', '2021-05-05 21:39:32'),
(592, 134, 5, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2\\src\\chunk_storage\\file_id_134_part_5_replica_1', 'file_id_134_part_5_replica_1', '2021-05-05 21:39:32', '2021-05-05 21:39:32'),
(593, 135, 1, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_135_part_1_replica_1', 'file_id_135_part_1_replica_1', '2021-05-05 21:47:40', '2021-05-05 21:47:40'),
(594, 135, 3, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_135_part_3_replica_1', 'file_id_135_part_3_replica_1', '2021-05-05 21:47:40', '2021-05-05 21:47:40'),
(595, 135, 5, 1, 360866, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_135_part_5_replica_1', 'file_id_135_part_5_replica_1', '2021-05-05 21:47:40', '2021-05-05 21:47:40'),
(596, 136, 1, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_136_part_1_replica_1', 'file_id_136_part_1_replica_1', '2021-05-06 13:44:06', '2021-05-06 13:44:06'),
(597, 136, 3, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_136_part_3_replica_1', 'file_id_136_part_3_replica_1', '2021-05-06 13:44:06', '2021-05-06 13:44:06'),
(598, 136, 5, 1, 360866, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_136_part_5_replica_1', 'file_id_136_part_5_replica_1', '2021-05-06 13:44:06', '2021-05-06 13:44:06'),
(599, 137, 1, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_137_part_1_replica_1', 'file_id_137_part_1_replica_1', '2021-05-06 13:56:46', '2021-05-06 13:56:46'),
(600, 137, 4, 2, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_137_part_4_replica_2', 'file_id_137_part_4_replica_2', '2021-05-06 13:56:46', '2021-05-06 13:56:46'),
(601, 138, 1, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_138_part_1_replica_1', 'file_id_138_part_1_replica_1', '2021-05-06 13:59:24', '2021-05-06 13:59:24'),
(602, 138, 4, 2, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_138_part_4_replica_2', 'file_id_138_part_4_replica_2', '2021-05-06 13:59:24', '2021-05-06 13:59:24'),
(603, 138, 6, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_138_part_6_replica_1', 'file_id_138_part_6_replica_1', '2021-05-06 13:59:24', '2021-05-06 13:59:24'),
(604, 138, 9, 2, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_138_part_9_replica_2', 'file_id_138_part_9_replica_2', '2021-05-06 13:59:24', '2021-05-06 13:59:24'),
(605, 138, 11, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_138_part_11_replica_1', 'file_id_138_part_11_replica_1', '2021-05-06 13:59:24', '2021-05-06 13:59:24'),
(606, 138, 14, 2, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_138_part_14_replica_2', 'file_id_138_part_14_replica_2', '2021-05-06 13:59:24', '2021-05-06 13:59:24'),
(607, 138, 16, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_138_part_16_replica_1', 'file_id_138_part_16_replica_1', '2021-05-06 13:59:24', '2021-05-06 13:59:24'),
(608, 138, 19, 2, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_138_part_19_replica_2', 'file_id_138_part_19_replica_2', '2021-05-06 13:59:24', '2021-05-06 13:59:24'),
(609, 138, 21, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_138_part_21_replica_1', 'file_id_138_part_21_replica_1', '2021-05-06 13:59:24', '2021-05-06 13:59:24'),
(610, 138, 24, 2, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_138_part_24_replica_2', 'file_id_138_part_24_replica_2', '2021-05-06 13:59:24', '2021-05-06 13:59:24'),
(611, 138, 26, 1, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_138_part_26_replica_1', 'file_id_138_part_26_replica_1', '2021-05-06 13:59:24', '2021-05-06 13:59:24'),
(612, 138, 29, 2, 1048576, 'E:\\astu_files\\4th_year\\1st_semester\\ds\\lab_project\\DataNode_2v\\src\\chunk_storage\\file_id_138_part_29_replica_2', 'file_id_138_part_29_replica_2', '2021-05-06 13:59:24', '2021-05-06 13:59:24');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chunks`
--
ALTER TABLE `chunks`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chunks`
--
ALTER TABLE `chunks`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=613;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
