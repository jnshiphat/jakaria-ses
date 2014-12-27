-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 04, 2014 at 05:43 AM
-- Server version: 5.5.40-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `atp1db`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE IF NOT EXISTS `cart` (
  `cartId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`cartId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`cartId`, `userId`, `productId`, `quantity`, `price`) VALUES
(7, 1, 3, 4, 1800),
(9, 1, 4, 1, 1800),
(10, 1, 1, 2, 900);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(20) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`pid`, `pname`, `price`) VALUES
(1, 'Mouse', 450),
(2, 'Speaker', 1800),
(3, 'Headphone', 450),
(4, 'Bluetooth-Mouse', 1800);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `bday` int(11) NOT NULL,
  `bmonth` int(11) NOT NULL,
  `byear` int(11) NOT NULL,
  `phone` varchar(16) NOT NULL,
  `email` varchar(24) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `pass` varchar(41) NOT NULL,
  `visit` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `email_2` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `fname`, `lname`, `bday`, `bmonth`, `byear`, `phone`, `email`, `gender`, `pass`, `visit`) VALUES
(1, 'Jakaria', 'Nawaz', 2, 5, 1990, '+8801912858123', 'b2nawaz@gmail.com', 'male', '123', 0),
(2, 'Amir', 'Ibrahim', 1, 5, 1990, '+8801829672374', 'amir@gmail.com', 'male', '123', 0),
(3, 'Maleka', 'Banu', 12, 5, 1991, '+8801829672374', 'urmi.gut@gmail.com', 'female', '929', 0),
(4, 'Rajib', 'Ahmed', 3, 4, 1990, '0123654789', 'rajib@gmail.com', 'male', '123', 0),
(5, 'Amir', 'Ibrahim', 5, 6, 1990, '15468987', 'abce@gmail.com', 'male', '123', 0),
(6, 'Nabil', 'Ibn Alamgir', 10, 7, 1988, '12345462', 'nabil@gmail.com', 'male', '123', 0),
(7, 'Mohammad', 'Araf', 5, 8, 1994, '12345678965', 'araf@gmail.com', 'male', '123', 0),
(8, 'Taz', 'Selim', 4, 9, 1990, '12345678965', 'taz@gmail.com', 'male', '123', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
