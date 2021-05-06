package com.example.lesson_531.data.remote;

import com.example.lesson_531.data.remote.interfaces.NoteApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static NoteApi instance;

    private RetrofitBuilder() {
    }

    public static NoteApi getInstance() {
        if (instance == null) instance = createRetrofit();
        return instance;
    }

    private static NoteApi createRetrofit() {
        return new Retrofit.Builder().baseUrl("https://android-3-mocker.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build().create(NoteApi.class);
    }

}
