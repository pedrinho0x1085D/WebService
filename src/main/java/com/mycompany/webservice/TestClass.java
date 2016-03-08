package com.mycompany.webservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mycompany.business.MyRecordList;
import static spark.Spark.*;

public class TestClass {

    private static ObjectWriter jsonWriter = new ObjectMapper().writer();

    public static void main(String[] args) {
        
        get("/hello", (req, repl) -> req.toString());
        get("/test", (req, repl) -> {
            MyRecordList myRL;
            myRL = MyRecordList.getRecordsFromCSVFile(req.queryParams("file"));
            System.out.println(myRL.getRecords().size());
            return jsonWriter.writeValueAsString(myRL.getRecords());
        });
    }

}
