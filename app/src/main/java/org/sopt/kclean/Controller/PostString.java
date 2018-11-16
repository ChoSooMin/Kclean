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
    public static String signupJson(String user_id,String user_name,String user_pw, String user_phone, int user_sex, String user_birth, String user_univ,String user_major)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("user_id",user_id);
            jsonObject.accumulate("user_phone",user_phone);
            jsonObject.accumulate("user_pw",user_pw);
            jsonObject.accumulate("user_sex",user_sex);
            jsonObject.accumulate("user_birth",user_birth);
            jsonObject.accumulate("user_univ",user_univ);
            jsonObject.accumulate("user_major",user_major);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    // 동아리 만들기
    public static String clubJson(String club_name, String club_logo, String club_explanation, String club_background)
    {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.accumulate("club_name", club_name);
            jsonObject.accumulate("club_logo", club_logo);
            jsonObject.accumulate("club_explanation", club_explanation);
            jsonObject.accumulate("club_background", club_background);
            jsonObject.accumulate("bank_name",bank_name);
            jsonObject.accumulate("bank_account",banl_account);
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

}

