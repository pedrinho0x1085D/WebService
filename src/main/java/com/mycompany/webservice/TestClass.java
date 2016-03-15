package com.mycompany.webservice;

import com.mycompany.business.MyRecord;
import com.mycompany.business.MyRecordList;
import com.mycompany.database.DatabaseConnect;
import java.sql.Connection;
import static spark.Spark.*;

public class TestClass {

    public static void main(String[] args) {
        try {
            DatabaseConnect.create();
        } catch (Exception e) {
            System.out.println("deu estrondo mano");
        }
        get("/hello", (req, repl) -> req.toString());
        get("/test", (req, repl) -> {
            MyRecordList myRL;
            myRL = MyRecordList.fromJsonString(MyRecordList.getRecordsFromCSVFile(req.queryParams("file")).toJsonString());
            System.out.println(myRL.getRecords().size());
            for (MyRecord mr : myRL.getRecords()) {
                try {
                    DatabaseConnect.insertInto(mr);
                } catch (Exception e) {
                }
            }
            return "ok";
        });
        get("/eval", (req, repl) -> {
            //select * from section records.
            //manda para avaliador; obtém resposta manda de volta.            
            return "ok";
            
        });
    }

}
