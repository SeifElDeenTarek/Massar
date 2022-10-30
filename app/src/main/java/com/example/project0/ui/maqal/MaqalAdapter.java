package com.example.project0.ui.maqal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project0.R;
import com.example.project0.pojo.MaqalModel;

import java.util.ArrayList;

public class MaqalAdapter extends RecyclerView.Adapter<MaqalAdapter.MaqalViewHolder>
{
    private ArrayList<MaqalModel> maqalModels = new ArrayList<>();
    private itemClickListener itemClickListener;

    @NonNull
    @Override
    public MaqalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new MaqalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lang_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MaqalViewHolder holder, int position)
    {
        holder.maqalSelect.setText(maqalModels.get(position).getMaqalName());

        holder.itemView.setOnClickListener(v -> {
            itemClickListener.onItemClick(maqalModels.get(position));
        });
    }

    public void setList(ArrayList<MaqalModel> sectionList, itemClickListener itemClickListener)
    {
        this.maqalModels = sectionList;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    public interface itemClickListener
    {
        void onItemClick(MaqalModel maqalModel);
    }

    @Override
    public int getItemCount()
    {
        return maqalModels.size();
    }

    public class MaqalViewHolder extends RecyclerView.ViewHolder
    {
        TextView maqalSelect;
        public MaqalViewHolder(@NonNull View itemView)
        {
            super(itemView);
            maqalSelect = itemView.findViewById(R.id.lang_select);
        }
    }
}
