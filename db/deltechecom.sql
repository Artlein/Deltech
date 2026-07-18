-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 02, 2024 at 09:46 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `deltechecom`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `status` enum('active','inactive') NOT NULL DEFAULT 'active',
  `create_token` varchar(6) NOT NULL DEFAULT '',
  `reset_token` varchar(255) DEFAULT NULL,
  `role_id` int(11) NOT NULL DEFAULT 3
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`, `fullname`, `email`, `phone`, `status`, `create_token`, `reset_token`, `role_id`) VALUES
(8, 'adminlead', '$2y$10$Q/dB7LTeF2hQulfqTkHnLOM.8N//EsIAj5JlxLg2eGpjfG4PaIgxS', 'adminlead', 'torresailene1@gmail.com', '0955988097', 'active', '', NULL, 2),
(10, 'superadm', '$2y$10$ZY8N0u9CG6sjDPg/oStyCecaAem9RPMogL7y0.YgFOKLleLCEDYZq', 'Superadmin', 'blaireashb@gmail.com', '0987654321', 'active', '', NULL, 1),
(11, 'suppad', '$2y$10$pOR631O52mzJ0zM43UG5xu.OzJfoNRXkDhqpWCFZkYoqwpntk0XRu', 'Support Admin', 'ashyrieheisen@gmail.com', '0987654321', 'active', '', NULL, 3),
(12, 'adlead', '$2y$10$yo0zRKhi2t5w/pWqXRbosOyytwsz8E5aMzOSIKDQq6wJ00HZ5cgOq', 'fsf', 'adlead@gmail.com', '0987654321', 'active', '', NULL, 2),
(13, 'supad', '$2y$10$bvuPhwrEcIhMPh.khT5aZuisjTyG0UzTKsfMx6CCDVB/h0o5.fDvu', 'adasda', 'supad@gmail.com', '09876543', 'active', '', NULL, 3),
(14, 'a2', '$2y$10$XNqmAJm/zUaraEgYNtcGhea8ZCv2wST6ox0WaPf2cwr2BKGnL4kKW', 'a2', 'ailenetorres834@gmail.com', '0955988097', 'active', '', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `admin_2fa`
--

CREATE TABLE `admin_2fa` (
  `id` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL,
  `otp_secret` varchar(255) NOT NULL,
  `is_2fa_enabled` tinyint(1) NOT NULL DEFAULT 0,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin_2fa`
--

INSERT INTO `admin_2fa` (`id`, `admin_id`, `otp_secret`, `is_2fa_enabled`, `created_at`, `updated_at`) VALUES
(3, 8, '6Q32LHD5N6BCWCG2RJ2LWEHK5YC5PPSQ', 1, '2024-11-25 09:15:01', '2024-11-25 09:15:39'),
(5, 10, 'PWDPLW52L5AUEOH2RVOCJMV33Y7ONRH3', 1, '2024-11-25 09:48:08', '2024-11-25 09:52:37'),
(6, 11, 'A6CFPVT4MV4DT3UCH24F3UANSMQVBA77', 0, '2024-11-26 08:40:59', '2024-11-26 08:40:59'),
(7, 12, 'RRBLZMRR3OYPZ7NVU6D7FYJ4T4IPZO7P', 1, '2024-11-27 08:27:17', '2024-11-27 08:27:53'),
(8, 13, 'IDNPENZENIYAUM2QRBROBGREOSCCBTNF', 1, '2024-11-27 08:35:05', '2024-11-27 08:35:41'),
(9, 14, 'WG6EQEBWV4PGDJ4AS7LU6EVQ7FSOJKJT', 0, '2024-11-27 14:49:33', '2024-12-02 05:43:39');

-- --------------------------------------------------------

--
-- Table structure for table `admin_role`
--

CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin_role`
--

INSERT INTO `admin_role` (`id`, `role_name`) VALUES
(1, 'Level 1 Access - Super Admin'),
(2, 'Level 2 Access - Admin Lead'),
(3, 'Level 3 Access - Standard / Support Admin');

-- --------------------------------------------------------

--
-- Table structure for table `contacts`
--

CREATE TABLE `contacts` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `email` varchar(255) NOT NULL,
  `subject` text NOT NULL DEFAULT 'none',
  `message` text NOT NULL,
  `created_c` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `contacts`
--

INSERT INTO `contacts` (`id`, `name`, `email`, `subject`, `message`, `created_c`) VALUES
(1, 'fdsf', 'ailenetorres834@gmail.com', 'vsdfs', 'vds', '2024-11-26 14:22:42');

-- --------------------------------------------------------

--
-- Table structure for table `currencies`
--

CREATE TABLE `currencies` (
  `id` int(11) NOT NULL,
  `currency` varchar(20) NOT NULL,
  `symbol` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `currencies`
--

INSERT INTO `currencies` (`id`, `currency`, `symbol`) VALUES
(1, 'PHP', '₱'),
(2, 'USD', '$');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `name_customer` varchar(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email_customer` varchar(255) NOT NULL,
  `phone_customer` varchar(10) NOT NULL,
  `address` text NOT NULL,
  `password` varchar(255) NOT NULL,
  `verification_code` varchar(6) DEFAULT NULL,
  `is_verified` tinyint(1) DEFAULT 0,
  `note_customer` text DEFAULT NULL,
  `reset_token` varchar(255) DEFAULT NULL,
  `date_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `name_customer`, `username`, `email_customer`, `phone_customer`, `address`, `password`, `verification_code`, `is_verified`, `note_customer`, `reset_token`, `date_at`) VALUES
(1, 'Juan Dela Cruz', 'juandc', 'juan@example.com', '9123456789', '123 Main St, Manila', '$2y$10$somehashedpassword1', NULL, 1, NULL, NULL, '2024-11-24 13:52:07'),
(2, 'Maria Santos', 'mariasantos', 'maria@example.com', '9234567890', '456 Park Ave, Quezon City', '$2y$10$somehashedpassword2', NULL, 1, NULL, NULL, '2024-11-24 13:52:07'),
(3, 'Pedro Reyes', 'pedroreyes', 'pedro@example.com', '9345678901', '789 Beach Rd, Cebu', '$2y$10$somehashedpassword3', NULL, 1, NULL, NULL, '2024-11-24 13:52:07'),
(16, 'Ailene Torres', 'ai', 'ailenetorres834@gmail.com', '0955988097', '1255 Makati City', '$2y$10$TgrSMG7SiXtEhpeDA/l9V.JgobtxZbVYKs.cUqm/ezC5VlOKvrXFC', NULL, 1, NULL, NULL, '2024-11-25 09:56:42'),
(17, 'Gian Bernardino', 'gb', 'atorres.a12241700@umak.edu.ph', '09876543', 'Baguio City', '$2y$10$KdmmGOyowA0utn43/NaijuCaGEsZ6vDV0PK5evnoYMbGh54Y5gqxm', NULL, 0, NULL, NULL, '2024-11-27 14:38:26');

-- --------------------------------------------------------

--
-- Table structure for table `customer_2fa`
--

CREATE TABLE `customer_2fa` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `otp_secret` varchar(255) NOT NULL,
  `is_2fa_enabled` tinyint(1) NOT NULL DEFAULT 0,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer_2fa`
--

INSERT INTO `customer_2fa` (`id`, `customer_id`, `otp_secret`, `is_2fa_enabled`, `created_at`, `updated_at`) VALUES
(1, 17, 'I2VBEI6YXF5UM6FUXGZ2GTUXR44NVBG7', 1, '2024-11-27 14:40:33', '2024-11-27 14:41:30');

-- --------------------------------------------------------

--
-- Table structure for table `customer_companies`
--

CREATE TABLE `customer_companies` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `company_name` varchar(255) NOT NULL,
  `company_address` text NOT NULL,
  `job_title` varchar(100) NOT NULL,
  `business_document` varchar(255) DEFAULT NULL,
  `business_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer_companies`
--

INSERT INTO `customer_companies` (`id`, `customer_id`, `company_name`, `company_address`, `job_title`, `business_document`, `business_id`) VALUES
(13, 16, 'PBL Co.', 'J.P. Rizal Makati', 'Manager', 'admin/customerfileupload/67444a0101064.pdf', 'customeridupload/PIC WITH BG OF CCIS LOGO.jpg'),
(14, 17, 'COJ', 'Ayala', 'Staff', 'admin/customerfileupload/67472f559f93e.pdf', 'admin/customeridupload/67472f60e644c.png');

