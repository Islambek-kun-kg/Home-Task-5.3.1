package com.example.lesson_531.ui.title;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lesson_531.R;
import com.example.lesson_531.data.model.Note;
import com.example.lesson_531.data.remote.EditNote;
import com.example.lesson_531.data.remote.interfaces.ListNoteEditInterface;
import com.example.lesson_531.data.remote.interfaces.NoteEditInterface;
import com.example.lesson_531.ui.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private RecyclerAdapter adapter;
    private RecyclerView rv;
    private List<Note> notes = new ArrayList<>();
    private Button btnAddNewNote;

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new RecyclerAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = view.findViewById(R.id.rv_fragmentTitle);
        btnAddNewNote = view.findViewById(R.id.btnAddNewNote);
        btnAddNewNote.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.navHostFragment).navigate(R.id.titleAddFragment);
        });
        showNotes();
        click();
    }

    private void showNotes() {
        EditNote.getNote(new ListNoteEditInterface() {
            @Override
            public void onSuccess(List<Note> notes) {
                adapter.getList(notes);
                rv.setAdapter(adapter);
            }
        });
    }

    private void click() {
        adapter.setOnItemClick(new RecyclerAdapter.OnItemClick() {
            @Override
            public void onClick(int position, Note note) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("title", note);
                Navigation.findNavController(getActivity(), R.id.navHostFragment).navigate(R.id.titleAddFragment, bundle);
            }

            @Override
            public void onLongClick(int position, Note note) {
                AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
                ad.setTitle("Delete?").setMessage("Remove...").setPositiveButton("Delete", (dialog, which) -> {
                    EditNote.deleteNote(note.getId(), new NoteEditInterface() {
                        @Override
                        public void onSuccess(Note note) {
                            Note note1 = adapter.getNote(position);
                            adapter.deleteNote(note1);
                        }
                    });
                }).setNegativeButton("Cancel", null).create().show();
            }
        });
    }
}