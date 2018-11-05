package org.sopt.kclean.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by choisunpil on 05/11/2018.
 */

public class User  implements Parcelable{
    String id;
    String password;
    String name;
    String phoneNumber;
    String gender;
    String birth;
    String token;
    Account account; //추후 객체로 변경

    public User(){

    }
    public User(String id, String password, String name, String phoneNumber, String gender, String birth, String token,Account account) {
    public User(String id, String password, String name, String phoneNumber, String gender, String birth) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birth = birth;
        this.token =token;
        this.account = account;
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
    account = source.readParcelable(Account.class.getClassLoader());
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
        parcel.writeParcelable(account,PARCELABLE_WRITE_RETURN_VALUE);

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
