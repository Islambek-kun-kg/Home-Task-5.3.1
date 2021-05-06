package com.example.lesson_531.ui.title;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.lesson_531.R;
import com.example.lesson_531.data.model.Note;
import com.example.lesson_531.data.remote.EditNote;
import com.example.lesson_531.data.remote.interfaces.NoteEditInterface;

public class AddTitleFragment extends Fragment {
    private EditText etTitle;
    private EditText etContent;
    private EditText etUser;
    private EditText etGroup;
    private Button btnSave;
    private Note note;

    public AddTitleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = (Note) getArguments().getSerializable("title");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_title_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etTitle = view.findViewById(R.id.etTitle);
        etContent = view.findViewById(R.id.etContent);
        etUser = view.findViewById(R.id.etUser);
        etGroup = view.findViewById(R.id.etGroup);
        btnSave = view.findViewById(R.id.btnSave);
        if (note != null) {
            etTitle.setText(note.getTitle());
            etContent.setText(note.getContent());
            etUser.setInputType(note.getUser());
            etGroup.setInputType(note.getGroup());
        }
        Click();
    }

    private void Click() {
        btnSave.setOnClickListener(v -> {
            if (note != null) {
                addToNote();
                EditNote.updateNote(note.getId(), note, new NoteEditInterface() {
                    @Override
                    public void onSuccess(Note note) {
                        Navigation.findNavController(requireActivity(), R.id.navHostFragment).navigateUp();
                    }
                });
            } else {
                note = new Note();
                addToNote();
                EditNote.addNote(note, new NoteEditInterface() {
                    @Override
                    public void onSuccess(Note note) {
                        Navigation.findNavController(requireActivity(), R.id.navHostFragment).navigateUp();
                    }
                });
            }
        });
    }

    private void addToNote() {
        if (etTitle != null)
            note.setTitle(etTitle.getText().toString());
        if (etContent != null)
            note.setContent(etContent.getText().toString());
        if (etUser != null) {
            int user = Integer.parseInt(etUser.getText().toString().trim());
            note.setUser(user);
        }
        if (etGroup != null) {
            int group = Integer.parseInt(etGroup.getText().toString().trim());
            note.setGroup(group);
        }
    }

}