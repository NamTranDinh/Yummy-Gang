-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 18, 2021 lúc 04:27 PM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `yummymanager`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tblfoodmenu`
--

CREATE TABLE `tblfoodmenu` (
  `FOODID` varchar(50) NOT NULL,
  `FOODIMAGE` text NOT NULL,
  `FOODNAME` text NOT NULL,
  `FOODTYPE` text NOT NULL,
  `FOODMATERIAL` text NOT NULL,
  `FOODSTATUS` text NOT NULL DEFAULT 'Active',
  `FOODPRICE` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tblfoodmenu`
--

INSERT INTO `tblfoodmenu` (`FOODID`, `FOODIMAGE`, `FOODNAME`, `FOODTYPE`, `FOODMATERIAL`, `FOODSTATUS`, `FOODPRICE`) VALUES
('F01', 'img1.jpg', 'Italy Noodle', 'Fast food', 'beff,salat,tomato,cheese', 'Active', 30),
('F02', 'img2.jpg', 'Ramen', 'Fast food', 'noodle,tomato and meat source', 'Active', 20),
('F03', 'img3.jpg', 'Seafood fried noodles', 'Fast food', 'a,b,c,d', 'Active', 50),
('F04', 'img4.jpg', 'Piza', 'Fast Food', 'x,y,z', 'Active', 40),
('F05', 'img5.jpg', 'Fried seafood noodles', 'Main food', '', 'Active', 50),
('F06', 'img6.jpg', 'Thai hot pot', 'Hot pot', '', 'Active', 50),
('F07', 'img7.jpg', 'KFC Chicken', 'Fast Food', '', 'Active', 50),
('F08', 'img8.jpg', 'Cocacola', 'Drinks', '', 'Active', 50),
('F09', 'img9.jpg', 'Pepsi', 'Drinks', '', 'Active', 50),
('F10', 'img10.jpg', 'Orange juice\n', 'Drinks', '', 'Active', 50),
('F11', 'img11.jpg', 'Iced tea\n', 'Drinks', '', 'Active', 50),
('F12', 'img12.jpg', 'Seafood fried rice', 'Main food', '', 'Active', 50),
('F13', 'img13.jpg', 'Beef fried rice', 'Main food', '', 'Active', 50),
('F14', 'img14.jpg', 'French fries', 'Fast Food', '', 'Active', 50),
('F15', 'img15.jpg', 'Fried spring rolls', 'Fast Food', '', 'Active', 50),
('F16', 'img16.jpg', 'Sweet potato cocoon', 'Fast Food', '', 'Active', 50),
('F17', 'img17.jpg', 'Stir-fried quail eggs with tamarind', 'Fast Food', '', 'Active', 50),
('F18', 'img18.jpg', 'Garlic butter toast\n', 'Fast Food', '', 'Active', 50),
('F19', 'img19.jpg', 'Hot dog', 'Fast Food', '', 'Active', 50),
('F20', 'img20.jpg', '7up', 'Drinks', '', 'Active', 50);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `tblfoodmenu`
--
ALTER TABLE `tblfoodmenu`
  ADD PRIMARY KEY (`FOODID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
