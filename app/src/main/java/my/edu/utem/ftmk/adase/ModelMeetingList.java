package my.edu.utem.ftmk.adase;

public class ModelMeetingList {
    String date;
    String place;
    String time;
    String report;

    public ModelMeetingList(){

    }

    public ModelMeetingList(String date, String place, String time, String report) {
        this.date = date;
        this.place = place;
        this.time = time;
        this.report = report;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }


}