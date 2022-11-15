package com.example.project0.ui.fragments;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project0.R;
import com.example.project0.pojo.LangCategoryModel;
import com.example.project0.pojo.LangLevelModel;
import com.example.project0.pojo.LessonModel;
import com.example.project0.pojo.MaqalModel;
import com.example.project0.pojo.TestYsSelectModel;

import java.util.ArrayList;

public class LangViewModel extends ViewModel
{
    public MutableLiveData<ArrayList<LangLevelModel>> langLevelList = new MutableLiveData<>();
    public MutableLiveData<ArrayList<LangCategoryModel>> langCategoryList = new MutableLiveData<>();
    public MutableLiveData<ArrayList<TestYsSelectModel>> testYsSelectList = new MutableLiveData<>();
    public MutableLiveData<ArrayList<LessonModel>> lessonList = new MutableLiveData<>();
    public MutableLiveData<ArrayList<MaqalModel>> maqalList = new MutableLiveData<>();

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
    public void getLessonList()
    {
        lessonList.setValue(getLessonListFromDatabase());
    }

    public void getMaqalList()
    {
        maqalList.setValue(getMaqalListFromDatabase());
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

        langCategoryList.add(new LangCategoryModel(R.string.catgory1));
        langCategoryList.add(new LangCategoryModel(R.string.catgory2));
        langCategoryList.add(new LangCategoryModel(R.string.catgory3));
        langCategoryList.add(new LangCategoryModel(R.string.catgory4));

        return langCategoryList;
    }

    private ArrayList<TestYsSelectModel> getTestYsSelectListFromDatabase()
    {
        ArrayList<TestYsSelectModel> testYsSelectList = new ArrayList<>();

        testYsSelectList.add(new TestYsSelectModel(R.string.ty_bylevel));
        testYsSelectList.add(new TestYsSelectModel(R.string.ty_generally));

        return testYsSelectList;
    }

    private ArrayList<LessonModel> getLessonListFromDatabase()
    {
        ArrayList<LessonModel> lessonModels = new ArrayList<>();

        lessonModels.add(new LessonModel("درس 1"));
        lessonModels.add(new LessonModel("درس 2"));
        lessonModels.add(new LessonModel("درس 3"));
        lessonModels.add(new LessonModel("درس 4"));
        lessonModels.add(new LessonModel("درس 5"));
        lessonModels.add(new LessonModel("درس 6"));
        lessonModels.add(new LessonModel("درس 7"));
        lessonModels.add(new LessonModel("درس 8"));
        lessonModels.add(new LessonModel("درس 9"));

        return lessonModels;
    }

    private ArrayList<MaqalModel> getMaqalListFromDatabase()
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

        return models;
    }
}
