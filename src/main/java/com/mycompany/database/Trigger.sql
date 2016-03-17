use ratercases;
DELIMITER $$
CREATE TRIGGER createAR
AFTER INSERT ON sectionrecord
FOR EACH ROW
BEGIN
DECLARE ageband INT;
DECLARE intDiff INT;
DECLARE intGend INT;
DECLARE imc double;
DECLARE intModal INT;
DECLARE SectionAVGA DOUBLE;
SET ageband=AgeToGroup(NEW.age);
set intDiff=DifficToInt(new.diffic);
set intGend=genderToInt(new.gender);
set imc=IMC(new.height,new.weight);
set intModal=ModalToInt(new.modal);
set SectionAVGA=SectionAvgAltitude(new.startAlt,new.endAlt);
INSERT INTO analysisrecord
VALUES(ageband,imc,new.height,new.weight,new.hasSportHistoric,new.hasWalkingHistoric,intGend,SectionAVGA,new.dist,new.altDiff,new.currSpd,new.avgSpd,new.accumSub,new.accumDesc,new.totDist,intModal,new.`load`,intDiff);
END$$
DELIMITER ;