package com.example.project0.ui.testYS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.project0.R;
import com.example.project0.ui.courses.FragmentAdapter;

public class TestYourselfActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_yourself);

        ViewPager2 viewPager = findViewById(R.id.testYS_view_pager);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(fragmentAdapter);

        viewPager.setUserInputEnabled(false);
    }
}
