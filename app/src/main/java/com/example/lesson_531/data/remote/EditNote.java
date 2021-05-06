package com.example.lesson_531.data.remote;

import com.example.lesson_531.data.model.Note;
import com.example.lesson_531.data.remote.interfaces.ListNoteEditInterface;
import com.example.lesson_531.data.remote.interfaces.NoteEditInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditNote {

    public static void getNote(ListNoteEditInterface getNotes) {
        RetrofitBuilder.getInstance().getNote().enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                if (response.isSuccessful() && response.body() != null)
                    getNotes.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {

            }
        });

    }

    public static void addNote(Note note, NoteEditInterface addNotes) {
        RetrofitBuilder.getInstance().addToNoteItem(note).enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                if (response.isSuccessful() && response.body() != null)
                    addNotes.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
            }
        });
    }

    public static void deleteNote(String id, NoteEditInterface deleteNotes) {
        RetrofitBuilder.getInstance()
                .deleteNoteItem(id)
                .enqueue(new Callback<Note>() {
                    @Override
                    public void onResponse(Call<Note> call, Response<Note> response) {
                        if (response.isSuccessful() && response.body() != null)
                            deleteNotes.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<Note> call, Throwable t) {
                    }
                });
    }

    public static void updateNote(String id, Note notes, NoteEditInterface updateNotes) {
        RetrofitBuilder.getInstance()
                .updateNoteItem(id, notes)
                .enqueue(new Callback<Note>() {
                    @Override
                    public void onResponse(Call<Note> call, Response<Note> response) {
                        if (response.isSuccessful() && response.body() != null)
                            updateNotes.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<Note> call, Throwable t) {
                    }
                });
    }
}