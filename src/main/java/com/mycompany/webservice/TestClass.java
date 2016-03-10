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
            String json = myRL.toJSonString();
            try {
                System.out.println(json);
                MyRecordList mrl2=MyRecordList.JSonToObj(json);
                System.out.println(mrl2.toJSonString());
            } catch (Exception e) {
                System.out.println("Exception!");
            }
            return json;
        });
    }

}