-- --------------------------------------------------------

--
-- Table structure for table `deployments`
--

CREATE TABLE `deployments` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `deployment_leader_id` int(11) NOT NULL,
  `creation_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `status` enum('pending','completed','cancelled') NOT NULL DEFAULT 'pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `deployments`
--

INSERT INTO `deployments` (`id`, `order_id`, `name`, `location`, `deployment_leader_id`, `creation_time`, `status`) VALUES
(1, 4, 'dfsdf', 'fsdf', 8, '2024-11-26 13:33:36', 'pending'),
(2, 5, 'hdh', 'h', 8, '2024-11-27 08:33:46', 'pending'),
(3, 5, 'fdsf', 'Makati City', 12, '2024-11-27 15:12:19', 'pending');

-- --------------------------------------------------------

--
-- Table structure for table `deployment_logs`
--

CREATE TABLE `deployment_logs` (
  `id` int(11) NOT NULL,
  `deployment_id` int(11) NOT NULL,
  `status` enum('completed','cancelled') NOT NULL DEFAULT 'completed',
  `deployment_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `order_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `completed_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `deployment_name` varchar(255) NOT NULL,
  `deployment_leader_id` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `deployment_team_members`
--

CREATE TABLE `deployment_team_members` (
  `id` int(11) NOT NULL,
  `deployment_id` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `deployment_team_members`
--

INSERT INTO `deployment_team_members` (`id`, `deployment_id`, `admin_id`) VALUES
(1, 1, 11);

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `admin_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `ticket_id` varchar(20) NOT NULL,
  `message` text DEFAULT NULL,
  `sender_type` enum('customer','admin') NOT NULL DEFAULT 'customer',
  `sender_id` int(11) NOT NULL DEFAULT 0,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`id`, `customer_id`, `admin_id`, `product_id`, `ticket_id`, `message`, `sender_type`, `sender_id`, `timestamp`) VALUES
(8, 17, NULL, 19, 'TICKET-1992859776900', 'Inquiry started.', 'customer', 17, '2024-12-02 08:03:36');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `orders_number` varchar(20) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `product_name` text NOT NULL,
  `product_quantity` varchar(20) NOT NULL,
  `product_price` decimal(10,2) NOT NULL,
  `currency` varchar(20) NOT NULL,
  `subtotal` varchar(20) NOT NULL,
  `note_customer` text NOT NULL,
  `order_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `order_status` enum('pending','cancelled','processing','pending payment','completed','failed') NOT NULL DEFAULT 'pending payment'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `orders_number`, `customer_id`, `product_name`, `product_quantity`, `product_price`, `currency`, `subtotal`, `note_customer`, `order_date`, `order_status`) VALUES
