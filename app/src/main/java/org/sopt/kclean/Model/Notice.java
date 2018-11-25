package org.sopt.kclean.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Notice implements Parcelable {

    String notice_id;
    String notice_title;
    int notice_category;
    String club_manager_img;
    String club_manager;
    String write_time;
    String notice_content;
    int notice_cost;
    String notice_date;
    int notice_participant;
    ArrayList<User> notice_people;
    String club_logo;
    String club_name;
    int account_check;
    String notice_time;

    public Notice(int notice_cost, String notice_title, String notice_date, String notice_time, int notice_participant) {
        this.notice_cost = notice_cost;
        this.notice_title = notice_title;
        this.notice_date = notice_date;
        this.notice_time = notice_time;
        this.notice_participant = notice_participant;
    }

    public Notice(String club_logo, String club_name, String write_time, String notice_title, String notice_content, int account_check) {
        this.club_logo = club_logo;
        this.club_name = club_name;
        this.write_time = write_time;
        this.notice_title = notice_title;
        this.notice_content = notice_content;
        this.account_check = account_check;
    }

    public Notice(String club_logo, String club_name, String club_manager, String write_time, String notice_id, String notice_title, String notice_content, int account_check) {
        this.club_logo = club_logo;
        this.club_name = club_name;
        this.club_manager = club_manager;
        this.write_time = write_time;
        this.notice_id = notice_id;
        this.notice_title = notice_title;
        this.notice_content = notice_content;
        this.account_check = account_check;
    }

    public Notice(String notice_id, String write_time, String notice_title, int notice_category, String notice_content) {
        this.notice_id = notice_id;
        this.write_time = write_time;
        this.notice_title = notice_title;
        this.notice_category = notice_category;
        this.notice_content = notice_content;
    }

    public Notice(Parcel source)
    {
        notice_id = source.readString();
        notice_title = source.readString();
        notice_category = source.readInt();
        club_manager_img = source.readString();
        club_manager = source.readString();
        write_time = source.readString();
        notice_content = source.readString();
        notice_cost = source.readInt();
        notice_date = source.readString();
        notice_participant = source.readInt();
        notice_people = source.readArrayList(ArrayList.class.getClassLoader());
        club_logo = source.readString();
        club_name = source.readString();
        account_check = source.readInt();
        notice_time = source.readString();
    }

    public String getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(String notice_id) {
        this.notice_id = notice_id;
    }

    public ArrayList<User> getNotice_people() {
        return notice_people;
    }

    public void setNotice_people(ArrayList<User> notice_people) {
        this.notice_people = notice_people;
    }

    public int getAccount_check() {
        return account_check;
    }

    public void setAccount_check(int account_check) {
        this.account_check = account_check;
    }

    public String getNotice_time() {
        return notice_time;
    }

    public void setNotice_time(String notice_time) {
        this.notice_time = notice_time;
    }

    public String getNotice_title() {
        return notice_title;
    }

    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    public int getNotice_category() {
        return notice_category;
    }

    public void setNotice_category(int notice_category) {
        this.notice_category = notice_category;
    }

    public String getClub_manager_img() {
        return club_manager_img;
    }

    public void setClub_manager_img(String club_manager_img) {
        this.club_manager_img = club_manager_img;
    }

    public String getClub_manager() {
        return club_manager;
    }

    public void setClub_manager(String club_manager) {
        this.club_manager = club_manager;
    }

    public String getWrite_time() {
        return write_time;
    }

    public void setWrite_time(String write_time) {
        this.write_time = write_time;
    }

    public String getNotice_content() {
        return notice_content;
    }

    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }

    public int getNotice_cost() {
        return notice_cost;
    }

    public void setNotice_cost(int notice_cost) {
        this.notice_cost = notice_cost;
    }

    public String getNotice_date() {
        return notice_date;
    }

    public void setNotice_date(String notice_date) {
        this.notice_date = notice_date;
    }

    public int getNotice_participant() {
        return notice_participant;
    }

    public void setNotice_participant(int notice_participant) {
        this.notice_participant = notice_participant;
    }

    public String getClub_logo() {
        return club_logo;
    }

    public void setClub_logo(String club_logo) {
        this.club_logo = club_logo;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(notice_id);
        parcel.writeString(notice_title);
        parcel.writeInt(notice_category);
        parcel.writeString(club_manager);
        parcel.writeString(club_manager);
        parcel.writeString(write_time);
        parcel.writeString(notice_content);
        parcel.writeInt(notice_cost);
        parcel.writeString(notice_date);
        parcel.writeInt(notice_participant);
        parcel.writeList(notice_people);
        parcel.writeString(club_logo);
        parcel.writeString(club_name);
        parcel.writeInt(account_check);
        parcel.writeString(notice_time);
    }

    public static Parcelable.Creator<Notice> CREATOR = new Parcelable.Creator<Notice>() {
        @Override
        public Notice createFromParcel(Parcel parcel) {
            return new Notice(parcel);
        }

        @Override
        public Notice[] newArray(int i) {
            return new Notice[i];
        }
    };
}
