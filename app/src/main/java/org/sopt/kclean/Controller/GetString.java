package org.sopt.kclean.Controller;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by choisunpil on 07/11/2018.
 */
//Get 할때 보낼 인자
public class GetString {

    //login
    public static String clubSearch(String word){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.accumulate("word", word);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    //재정 정보
    public static String GroupFinanceInfo(String club_id)
    {
        JSONObject jsonObject =  new JSONObject();

        try{
            jsonObject.accumulate("club_id",club_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    public static String GroupFinanceInfoList(String club_id,int search_year, int serach_month)
    {
        JSONObject jsonObject =  new JSONObject();

        try{
            jsonObject.accumulate("club_id",club_id);
            jsonObject.accumulate("search_year",search_year);
            jsonObject.accumulate("search_month",serach_month);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }



}
