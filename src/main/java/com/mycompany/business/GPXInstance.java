/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.business;

import java.util.GregorianCalendar;

/**
 *
 * @author PedroJosé
 */
public class GPXInstance {

    private double latitude, longitude, elevation;
    private GregorianCalendar datetime;

    public GPXInstance(double latitude, double longitude, double elevation, GregorianCalendar datetime) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.datetime = (GregorianCalendar) datetime.clone();
    }

    public GregorianCalendar getDatetime() {
        return datetime;
    }

    public double getElevation() {
        return elevation;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setDatetime(GregorianCalendar datetime) {
        this.datetime = datetime;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float distanceTo(GPXInstance gpxOther) {
        final int R = 6371; // Radius of the earth
        Double latDistance = Math.toRadians(gpxOther.getLatitude() - this.getLatitude());
        Double lonDistance = Math.toRadians(gpxOther.getLongitude() - this.getLongitude());
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.getLatitude())) * Math.cos(Math.toRadians(gpxOther.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        double height = this.getElevation() - gpxOther.getElevation();
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return (float) Math.sqrt(distance);
    }

    public double heightDiff(GPXInstance other) {
        return this.elevation - other.getElevation();
    }

    public double timeDiff(GPXInstance other) {
        long thismilis = this.datetime.getTimeInMillis();
        long othermilis = other.getDatetime().getTimeInMillis();
        return (thismilis - othermilis) / 1000;
    }

    public float speed(GPXInstance other) {
        return this.distanceTo(other) / (float) Math.abs(this.timeDiff(other));
    }

}
