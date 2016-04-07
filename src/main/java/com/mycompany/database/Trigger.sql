Drop Trigger if exists createAR;
use ratercases;
DELIMITER $$
CREATE TRIGGER createAR
AFTER INSERT ON sectionrecord
FOR EACH ROW
BEGIN
DECLARE ageband INT;
DECLARE intGend INT;
DECLARE imc double;
DECLARE intModal INT;
DECLARE SectionAVGA DOUBLE;
DECLARE Region Varchar(50);
DECLARE PercentUphill DOUBLE;
DECLARE PercentDownhill DOUBLE;
SET ageband=AgeToGroup(NEW.age);
set intGend=genderToInt(new.gender);
set imc=IMC(new.height,new.weight);
set intModal=ModalToInt(new.modal);
set SectionAVGA=SectionAvgAltitude(new.startAlt,new.endAlt);
set Region=latLonToRegion(new.startLat,new.startLon,new.endLat,new.endLon);
set PercentUphill=percentUphill(new.accumSub,new.totDist);
set PercentDownhill=percentDownhill(new.accumDesc,new.totDist);
INSERT INTO analysisrecord
VALUES(ageband,imc,new.height,new.weight,new.hasSportHistoric,new.hasWalkingHistoric,intGend,SectionAVGA,new.dist,new.altDiff,new.currSpd,new.avgSpd,new.accumSub,new.accumDesc,new.totDist,intModal,new.`load`,Region,PercentUphill,PercentDownhill,DAY(new.recordDate),MONTH(new.recordDate),hour(new.recordDate),minute(new.recordDate),new.diffic);
END$$
DELIMITER ;