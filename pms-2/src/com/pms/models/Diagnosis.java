package com.pms.models;

public class Diagnosis {
    private String diagnosis;
    private String medicines;
    private String date;

    public Diagnosis(String diagnosis, String medicines, String date) {
        this.diagnosis = diagnosis;
        this.medicines = medicines;
        this.date = date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getMedicines() {
        return medicines;
    }

    public String getDate() {
        return date;
    }
}

