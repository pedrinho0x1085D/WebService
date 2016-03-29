package com.mycompany.webservice;

import com.mycompany.business.MyRecord;
import com.mycompany.business.MyRecordList;
import com.mycompany.database.DatabaseConnect;
import static spark.Spark.*;

public class TestClass {

    public static void main(String[] args) {
        try {
            DatabaseConnect.create();
        } catch (Exception e) {
            System.out.println("deu estrondo mano");
            e.printStackTrace();
        }
        get("/hello", (req, repl) -> req.toString());
        post("/insert", (req, repl) -> {
            String json = req.body();
            MyRecordList myRL = MyRecordList.fromJsonString(json);
            System.out.println(myRL.getRecords().size());
            for (MyRecord mr : myRL.getRecords()) {
                try {
                    DatabaseConnect.insertInto(mr);
                } catch (Exception e) {
                }
                System.out.println("Done");
            }
            return "ok";
        });
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
            try {
                //lista = parse from req; 
                MyRecordList mrl = DatabaseConnect.selectAll();
                
                System.out.println(mrl.getRecords().size());
                return "" + mrl.getRecords().size() + " records available";
            } catch (Exception e) {
                return "err";
            }
            //manda para avaliador; obtém resposta manda de volta.            

        });
    }

}
