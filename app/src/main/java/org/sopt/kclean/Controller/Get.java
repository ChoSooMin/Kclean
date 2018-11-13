package org.sopt.kclean.Controller;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by choisunpil on 07/11/2018.
 */


// Get 통신
public class Get {
    OkHttpClient client = new OkHttpClient();

 public  String run(String url,String content,String token) throws IOException {
        Request request = new Request.Builder()
                .addHeader("Content-type", content)
                .addHeader("token",token)
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
