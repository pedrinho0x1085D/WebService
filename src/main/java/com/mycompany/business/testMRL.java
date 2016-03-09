/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.business;

/**
 *
 * @author PedroJosé
 */
public class testMRL {
    public static void main(String[] args) {
        try{
            System.out.println("here!");
            MyRecordList mrl=MyRecordList.getRecordsFromCSVFile("C:\\Users\\PedroJosé\\Google Drive\\MIEI\\SI\\2-PI\\Archive\\832016_143259PedroCunha.csv");
            System.out.println(mrl.getRecords().isEmpty());
            System.out.println(mrl.getRecords().size()+" distintos");
            mrl.addAllRecords(MyRecordList.getRecordsFromCSVFile("C:\\Users\\PedroJosé\\Google Drive\\MIEI\\SI\\2-PI\\Archive\\832016_143259PedroCunha.csv"));
            System.out.println(mrl.getRecords().size()+" distintos");
        }   
        catch (Exception e){}
    }
}
