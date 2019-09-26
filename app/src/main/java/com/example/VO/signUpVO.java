package com.example.VO;

public class signUpVO {
    private String id;
    private String pw;
    private String name;
    private String number;
    private String address;
    private String gender;

    public signUpVO(String id, String pw, String name, String number, String address, String gender) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.number = number;
        this.address = address;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
