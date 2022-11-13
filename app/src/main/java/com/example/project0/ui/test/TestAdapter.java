package com.example.project0.ui.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project0.R;
import com.example.project0.database.Test;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder>
{
    List<Test> testModels = new ArrayList<>();
    itemClickListener itemClickListener;

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new TestViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ques_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position)
    {
        holder.ques.setText(testModels.get(position).getQues());
        holder.ans1.setText(testModels.get(position).getAns1());
        holder.ans2.setText(testModels.get(position).getAns2());
        holder.ans3.setText(testModels.get(position).getAns3());
        holder.ans4.setText(testModels.get(position).getAns4());

        holder.itemView.setOnClickListener(v ->{
            itemClickListener.onItemClick(testModels.get(position));
        });

    }

    @Override
    public int getItemCount()
    {
        return 1;
    }

    public void setList(List<Test> tests, TestAdapter.itemClickListener itemClickListener)
    {
        this.testModels = tests;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    public interface itemClickListener
    {
        void onItemClick(Test test);
    }

    public class TestViewHolder extends RecyclerView.ViewHolder
    {
        TextView ques, ans1, ans2, ans3, ans4;
        String selected;
        public TestViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ques = itemView.findViewById(R.id.ques);
            ans1 = itemView.findViewById(R.id.ans1);
            ans2 = itemView.findViewById(R.id.ans2);
            ans3 = itemView.findViewById(R.id.ans3);
            ans4 = itemView.findViewById(R.id.ans4);
        }
    }
}
