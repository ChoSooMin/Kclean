package org.sopt.kclean.Controller;

import java.io.IOException;

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
    String query;
    String queryName;
    String token;

    // token 없을 때
    public Get() {}

    // token 있을 때 & query 없을 때
    public Get(String token) {
        this.token = token;
    }

    // token 없을 때 & query 있을 때
    public Get(String queryName, String query) {
        this.queryName = queryName;
        this.query = query;
    }

    // token & query 있을 때
    public Get(String token, String queryName, String query) {
        this.token = token;
        this.queryName = queryName;
        this.query = query;
    }

    // Get에서,,, ㅇㅇ,,,
    public  String run(String url, String content) throws IOException {

        Request request;

        if (token != null) {
            if (query != null) {
                HttpUrl.Builder httpUrl = HttpUrl.parse(url).newBuilder();

                httpUrl.addQueryParameter(queryName, query);

                request = new Request.Builder()
                        .addHeader("Content-type", content)
                        .addHeader("token", token)
                        .url(httpUrl.toString())
                        .build();
                }
            else { // query == null
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

                httpUrl.addQueryParameter(queryName, query);

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
