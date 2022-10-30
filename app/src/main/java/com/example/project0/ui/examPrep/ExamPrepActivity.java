package com.example.project0.ui.examPrep;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.project0.R;
import com.example.project0.ui.fragments.ExamPrepFragmentAdapter;

import java.util.Objects;

public class ExamPrepActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_prep);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        ViewPager2 viewPager = findViewById(R.id.exam_prep__view_pager);
        ExamPrepFragmentAdapter examPrepFragmentAdapter = new ExamPrepFragmentAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(examPrepFragmentAdapter);
        viewPager.setUserInputEnabled(false);

        //Add back navigation in the title bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            //Title bar back press triggers onBackPressed()
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Both navigation bar back press and title bar back press will trigger this method
    @Override
    public void onBackPressed()
    {
        if (getFragmentManager().getBackStackEntryCount() > 0 )
        {
            getFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }
}
