/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.business;

/*import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.TypeFactory;*/
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PedroJosé
 */
public class MyRecordList implements Serializable {

    private ArrayList<MyRecord> records;

    public MyRecordList() {
        this.records = new ArrayList<>();
    }

    public void addRecord(MyRecord mr) {
        if (!this.records.contains(mr)) {
            this.records.add(mr);
        }
    }

    public void addAllRecords(MyRecordList mrl) {
        for (MyRecord mr : mrl.getRecords()) {
            this.addRecord(mr);
        }
    }

    public ArrayList<MyRecord> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<MyRecord> records) {
        this.records = records;
    }

    public static MyRecordList getRecordsFromCSVFile(String pathToFile) {
        try {
            MyRecordList mrl = new MyRecordList();
            String line;
            FileInputStream fis = new FileInputStream(pathToFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            br.readLine();
            while ((line = br.readLine()) != null) {
                mrl.addRecord(MyRecord.parseMyRecord(line));
            }
            return mrl;
        } catch (Exception e) {
        }
        return new MyRecordList();
    }

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static MyRecordList fromJsonString(String json) {
        Gson gson = new Gson();
        return (MyRecordList) gson.fromJson(json, MyRecordList.class);
    }
    /*
    public String toJSonString() throws Exception {
        ObjectWriter jsonWriter = new ObjectMapper().writer();
        return jsonWriter.writeValueAsString(this.records);
    }

    public static MyRecordList JSonToObj(String json) throws IOException {
        MyRecordList mrl = new MyRecordList();
        ObjectMapper mapper = new ObjectMapper();
        List<MyRecord> lista;
        lista = mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class,
                MyRecord.class));
        ArrayList<MyRecord> list = new ArrayList<>();
        lista.stream().forEach((mr) -> {
            list.add(mr);
        });
        mrl.setRecords(list);

        return mrl;
    }*/
}
