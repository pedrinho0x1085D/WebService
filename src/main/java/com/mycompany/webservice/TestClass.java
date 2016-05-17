package com.mycompany.webservice;

import com.mycompany.business.MyRecord;
import com.mycompany.business.MyRecordList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static spark.Spark.*;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
/*
http://stackoverflow.com/questions/14073874/interpretation-of-classification-in-weka -> Resultados do classify;
VER A CENA DA ULTIMA POSIÇÃO DA INSTANCIA
*/
public class TestClass {
    
    public static void main(String[] args) {
        
        port(getHerokuAssignedPort());
        Instances trainset = new Instances("AnalysisRecords", attrInfo(), 25000);
        trainset.setClass(trainset.attribute(23));
        get("/hello", (req, repl) -> {
            System.out.println("sup??");
            return "sup??";
        });
        post("/insert", (req, repl) -> {
            String json=new String(req.bodyAsBytes());
            MyRecordList myRL=MyRecordList.fromJsonString(json);
            for (MyRecord mr : myRL.getRecords()) {
                Instance inst = mr.toWekaInstance(trainset);
                trainset.add(inst);
            }       
            
            return "ok";
        });
        
        get("/jsonFromCSV", (req,repl)->{
            return MyRecordList.getRecordsFromCSVFile(req.queryParams("file")).toJsonString();
        });
        
        get("/test", (req, repl) -> {
            MyRecordList myRL;
            myRL = MyRecordList.getRecordsFromCSVFile(req.queryParams("file"));
            System.out.println(myRL.getRecords().size());
            for (MyRecord mr : myRL.getRecords()) {
                Instance inst = mr.toWekaInstance(trainset);
                trainset.add(inst);
            }
            return trainset.numInstances();

        }
        );
        get("/buildClassifier", (req, repl) -> {
            J48 classifier = new J48();
            try {
                classifier.setOptions(weka.core.Utils.splitOptions("-C 0.25 -M 2"));
            } catch (Exception e) {
                System.out.println("estrondo no parsing de opções ó mano");
            }
            String toSend = "";
            try {
                classifier.buildClassifier(trainset);
            } catch (Exception e) {
                e.printStackTrace();
            }
            toSend = classifier.prefix();
            return toSend;
        });
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    static FastVector attrInfo() {
        FastVector returnVect = new FastVector();
        FastVector difficValues = new FastVector();
        difficValues.addElement("easy");
        difficValues.addElement("medium");
        difficValues.addElement("hard");
        returnVect.addElement(new Attribute("ageband"));
        returnVect.addElement(new Attribute("imc"));
        returnVect.addElement(new Attribute("weight"));
        returnVect.addElement(new Attribute("hasSportHistoric"));
        returnVect.addElement(new Attribute("hasWalkingHistoric"));
        returnVect.addElement(new Attribute("gender"));
        returnVect.addElement(new Attribute("avgalt"));
        returnVect.addElement(new Attribute("dist"));
        returnVect.addElement(new Attribute("altDiff"));
        returnVect.addElement(new Attribute("currSpd"));
        returnVect.addElement(new Attribute("avgSpd"));
        returnVect.addElement(new Attribute("accumSub"));
        returnVect.addElement(new Attribute("accumDesc"));
        returnVect.addElement(new Attribute("totDist"));
        returnVect.addElement(new Attribute("modal"));
        returnVect.addElement(new Attribute("load"));
        returnVect.addElement(new Attribute("region"));
        returnVect.addElement(new Attribute("percentUphill"));
        returnVect.addElement(new Attribute("percentDownhill"));
        returnVect.addElement(new Attribute("day"));
        returnVect.addElement(new Attribute("month"));
        returnVect.addElement(new Attribute("hour"));
        returnVect.addElement(new Attribute("minute"));
        returnVect.addElement(new Attribute("diffic", difficValues));
        return returnVect;
    }
}
