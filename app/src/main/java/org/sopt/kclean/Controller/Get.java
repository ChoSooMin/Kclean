package org.sopt.kclean.Controller;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by choisunpil on 07/11/2018.
 */


// Get 통신
public class Get {

    OkHttpClient client = new OkHttpClient();
    String token;
    ArrayList<String> query = new ArrayList<String>();
    ArrayList<String> queryName  = new ArrayList<String>();


    // token 없을 때
    public Get() {}

    // token 있을 때 & query 없ㅇ을 때
    public Get(String token) {
        this.token = token;
    }

    // token 없을 때 & query 있을 때
    public Get(String queryName, String query) {
        this.queryName.add(queryName);
        this.query.add(query);
    }

    // query & token 있을 때
    public Get(String token, String queryName, String query) {
        this.token = token;
        this.queryName.add(queryName);
        this.query.add(query);
    }

    public Get(String token , String queryName1,String query1,String queryName2,String query2,String queryName3,String query3)
    {
        this.token = token;
        this.query.add(query1);
        this.query.add(query2);
        this.query.add(query3);
        this.queryName.add(queryName1);
        this.queryName.add(queryName2);
        this.queryName.add(queryName3);
    }

    // Get에서 ,,, 포스트,,, 값,, ㅛ요청한다고라,,,,?
    public  String run(String url, String content) throws IOException {


        Request request;

        if (token != null) {
            if (query.size() != 0) {
                HttpUrl.Builder httpUrl = HttpUrl.parse(url).newBuilder();
                for(int i= 0 ; i < query.size(); i++ )
                 httpUrl.addQueryParameter(queryName.get(i), query.get(i));

                request = new Request.Builder()
                        .addHeader("Content-type", content)
                        .addHeader("token", token)
                        .url(httpUrl.toString())
                        .build();
            }
            else{ // query == null
                request = new Request.Builder()
                        .addHeader("Content-type", content)
                        .addHeader("token", token)
                        .url(url)
                        .build();
            }
        }
        else { // token == null
            if (query != null) {
                HttpUrl.Builder httpUrl = HttpUrl.parse(url).newBuilder();
                httpUrl.addQueryParameter(queryName.get(0), query.get(0));
                request = new Request.Builder()
                        .addHeader("Content-type", content)
                        .url(httpUrl.toString())
                        .build();
            }
            else { // query == null
                request = new Request.Builder()
                        .addHeader("Content-type", content)
                        .url(url)
                        .build();
            }
        }

        // Response 처리
        try (Response response = client.newCall(request).execute()) {
            String s = response.header("state");

            if (response.code() == 200 || response.code() == 201) {
                return response.body().string();
            }
            else {
                return null;
            }
        }
    }
}
