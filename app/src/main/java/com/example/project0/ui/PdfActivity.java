package com.example.project0.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.project0.R;
import com.example.project0.pojo.MaqalModel;
import com.example.project0.ui.maqal.MaqalActivity;
import com.github.barteksc.pdfviewer.PDFView;

public class PdfActivity extends AppCompatActivity
{
    PDFView maqalPDF;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("");

        maqalPDF = findViewById(R.id.pdf_view);

        maqalPDF.fromAsset("test.pdf")
                .defaultPage(0)
                .enableSwipe(true) // allows to block changing pages using swipe
                .enableDoubletap(true)
                .load();
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
