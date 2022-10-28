package com.example.project0.ui.courses;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project0.R;
import com.example.project0.pojo.LangCategoryModel;
import com.example.project0.pojo.LangLevelModel;

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
        View rootView = inflater.inflate(R.layout.lang_category, container, false);

        langViewModel = ViewModelProviders.of(this).get(LangViewModel.class);
        langViewModel.getLangCategoryList();

        Bundle bundle = this.getArguments();
        if(bundle != null)
        {
            String level = bundle.getString("level");
            Log.d(TAG, "onCreateView: " + level);
        }


        RecyclerView langRecycler = rootView.findViewById(R.id.lang_category_recycler);
        final LangCategoryAdapter langCategoryAdapter = new LangCategoryAdapter();
        langRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        langRecycler.setAdapter(langCategoryAdapter);

        langViewModel.langCategoryList.observe(getViewLifecycleOwner(), new Observer<ArrayList<LangCategoryModel>>()
        {
            @Override
            public void onChanged(ArrayList<LangCategoryModel> langCategoryModels)
            {
                langCategoryAdapter.setList(langCategoryModels, new LangCategoryAdapter.itemClickListener()
                {
                    @Override
                    public void onItemClick(LangCategoryModel langCategoryModel)
                    {
                    }
                });
            }
        });

        return rootView;
    }
}
