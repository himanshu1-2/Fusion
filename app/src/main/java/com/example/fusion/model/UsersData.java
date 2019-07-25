package com.example.fusion.model;

public class UsersData {

    private String name;
    private String designation;

    public String getName() {

        if(name!=null)
            return name;

        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        if(designation!=null)
            return designation;
        return null;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
