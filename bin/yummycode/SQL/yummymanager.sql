-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 13, 2021 lúc 04:46 PM
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

DELIMITER $$
--
-- Thủ tục
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddNewAccount` (IN `AccID` CHAR(50), IN `UserName` CHAR(50), IN `Email` CHAR(50), IN `Pass` CHAR(50), IN `Type` CHAR(50))  BEGIN
INSERT INTO `tblaccount`(  `ACCOUNTID`, `NAME`, `EMAIL`, `PASSWORD`, `TYPE`) VALUES (AccID,UserName,Email,Pass,`Type`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewSaleCode` (IN `saleCode` TEXT, IN `dateStart` TEXT, IN `dateEnd` TEXT, IN `decription` TEXT, IN `discount` INT, IN `saleStatus` TEXT)  BEGIN
     	INSERT INTO  tblsaledetail (SALECODE,DATESTART,DATEEND,DECRIPTION,DISCOUNT,SALESTATUS) VALUES (saleCode,dateStart,dateEnd,decription,discount,saleStatus);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewTable` (IN `tableID` TEXT, IN `tableName` TEXT, IN `seatsNumber` INT, IN `floorsNumber` INT, IN `tableStatus` TEXT)  BEGIN
	INSERT INTO  tbltablemap (TABLEID,TABLENAME,SEATSNUMBER,FLOORNUMBER,TABLESTATUS) VALUES (tableID,tableName,seatsNumber,floorsNumber,tableStatus);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteAccByID` (IN `IDAcc` CHAR(50))  BEGIN
DELETE FROM `tblaccount` WHERE `ACCOUNTID` =  IDAcc;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `EditAccount` (IN `userName` CHAR(50), IN `email` CHAR(50), IN `pass` CHAR(50), IN `type` CHAR(50), IN `status` CHAR(50), IN `idAcc` CHAR(50))  BEGIN
	UPDATE `tblaccount` SET `NAME`= `userName` ,`EMAIL`= email ,`PASSWORD`= pass,`TYPE`= `type` ,`STATUS`= `status` WHERE `ACCOUNTID` = idAcc ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `editSaleCode` (IN `dateStart` TEXT, IN `dateEnd` TEXT, IN `decription` TEXT, IN `discount` INT, IN `saleStatus` TEXT, IN `saleCode` TEXT)  BEGIN
   UPDATE tblsaledetail SET  DATESTART = dateStart, DATEEND = dateEnd, DECRIPTION = decription , DISCOUNT = discount , SALESTATUS = saleStatus WHERE tblsaledetail.SALECODE = saleCode; 
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `editTableMap` (IN `tableName` TEXT, IN `seatsNumber` INT, IN `floorsNumber` INT, IN `tableStatus` TEXT, IN `tableID` TEXT)  BEGIN
  UPDATE tbltablemap SET  TABLENAME = tableName,SEATSNUMBER = seatsNumber,FLOORNUMBER = floorsNumber,TABLESTATUS = tableStatus WHERE tbltablemap.TABLEID = tableID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `filterDate` (IN `DateStart` CHAR(50), IN `DateEnd` CHAR(50))  BEGIN
SELECT * FROM `tblbill` WHERE DATETIME BETWEEN DateStart AND DateEnd ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllAccount` ()  BEGIN
   SELECT * FROM tblaccount;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllBillOrder` ()  BEGIN
SELECT o.*, c.DISCOUNT
FROM tblbill As o, tblsaledetail AS c
WHERE o.SALECODE=c.SALECODE;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllSaleCode` ()  BEGIN
 SELECT * FROM tblsaledetail;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllTable` ()  BEGIN
   SELECT * FROM tbltablemap;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getDataForBill` (IN `BillID` CHAR(50))  BEGIN
SELECT c.DETAILBILLID , a.FOODNAME,SUM(b.FOODQUANTITY) AS FOODQUANTITY,a.FOODPRICE,c.SUMOFPRICE from tblfoodmenu a JOIN tbldetailbill b on a.FOODID=b.FOODID JOIN tblbill c ON c.DETAILBILLID = b.DETAILBILLID WHERE c.DETAILBILLID = BillID GROUP BY a.FOODNAME;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `searchByNameAcc` (IN `inputSQL` CHAR(50))  BEGIN
	SELECT * FROM `tblaccount` WHERE `NAME` LIKE  ''%'+inputSQL+'%'';
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateOTP` (IN `otpCode` TEXT, IN `email` TEXT)  BEGIN
   UPDATE tblaccount SET OTP = otpCode  WHERE tblaccount.EMAIL = email;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updatePassword` (IN `passwordUpdate` TEXT, IN `email` TEXT)  BEGIN
    UPDATE tblaccount SET PASSWORD = passwordUpdate WHERE tblaccount.EMAIL = email;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tblaccount`
