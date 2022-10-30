package com.example.project0.ui.maqal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project0.R;
import com.example.project0.pojo.MaqalModel;

import java.util.ArrayList;
import java.util.Objects;

public class MaqalActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maqal);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        ArrayList<MaqalModel> maqalList = testMaqal();

        RecyclerView maqalRecycler = findViewById(R.id.maqal_recycler);
        MaqalAdapter maqalAdapter = new MaqalAdapter();
        maqalRecycler.setLayoutManager(new LinearLayoutManager(this));
        maqalAdapter.setList(maqalList, new MaqalAdapter.itemClickListener()
        {
            @Override
            public void onItemClick(MaqalModel maqalModel)
            {
            }
        }
        );
        maqalRecycler.setAdapter(maqalAdapter);
    }

    private ArrayList<MaqalModel> testMaqal()
    {
        ArrayList<MaqalModel> models = new ArrayList<>();

        models.add(new MaqalModel("Name 1"));
        models.add(new MaqalModel("Name 2"));
        models.add(new MaqalModel("Name 3"));
        models.add(new MaqalModel("Name 4"));
        models.add(new MaqalModel("Name 5"));
        models.add(new MaqalModel("Name 6"));
        models.add(new MaqalModel("Name 7"));
        models.add(new MaqalModel("Name 8"));
        models.add(new MaqalModel("Name 9"));
        models.add(new MaqalModel("Name 10"));
        models.add(new MaqalModel("Name 11"));
        models.add(new MaqalModel("Name 12"));
        models.add(new MaqalModel("Name 13"));
        models.add(new MaqalModel("Name 14"));
        models.add(new MaqalModel("Name 15"));
        models.add(new MaqalModel("Name 16"));
        models.add(new MaqalModel("Name 17"));

        return models;
    }
}
