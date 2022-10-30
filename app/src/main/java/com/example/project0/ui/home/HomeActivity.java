package com.example.project0.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project0.R;
import com.example.project0.pojo.HomeModel;
import com.example.project0.ui.courses.CoursesActivity;
import com.example.project0.ui.examPrep.ExamPrepActivity;
import com.example.project0.ui.maqal.MaqalActivity;
import com.example.project0.ui.testYS.TestYourselfActivity;

import java.util.ArrayList;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity
{
    HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

/**
        getWindow().setStatusBarColor(Color.parseColor("#453F6B"));
        ActionBar actionBar;

        actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color='#453F6B'</font>"));
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#453F6B"));
        actionBar.setBackgroundDrawable(colorDrawable);
**/

        Objects.requireNonNull(getSupportActionBar()).hide();

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getHomeListData();

        RecyclerView homeRecycler = findViewById(R.id.home_recycler);
        HomeAdapter homeAdapter = new HomeAdapter();
        homeRecycler.setLayoutManager(new LinearLayoutManager(this));
        homeRecycler.setAdapter(homeAdapter);

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
            case R.string.home_exam_prep:
                intent = new Intent(this, ExamPrepActivity.class);
                startActivity(intent);
                return intent;
            case R.string.home_maqal:
                intent = new Intent(this, MaqalActivity.class);
                startActivity(intent);
                return intent;
        }
        return intent;
    }
}
