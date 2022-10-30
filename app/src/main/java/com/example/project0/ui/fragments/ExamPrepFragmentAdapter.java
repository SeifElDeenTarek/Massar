package com.example.project0.ui.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ExamPrepFragmentAdapter extends FragmentStateAdapter
{
    public ExamPrepFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle)
    {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position)
    {
        if (position == 0)
        {
            return new LangLevelFragment();
        }
        else
        {
            return new LessonFragment();
        }
    }

    @Override
    public int getItemCount()
    {
        return 2;
    }
}