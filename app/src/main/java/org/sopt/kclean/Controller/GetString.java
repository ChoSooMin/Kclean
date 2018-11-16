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

}
