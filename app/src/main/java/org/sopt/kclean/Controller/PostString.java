package org.sopt.kclean.Controller;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by choisunpil on 07/11/2018.
 */

//Post 할때 인자
public  class PostString {

    //login
    public static String signinJson(String user_id, String deviceToken,String user_pw){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.accumulate("user_id",user_id);
            jsonObject.accumulate("deviceToken",deviceToken);
            jsonObject.accumulate("user_pw",user_pw);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    // 회원가입
    public static String signupJson(String user_id, String user_pw, String user_name, String user_birth, int user_sex, String user_phone, String user_univ, String user_major)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("user_id",user_id);
            jsonObject.accumulate("user_pw",user_pw);
            jsonObject.accumulate("user_name",user_name);
            jsonObject.accumulate("user_birth",user_birth);
            jsonObject.accumulate("user_sex",user_sex);
            jsonObject.accumulate("user_phone",user_phone);
            jsonObject.accumulate("user_univ",user_univ);
            jsonObject.accumulate("user_major",user_major);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    // 동아리 만들기
    public static String clubJson(String club_name, String club_logo, String club_explanation, String club_background, String bank_name, String bank_account)
    {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.accumulate("club_name", club_name);
            jsonObject.accumulate("club_logo", club_logo);
            jsonObject.accumulate("club_explanation", club_explanation);
            jsonObject.accumulate("club_background", club_background);
            jsonObject.accumulate("bank_name",bank_name);
            jsonObject.accumulate("bank_account",bank_account);
        } catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    // 동아리 가입하기
    public static String groupJoinJson(String club_id) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.accumulate("club_id", club_id);
        } catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    // 송금 요청
    public static String requestMoney(String notice_id) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.accumulate("notice_id", notice_id);
        } catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    // 송금하기
    public static String sendMoney(String notice_id, int notice_price) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.accumulate("notice_id", notice_id);
            jsonObject.accumulate("notice_price", notice_price);
        } catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    // 공지 작성 (등록)
    public static String writeAnnounce(String club_id, String notice_title, int notice_category, String notice_place, String notice_date, String notice_time, String notice_content, String notice_cost) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.accumulate("club_id", club_id);
            jsonObject.accumulate("notice_title", notice_title);
            jsonObject.accumulate("notice_category", notice_category);
            jsonObject.accumulate("notice_place", notice_place);
            jsonObject.accumulate("notice_date", notice_date);
            jsonObject.accumulate("notice_time", notice_time);
            jsonObject.accumulate("notice_content", notice_content);
            jsonObject.accumulate("notice_cost", notice_cost);
        } catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    // 공지 참여
    public static String attendAnnuonce(String notice_id) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.accumulate("notice_id", notice_id);
        } catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    // 계좌 등록
    public static String registerAccount(String user_account, String user_bank) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.accumulate("user_account", user_account);
            jsonObject.accumulate("user_bank", user_bank);
        } catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject.toString();
    }
}

