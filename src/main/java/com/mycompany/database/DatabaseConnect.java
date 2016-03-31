/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.database;

import com.mycompany.business.MyRecord;
import com.mycompany.business.MyRecordList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.Properties;

/**
 *
 * @author PedroJosé
 */
public class DatabaseConnect {
    
    static final String className = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/";
    static final String Fullurl = "jdbc:mysql://localhost:3306/raterCases";
    static final String insertHeader = "INSERT INTO `raterCases`.`SectionRecord` VALUES";
    static final String selectSQL = "SELECT * FROM `raterCases`.`SectionRecord`;";
    
    public static void create() throws Exception {
        Class.forName(className);
        Connection connect = DriverManager.getConnection(url, "root", "");
        Statement stmt = connect.createStatement();
        stmt.execute("CREATE DATABASE IF NOT EXISTS raterCases");
        stmt.close();
        connect.close();
        connect = DriverManager.getConnection(Fullurl, "root", "");
        createTabIfNeeded(connect);
        connect.close();
    }
    
    private static void createTabIfNeeded(Connection conn) throws Exception {
        Statement ps = conn.createStatement();
        ps.executeUpdate("CREATE TABLE IF NOT EXISTS `ratercases`.`sectionrecord` ("
                + "  `username` VARCHAR(100) NOT NULL,"
                + "  `recordDate` DATETIME NOT NULL,"
                + "  `age` INT(11) NOT NULL,"
                + "  `height` INT(11) NOT NULL,"
                + "  `weight` INT(11) NOT NULL,"
                + "  `hasSportHistoric` TINYINT(1) NOT NULL,"
                + "  `hasWalkingHistoric` TINYINT(1) NOT NULL,"
                + "  `gender` CHAR(1) NOT NULL,"
                + "  `startLat` DOUBLE NOT NULL,"
                + "  `startLon` DOUBLE NOT NULL,"
                + "  `startAlt` DOUBLE NOT NULL,"
                + "  `endLat` DOUBLE NOT NULL,"
                + "  `endLon` DOUBLE NOT NULL,"
                + "  `endAlt` DOUBLE NOT NULL,"
                + "  `dist` DOUBLE NOT NULL,"
                + "  `altDiff` DOUBLE NOT NULL,"
                + "  `currSpd` DOUBLE NOT NULL,"
                + "  `avgSpd` DOUBLE NOT NULL,"
                + "  `accumSub` INT(11) NOT NULL,"
                + "  `accumDesc` INT(11) NOT NULL,"
                + "  `totDist` INT(11) NOT NULL,"
                + "  `modal` VARCHAR(45) NOT NULL,"
                + "  `load` INT(11) NOT NULL,"
                + "  `diffic` ENUM('easy', 'medium', 'hard') NOT NULL,"
                + "  PRIMARY KEY (`username`, `recordDate`, `age`, `height`, `weight`, `hasSportHistoric`, `hasWalkingHistoric`, `gender`, `startAlt`, `startLon`, `startLat`, `diffic`))"
                + "ENGINE = InnoDB;");
        ps.executeUpdate(
                "CREATE TABLE IF NOT EXISTS `ratercases`.`AnalysisRecord` ("
                + "  `ageband` INTEGER NOT NULL,"
                + "  `imc` DOUBLE NOT NULL,"
                + "  `height` INTEGER NOT NULL,"
                + "  `weight` INTEGER NOT NULL,"
                + "  `hasSportHistoric` TINYINT(1) NOT NULL,"
                + "  `hasWalkingHistoric` TINYINT(1) NOT NULL,"
                + "  `gender` INTEGER NOT NULL,"
                + "  `avgalt` DOUBLE NOT NULL,"
                + "  `dist` DOUBLE NOT NULL,"
                + "  `altDiff` DOUBLE NOT NULL,"
                + "  `currSpd` DOUBLE NOT NULL,"
                + "  `avgSpd` DOUBLE NOT NULL,"
                + "  `accumSub` INTEGER NOT NULL,"
                + "  `accumDesc` INTEGER NOT NULL,"
                + "  `totDist` INTEGER NOT NULL,"
                + "  `modal` INTEGER NOT NULL,"
                + "  `load` INTEGER NOT NULL,"
                + "  `diffic` INTEGER NOT NULL"
                + "  )"
                + "ENGINE = InnoDB;");
        /*
        Creating Body Mass Index Function;
         */
        ps.execute("DROP FUNCTION IF EXISTS `IMC`;");
        ps.execute("CREATE FUNCTION `IMC` (altura INT,peso INT)"
                + "RETURNS DOUBLE "
                + "BEGIN "
                + "DECLARE AltM DOUBLE;"
                + "DECLARE AltSqr DOUBLE;"
                + "SET AltM=(altura/100);"
                + "SET AltSqr=AltM*AltM;"
                + "RETURN peso/AltSqr;"
                + "END;");
        /*
        Creating Gender To Int Function
         */
        ps.execute("DROP FUNCTION IF EXISTS `genderToInt`;");
        ps.execute("CREATE FUNCTION `genderToInt` (gender char)\n"
                + "RETURNS INTEGER\n"
                + "BEGIN\n"
                + "CASE(gender)\n"
                + "	WHEN 'M' then return 0;\n"
                + "    else return 1;\n"
                + "END CASE;\n"
                + "END ;");
        /*
        Creating Section Average Altitude Function
         */
        ps.execute("DROP FUNCTION IF EXISTS `SectionAvgAltitude`;");
        ps.execute("CREATE FUNCTION `SectionAvgAltitude` (AI DOUBLE, AF DOUBLE)\n"
                + "RETURNS DOUBLE\n"
                + "BEGIN\n"
                + "RETURN (AI+AF)/2;\n"
                + "END;");
        /*
        Creating Age to AgeBand Function
         */
        ps.execute("DROP FUNCTION IF EXISTS `AgeToGroup`;");
        ps.execute("CREATE FUNCTION `AgeToGroup` (Age INT)\n"
                + "RETURNS INTEGER\n"
                + "BEGIN\n"
                + "DECLARE grupo INTEGER;\n"
                + "CASE \n"
                + "	WHEN(Age<18) then set grupo=0;\n"
                + "    WHEN(Age BETWEEN 18 AND 24) then set grupo=1;\n"
                + "    WHEN(Age BETWEEN 25 AND 35) then set grupo=2;\n"
                + "    WHEN(Age BETWEEN 36 AND 45) then set grupo=3;\n"
                + "    WHEN(Age BETWEEN 46 AND 54) then set grupo=4;\n"
                + "    WHEN(Age BETWEEN 55 AND 65) then set grupo=5;\n"
                + "    ELSE set grupo=6;\n"
                + "END CASE;\n"
                + "return grupo;\n"
                + "END; ");
        /*
        Creating Modality to Int Function
         */
        ps.execute("DROP FUNCTION IF EXISTS `ModalToInt` ;");
        ps.execute("CREATE FUNCTION `ModalToInt` (Modal VARCHAR(45))\n"
                + "RETURNS INTEGER\n"
                + "BEGIN\n"
                + "CASE (Modal)\n"
                + "	WHEN 'Caminhada' then return 0;\n"
                + "    ELSE return 1;\n"
                + "END CASE;\n"
                + "END;");
        /*
        Creating Difficulty to Int Function
         */
        ps.execute("DROP FUNCTION IF EXISTS `DifficToInt` ;");
        ps.execute("CREATE FUNCTION `DifficToInt` (Diffic ENUM('easy','medium','hard'))\n"
                + "RETURNS INTEGER\n"
                + "BEGIN\n"
                + "CASE (Diffic)\n"
                + "	WHEN 'easy' then return 0;\n"
                + "    WHEN 'medium' then return 1;\n"
                + "    else return 2;\n"
                + "END CASE;\n"
                + "END;");
        /*Finally The trigger that after inserting in the SectionRecords puts into the analysis Records.*/
        ps.execute("DROP TRIGGER IF EXISTS createAR;");
        ps.execute("CREATE TRIGGER createAR\n"
                + "AFTER INSERT ON sectionrecord\n"
                + "FOR EACH ROW\n"
                + "BEGIN\n"
                + "DECLARE ageband INT;\n"
                + "DECLARE intDiff INT;\n"
                + "DECLARE intGend INT;\n"
                + "DECLARE imc double;\n"
                + "DECLARE intModal INT;\n"
                + "DECLARE SectionAVGA DOUBLE;\n"
                + "SET ageband=AgeToGroup(NEW.age);\n"
                + "set intDiff=DifficToInt(new.diffic);\n"
                + "set intGend=genderToInt(new.gender);\n"
                + "set imc=IMC(new.height,new.weight);\n"
                + "set intModal=ModalToInt(new.modal);\n"
                + "set SectionAVGA=SectionAvgAltitude(new.startAlt,new.endAlt);\n"
                + "INSERT INTO analysisrecord\n"
                + "VALUES(ageband,imc,new.height,new.weight,new.hasSportHistoric,new.hasWalkingHistoric,intGend,SectionAVGA,new.dist,new.altDiff,new.currSpd,new.avgSpd,new.accumSub,new.accumDesc,new.totDist,intModal,new.`load`,intDiff);\n"
                + "END;");
        ps.close();
        
    }
    
