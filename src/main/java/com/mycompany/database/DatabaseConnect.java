/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.database;

import com.mycompany.business.MyRecord;
import com.mycompany.business.MyRecordList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author PedroJosé
 */
public class DatabaseConnect {

    static final String className = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/";
    static final String insertHeader = "INSERT INTO `raterCases`.`SectionRecord` VALUES";
    static final String selectSQL = "SELECT * FROM `raterCases`.`SectionRecord`";

    public static void create() throws SQLException, ClassNotFoundException {
        Class.forName(className);
        Connection connect = DriverManager.getConnection(url, "root", "");
        Statement stmt = connect.createStatement();
        stmt.execute("CREATE DATABASE IF NOT EXISTS raterCases");
        stmt.close();
        createIfNeeded(connect);
        connect.close();
    }

    private static void createIfNeeded(Connection conn) throws SQLException {
        Statement ps = conn.createStatement();
        ps.executeUpdate("CREATE TABLE IF NOT EXISTS `raterCases`.`SectionRecord` ("
                + "  `username` VARCHAR(100) NOT NULL,"
                + "  `recordDate` DATETIME NOT NULL,"
                + "  `age` INT(11) NOT NULL,"
                + "  `height` INT(11) NOT NULL,"
                + "  `weight` INT(11) NOT NULL,"
                + "  `hasSportHistoric` TINYINT(1) NOT NULL,"
                + "  `hasWalkingHistoric` TINYINT(1) NOT NULL,"
                + "  `gender` CHAR NOT NULL,"
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

    public static MyRecordList selectAll() {
        //TODO!
        return new MyRecordList();
    }
}
