package org.sopt.kclean.Model;

/**
 * Created by choisunpil on 05/11/2018.
 */

public class User {
    String id;
    String password;
    String name;
    String phoneNumber;
    String gender;
    String birth;

    public User(String id, String password, String name, String phoneNumber, String gender, String birth) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birth = birth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
