package com.pms.models;

public class Appointment {
	private String p_id;
	private String name;
    private String phone;
    private String date;
    private String status;
    private String id;
    
    public Appointment(String id, String p_id,String name, String phone, String date, String status) {
    	this.id = id;
        this.p_id = p_id;
    	this.name = name;
        this.phone = phone;
        this.date = date;
        this.status = status;
        
    }
    
    public String getId() {
        return id;
    }
    
    public String getPid() {
        return p_id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDate() {
        return date;
    }
    
    public String getStatus() {
        return status;
    }
}