package com.demoqa.api.services;

import com.demoqa.api.models.Book;
import com.demoqa.api.models.TokenResponse;
import com.demoqa.api.models.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface UserService {
   @POST("Account/v1/User")
    Call<Void> createUser(@Body User user);

   @POST("Account/v1/GenerateToken")
    Call<TokenResponse> generateToken(@Body User user);

    @GET("Account/v1/Authorized")
    Call<Void> authorized(@Header("Authorization") String authToken);

  @GET("BookStore/v1/Books")
    Call<List<Book>> getBooks();

  @POST("BookStore/v1/Books")
    Call<Void> postBooks(@Header("Authorization") String authToken, @Body List<Book> books);
}
