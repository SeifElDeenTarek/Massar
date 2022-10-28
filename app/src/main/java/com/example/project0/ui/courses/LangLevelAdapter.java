package com.example.project0.ui.courses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project0.R;
import com.example.project0.pojo.LangLevelModel;

import java.util.ArrayList;

public class LangLevelAdapter extends RecyclerView.Adapter<LangLevelAdapter.LangViewHolder>
{
    private ArrayList<LangLevelModel> langLevelModels = new ArrayList<>();
    private LangLevelAdapter.itemClickListener itemClickListener;

    @NonNull
    @Override
    public LangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new LangLevelAdapter.LangViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lang_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LangViewHolder holder, int position)
    {
        holder.langSelect.setText(langLevelModels.get(position).getLangLevel());

        holder.itemView.setOnClickListener(v ->{
            itemClickListener.onItemClick(langLevelModels.get(position));
        });
    }

    @Override
    public int getItemCount()
    {
        return langLevelModels.size();
    }

    public void setList(ArrayList<LangLevelModel> sectionList, itemClickListener itemClickListener)
    {
        this.langLevelModels = sectionList;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    public interface itemClickListener
    {
        void onItemClick(LangLevelModel langLevelModel);
    }

    public class LangViewHolder extends RecyclerView.ViewHolder
    {
        TextView langSelect;

        public LangViewHolder(@NonNull View itemView)
        {
            super(itemView);
            langSelect = itemView.findViewById(R.id.lang_select);
        }
    }
}
