package my.edu.utem.ftmk.adase;

public class ModelGroupBList {


    String fullname;
    String phoneNum;

    public ModelGroupBList() {

    }

    public ModelGroupBList(String fullname, String phoneNum) {
        this.fullname = fullname;
        this.phoneNum = phoneNum;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

}
