package my.edu.utem.ftmk.adase;

public class ModelReportNightGuardList {

    String dateOccur;
    String reportCrime;
    String group;
    String street;
    String nickname;

    public ModelReportNightGuardList() {
    }

    public ModelReportNightGuardList(String uid, String dateOccur, String reportCrime, String group, String street, String nickname) {
        this.dateOccur = dateOccur;
        this.reportCrime = reportCrime;
        this.group = group;
        this.street = street;
        this.nickname = nickname;
    }

    public String getDateOccur() {
        return dateOccur;
    }

    public void setDateOccur(String dateOccur) {
        this.dateOccur = dateOccur;
    }

    public String getReportCrime() {
        return reportCrime;
    }

    public void setReportCrime(String reportCrime) {
        this.reportCrime = reportCrime;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
