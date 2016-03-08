/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.business;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author PedroJosé
 */
public class MyRecordList implements Serializable{

    private ArrayList<MyRecord> records;

    public MyRecordList() {
        this.records = new ArrayList<>();
    }

    public void addRecord(MyRecord mr) {
        this.records.add(mr);
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

}
