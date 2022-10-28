package com.example.project0.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project0.R;
import com.example.project0.pojo.HomeModel;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder>
{
    private ArrayList<HomeModel> homeModels = new ArrayList<>();
    private itemClickListener itemClickListener;

    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new HomeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position)
    {
        holder.sectionImage.setImageResource(homeModels.get(position).getSectionImage());
        holder.sectionName.setText(homeModels.get(position).getSectionName());

        holder.itemView.setOnClickListener(v ->{
            itemClickListener.onItemClick(homeModels.get(position));
        });
    }

    @Override
    public int getItemCount()
    {
        return homeModels.size();
    }

    public void setList(ArrayList<HomeModel> sectionList, itemClickListener itemClickListener)
    {
        this.homeModels = sectionList;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    public interface itemClickListener
    {
        void onItemClick(HomeModel homeModel);
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder
    {
        ImageView sectionImage;
        TextView sectionName;
        public HomeViewHolder(@NonNull View itemView)
        {
            super(itemView);
            sectionImage = itemView.findViewById(R.id.section_image);
            sectionName = itemView.findViewById(R.id.section_name);
        }
    }
}
