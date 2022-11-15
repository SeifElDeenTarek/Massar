package com.example.project0.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project0.R;
import com.example.project0.pojo.TestYsSelectModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class TestYsSelectFragment extends Fragment
{
    public TestYsSelectFragment()
    {}

    LangViewModel langViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.testys_select_fragment, container, false);

        langViewModel = ViewModelProviders.of(this).get(LangViewModel.class);
        langViewModel.getTestYsSelectList();

        String activityName = getActivity().getClass().getSimpleName();
        int containerID = R.id.testYS_container;

        RecyclerView testYsRecycler = rootView.findViewById(R.id.test_ys_recycler);
        TestYsSelectAdapter testYsSelectAdapter = new TestYsSelectAdapter();
        testYsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        testYsRecycler.setAdapter(testYsSelectAdapter);

        langViewModel.testYsSelectList.observe(getViewLifecycleOwner(), new Observer<ArrayList<TestYsSelectModel>>()
        {
            @Override
            public void onChanged(ArrayList<TestYsSelectModel> testYsSelectModels)
            {
                testYsSelectAdapter.setList(testYsSelectModels, new TestYsSelectAdapter.itemClickListener()
                {
                    @Override
                    public void onItemClick(TestYsSelectModel testYsSelectModel)
                    {
                        Fragment fragment = new LangLevelFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("type", String.valueOf(testYsSelectModel.getType()));
                        bundle.putString("activity", activityName);
                        if(String.valueOf(testYsSelectModel.getType()).equals("بشكل عام"))
                        {
                            fragment = new LangCategoryFragment();
                        }
                        fragment.setArguments(bundle);
                        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(containerID, fragment);
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
