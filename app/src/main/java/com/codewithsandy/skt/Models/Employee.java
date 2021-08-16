package com.codewithsandy.skt.Models;

public class Employee {

    public Employee(String name, String category, String phNo, String location) {
        this.name = name;
        this.category = category;
        this.phNo = phNo;
        this.location = location;
    }

    private String name;
   private String category;
   private String phNo;
   public Employee() {
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

    private String location;

}
