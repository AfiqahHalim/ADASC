package my.edu.utem.ftmk.adase;

public class ModelReportMeetingsList {

    String meetingDate;
    String reportVenue;
    String reportedBy;
    String endingTime;
    String startingTime;
    String report;

    public ModelReportMeetingsList() {
    }

    public ModelReportMeetingsList(String meetingDate, String reportVenue, String endingTime, String startingTime, String report,  String reportedBy) {
        this.meetingDate = meetingDate;
        this.reportVenue = reportVenue;
        this.reportedBy = reportedBy;
        this.endingTime = endingTime;
        this.startingTime = startingTime;
        this.report = report;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getReportVenue() {
        return reportVenue;
    }

    public void setReportVenue(String reportVenue) {
        this.reportVenue = reportVenue;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
