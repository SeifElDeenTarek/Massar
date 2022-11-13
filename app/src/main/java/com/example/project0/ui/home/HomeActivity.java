package com.example.project0.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.SwitchCompat;
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

import static androidx.constraintlayout.widget.StateSet.TAG;

public class HomeActivity extends AppCompatActivity
{
    HomeViewModel homeViewModel;
    SwitchCompat switchBtn;
    boolean dark;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getHomeListData();

        switchBtn = findViewById(R.id.switch_btn);

        if(switchBtn.isChecked())
        {}
        else
        {}

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
