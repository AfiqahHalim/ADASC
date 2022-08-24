package my.edu.utem.ftmk.adase;

public class ModelCommitteeList {

    String fullname;
    String role;

    public ModelCommitteeList() {

    }

    public ModelCommitteeList(String fullname, String role) {
        this.fullname = fullname;
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
