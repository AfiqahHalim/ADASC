package my.edu.utem.ftmk.adase;

public class ModelCommunityList {

    String uid;
    String fullname;
    String house;
    String age;
    String street;
    String gender;
    String work;
    String phone;
    String family;

    public ModelCommunityList() {
    }

    public ModelCommunityList(String uid, String fullname, String house, String age, String street, String gender, String work, String phone, String family) {
        this.uid = uid;
        this.fullname = fullname;
        this.house = house;
        this.age = age;
        this.street = street;
        this.gender = gender;
        this.work = work;
        this.phone = phone;
        this.family = family;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
