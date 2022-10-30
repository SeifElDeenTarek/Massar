package com.example.project0.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project0.R;
import com.example.project0.pojo.HomeModel;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel
{
    public MutableLiveData<ArrayList<HomeModel>> homeList = new MutableLiveData<>();

    public void getHomeListData()
    {
        homeList.setValue(getHomeListDataFromDataBase());
    }

    private ArrayList<HomeModel> getHomeListDataFromDataBase()
    {
        ArrayList<HomeModel> homeModels = new ArrayList<>();

        homeModels.add(new HomeModel(R.drawable.courses, R.string.home_courses));
        homeModels.add(new HomeModel(R.drawable.exams, R.string.home_test_yourself));
        homeModels.add(new HomeModel(R.drawable.exams_prep, R.string.home_exam_prep));
        homeModels.add(new HomeModel(R.drawable.articles, R.string.home_maqal));

        return homeModels;
    }
}
