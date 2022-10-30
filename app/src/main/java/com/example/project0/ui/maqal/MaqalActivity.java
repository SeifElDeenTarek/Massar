package com.example.project0.ui.maqal;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project0.R;
import com.example.project0.pojo.MaqalModel;
import com.example.project0.ui.PdfActivity;
import com.example.project0.ui.fragments.LangViewModel;
import com.example.project0.ui.home.HomeActivity;

import java.util.ArrayList;
import java.util.Objects;

public class MaqalActivity extends AppCompatActivity
{
    LangViewModel langViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maqal);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        langViewModel = ViewModelProviders.of(this).get(LangViewModel.class);
        langViewModel.getMaqalList();

        RecyclerView maqalRecycler = findViewById(R.id.maqal_recycler);
        MaqalAdapter maqalAdapter = new MaqalAdapter();
        maqalRecycler.setLayoutManager(new LinearLayoutManager(this));
        maqalRecycler.setAdapter(maqalAdapter);

        langViewModel.maqalList.observe(this, new Observer<ArrayList<MaqalModel>>()
        {
            @Override
            public void onChanged(ArrayList<MaqalModel> maqalModels)
            {
                maqalAdapter.setList(maqalModels, new MaqalAdapter.itemClickListener()
                {
                    @Override
                    public void onItemClick(MaqalModel maqalModel)
                    {
                        intent();
                    }
                });
            }
        });
    }

    private void intent()
    {
        Intent intent = new Intent(this, PdfActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
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
