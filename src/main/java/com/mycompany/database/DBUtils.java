/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author PedroJosé
 */
public class DBUtils {

    public static String toSQL(GregorianCalendar o) {
        if (o == null) {
            return "NULL";
        } else {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return "'" + df.format(o.getTime()) + "'";
        }
    }
    
    

    public static String toSQL(String o) {
        if (o == null) {
            return "NULL";
        } else {
            return "'" + o + "'";
        }
    }

    public static String toSQL(char o) {
        return "'" + o + "'";
    }

    public static String toSQL(boolean o) {
        return Boolean.toString(o);
    }

    public static String toSQL(int o) {
        return "'" + o + "'";
    }

    public static String toSQL(double o) {
        return "'" + o + "'";
    }

    public static String toSQL(float o) {
        return "'" + o + "'";
    }
    
    public static GregorianCalendar fromSQL(Date date) {
        if (date == null) {
            return null;
        }
        GregorianCalendar g = new GregorianCalendar();
        g.setTime(new java.util.Date(date.getTime()));
        return g;
    }
}
