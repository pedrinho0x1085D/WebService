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
public class User {

    private String username;
    private int age, height, weight;
    private boolean hasSportHistoric, hasWalkingHistoric;
    private char gender;

    public User(String username, int age, int height, int weight, boolean hasSportHistoric, boolean hasWalkingHistoric, char gender) {
        this.username = username;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.hasSportHistoric = hasSportHistoric;
        this.hasWalkingHistoric = hasWalkingHistoric;
        this.gender = gender;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @return the hasSportHistoric
     */
    public boolean isHasSportHistoric() {
        return hasSportHistoric;
    }

    /**
     * @return the hasWalkingHistoric
     */
    public boolean isHasWalkingHistoric() {
        return hasWalkingHistoric;
    }

    /**
     * @return the gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @param hasSportHistoric the hasSportHistoric to set
     */
    public void setHasSportHistoric(boolean hasSportHistoric) {
        this.hasSportHistoric = hasSportHistoric;
    }

    /**
     * @param hasWalkingHistoric the hasWalkingHistoric to set
     */
    public void setHasWalkingHistoric(boolean hasWalkingHistoric) {
        this.hasWalkingHistoric = hasWalkingHistoric;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(char gender) {
        this.gender = gender;
    }
       
}
