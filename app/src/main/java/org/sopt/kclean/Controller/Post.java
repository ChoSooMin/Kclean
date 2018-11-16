package org.sopt.kclean.Controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MultipartBody;

/**
 * Created by choisunpil on 07/11/2018.
 */


//Post 통신
public class Post {

    OkHttpClient client = new OkHttpClient();
    final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*");
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String url;
    private String json;
    private String token;
    private String content;
    private JSONObject jsonObject;

    //
    public RequestBody requestBody;

    public Post(String url, String json, String content)
    {
        this.url = url;
        this.json = json;
        this.content = content;

    }

    public Post(String url, String json,String token,String content){
        this.url = url;
        this.json = json;
        this.token = token;
        this.content = content;
    }

    public Post(String url){
        this.url = url;
    }

    public String post()  {

        RequestBody body = RequestBody.create(JSON, json);
        MediaType fileContentType = MediaType.parse("File/*");
        File file;
        Request request;

        if(0 ==content.compareTo("application/x-www-form-urlencoded"))
        {
            if(token != null) {
                request = new Request.Builder()
                        .addHeader("Content-type", content)
                        .addHeader("token",token)
                        .url(url)
                        .post(body)
                        .build();
            }
            else
            {
                request = new Request.Builder()
                        .addHeader("Content-type", "application/x-www-form-urlencoded")
                        .url(url)
                        .post(body)
                        .build();
            }

        }
        else {
            // 동아리 생성시 multipart 구현
            JSONObject jsonObject = null;
            RequestBody requestBody = null;
            client  = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.MINUTES)
                    .build();
            try {
                jsonObject = new JSONObject(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                String s = jsonObject.toString();
                requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("club_explanation",jsonObject.getString("club_explanation"))
                        .addFormDataPart("club_name", jsonObject.getString("club_name"))
                        .addFormDataPart("club_logo", "club_logo.png",RequestBody.create(MEDIA_TYPE_PNG, new File(jsonObject.getString("club_logo"))))
                        .addFormDataPart("club_background","club_background.png",RequestBody.create(MEDIA_TYPE_PNG, new File(jsonObject.getString("club_background"))))
                        .addFormDataPart("bank_name",jsonObject.getString("bank_name"))
                        .addFormDataPart("bank_account",jsonObject.getString("bank_account"))
                        .build();
            } catch (JSONException e) {

                e.printStackTrace();
            }
            catch(NullPointerException n)
            {
                n.printStackTrace();
            }


            request = new Request.Builder()
                    .addHeader("Content-type", content)
                    .addHeader("token",token)
                    .url(url)
                    .post(requestBody)
                    .build();

        }
        try(Response response = client.newCall(request).execute()){
             if(response.code() == 200 ||response.code() == 201 )
             {

                 return response.body().string();
             }
             else
                 return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}