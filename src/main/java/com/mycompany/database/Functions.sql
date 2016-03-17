USE ratercases;
DROP FUNCTION IF EXISTS `IMC`;
DELIMITER $$
CREATE FUNCTION `IMC` (altura INT,peso INT)
RETURNS DOUBLE
BEGIN
DECLARE AltM DOUBLE;
DECLARE AltSqr DOUBLE;
SET AltM=(altura/100);
SET AltSqr=AltM*AltM;
RETURN peso/AltSqr;
END $$
DELIMITER ;
DROP FUNCTION IF EXISTS `genderToInt`;
DELIMITER $$
CREATE FUNCTION `genderToInt` (gender char)
RETURNS INTEGER
BEGIN
CASE(gender)
	WHEN 'M' then return 0;
    else return 1;
END CASE;
END $$
DELIMITER ;
DROP FUNCTION IF EXISTS `SectionAvgAltitude`;
DELIMITER $$
CREATE FUNCTION `SectionAvgAltitude` (AI DOUBLE, AF DOUBLE)
RETURNS DOUBLE
BEGIN
RETURN (AI+AF)/2;
END $$
DELIMITER ;
DROP FUNCTION IF EXISTS `AgeToGroup`;
DELIMITER $$
CREATE FUNCTION `AgeToGroup` (Age INT)
RETURNS INTEGER
BEGIN
DECLARE grupo INTEGER;
CASE 
	WHEN(Age<18) then set grupo=0;
    WHEN(Age BETWEEN 18 AND 24) then set grupo=1;
    WHEN(Age BETWEEN 25 AND 35) then set grupo=2;
    WHEN(Age BETWEEN 36 AND 45) then set grupo=3;
    WHEN(Age BETWEEN 46 AND 54) then set grupo=4;
    WHEN(Age BETWEEN 55 AND 65) then set grupo=5;
    ELSE set grupo=6;
END CASE;
return grupo;
END $$
DELIMITER ;
DROP FUNCTION IF EXISTS `ModalToInt` ;
DELIMITER $$
CREATE FUNCTION `ModalToInt` (Modal VARCHAR(45))
RETURNS INTEGER
BEGIN
CASE (Modal)
	WHEN 'Caminhada' then return 0;
    ELSE return 1;
END CASE;
END $$
DELIMITER ;

DROP FUNCTION IF EXISTS `DifficToInt` ;
DELIMITER $$
CREATE FUNCTION `DifficToInt` (Diffic ENUM('easy','medium','hard'))
RETURNS INTEGER
BEGIN
CASE (Diffic)
	WHEN 'easy' then return 0;
    WHEN 'medium' then return 1;
    else return 2;
END CASE;
END $$
DELIMITER ;

