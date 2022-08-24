package my.edu.utem.ftmk.adase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class User extends AppCompatActivity {

    public String fullName, icNum, phoneNum, eMail, pass, position, address, birthDate, work;

    public User(String fullName, String icNum, String phoneNum, String eMail, String pass, String position, String address, String birthDate, String work) {

        this.fullName = fullName;
        this.icNum = icNum;
        this.phoneNum = phoneNum;
        this.eMail = eMail;
        this.pass = pass;
        this.position = position;
        this.address = address;
        this.birthDate = birthDate;
        this.work = work;
    }

    public String getFullName() {

        return fullName;
    }

    public void setFullName(String fullName) {

        this.fullName = fullName;
    }

    public String getIcNum() {

        return icNum;
    }

    public void setIcNum(String icNum) {

        this.icNum = icNum;
    }

    public String getPhoneNum() {

        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {

        this.phoneNum = phoneNum;
    }

    public String geteMail() {

        return eMail;
    }

    public void seteMail(String eMail) {

        this.eMail = eMail;
    }

    public String getPass() {

        return pass;
    }

    public void setPass(String pass) {

        this.pass = pass;
    }

}