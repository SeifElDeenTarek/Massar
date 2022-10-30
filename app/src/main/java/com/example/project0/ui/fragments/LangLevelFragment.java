package com.example.project0.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project0.R;
import com.example.project0.pojo.LangLevelModel;

import java.util.ArrayList;
import java.util.Objects;

import static androidx.constraintlayout.widget.StateSet.TAG;

public class LangLevelFragment extends Fragment
{
    public LangLevelFragment()
    {}

    LangViewModel langViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.lang_level_fragment, container, false);

        String activityName = getActivity().getClass().getSimpleName();
        int containerID = R.id.courses_container;

        if(activityName.equals("TestYourselfActivity"))
        {
            containerID = R.id.testYS_container;
        }
        else if(activityName.equals("ExamPrepActivity"))
        {
            containerID = R.id.exam_prep_container;
        }

        langViewModel = ViewModelProviders.of(this).get(LangViewModel.class);
        langViewModel.getLangLevelList();

        RecyclerView langRecycler = rootView.findViewById(R.id.lang_level_recycler);
        final LangLevelAdapter langLevelAdapter = new LangLevelAdapter();
        langRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        langRecycler.setAdapter(langLevelAdapter);

        Log.d(TAG, "onCreateView: " + containerID + activityName);

        int finalContainerID = containerID;

        langViewModel.langLevelList.observe(getViewLifecycleOwner(), new Observer<ArrayList<LangLevelModel>>()
        {
            @Override
            public void onChanged(ArrayList<LangLevelModel> langLevelModels)
            {
                langLevelAdapter.setList(langLevelModels, new LangLevelAdapter.itemClickListener()
                {
                    @Override
                    public void onItemClick(LangLevelModel langLevelModel)
                    {
                        Fragment fragment = new LessonFragment();
                        //Bundle bundle = new Bundle();
                        //bundle.putString("level", langLevelModel.getLangLevel());
                        //fragment.setArguments(bundle);
                        if(!activityName.equals("ExamPrepActivity"))
                        {
                            fragment = new LangCategoryFragment();
                            Log.d(TAG, "onCreateView: ");
                        }
                        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(finalContainerID, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });
            }
        });
        return rootView;
    }
}
