package com.example.retrofittest;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {
@Headers({
        "Content-Type: application/json",
        "Accept: application/json"
})
@POST("users/signin")
         Call<Login> requestLogin(@Body UserInfo userInfo);
}
