package com.example.project0.ui.explain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.project0.R;
import com.example.project0.firebase.ExplainConvDao;
import com.example.project0.firebase.ExplainSentenceDao;
import com.example.project0.firebase.ExplainVocabDao;
import com.example.project0.pojo.ExplainConvModel;
import com.example.project0.pojo.ExplainModel;
import com.example.project0.pojo.ExplainSentenceModel;
import com.example.project0.pojo.ExplainVocabModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.StateSet.TAG;

public class ExplainActivity extends AppCompatActivity
{
    String level, testType, activity, category;
    RecyclerView explainRecycler;
    ExplainConvAdapter explainConvAdapter; ExplainSentenceAdapter explainSentenceAdapter; ExplainVocabAdapter explainVocabAdapter;
    ExplainVocabDao vocabDao;
    ExplainSentenceDao sentenceDao;
    ExplainConvDao convDao;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

        getSupportActionBar().setTitle("مفردات");

        getSupportActionBar().setElevation(0);

        level = getIntent().getStringExtra("level");
        testType = getIntent().getStringExtra("type");
        activity = getIntent().getStringExtra("activity");
        category = getIntent().getStringExtra("category");

        explainRecycler = findViewById(R.id.explain_recycler);
        explainVocabAdapter = new ExplainVocabAdapter();
        explainRecycler.setLayoutManager(new LinearLayoutManager(this));
        explainRecycler.setAdapter(explainVocabAdapter);

        vocabDao = new ExplainVocabDao();

        loadVocabData();
/**
        if(category.equals("مفردات"))
        {}
        else if(category.equals("جمل"))
        {}
        else
            {}
 **/
    }

    private void loadVocabData()
    {
        vocabDao.get().addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                List<ExplainVocabModel> list = new ArrayList<>();

                for(DataSnapshot data : snapshot.getChildren())
                {
                    ExplainVocabModel model = data.getValue(ExplainVocabModel.class);
                    list.add(model);

                    key = data.getKey();
                    Log.d(TAG, "Item Key:  " + key);
                }

                explainVocabAdapter.setList(list, new ExplainVocabAdapter.itemClickListener()
                {
                    @Override
                    public void onItemClick(ExplainVocabModel explainVocabModel)
                    {
                    }
                });
                explainVocabAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {}
        });
    }

    private void loadSentenceData()
    {
        explainRecycler.setAdapter(explainSentenceAdapter);

        sentenceDao.get().addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                List<ExplainSentenceModel> list = new ArrayList<>();

                for(DataSnapshot data : snapshot.getChildren())
                {
                    ExplainSentenceModel model = data.getValue(ExplainSentenceModel.class);
                    list.add(model);

                    key = data.getKey();
                    Log.d(TAG, "Item Key:  " + key);
                }

                explainSentenceAdapter.setList(list, new ExplainSentenceAdapter.itemClickListener()
                {
                    @Override
                    public void onItemClick(ExplainSentenceModel explainSentenceModel)
                    {
                    }
                });
                explainSentenceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {}
        });
    }

    private void loadConvData()
    {
        explainRecycler.setAdapter(explainConvAdapter);

        convDao.get().addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                List<ExplainConvModel> list = new ArrayList<>();

                for(DataSnapshot data : snapshot.getChildren())
                {
                    ExplainConvModel model = data.getValue(ExplainConvModel.class);
                    list.add(model);

                    key = data.getKey();
                    Log.d(TAG, "Item Key:  " + key);
                }

                explainConvAdapter.setList(list, new ExplainConvAdapter.itemClickListener()
                {
                    @Override
                    public void onItemClick(ExplainConvModel explainConvModel)
                    {
                    }
                });
                explainConvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {}
        });
    }
}
