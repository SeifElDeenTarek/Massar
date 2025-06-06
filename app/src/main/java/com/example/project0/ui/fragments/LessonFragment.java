package com.example.project0.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project0.R;
import com.example.project0.pojo.LessonModel;

import java.util.ArrayList;
import java.util.zip.Inflater;

import static androidx.constraintlayout.widget.StateSet.TAG;

public class LessonFragment extends Fragment
{
    public LessonFragment()
    {}

    LangViewModel langViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.lesson_fragment, container, false);

        String level = null;
        String activityName = getActivity().getClass().getSimpleName();

        Bundle bundle = this.getArguments();
        if(bundle != null)
        {
            level = bundle.getString("level");
            Log.d(TAG, "onCreateView: " + level);
        }

        langViewModel = ViewModelProviders.of(this).get(LangViewModel.class);
        langViewModel.getLessonList();

        RecyclerView lessonRecycler = rootView.findViewById(R.id.lesson_recycler);
        final LessonAdapter lessonAdapter = new LessonAdapter();
        lessonRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        lessonRecycler.setAdapter(lessonAdapter);

        String finalLevel = level;

        langViewModel.lessonList.observe(getViewLifecycleOwner(), new Observer<ArrayList<LessonModel>>()
        {
            @Override
            public void onChanged(ArrayList<LessonModel> lessonModels)
            {
                lessonAdapter.setList(lessonModels, new LessonAdapter.itemClickListener()
                {
                    @Override
                    public void onItemClick(LessonModel lessonModel)
                    {
                        Log.d(TAG, "Test " + activityName + " " + finalLevel + " " + lessonModel.getLesson());
                    }
                });
            }
        });



        return rootView;
    }
}
