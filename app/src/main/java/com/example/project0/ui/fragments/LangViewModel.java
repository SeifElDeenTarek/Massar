package com.example.project0.ui.fragments;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project0.pojo.LangCategoryModel;
import com.example.project0.pojo.LangLevelModel;
import com.example.project0.pojo.TestYsSelectModel;

import java.util.ArrayList;

public class LangViewModel extends ViewModel
{
    public MutableLiveData<ArrayList<LangLevelModel>> langLevelList = new MutableLiveData<>();
    public MutableLiveData<ArrayList<LangCategoryModel>> langCategoryList = new MutableLiveData<>();
    public MutableLiveData<ArrayList<TestYsSelectModel>> testYsSelectList = new MutableLiveData<>();

    public void getLangLevelList()
    {
        langLevelList.setValue(getLangLevelListFromDatabase());
    }

    public void getLangCategoryList()
    {
        langCategoryList.setValue(getLangCategoryListFromDatabase());
    }

    public void getTestYsSelectList()
    {
        testYsSelectList.setValue(getTestYsSelectListFromDatabase());
    }

    private ArrayList<LangLevelModel> getLangLevelListFromDatabase()
    {
        ArrayList<LangLevelModel> langLevelList = new ArrayList<>();

        langLevelList.add(new LangLevelModel("A1"));
        langLevelList.add(new LangLevelModel("A2"));
        langLevelList.add(new LangLevelModel("B1"));
        langLevelList.add(new LangLevelModel("B2"));
        langLevelList.add(new LangLevelModel("C1"));
        langLevelList.add(new LangLevelModel("C2"));

        return langLevelList;
    }

    private ArrayList<LangCategoryModel> getLangCategoryListFromDatabase()
    {
        ArrayList<LangCategoryModel> langCategoryList = new ArrayList<>();

        langCategoryList.add(new LangCategoryModel("قواعد"));
        langCategoryList.add(new LangCategoryModel("جمل"));
        langCategoryList.add(new LangCategoryModel("مفردات"));
        langCategoryList.add(new LangCategoryModel("محادثة"));

        return langCategoryList;
    }

    private ArrayList<TestYsSelectModel> getTestYsSelectListFromDatabase()
    {
        ArrayList<TestYsSelectModel> testYsSelectList = new ArrayList<>();

        testYsSelectList.add(new TestYsSelectModel("حسب المستوي"));
        testYsSelectList.add(new TestYsSelectModel("بشكل عام"));

        return testYsSelectList;
    }
}
