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
Drop function if Exists `latLonToRegion`;
DELIMITER $$

CREATE FUNCTION `latLonToRegion` (startLat double, startLon double, endLat double, endLon double)
RETURNS VARCHAR(50)
BEGIN
declare avgLat double;
declare avgLon double;
SET avgLat=(startLat+endLat)/2;
SET avgLon=(endLon+startLon)/2;
CASE
	WHEN avgLat between 36.965003 and 37.408667 then if(avgLon between -9.000971 and -7.395594) then return 'Algarve' ;else return 'Outra'; end if;
    WHEN avgLat between 37.408667 and 38.374813 then if(avgLon between -8.883119 and -6.935792) then return 'Alentejo' ;else return 'Outra'; end if;
    WHEN avgLat between 38.374813 and 39.754828 then if(avgLon between -9.500805 and -6.867947) then return 'Estremadura e Ribatejo'; else return 'Outra'; end if;
    WHEN avgLat between 39.754828 and 40.96941 then if(avgLon between -9.059689 and -6.796250) then return 'Beiras'; else return 'Outra'; end if;
    WHEN avgLat between 40.96941 and 42.154295 then CASE
		When avgLon between -8.875645 and -7.804478 then return 'Minho e Douro';
        When avgLon between -7.804478 and -6.189488 then return 'Tras os Montes';
        ELSE return 'Outra';
        END CASE;
	ELSE return 'Outra';
END CASE;
END;

Delimiter ;
Drop function if exists `percentUphill`;
DELIMITER $$
CREATE FUNCTION `percentUphill` (accumSub integer, totDist integer)
RETURNS Double
BEGIN
RETURN (accumSub/totDist)*100;
END;
DELIMITER ;
Drop function if exists `percentDownhill`;
Delimiter $$
CREATE FUNCTION `percentDownhill` (accumDesc integer, totDist integer)
RETURNS double
BEGIN
RETURN (accumDesc/totDist)*100;
END;
Delimiter ;