(1, '#23380', 1, 'Parking Sensor Kit - 4 Sensors', '2', '49.99', 'PHP', '99.98', 'Juan Dela Cruz', '2023-06-20 22:32:10', 'completed'),
(2, '#68817', 2, 'Wireless Parking Sensor System', '1', '89.99', 'PHP', '89.99', 'Maria Santos', '2023-06-21 16:23:27', 'completed'),
(3, '#18661', 3, 'Parking Assist Camera', '3', '39.99', 'PHP', '119.97', 'Rush order', '2023-06-21 15:49:54', 'completed'),
(4, '#49459', 16, 'Wireless Parking Sensor System', '2', '89.99', 'USD', '179.98', 'gfgfgfg', '2024-11-25 10:05:02', 'completed'),
(5, '#75666', 16, 'Parking Sensor Kit - 4 Sensors', '6', '49.99', 'USD', '299.94', 'fsdfsddff', '2024-11-26 13:37:17', 'completed'),
(6, '#75666', 16, 'Wireless Parking Sensor System', '3', '89.99', 'USD', '269.97', 'fsdfsddff', '2024-11-26 13:37:17', 'completed'),
(7, '#82668', 17, 'Parking Sensor Kit - 4 Sensors', '1', '49.99', 'PHP', '49.99', 'fwefwefwefwef', '2024-11-27 15:03:29', 'pending payment');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name_product` text NOT NULL,
  `description_product` text NOT NULL,
  `price_product` decimal(10,2) NOT NULL,
  `currency` varchar(20) NOT NULL,
  `img_product` varchar(255) NOT NULL,
  `stock_product` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `category` enum('PARKING EQUIPMENT','RFID READER','OTHERS') NOT NULL DEFAULT 'OTHERS'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--









INSERT INTO `products` (`id`, `name_product`, `description_product`, `price_product`, `currency`, `img_product`, `stock_product`, `created_at`, `category`) VALUES
(13, 'BOOM BARRIER WITH ARM', '> This electromechanical barrier ensures smooth traffic control with adjustable speed and anti-crash safety features. Its durable fixed or articulated arm is ideal for entrance and exit management in parking areas.\r\n\r\n✅SPECIFICATION:\r\n- Electromechanical Barrier\r\n- Adjustable Speed and anti-crashing - safety features\r\n- Fixed arm and Articulated\r\n- Entrance and Exit Traffic Control Device\r\n', '339.00', 'USD', '674c844ebce3a.png', 20, '2024-12-01 14:11:11', 'PARKING EQUIPMENT'),
(14, 'MEMBER TERMINAL', '> The RFID-based Member Terminal is designed for efficient member access management, featuring a wall-mounted LED display and industrial-grade hardware. Its locally assembled setup supports high-performance operations with built-in parking software.\r\n\r\n✅SPECIFICATION:\r\n- Wall Mount Type\r\n- Industrial type PC based controller\r\n- LED Display\r\n\r\n✅PARKING SOFTWARE APPLICATION\r\n- OS: Windows 10 (64bit)\r\n- with Built In Anti-Virus\r\n\r\n✅HARDWARE:\r\n- Motherboard & Proccesor (Industrial Grade)\r\n- 8gb Memory\r\n- 240gb SSD x 2\r\n\r\n✅LOCALLY ASSEMBLED - PHILLIPINES\r\n- Parts are made in Taiwan, China and Philippines\r\n', '245.99', 'USD', '674c70edeb5d5.png', 20, '2024-12-01 14:21:33', 'PARKING EQUIPMENT'),
(15, 'SHORT RANGE READER', '> Designed for close-range scanning (10-20cm), this device ensures quick and accurate RFID detection.\r\n\r\n✅SPECIFICATION:\r\n- Model Number: IN-EM\r\n- Frequency: 125KHZ\r\n- Interface: Wiegand\r\n- Read Range: 10-20cm\r\n- Size: 126*86*23(mm)\r\n- Goldbridge- Made in China\r\n', '75.00', 'USD', '674c71ba227b6.png', 20, '2024-12-01 14:24:58', 'RFID READER'),
(16, 'SERVER TERMINAL', '> The Server Terminal is designed for robust parking system management with 16GB RAM and dual SSDs for optimal performance. It comes pre-loaded with secure, anti-virus-protected software for uninterrupted operations.\r\n\r\n✅SPECIFICATION:\r\n- Intel Core Series\r\n- 2 x 500gb SSD;\r\n- Memory :  16GB\r\n- Windows 10\r\n- Monitor, Keyboard and Mouse(A4tech)\r\n- UPS 650VA \r\n\r\n✅PARKING SOFTWARE APPLICATION\r\n -OS: Windows 10 (64bit)\r\n- with Built In Anti-Virus\r\n', '3495.00', 'USD', '674c73b46038a.png', 20, '2024-12-01 14:33:24', 'OTHERS'),
(17, 'AUTOPAY STATION TERMINAL', '> The RFID-based Member Terminal is designed for efficient member access management, featuring a wall-mounted LED display and industrial-grade hardware. Its locally assembled setup supports high-performance operations with built-in parking software.\r\n\r\n✅SPECIFICATION:\r\n- Specially designed All-in-One Metal Casing Payment Kiosk\r\n- Touchscreen Display \r\n- Sounds in Kiosk\r\n\r\n✅PARKING SOFTWARE APPLICATION\r\n- OS: Windows 10 (64bit)\r\n- with Built In Anti-Virus\r\n- Accepts both Paper Bills and Coins as Payment\r\n- ** 20,50,100,500,1000 ; \r\n- Dispense both Paper Bills and Coins as Payment\r\n- **Options:  20,50,100,500 \r\n- ** 5, 10  (Coin hopper)\r\n- CPU:  8gb memory; 240gb SSD x 2\r\n UPS 1KVA/Power Supply \r\n- Operating Temperature: 0 to 50 degree\r\n\r\n', '7500.00', 'USD', '674c7f0944db1.png', 20, '2024-12-01 15:21:45', 'PARKING EQUIPMENT'),
(18, 'POS TERMINAL', '> This POS terminal integrates a customer display, cash drawer, and receipt printer for easy payment processing. Built with industrial-grade components, it includes an anti-virus-protected Windows 10 system for secure operations.\r\n\r\n✅SPECIFICATION:\r\n- Intel Processor I Series\r\n- RAM: DDR4 8GB\r\n- 240gb SSD x 2\r\n- Ethernet: Gigabit Ethernet\r\n\r\n✅PARKING SOFTWARE APPLICATION\r\n- OS: Windows 10 (64bit)\r\n- with Built In Anti-Virus\r\n\r\n✅INCLUSIONS:\r\n- Industrial Controller\r\n-  Monitor\r\n- Cash Drawer\r\n- Customer Display\r\n- Receipt Printer\r\n- Mouse and Keyboard (A4tech)\r\n- Mifare/ QR Reader\r\n- UPS 650VA/Power Supply\r\n\r\n✅ LOCALLY ASSEMBLED - PHILLIPINES\r\n-Parts are made in Taiwan, China and Philippines\r\n', '297.49', 'USD', '674c86bbf3413.png', 20, '2024-12-01 15:24:39', 'PARKING EQUIPMENT'),
(19, 'EXIT KIOSK TERMINAL', '> The Unmanned Exit Terminal ensures quick and secure parking exit with card retrieval or QR scanning. It features a robust industrial-grade controller, speaker system, and integrated software for efficient operations.\r\n\r\n✅SPECIFICATION:\r\n- Unmanned Exit Terminal Kiosk \r\n- Over-all transaction time maximum of 4 seconds\r\n- Card Retriever/QR Scanner\r\n- Speaker System\r\n- Digital greeting System\r\n- Monitor\r\n- Industrial type PC based controller\r\n\r\n✅PARKING SOFTWARE APPLICATION:\r\n- OS: Windows 10 (64bit)\r\n- with Built In Anti-Virus\r\n\r\n✅HARDWARE:\r\n- Motherboard & Proccesor (Industrial Grade)\r\n- 8gb Memory\r\n- 240gb SSD x 2\r\n- UPS 1KVA/Power Supply \r\n\r\n✅LOCALLY ASSEMBLED - PHILLIPINES\r\n- Parts are made in Taiwan, China and Philippines\r\n', '499.00', 'USD', '674c875bae312.png', 20, '2024-12-01 15:57:15', 'PARKING EQUIPMENT'),
(20, ' ENTRANCE  KIOSKTERMINAL ', '> The Unmanned Entrance Terminal offers a seamless parking experience with a transaction time of just 4 seconds. It equipped with a durable PC-based controller, ticket dispenser, and advanced software, it also ensures efficient and secure entry management.\r\n\r\n✅SPECIFICATION:\r\n- Unmanned Entrance Terminal Kiosk \r\n- Over-all transaction time maximum of 4 seconds\r\n- Card Retriever/QR Scanner\r\n- Speaker System\r\n- Digital greeting System\r\n- Monitor\r\n- Industrial type PC based controller\r\n\r\n✅PARKING SOFTWARE APPLICATION:\r\n- OS: Windows 10 (64bit)\r\n- with Built In Anti-Virus\r\n\r\n✅HARDWARE:\r\n- Motherboard & Proccesor (Industrial Grade)\r\n- 8gb Memory\r\n- 240gb SSD x 2\r\n- UPS 1KVA/Power Supply \r\n\r\n✅LOCALLY ASSEMBLED - PHILLIPINES\r\n- Parts are made in Taiwan, China and Philippines\r\n', '499.00', 'USD', '674c880928bb4.png', 20, '2024-12-01 16:00:09', 'PARKING EQUIPMENT'),
(21, 'MID RANGE READER', '> A compact reader with a 40-60cm range, it offers reliable performance with Wiegand interface compatibility.\r\n\r\n✅SPECIFICATION:\r\n- Model Number: ACM-A26C\r\n- Frequency: 125KHZ\r\n-Interface: Wiegand\r\n- Read Range: 40-60cm\r\n- Size: 235*240*35(mm)\r\n\r\n✅Goldbridge- Made in China\r\n\r\n', '99.00', 'USD', '674d4083a8fc8.png', 20, '2024-12-02 05:07:15', 'RFID READER'),
(22, 'LONG RANGE READER', '> This RFID reader provides over 6 meters of sensitivity and supports multiple interfaces for secure access control.\r\n\r\n✅SPECIFICATION:\r\n\r\n- NTC Approved\r\n- RS232, RS485, Wiegand and TCP/IP\r\n- Gray color, Flat plug, \r\n- More than 6 meter Sensitivity\r\n\r\n✅ Mawari- Made in China\r\n\r\n', '200.00', 'USD', '674d411c4df04.png', 20, '2024-12-02 05:09:48', 'RFID READER'),
(23, 'SECURITY CAMERA POLE', '> The IP Camera delivers high-definition video with 1920x1080 resolution at 30fps and excellent low-light performance. Featuring a 3-axis adjustment, it is designed for versatile and secure surveillance.\r\n\r\n✅SPECIFICATION:\r\n- Camera Pole\r\n- 1/2.8\'\' Progressive scan CMOS\r\n- 1920 X 1080 @ 30fps\r\n- Color: 0.01 Lux @ (F1.2, AGC ON), 0.028 Lux  @ (F2.0, AGC ON)\r\n- Three Streams\r\n- 3 Axis Adjustment\r\n\r\n', '945.00', 'USD', '674d42448b3ac.png', 20, '2024-12-02 05:14:44', 'PARKING EQUIPMENT'),
(24, 'PORTABLE DATA TERMINAL W/ BT PRINTER', '> The Portable Data Terminal with Bluetooth printer features an Android OS, an 8-core processor, and a 5.5-inch display. With long battery life and fast charging, it is ideal for mobile operations.\r\n\r\n✅SPECIFICATION:\r\n\r\n- With Bluetooth Printer\r\n- Screen Size: 5.5 inch\r\n- Android\r\n- 8 cores, 2.0 GHz\r\n- RAM: 3GB, ROM: 64GB\r\n- 4900 mAh\r\n- standby time > 500 h\r\n- Charging Time ≤ 4 h\r\n\r\n✅ Hik Vision- Made in China\r\n', '159.00', 'USD', '674d72e7e34d7.PNG', 20, '2024-12-02 05:20:05', 'OTHERS');

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`id`, `name`) VALUES
(1, 'pending'),
(2, 'cancelled'),
(3, 'processing'),
(4, 'pending payment'),
(5, 'completed'),
(6, 'failed');

-- --------------------------------------------------------

--
-- Table structure for table `support_tickets`
--

CREATE TABLE `support_tickets` (
  `ticket_id` varchar(20) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `resolved` enum('yes','no') NOT NULL DEFAULT 'no',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `support_tickets`
--

INSERT INTO `support_tickets` (`ticket_id`, `customer_id`, `product_id`, `resolved`, `created_at`) VALUES
('TICKET-1992859776900', 17, 19, 'no', '2024-12-02 08:03:36');

-- --------------------------------------------------------

--
-- Table structure for table `user_cart`
--

CREATE TABLE `user_cart` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_price` decimal(10,2) NOT NULL,
  `currency` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`,`email`,`phone`),
  ADD KEY `fk_admin_role` (`role_id`);

--
-- Indexes for table `admin_2fa`
--
ALTER TABLE `admin_2fa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `admin_id` (`admin_id`);

