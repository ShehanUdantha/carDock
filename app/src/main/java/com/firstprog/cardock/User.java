package com.firstprog.cardock;

public class User {
    private String personName, personEmail, personLocation, personPhone, password;
    private int userId;

    public User(int userId, String personName, String personEmail, String personLocation, String personPhone, String password) {
        this.personName = personName;
        this.personEmail = personEmail;
        this.personLocation = personLocation;
        this.userId = userId;
        this.personPhone = personPhone;
        this.password = password;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonLocation() {
        return personLocation;
    }

    public void setPersonLocation(String personLocation) {
        this.personLocation = personLocation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "PersonName='" + personName + '\'' +
                ", PersonEmail='" + personEmail + '\'' +
                ", personLocation='" + personLocation + '\'' +
                ", id=" + userId +
                ", password=" + password +
                ", PersonPhone=" + personPhone +
                '}';
    }
}
