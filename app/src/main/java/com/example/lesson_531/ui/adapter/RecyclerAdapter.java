package com.example.lesson_531.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson_531.R;
import com.example.lesson_531.data.model.Note;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Note> notes = new ArrayList<>();
    private OnItemClick onItemClick;

    public RecyclerAdapter() {
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void getList(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public void deleteNote(Note note) {
        notes.remove(note);
        notifyDataSetChanged();
    }

    public Note getNote(int position) {
        return notes.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvContent;
        private TextView tvUser;
        private TextView tvGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvGroup = itemView.findViewById(R.id.tvGroup);
            itemView.setOnClickListener(v -> {
                onItemClick.onClick(getAdapterPosition(), notes.get(getAdapterPosition()));
            });
            itemView.setOnLongClickListener(v -> {
                onItemClick.onLongClick(getAdapterPosition(), notes.get(getAdapterPosition()));
                return true;
            });
        }

        public void onBind(Note note) {
            tvTitle.setText(note.getTitle());
            tvContent.setText(note.getContent());
            tvUser.setInputType(note.getUser());
            tvGroup.setInputType(note.getGroup());
        }
    }

    public interface OnItemClick {
        void onClick(int position, Note note);

        void onLongClick(int position, Note note);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}