--

CREATE TABLE `tblaccount` (
  `ACCOUNTID` text NOT NULL,
  `NAME` text NOT NULL,
  `EMAIL` text NOT NULL,
  `PASSWORD` text NOT NULL,
  `TYPE` text NOT NULL,
  `STATUS` text NOT NULL,
  `OTP` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tblaccount`
--

INSERT INTO `tblaccount` (`ACCOUNTID`, `NAME`, `EMAIL`, `PASSWORD`, `TYPE`, `STATUS`, `OTP`) VALUES
('A01', 'Trần Đình Nam', 'Nam@gmail.com', 'Nam123456', 'Admin', 'Unlock', '0'),
('A02', 'Nguyễn Văn Luận', 'vanluan101002@gmail.com', 'mEeIqETmKS', 'Admin', 'Unlock', 'r6252vb');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tblbill`
--

CREATE TABLE `tblbill` (
  `BILLID` varchar(50) NOT NULL,
  `DATETIME` varchar(50) NOT NULL,
  `TABLEID` varchar(50) NOT NULL,
  `DETAILBILLID` varchar(50) NOT NULL,
  `SALECODE` varchar(50) NOT NULL,
  `SUMOFPRICE` double NOT NULL,
  `BillStatus` varchar(50) NOT NULL DEFAULT 'UnPaid'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tblbill`
--

INSERT INTO `tblbill` (`BILLID`, `DATETIME`, `TABLEID`, `DETAILBILLID`, `SALECODE`, `SUMOFPRICE`, `BillStatus`) VALUES
('D01', '2021-12-01', 'T01', 'D01', '88waF6k7', 100, 'Paid');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbldetailbill`
--

CREATE TABLE `tbldetailbill` (
  `DETAILBILLID` varchar(50) NOT NULL,
  `FOODID` varchar(50) NOT NULL,
  `TABLEID` varchar(50) NOT NULL,
  `FOODQUANTITY` int(10) NOT NULL,
  `SUMOFPRICE` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tbldetailbill`
--

INSERT INTO `tbldetailbill` (`DETAILBILLID`, `FOODID`, `TABLEID`, `FOODQUANTITY`, `SUMOFPRICE`) VALUES
('D01', 'F01', 'T01', 12, 360),
('D01', 'F02', 'T01', 13, 260),
('D01', 'F03', 'T01', 8, 400),
('D01', 'F04', 'T01', 1, 40);

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

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tblsaledetail`
--

CREATE TABLE `tblsaledetail` (
  `SALECODE` varchar(50) NOT NULL,
  `DATESTART` text NOT NULL,
  `DATEEND` text NOT NULL,
  `DECRIPTION` text NOT NULL,
  `DISCOUNT` int(11) NOT NULL,
  `SALESTATUS` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tblsaledetail`
--

INSERT INTO `tblsaledetail` (`SALECODE`, `DATESTART`, `DATEEND`, `DECRIPTION`, `DISCOUNT`, `SALESTATUS`) VALUES
('88waF6k7', '26/12/2021', '31/12/2023', 'Summer Time', 30, 'Off'),
('HJ4J3306', '16/12/2021', '23/12/2021', 'Tet Holiday', 30, 'On'),
('Null', 'Null', 'null', 'No apply discount', 0, 'On');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbltablemap`
--

CREATE TABLE `tbltablemap` (
  `TABLEID` varchar(50) NOT NULL,
  `TABLENAME` text NOT NULL,
  `SEATSNUMBER` int(11) NOT NULL,
  `FLOORNUMBER` int(11) NOT NULL,
  `TABLESTATUS` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tbltablemap`
--

INSERT INTO `tbltablemap` (`TABLEID`, `TABLENAME`, `SEATSNUMBER`, `FLOORNUMBER`, `TABLESTATUS`) VALUES
('T01', 'Bàn T01 tầng 1', 4, 1, 'Full'),
('T02', 'bàn T02 tầng 2', 4, 1, 'Full'),
('T03', 'Bàn T03 tầng 2', 4, 1, 'Empty'),
('T05', '0', 2, 1, 'Inactive'),
('T06', '0', 4, 1, 'Full'),
('T07', 'Bàn T07 tầng 1', 4, 1, 'Empty'),
('T08', 'Bàn T08 tầng 2', 2, 1, 'Empty'),
('T09', 'Bàn T09 tầng 2', 2, 1, 'Empty'),
('T10', 'Bàn T10 tầng 2', 2, 1, 'Empty'),
('T11', 'Bàn T11 tầng 2', 4, 1, 'Full'),
('T12', 'Bàn T12 tầng 2', 4, 1, 'Empty'),
('T13', 'Bàn T13 tầng 2', 4, 2, 'Empty'),
('T14', 'bàn T14 tầng 1', 2, 2, 'Empty'),
('T15', 'Bàn T15 tầng 2', 4, 2, 'Full'),
('T16', 'Bàn T16 tầng 2', 4, 2, 'Empty'),
('T17', 'Bàn T17 tầng 1', 2, 2, 'Full'),
('T18 ', 'Bàn T18 tầng 2', 2, 2, 'Full'),
('T20', 'Bàn T20 tầng 2', 4, 2, 'Full'),
('T23', 'Bàn T23 tầng 2', 4, 2, 'Empty');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbltypefood`
--

CREATE TABLE `tbltypefood` (
  `ID` int(20) NOT NULL,
  `TYPE` text NOT NULL,
  `STATUS` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tbltypefood`
--

INSERT INTO `tbltypefood` (`ID`, `TYPE`, `STATUS`) VALUES
(1, 'Main Food', 'UnLock'),
(2, 'Drinks', 'UnLock'),
(3, 'Hot Pot', 'UnLock'),
(4, 'Salad', 'UnLock'),
(5, 'Dessert', 'UnLock');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `tblbill`
--
ALTER TABLE `tblbill`
  ADD PRIMARY KEY (`BILLID`,`TABLEID`,`DETAILBILLID`),
  ADD KEY `FKTABLEID` (`TABLEID`),
  ADD KEY `FK_DEATILBILLID` (`DETAILBILLID`),
  ADD KEY `FK_SALECODE` (`SALECODE`);

--
-- Chỉ mục cho bảng `tbldetailbill`
--
ALTER TABLE `tbldetailbill`
  ADD PRIMARY KEY (`DETAILBILLID`,`FOODID`,`TABLEID`),
  ADD KEY `FKBILLTABLEID` (`TABLEID`),
  ADD KEY `FK_DETAIL_FOODID` (`FOODID`);

--
-- Chỉ mục cho bảng `tblfoodmenu`
--
ALTER TABLE `tblfoodmenu`
  ADD PRIMARY KEY (`FOODID`);

--
-- Chỉ mục cho bảng `tblsaledetail`
--
ALTER TABLE `tblsaledetail`
  ADD PRIMARY KEY (`SALECODE`);

--
-- Chỉ mục cho bảng `tbltablemap`
--
ALTER TABLE `tbltablemap`
  ADD PRIMARY KEY (`TABLEID`);

--
-- Chỉ mục cho bảng `tbltypefood`
--
ALTER TABLE `tbltypefood`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `tbltypefood`
--
ALTER TABLE `tbltypefood`
  MODIFY `ID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `tblbill`
--
ALTER TABLE `tblbill`
  ADD CONSTRAINT `FKTABLEID` FOREIGN KEY (`TABLEID`) REFERENCES `tbltablemap` (`TABLEID`),
  ADD CONSTRAINT `FK_DEATILBILLID` FOREIGN KEY (`DETAILBILLID`) REFERENCES `tbldetailbill` (`DETAILBILLID`),
  ADD CONSTRAINT `FK_SALECODE` FOREIGN KEY (`SALECODE`) REFERENCES `tblsaledetail` (`SALECODE`);

--
-- Các ràng buộc cho bảng `tbldetailbill`
--
ALTER TABLE `tbldetailbill`
  ADD CONSTRAINT `FKBILLTABLEID` FOREIGN KEY (`TABLEID`) REFERENCES `tbltablemap` (`TABLEID`),
  ADD CONSTRAINT `FK_DETAIL_FOODID` FOREIGN KEY (`FOODID`) REFERENCES `tblfoodmenu` (`FOODID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
