package com.example.project0.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.project0.R;
import com.example.project0.pojo.HomeModel;
import com.example.project0.ui.courses.CoursesActivity;
import com.example.project0.ui.testYS.TestYourselfActivity;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.StateSet.TAG;

public class HomeActivity extends AppCompatActivity
{
    HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        RecyclerView homeRecycler = findViewById(R.id.home_recycler);
        HomeAdapter homeAdapter = new HomeAdapter();
        homeRecycler.setAdapter(homeAdapter);

        homeViewModel.getHomeListData();

        homeViewModel.homeList.observe(this, new Observer<ArrayList<HomeModel>>()
        {
            @Override
            public void onChanged(ArrayList<HomeModel> homeModels)
            {
                homeAdapter.setList(homeModels, new HomeAdapter.itemClickListener()
                {
                    @Override
                    public void onItemClick(HomeModel homeModel)
                    {
                        intent(homeModel.getSectionName());
                    }
                });
            }
        });

        homeRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    public Intent intent(int name)
    {
        Intent intent = new Intent();
        switch(name)
        {
            case R.string.home_courses:
                intent = new Intent(this, CoursesActivity.class);
                startActivity(intent);
                return intent;
            case R.string.home_test_yourself:
                intent = new Intent(this, TestYourselfActivity.class);
                startActivity(intent);
                return intent;
        }
        return intent;
    }
}
