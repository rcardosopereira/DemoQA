package com.demoqa.api.tests;

import org.junit.Test;
import com.demoqa.api.models.Book;
import com.demoqa.api.models.User;
import com.demoqa.api.models.TokenResponse;
import com.demoqa.api.services.UserService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DemoQATest {
    @Test
    public static void main(String[] args) {

        // Create Gson instance with lenient mode
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        // Create OkHttpClient instance
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS) // Increase the timeout as per your needs
                .build();

        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://demoqa.com/")
                .client(client) // Set the customized OkHttp client
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create UserService instance
        UserService userService = retrofit.create(UserService.class);

        String username = "RafaelTestePortugal";
        String password = "!Qwerty123!@#!";
        User user = new User(username, password);

        try {
            // Create user
            Call<Void> createUserCall = userService.createUser(user);
            Response<Void> createUserResponse = createUserCall.execute();
            if (!createUserResponse.isSuccessful()) {
                System.out.println("--->|Failed to create user!|<---");
                System.out.println("Error: " + createUserResponse.message() + "\nCode: " + createUserResponse.code() + "\nBody: " + createUserResponse.errorBody().string());
                return;
            }else{
                System.out.println("User created successfully. Code: " + createUserResponse.code());
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
            }

            // Generate token
            Call<TokenResponse> generateTokenCall = userService.generateToken(user);
            Response<TokenResponse> generateTokenResponse = generateTokenCall.execute();
            if (!generateTokenResponse.isSuccessful()) {
                System.out.println("Failed to generate token");
                System.out.println("Error: " + generateTokenResponse.message() + "\nCode: " + generateTokenResponse.code() + "\nBody: " + generateTokenResponse.errorBody().string());
                return;
            }else{
                System.out.println("Generated token successfully. Code: " + generateTokenResponse.code());
            }
            TokenResponse tokenResponse = generateTokenResponse.body();
            String authToken = tokenResponse.getToken();
            System.out.println("Authentication token: " + authToken + "\nToken: "+ tokenResponse);

            // Call Authorized endpoint
            Call<Void> authorizedCall = userService.authorized(authToken);
            Response<Void> authorizedResponse = authorizedCall.execute();
            if (!authorizedResponse.isSuccessful()) {
                System.out.println("Failed to call Authorized endpoint");
                System.out.println("Error: " + authorizedResponse.message() + "\nCode: " + authorizedResponse.code() + "\nBody: " + authorizedResponse.errorBody().string());
                return;
            }else{
                System.out.println("Authorized successfully. Code: " + authorizedResponse.code());
            }

            // Get list of books
            Call<List<Book>> getBooksCall = userService.getBooks();
            Response<List<Book>> getBooksResponse = getBooksCall.execute();
            System.out.println("Response Body: " + getBooksResponse.body());

            if (!getBooksResponse.isSuccessful()) {
                System.out.println("Failed to get books");
                System.out.println("Error: " + getBooksResponse.message() + "\nCode: " + getBooksResponse.code() + "\nBody: " + getBooksResponse.errorBody().string());
                return;
            }else{
                List<Book> bookList = getBooksResponse.body();
                System.out.println("List of Books:");
                for (Book book : bookList) {
                    System.out.println("Book Title: " + book.getTitle());
                    System.out.println("Book Author: " + book.getAuthor());
                    System.out.println("------------------------------");
                }
            }

            List<Book> books = getBooksResponse.body();
            if (books != null && !books.isEmpty()) {

                // Filter books by publisher or author
                List<Book> filteredBooks = filterBooksByPublisherOrAuthor(books, "publisher", "author");
                System.out.println("Filtered Books: " + filteredBooks);

                // Post books to the user
                Call<Void> postBooksCall = userService.postBooks(authToken, filteredBooks);
                Response<Void> postBooksResponse = postBooksCall.execute();
                if (!postBooksResponse.isSuccessful()) {
                    System.out.println("Failed to post books");
                    System.out.println("Error: " + postBooksResponse.message());
                } else {
                    System.out.println("Books posted successfully");
                }
            } else {
                System.out.println("No books available");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Filter books by publisher or author
    private static List<Book> filterBooksByPublisherOrAuthor(List<Book> books, String publisher, String author) {
        List<Book> filteredBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getPublisher().equalsIgnoreCase(publisher) || book.getAuthor().equalsIgnoreCase(author)) {
                filteredBooks.add(book);

                System.out.println("List of Books:");
                System.out.println("Book Title: " + book.getTitle());
                System.out.println("Book Author: " + book.getAuthor());
                System.out.println("------------------------------");
            }
        }

        return filteredBooks;
    }
}