--
-- Indexes for table `admin_role`
--
ALTER TABLE `admin_role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `contacts`
--
ALTER TABLE `contacts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `currencies`
--
ALTER TABLE `currencies`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `currency` (`currency`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `customer_2fa`
--
ALTER TABLE `customer_2fa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `customer_companies`
--
ALTER TABLE `customer_companies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `deployments`
--
ALTER TABLE `deployments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `deployment_leader_id` (`deployment_leader_id`);

--
-- Indexes for table `deployment_logs`
--
ALTER TABLE `deployment_logs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `deployment_id` (`deployment_id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `deployment_leader_id` (`deployment_leader_id`),
  ADD KEY `admin_id` (`admin_id`);

--
-- Indexes for table `deployment_team_members`
--
ALTER TABLE `deployment_team_members`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `deployment_id` (`deployment_id`,`admin_id`),
  ADD KEY `admin_id` (`admin_id`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `currency` (`currency`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `currency` (`currency`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `support_tickets`
--
ALTER TABLE `support_tickets`
  ADD PRIMARY KEY (`ticket_id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `user_cart`
--
ALTER TABLE `user_cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `product_id` (`product_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `admin_2fa`
--
ALTER TABLE `admin_2fa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `admin_role`
--
ALTER TABLE `admin_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `contacts`
--
ALTER TABLE `contacts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `currencies`
--
ALTER TABLE `currencies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `customer_2fa`
--
ALTER TABLE `customer_2fa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `customer_companies`
--
ALTER TABLE `customer_companies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `deployments`
--
ALTER TABLE `deployments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `deployment_logs`
--
ALTER TABLE `deployment_logs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `deployment_team_members`
--
ALTER TABLE `deployment_team_members`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user_cart`
--
ALTER TABLE `user_cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `fk_admin_role` FOREIGN KEY (`role_id`) REFERENCES `admin_role` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `admin_2fa`
--
ALTER TABLE `admin_2fa`
  ADD CONSTRAINT `admin_2fa_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `customer_2fa`
--
ALTER TABLE `customer_2fa`
  ADD CONSTRAINT `customer_2fa_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `customer_companies`
--
ALTER TABLE `customer_companies`
  ADD CONSTRAINT `customer_companies_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `deployments`
--
ALTER TABLE `deployments`
  ADD CONSTRAINT `deployments_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `deployments_ibfk_2` FOREIGN KEY (`deployment_leader_id`) REFERENCES `admin` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `deployment_logs`
--
ALTER TABLE `deployment_logs`
  ADD CONSTRAINT `deployment_logs_ibfk_1` FOREIGN KEY (`deployment_id`) REFERENCES `deployments` (`id`) ON DELETE NO ACTION,
  ADD CONSTRAINT `deployment_logs_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION,
  ADD CONSTRAINT `deployment_logs_ibfk_3` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE NO ACTION,
  ADD CONSTRAINT `deployment_logs_ibfk_4` FOREIGN KEY (`deployment_leader_id`) REFERENCES `admin` (`id`) ON DELETE NO ACTION,
  ADD CONSTRAINT `deployment_logs_ibfk_5` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE NO ACTION;

--
-- Constraints for table `deployment_team_members`
--
ALTER TABLE `deployment_team_members`
  ADD CONSTRAINT `deployment_team_members_ibfk_1` FOREIGN KEY (`deployment_id`) REFERENCES `deployments` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `deployment_team_members_ibfk_2` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  ADD CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`currency`) REFERENCES `currencies` (`currency`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`currency`) REFERENCES `currencies` (`currency`);

--
-- Constraints for table `support_tickets`
--
ALTER TABLE `support_tickets`
  ADD CONSTRAINT `support_tickets_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  ADD CONSTRAINT `support_tickets_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `user_cart`
--
ALTER TABLE `user_cart`
  ADD CONSTRAINT `user_cart_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE;







--yung mga bago na need iquery

  ALTER TABLE `deployment_logs` DROP FOREIGN KEY deployment_logs_ibfk_3;
  ALTER TABLE `deployment_logs` ADD CONSTRAINT deployment_logs_ibfk_3 FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE;


  
ALTER TABLE `messages` DROP FOREIGN KEY messages_ibfk_1;
ALTER TABLE `messages`
ADD CONSTRAINT messages_ibfk_1 FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE;



ALTER TABLE `support_tickets` DROP FOREIGN KEY support_tickets_ibfk_1;
ALTER TABLE `support_tickets`
  ADD CONSTRAINT support_tickets_ibfk_1 FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE;

--contacts table, need ifix, delete muna lahat ng messages kasi lahat ng id nun 0

-- SELECT * FROM contacts WHERE id = 0;
-- DELETE FROM contacts WHERE id = 0;
-- ALTER TABLE `contacts` ADD PRIMARY KEY (id);
-- ALTER TABLE `contacts` MODIFY id INT(11) NOT NULL AUTO_INCREMENT;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
