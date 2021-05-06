package com.example.lesson_531.data.remote.interfaces;

import com.example.lesson_531.data.model.Note;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NoteApi {

    @GET("posts/")
    Call<List<Note>> getNote();

    @POST("posts/")
    Call<Note> addToNoteItem(@Body Note note);

    @DELETE("posts/{id}")
    Call<Note> deleteNoteItem(@Path("id") String id);

    @PUT("posts/{id}")
    Call<Note> updateNoteItem(@Path("id") String id, @Body Note note);

}
