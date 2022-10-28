package com.example.project0.ui.courses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project0.R;
import com.example.project0.pojo.LangCategoryModel;

import java.util.ArrayList;

public class LangCategoryAdapter extends RecyclerView.Adapter<LangCategoryAdapter.LangViewHolder>
{
    private ArrayList<LangCategoryModel> langCategoryModels = new ArrayList<>();
    private LangCategoryAdapter.itemClickListener itemClickListener;

    @NonNull
    @Override
    public LangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new LangCategoryAdapter.LangViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lang_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LangViewHolder holder, int position)
    {
        holder.langSelect.setText(langCategoryModels.get(position).getLangCategory());

        holder.itemView.setOnClickListener(v ->{
            itemClickListener.onItemClick(langCategoryModels.get(position));
        });
    }

    @Override
    public int getItemCount()
    {
        return langCategoryModels.size();
    }

    public void setList(ArrayList<LangCategoryModel> sectionList, itemClickListener itemClickListener)
    {
        this.langCategoryModels = sectionList;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    public interface itemClickListener
    {
        void onItemClick(LangCategoryModel langCategoryModel);
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
