package com.example.project0.ui.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project0.R;
import com.example.project0.pojo.LessonModel;

import java.util.ArrayList;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder>
{
    private ArrayList<LessonModel> lessonModels = new ArrayList<>();
    private itemClickListener itemClickListener;

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new LessonAdapter.LessonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lang_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) 
    {
        holder.langSelect.setText(lessonModels.get(position).getLesson());

        holder.itemView.setOnClickListener(v ->{
            itemClickListener.onItemClick(lessonModels.get(position));
        });
    }

    @Override
    public int getItemCount()
    {
        return lessonModels.size();
    }

    public void setList(ArrayList<LessonModel> sectionList, itemClickListener itemClickListener)
    {
        this.lessonModels = sectionList;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    public interface itemClickListener
    {
        void onItemClick(LessonModel lessonModel);
    }

    public class LessonViewHolder extends RecyclerView.ViewHolder
    {
        TextView langSelect;

        public LessonViewHolder(@NonNull View itemView)
        {
            super(itemView);
            langSelect = itemView.findViewById(R.id.lang_select);
        }
    }
}
