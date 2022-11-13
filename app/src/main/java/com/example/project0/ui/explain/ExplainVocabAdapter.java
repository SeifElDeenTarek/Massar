package com.example.project0.ui.explain;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project0.R;
import com.example.project0.pojo.ExplainVocabModel;
import com.google.android.material.card.MaterialCardView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExplainVocabAdapter extends RecyclerView.Adapter<ExplainVocabAdapter.ExplainVocabViewHolder>
{
    List<ExplainVocabModel> explainModels = new ArrayList<>();
    itemClickListener itemClickListener;
    boolean Clicked3 = true;

    @NonNull
    @Override
    public ExplainVocabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new ExplainVocabViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.explain_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExplainVocabViewHolder holder, int position)
    {
        holder.german.setText(explainModels.get(position).getGerman());
        holder.arabic.setText(explainModels.get(position).getArabic());
        holder.germanVoice.setText(String.valueOf(explainModels.get(position).getGermanVoice()));

        holder.image1.setImageResource(R.drawable.share_2);
        holder.image2.setImageResource(R.drawable.volume_2);
        holder.image3.setImageResource(R.drawable.favorite);

        holder.card1.setOnClickListener(v -> {
            itemClickListener.onShareClick(explainModels.get(position));
        });

        holder.card2.setOnClickListener(v -> {
            itemClickListener.onVoiceClick(explainModels.get(position));
        });

        holder.card3.setOnClickListener(v -> {
            itemClickListener.onFavoriteClick(explainModels.get(position));
            if(Clicked3)
            {
                holder.image3.setImageResource(R.drawable.favorite_fill);
                Clicked3 = false;
            } else
            {
                holder.image3.setImageResource(R.drawable.favorite);
                Clicked3 = true;
            }
        });

        holder.itemView.setOnClickListener(v ->{
            itemClickListener.onItemClick(explainModels.get(position));
        });
    }

    @Override
    public int getItemCount()
    {
        return explainModels.size();
    }

    public interface itemClickListener
    {
        void onItemClick(ExplainVocabModel explainModel);
        void onShareClick(ExplainVocabModel explainModel);
        void onVoiceClick(ExplainVocabModel explainModel);
        void onFavoriteClick(ExplainVocabModel explainModel);
    }

    public void setList(List<ExplainVocabModel> explainModels, itemClickListener itemClickListener)
    {
        this.explainModels = explainModels;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    public class ExplainVocabViewHolder extends RecyclerView.ViewHolder
    {
        TextView german, arabic, germanVoice;
        MaterialCardView card1, card2, card3;
        ImageView image1, image2, image3;

        public ExplainVocabViewHolder(@NonNull View itemView)
        {
            super(itemView);

            german = itemView.findViewById(R.id.german);
            arabic = itemView.findViewById(R.id.arabic);
            germanVoice = itemView.findViewById(R.id.audio);

            card1 = itemView.findViewById(R.id.card_icon1);
            card2 = itemView.findViewById(R.id.card_icon2);
            card3 = itemView.findViewById(R.id.card_icon3);

            image1 = itemView.findViewById(R.id.share_icon);
            image2 = itemView.findViewById(R.id.voice_icon);
            image3 = itemView.findViewById(R.id.favorite_icon);

            /**
             card1.setOnClickListener(new View.OnClickListener()
             {

             @Override
             public void onClick(View v)
             {
             if(Clicked1)
             {
             image1.setImageResource(R.drawable.share);
             Clicked1 = false;
             }
             else
             {
             image1.setImageResource(R.drawable.share_fill);
             Clicked1 = true;
             }
             }
             });

             card2.setOnClickListener(new View.OnClickListener()
             {
             @Override
             public void onClick(View v)
             {
             if(Clicked2)
             {
             image2.setImageResource(R.drawable.voice);
             Clicked2 = false;
             }
             else
             {
             image2.setImageResource(R.drawable.voice_fill);
             Clicked2 = true;
             }


             }
             });

             card3.setOnClickListener(new View.OnClickListener()
             {
             @Override
             public void onClick(View v)
             {
             if(Clicked3)
             {
             image3.setImageResource(R.drawable.favorite);
             Clicked3 = false;
             }
             else
             {
             image3.setImageResource(R.drawable.favorite_fill);
             Clicked3 = true;
             }
             }
             });
             **/
        }
    }
}