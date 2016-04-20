package com.mycompany.webservice;

import com.mycompany.business.MyRecord;
import com.mycompany.business.MyRecordList;
import com.mycompany.database.DatabaseConnect;
import static spark.Spark.*;

public class TestClass {

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        try {
            DatabaseConnect.create();
        } catch (Exception e) {
            System.out.println("deu estrondo mano");
            e.printStackTrace();
        }

        get(
                "/hello", (req, repl) -> {
                    System.out.println("sup");
                    return "sup??";
                }
        );
        post(
                "/insert", (req, repl) -> {
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
                }
        );
        get(
                "/test", (req, repl) -> {
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
                }
        );
        get(
                "/eval", (req, repl) -> {
                    try {
                        //lista = parse from req; 
                        MyRecordList mrl = DatabaseConnect.selectAll();

                        System.out.println(mrl.getRecords().size());
                        return "" + mrl.getRecords().size() + " records available";
                    } catch (Exception e) {
                        return "err";
                    }
                    //manda para avaliador; obtém resposta manda de volta.            

                }
        );
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}
