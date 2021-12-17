CREATE TABLE IF NOT EXISTS `Department` (
  `Deptid` int NOT NULL AUTO_INCREMENT,
  `Deptname` varchar(50) NOT NULL,
  `Description` varchar(100),
  `EstablishedYear` varchar(10),
  PRIMARY KEY (`Deptid`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 ;


CREATE TABLE IF NOT EXISTS `Manager` (
  `Managerid` int NOT NULL AUTO_INCREMENT,
  `Deptid` int NOT NULL,
  `Fname` varchar(50) NOT NULL,
  `Lname` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Street` varchar(50) NOT NULL,
  `Area` varchar(50) NOT NULL,
  `Pincode` long NOT NULL,
  `Contact` varchar(20) NOT NULL,
  `Email` varchar(100) NOT NULL,
   PRIMARY KEY (`Managerid`),
   FOREIGN KEY(`Deptid`) references Department(`Deptid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `Retailer`(
	`Retailerid` int NOT NULL AUTO_INCREMENT,
    `Fname` varchar(50) NOT NULL,
	`Lname` varchar(50) NOT NULL,
    `Contact` varchar(20) NOT NULL,
	`Email` varchar(100) NOT NULL,
     PRIMARY KEY (`Retailerid`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `Staff`(
	`Staffid` int NOT NULL AUTO_INCREMENT,
    `Fname` varchar(50) NOT NULL,
    `Lname` varchar(50) NOT NULL,
    `Password` varchar(50) NOT NULL,
    `Gender` varchar(50) NOT NULL,
    `Contact` varchar(20) NOT NULL,
	`Email` varchar(100) NOT NULL,
    `Street` varchar(50) NOT NULL,
    `Area` varchar(50) NOT NULL,
    `Pincode` long NOT NULL,
    `HandLesShelf` int DEFAULT 0,
    PRIMARY KEY (`Staffid`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `Shelf`(
	`Shelfid` int NOT NULL AUTO_INCREMENT,
    `Handler` int DEFAULT NULL,
    `Floor` int NOT NULL,
    `Description` varchar(100),
    PRIMARY KEY (`Shelfid`),
	FOREIGN KEY (`Handler`) references Staff(`Staffid`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `Student`(
	`Studentid` int NOT NULL AUTO_INCREMENT,
    `Fname` varchar(50) NOT NULL,
    `Lname` varchar(50) NOT NULL,
    `Password` varchar(50) NOT NULL,
    `Age` int NOT NULL,
    `Gender` varchar(50) NOT NULL,
    `Contact` varchar(20) NOT NULL,
	`Email` varchar(100) NOT NULL,
    `Street` varchar(50) NOT NULL,
    `Area` varchar(50) NOT NULL,
    `Pincode` long NOT NULL,
    `BooksIssued` int DEFAULT 0,
    PRIMARY KEY (`Studentid`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `Stock`(
	`title` varchar(50) NOT NULL,
    `author` varchar(50) NOT NULL ,
    `publications` varchar(50) NOT NULL,
    `booksAvailable` int NOT NULL DEFAULT 0,
    `booksIssued` int NOT NULL DEFAULT 0,
    PRIMARY KEY(TITLE,AUTHOR,PUBLICATIONS)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `Book`(
	`bookid` int NOT NULL AUTO_INCREMENT,
    `Shelfid` int,
    `title` varchar(50) NOT NULL,
    `author` varchar(50) NOT NULL ,
    `publications` varchar(50) NOT NULL,
    `IsIssued` boolean DEFAULT FALSE,
    `Language` varchar(50) NOT NULL,
    PRIMARY KEY(Bookid),
    foreign key(Shelfid) references Shelf(shelfid),
    foreign key(TITLE,AUTHOR,PUBLICATIONS) references Stock(TITLE,AUTHOR,PUBLICATIONS)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `BookIssue`(
	`Bookid` int NOT NULL,
    `Studentid` int NOT NULL,
    `IssueDate` date NOT NULL,
    `ReturnDate` date NOT NULL,
    `DaysGap` int,
    foreign key(Bookid) references Book(bookid),
    foreign key(Studentid) references Student(studentid),
    KEY (BOOKID,STUDENTID) 
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE STUDENT AUTO_INCREMENT=100;
ALTER TABLE STAFF AUTO_INCREMENT=100;
ALTER TABLE DEPARTMENT AUTO_INCREMENT=100;
ALTER TABLE BOOK AUTO_INCREMENT=100;
ALTER TABLE MANAGER AUTO_INCREMENT=100;
ALTER TABLE SHELF AUTO_INCREMENT=100;
ALTER TABLE RETAILER AUTO_INCREMENT=100;

DELIMITER $$
CREATE TRIGGER check_valid_contact BEFORE INSERT ON Student
FOR EACH ROW
BEGIN
IF (NEW.Contact REGEXP '^[1-9][0-9]{9,9}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong Phone Number Format';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_valid_Email BEFORE INSERT ON Student
FOR EACH ROW
BEGIN
IF (NEW.Email REGEXP '^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = 'Invalid email address';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_valid_contact_update BEFORE UPDATE ON Student
FOR EACH ROW
BEGIN
IF (NEW.Contact REGEXP '^[1-9][0-9]{9,9}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong Phone Number Format';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_valid_Email_update BEFORE UPDATE ON Student
FOR EACH ROW
BEGIN
IF (NEW.Email REGEXP '^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = 'Invalid email address';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_correct_contact BEFORE INSERT ON Manager
FOR EACH ROW
BEGIN
IF (NEW.Contact REGEXP '^[1-9][0-9]{9,9}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong Phone Number Format';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_correct_Email BEFORE INSERT ON Manager
FOR EACH ROW
BEGIN
IF (NEW.Email REGEXP '^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = 'Invalid email address';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_correct_contact_updated BEFORE UPDATE ON Manager
FOR EACH ROW
BEGIN
IF (NEW.Contact REGEXP '^[1-9][0-9]{9,9}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong Phone Number Format';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_correct_Email_updated BEFORE UPDATE ON Manager
FOR EACH ROW
BEGIN
IF (NEW.Email REGEXP '^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = 'Invalid email address';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_contact BEFORE INSERT ON Retailer
FOR EACH ROW
BEGIN
IF (NEW.Contact REGEXP '^[1-9][0-9]{9,9}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong Phone Number Format';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_Email BEFORE INSERT ON Retailer
FOR EACH ROW
BEGIN
IF (NEW.Email REGEXP '^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = 'Invalid email address';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_contact_updated BEFORE UPDATE ON Retailer
FOR EACH ROW
BEGIN
IF (NEW.Contact REGEXP '^[1-9][0-9]{9,9}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong Phone Number Format';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_Email_updated BEFORE UPDATE ON Retailer
FOR EACH ROW
BEGIN
IF (NEW.Email REGEXP '^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = 'Invalid email address';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER validate_contact BEFORE INSERT ON Staff
FOR EACH ROW
BEGIN
IF (NEW.Contact REGEXP '^[1-9][0-9]{9,9}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong Phone Number Format';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER validate_Email BEFORE INSERT ON Staff
FOR EACH ROW
BEGIN
IF (NEW.Email REGEXP '^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = 'Invalid email address';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER validate_contact_updated BEFORE UPDATE ON Staff
FOR EACH ROW
BEGIN
IF (NEW.Contact REGEXP '^[1-9][0-9]{9,9}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong Phone Number Format';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER validate_Email_updated BEFORE UPDATE ON Staff
FOR EACH ROW
BEGIN
IF (NEW.Email REGEXP '^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = 'Invalid email address';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER validate_floor BEFORE INSERT ON Shelf
FOR EACH ROW
BEGIN
IF(NEW.Floor > 3 or NEW.Floor < 0) THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = 'No such floor exists in the library';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER update_validate_floor BEFORE UPDATE ON Shelf
FOR EACH ROW
BEGIN
IF(NEW.Floor > 3 or NEW.Floor < 0) THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = 'No such floor exists in the library';
END IF;
END$$
DELIMITER ;

-- DELIMITER $$
-- CREATE PROCEDURE InsertDaysGap(IN Bookid int, IN Studentid int , IN IssueDate date, IN ReturnDate date)
-- BEGIN
-- 	INSERT INTO BookIssue VALUES(Bookid,Studentid,IssueDate,ReturnDate,DATEDIFF(ReturnDate,IssueDate));
-- END$$
-- DELIMITER ;

-- DELIMITER $$
-- CREATE TRIGGER insert_in_Table BEFORE INSERT ON bookissue
-- FOR EACH ROW
-- BEGIN
-- 	CALL InsertDaysGap(101,101,'2019-03-21','2019-05-21');
-- END$$
-- DELIMITER ;

-- CALL InsertDaysGap(101,101,'2019-03-21','2019-05-21');