    public static void insertInto(MyRecord mr) throws SQLException, ClassNotFoundException {
        Class.forName(className);
        Connection connect = DriverManager.getConnection(url, "root", "");
        String sqlInsert = insertHeader;
        sqlInsert += " (";
        sqlInsert += DBUtils.toSQL(mr.getUsername()) + ", ";
        sqlInsert += DBUtils.toSQL(mr.getDateTime()) + ", ";
        sqlInsert += mr.getAge() + ", ";
        sqlInsert += mr.getHeight() + ", ";
        sqlInsert += mr.getWeight() + ", ";
        sqlInsert += mr.getSportHistAsInt() + ", ";
        sqlInsert += mr.getWalkingHistAsInt() + ", ";
        sqlInsert += DBUtils.toSQL(mr.getGender()) + ", ";
        sqlInsert += mr.getStartLat() + ", ";
        sqlInsert += mr.getStartLon() + ", ";
        sqlInsert += mr.getStartAlt() + ", ";
        sqlInsert += mr.getEndLat() + ", ";
        sqlInsert += mr.getEndLon() + ", ";
        sqlInsert += mr.getEndAlt() + ", ";
        sqlInsert += mr.getDistance() + ", ";
        sqlInsert += mr.getAltDiff() + ", ";
        sqlInsert += mr.getCurrSpeed() + ", ";
        sqlInsert += mr.getAvgSpeed() + ", ";
        sqlInsert += mr.getAccumSub() + ", ";
        sqlInsert += mr.getAccumDesc() + ", ";
        sqlInsert += mr.getTotDist() + ", ";
        sqlInsert += DBUtils.toSQL(mr.getModal()) + ", ";
        sqlInsert += mr.getLoad() + ", ";
        sqlInsert += DBUtils.toSQL(mr.getDiffic());
        sqlInsert += ");";
        Statement stat = connect.createStatement();
        stat.executeUpdate(sqlInsert);
        stat.close();
        connect.close();
    }

