package org.sopt.kclean.Controller;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by choisunpil on 07/11/2018.
 */


//Post 통신
public class Post {
    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String url;
    private String json;
    private String token;
    public Post(String url, String json)
    {
        this.url = url;
        this.json = json;

    }

    public Post(String url, String json,String token){
        this.url = url;
        this.json = json;
        this.token = token;
    }
    public Post(String url){
        this.url = url;
    }

    public String post() throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request;
        if(token != null) {
            request = new Request.Builder()
                    .addHeader("Content-type", "application/x-www-form-urlencoded")
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
        try(Response response = client.newCall(request).execute()){
             String s = response.header("state");

             if(response.code() == 200 ||response.code() == 201 )
             {

                 return response.body().string();
             }
             else
                 return null;
        }
    }

}