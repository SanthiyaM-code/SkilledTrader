package com.codewithsandy.skt.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Favourites {
    public Favourites(){}

    private String name;
    private String category;

    public Favourites(String name, String category, String phNo, String location) {
        this.name = name;
        this.category = category;
        this.phNo = phNo;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String phNo;
    private String location;


}
