package org.sopt.kclean.Network;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface NetworkService {

    @Multipart
    @POST("https://klean.apps.dev.clayon.io/api/club")
    Call<CreateResponse> createClub(
            @Part("club_name") RequestBody club_name,
            @Part MultipartBody.Part club_logo,
            @Part("club_explanation") RequestBody club_explanation,
            @Part MultipartBody.Part club_background,
            @Part("bank_name") RequestBody bank_name,
            @Part("bank_account") RequestBody back_account
    );

}
