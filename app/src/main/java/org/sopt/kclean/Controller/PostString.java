package org.sopt.kclean.Controller;

/**
 * Created by choisunpil on 07/11/2018.
 */

//Post 할때 인자
public class PostString {

    // signup (회원가입)
    private String user_id;
    private String user_name;
    private String user_pw;
    private String user_phone;
    private String user_sex;
    private String user_birth;
    private String user_univ;
    private String user_major;


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


    // 회원가입,,
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_birth() {
        return user_birth;
    }

    public void setUser_birth(String user_birth) {
        this.user_birth = user_birth;
    }

    public String getUser_univ() {
        return user_univ;
    }

    public void setUser_univ(String user_univ) {
        this.user_univ = user_univ;
    }

    public String getUser_major() {
        return user_major;
    }

    public void setUser_major(String user_major) {
        this.user_major = user_major;
    }
    // 요까지 회원가입,,

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
