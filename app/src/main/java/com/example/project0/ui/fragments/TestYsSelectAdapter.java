package com.example.project0.ui.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project0.R;
import com.example.project0.pojo.TestYsSelectModel;

import java.util.ArrayList;

public class TestYsSelectAdapter extends RecyclerView.Adapter<TestYsSelectAdapter.TestYsViewHolder>
{
    private ArrayList<TestYsSelectModel> testYsSelectModels = new ArrayList<>();
    private TestYsSelectAdapter.itemClickListener itemClickListener;

    @NonNull
    @Override
    public TestYsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new TestYsSelectAdapter.TestYsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lang_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TestYsViewHolder holder, int position)
    {
        holder.langSelect.setText(testYsSelectModels.get(position).getType());

        holder.itemView.setOnClickListener(v ->{
            itemClickListener.onItemClick(testYsSelectModels.get(position));
        });
    }

    @Override
    public int getItemCount()
    {
        return testYsSelectModels.size();
    }

    public void setList(ArrayList<TestYsSelectModel> sectionList, itemClickListener itemClickListener)
    {
        this.testYsSelectModels = sectionList;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    public interface itemClickListener
    {
        void onItemClick(TestYsSelectModel testYsSelectModel);
    }

    public class TestYsViewHolder extends RecyclerView.ViewHolder
    {
        TextView langSelect;

        public TestYsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            langSelect = itemView.findViewById(R.id.lang_select);
        }
    }
}
