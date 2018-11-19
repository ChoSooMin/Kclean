package org.sopt.kclean.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by choisunpil on 05/11/2018.
 */

public class User implements Parcelable {

    String id;
    String password;
    String name;
    String phoneNumber;
    String gender;
    String birth;
    String token;
    String university;
    String major;
    Account account; //추후 객체로 변경
    String userImg;
    int account_check;
    String user_deviceToken;
    String current_time;

    public User() {}

    public User(String name, String userImg, String id, String current_time, int account_check, String user_deviceToken){
        this.name = name;
        this.userImg = userImg;
        this.id = id;
        this.current_time = current_time;
        this.account_check = account_check;
        this.user_deviceToken = user_deviceToken;
    }

    public User(String id, String password, String name, String phoneNumber, String gender, String birth, String university , String major) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birth = birth;
        this.university = university;
        this.major = major;
    }

    public User(String id, String password, String name, String phoneNumber, String gender, String birth, String university , String major, String userImg, int account_check) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birth = birth;
        this.university =  university;
        this.major = major;
        this.userImg = userImg;
        this.account_check = account_check;
    }
    public User(Parcel source)
    {
        id = source.readString();
        password = source.readString();
        name = source.readString();
        phoneNumber = source.readString();
        gender = source.readString();
        birth = source.readString();
        token = source.readString();
        university = source.readString();
        major = source.readString();
        account = source.readParcelable(Account.class.getClassLoader());
        userImg = source.readString();
        account_check = source.readInt();
        current_time = source.readString();
    }

    public String getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(String current_time) {
        this.current_time = current_time;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public int getAccount_check() {
        return account_check;
    }

    public void setAccount_check(int account_check) {
        this.account_check = account_check;
    }

    public String getUser_deviceToken() {
        return user_deviceToken;
    }

    public void setUser_deviceToken(String user_deviceToken) {
        this.user_deviceToken = user_deviceToken;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(password);
        parcel.writeString(name);
        parcel.writeString(phoneNumber);
        parcel.writeString(gender);
        parcel.writeString(birth);
        parcel.writeString(token);
        parcel.writeString(university);
        parcel.writeString(major);
        parcel.writeParcelable(account,PARCELABLE_WRITE_RETURN_VALUE);
        parcel.writeString(userImg);
        parcel.writeInt(account_check);
        parcel.writeString(current_time);
    }

    public static Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel parcel) {
            return new User(parcel);
        }

        @Override
        public User[] newArray(int i) {
            return new User[i];
        }
    };

}
