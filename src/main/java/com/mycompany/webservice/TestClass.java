package com.mycompany.webservice;

import com.mycompany.business.MyRecordList;
import static spark.Spark.*;

public class TestClass {

    public static void main(String[] args) {

        get("/hello", (req, repl) -> req.toString());
        get("/test", (req, repl) -> {
            MyRecordList myRL, testParsing;
            myRL = MyRecordList.getRecordsFromCSVFile(req.queryParams("file"));
            System.out.println(myRL.getRecords().size());
            String json = myRL.toJsonString();
            try {
                System.out.println(json);
                MyRecordList mrl2=MyRecordList.fromJsonString(json);
                System.out.println(mrl2.toJsonString());
                System.out.println(mrl2.getRecords().size());
            } catch (Exception e) {
                System.out.println("Exception!");
            }
            return "ok";
        });
    }

}
