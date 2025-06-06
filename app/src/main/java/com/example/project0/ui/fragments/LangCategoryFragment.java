package com.example.project0.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project0.R;
import com.example.project0.pojo.LangCategoryModel;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.StateSet.TAG;

public class LangCategoryFragment extends Fragment
{
    public LangCategoryFragment()
    {}

    LangViewModel langViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.lang_category_fragment, container, false);

        String type = null, level = null;

        langViewModel = ViewModelProviders.of(this).get(LangViewModel.class);
        langViewModel.getLangCategoryList();

        String activityName = getActivity().getClass().getSimpleName();

        Bundle bundle = this.getArguments();
        if(bundle != null)
        {
            type = bundle.getString("type");
            level = bundle.getString("level");
            Log.d(TAG, "onCreateView: " + type + level);
        }

        RecyclerView langRecycler = rootView.findViewById(R.id.lang_category_recycler);
        final LangCategoryAdapter langCategoryAdapter = new LangCategoryAdapter();
        langRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        langRecycler.setAdapter(langCategoryAdapter);

        String finalType = type;
        String finalLevel = level;

        langViewModel.langCategoryList.observe(getViewLifecycleOwner(), new Observer<ArrayList<LangCategoryModel>>()
        {
            @Override
            public void onChanged(ArrayList<LangCategoryModel> langCategoryModels)
            {
                if(activityName.equals("TestYourselfActivity"))
                {
                    langCategoryModels = new ArrayList<>(langCategoryModels.subList(0, 3));

                    Log.d(TAG, "Test  " + activityName + " " + finalType + " " + finalLevel);
                }
                langCategoryAdapter.setList(langCategoryModels, new LangCategoryAdapter.itemClickListener()
                {
                    @Override
                    public void onItemClick(LangCategoryModel langCategoryModel)
                    {
                        Log.d(TAG, "Test  " + activityName  + " " + finalLevel + langCategoryModel.getLangCategory());
                    }
                });
            }
        });
        return rootView;
    }
}