    public static MyRecordList selectAll() throws Exception {
        MyRecordList mrl = new MyRecordList();
        Class.forName(className);
        Connection connect = DriverManager.getConnection(url, "root", "");
        Statement stat = connect.createStatement();
        ResultSet recordset = stat.executeQuery(selectSQL);
        while (recordset.next()) {
            String username = recordset.getNString(1);
            GregorianCalendar gc = DBUtils.fromSQL(recordset.getDate(2));
            int age = recordset.getInt(3);
            int height = recordset.getInt(4);
            int weight = recordset.getInt(5);
            boolean hasSportHist = recordset.getBoolean(6);
            boolean hasWalkHist = recordset.getBoolean(7);
            char gender = recordset.getString(8).charAt(0);
            double startLat = recordset.getDouble(9);
            double startLon = recordset.getDouble(10);
            double startAlt = recordset.getDouble(11);
            double endLat = recordset.getDouble(12);
            double endLon = recordset.getDouble(13);
            double endAlt = recordset.getDouble(14);
            float distance = recordset.getFloat(15);
            double altDiff = recordset.getDouble(16);
            float currSpd = recordset.getFloat(17);
            float avgSpd = recordset.getFloat(18);
            int accumSub = recordset.getInt(19);
            int accumDesc = recordset.getInt(20);
            int totDist = recordset.getInt(21);
            String modal = recordset.getString(22);
            int load = recordset.getInt(23);
            String diffic = recordset.getString(24);
            mrl.addRecord(new MyRecord(username, gc, age, height, weight, hasSportHist, hasWalkHist, gender, startLat, startLon, startAlt, endLat, endLon, endAlt, distance, altDiff, currSpd, avgSpd, accumSub, accumDesc, totDist, modal, load, diffic));
        }
        recordset.close();
        connect.close();
        return mrl;
    }
}
