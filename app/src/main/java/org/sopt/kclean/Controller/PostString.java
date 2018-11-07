package org.sopt.kclean.Controller;

/**
 * Created by choisunpil on 07/11/2018.
 */

//Post 할때 인자
public class PostString {

    //account
    private String account_number;//계좌번호
    private String balance; //잔고

    //user
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String gender;
    private String birth;
    private String token;
    private String university;
    private String major;

    //group
    private String groupName;
    private int totalMember;
    private String masterName;
    private String groupImage;
    private String  groupBackground;
    private String groupDetail;
    private String groupPassword;

    public String getAccount_number() {
        return account_number;
    }

    public String getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getBirth() {
        return birth;
    }

    public String getToken() {
        return token;
    }

    public String getUniversity() {
        return university;
    }

    public String getMajor() {
        return major;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getTotalMember() {
        return totalMember;
    }

    public String getMasterName() {
        return masterName;
    }

    public String getGroupImage() {
        return groupImage;
    }

    public String getGroupBackground() {
        return groupBackground;
    }

    public String getGroupDetail() {
        return groupDetail;
    }

    public String getGroupPassword() {
        return groupPassword;
    }
}
