package com.example.project0.ui.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.project0.R;
import com.example.project0.database.Test;
import com.example.project0.ui.home.HomeActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;
import java.util.List;

import static android.service.controls.ControlsProviderService.TAG;

public class TestActivity extends AppCompatActivity
{
    String level, testType, activity, category;

    CountDownTimer countDownTimer;
    Dialog dialog, scoreDialog;
    LinearProgressIndicator progressIndicator;
    TextView ques, ans1, ans2, ans3, ans4, score, grade;
    MaterialCardView select1, select2, select3, select4;
    List<Test> tests;
    Test test;
    int index = 0, correctCount = 0, wrongCount = 0, timesValue = 0;
    Button next;

    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener()
    {
        @Override
        public void onCompletion(MediaPlayer mp)
        {
            releaseMediaPlayer();
        }
    };

    AudioManager.OnAudioFocusChangeListener mOnAudioChangeListener = new AudioManager.OnAudioFocusChangeListener()
    {
        public void onAudioFocusChange(int focusChange)
        {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS)
            {
                releaseMediaPlayer();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN)
            {
                mMediaPlayer.start();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mAudioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        getSupportActionBar().hide();

        hooks();

        countDownTimer = new CountDownTimer(60000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                timesValue = timesValue + 5;
                progressIndicator.setProgress(timesValue, true);
                progressIndicator.setIndicatorDirection(LinearProgressIndicator.INDICATOR_DIRECTION_LEFT_TO_RIGHT);
            }

            @Override
            public void onFinish()
            {
                dialog = new Dialog(TestActivity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog);

                dialog.findViewById(R.id.try_again).setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        onBackPressed();
                    }
                });
                dialog.show();
            }
        }.start();

        level = getIntent().getStringExtra("level");
        testType = getIntent().getStringExtra("type");
        activity = getIntent().getStringExtra("activity");
        category = getIntent().getStringExtra("category");

        if(category.equals("قواعد"))
        {
            tests = getTestRulesListFromDatabase();
            test = getTestRulesListFromDatabase().get(index);
        }
        else if(category.equals("جمل"))
        {
            tests = getTestSentenceListFromDatabase();
            test = getTestSentenceListFromDatabase().get(index);
        }
        else if(category.equals("مفردات"))
        {
            tests = getTestVocabListFromDatabase();
            test = getTestVocabListFromDatabase().get(index);
        }
        setAllDate();
    }

    private void setAllDate()
    {
        ques.setText(test.getQues());
        ans1.setText(test.getAns1());
        ans2.setText(test.getAns2());
        ans3.setText(test.getAns3());
        ans4.setText(test.getAns4());
    }

    private void hooks()
    {
        progressIndicator = findViewById(R.id.progress);

        ques = findViewById(R.id.ques);

        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);

        select1 = findViewById(R.id.select1);
        select2 = findViewById(R.id.select2);
        select3 = findViewById(R.id.select3);
        select4 = findViewById(R.id.select4);

        next = findViewById(R.id.next);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        countDownTimer.cancel();
    }

    private void Correct(MaterialCardView cardView)
    {
        cardView.setCardBackgroundColor(getResources().getColor(R.color.green));

        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                correctCount++;
                if(index < tests.size() - 1)
                {
                    index++;
                    test = tests.get(index);
                    resetColor();
                    setAllDate();
                    enableButton();
                } else
                {
                    testRight();
                }
            }
        });
    }

    private void Wrong(MaterialCardView cardView)
    {
        cardView.setCardBackgroundColor(getResources().getColor(R.color.red));

        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                wrongCount++;
                if(index < tests.size() - 1)
                {
                    index++;
                    test = tests.get(index);
                    resetColor();
                    setAllDate();
                    enableButton();
                } else
                {
                    testRight();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void testRight()
    {
        countDownTimer.cancel();

        scoreDialog = new Dialog(TestActivity.this);
        scoreDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        scoreDialog.setContentView(R.layout.result_dialog_item);

        score = scoreDialog.findViewById(R.id.test_score);
        grade = scoreDialog.findViewById(R.id.test_grade);

        float total = correctCount + wrongCount;
        Log.d(TAG, "TEST ANSWERS:  " + correctCount + " " + wrongCount + " " + total);

        total = correctCount / total;
        total = total * 100;
        Log.d(TAG, "TEST SCORE:  " + total);

        score.setText(total + "%");
        grade.setText(getGrade(total));

        scoreDialog.findViewById(R.id.try_again_test).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        scoreDialog.findViewById(R.id.back_from_test).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
        scoreDialog.show();
    }

    public String getGrade(float total)
    {
        String[] grades = {"Failed", "Good", "Very Good", "Excellent", "Outstanding"};
        String grade = "";

        if(total >= 0 && total <= 50)
        {
            grade = grades[0];
        }
        else if(total > 50 && total <= 70)
        {
            grade = grades[1];
        }
        else if(total > 70 && total <= 80)
        {
            grade = grades[2];
        }
        else if(total > 80 && total <= 95)
        {
            grade = grades[3];
        }
        else if(total > 95 && total <= 100)
        {
            grade = grades[4];
        }

        return grade;
    }

    public void enableButton()
    {
        select1.setClickable(true);
        select2.setClickable(true);
        select3.setClickable(true);
        select4.setClickable(true);
    }

    public void disableButton()
    {
        select1.setClickable(false);
        select2.setClickable(false);
        select3.setClickable(false);
        select4.setClickable(false);
    }

    public void resetColor()
    {
        select1.setCardBackgroundColor(getResources().getColor(R.color.primary));
        select2.setCardBackgroundColor(getResources().getColor(R.color.primary));
        select3.setCardBackgroundColor(getResources().getColor(R.color.primary));
        select4.setCardBackgroundColor(getResources().getColor(R.color.primary));
    }

    private ArrayList<Test> getTestRulesListFromDatabase()
    {
        ArrayList<Test> list = new ArrayList<>();

        list.add(new Test("Laura ..... nach Wien gefahren", "ist", "seid", "ist", "bist", "sind"));
        list.add(new Test("Möchten Sie einen .......?", "Kaffee", "seid", "ist", "bist", "Kaffee"));
        list.add(new Test("Wir möchten ...... Hause fahren.", "nach", "nach", "ist", "bist", "sind"));
        list.add(new Test("Laura ..... nach Wien gefahren", "ist", "seid", "ist", "bist", "sind"));
        list.add(new Test("Möchten Sie einen .......?", "Kaffee", "seid", "ist", "bist", "Kaffee"));
        list.add(new Test("Wir möchten ...... Hause fahren.", "nach", "nach", "ist", "bist", "sind"));
        list.add(new Test("Wir möchten ...... Hause fahren.", "nach", "nach", "ist", "bist", "sind"));
        list.add(new Test("Laura ..... nach Wien gefahren", "ist", "seid", "ist", "bist", "sind"));
        list.add(new Test("Möchten Sie einen .......?", "Kaffee", "seid", "ist", "bist", "Kaffee"));
        list.add(new Test("Wir möchten ...... Hause fahren.", "nach", "nach", "ist", "bist", "sind"));

        return list;
    }

    private ArrayList<Test> getTestSentenceListFromDatabase()
    {
        ArrayList<Test> list = new ArrayList<>();

        list.add(new Test("العفو,بكل سرور", "Bitte, gern geschehen", "Bis denn", "Bis später", "Keine Ursache", "Bitte, gern geschehen"));
        list.add(new Test("أحب أن أشرب شيئًا.", "Ich möchte etwas trinken.", "Bis denn", "Ich möchte etwas trinken.", "Keine Ursache", "Bitte, gern geschehen"));
        list.add(new Test("أحب أن أريح نفسي قليلاً.", "Ich möchte mich etwas ausruhen.", "Bis denn", "Ich möchte mich etwas ausruhen.", "Keine Ursache", "Bitte, gern geschehen"));
        list.add(new Test("العفو,بكل سرور", "Bitte, gern geschehen", "Bis denn", "Bis später", "Keine Ursache", "Bitte, gern geschehen"));
        list.add(new Test("أحب أن أشرب شيئًا.", "Ich möchte etwas trinken.", "Bis denn", "Ich möchte etwas trinken.", "Keine Ursache", "Bitte, gern geschehen"));
        list.add(new Test("أحب أن أريح نفسي قليلاً.", "Ich möchte mich etwas ausruhen.", "Bis denn", "Ich möchte mich etwas ausruhen.", "Keine Ursache", "Bitte, gern geschehen"));
        list.add(new Test("أحب أن أريح نفسي قليلاً.", "Ich möchte mich etwas ausruhen.", "Bis denn", "Ich möchte mich etwas ausruhen.", "Keine Ursache", "Bitte, gern geschehen"));
        list.add(new Test("العفو,بكل سرور", "Bitte, gern geschehen", "Bis denn", "Bis später", "Keine Ursache", "Bitte, gern geschehen"));
        list.add(new Test("أحب أن أشرب شيئًا.", "Ich möchte etwas trinken.", "Bis denn", "Ich möchte etwas trinken.", "Keine Ursache", "Bitte, gern geschehen"));
        list.add(new Test("أحب أن أريح نفسي قليلاً.", "Ich möchte mich etwas ausruhen.", "Bis denn", "Ich möchte mich etwas ausruhen.", "Keine Ursache", "Bitte, gern geschehen"));

        return list;
    }

    private ArrayList<Test> getTestVocabListFromDatabase()
    {
        ArrayList<Test> list = new ArrayList<>();

        list.add(new Test("الذراع", "der Arm", "Tschüss", "Bitte", "Danke", "der Arm"));
        list.add(new Test("مرحبا", "Hallo", "Tschüss", "Bitte", "Hallo", "der Arm"));
        list.add(new Test("مع السلامة", "Tschüss", "Tschüss", "Bitte", "Danke", "der Arm"));
        list.add(new Test("الذراع", "der Arm", "Tschüss", "Bitte", "Danke", "der Arm"));


        return list;
    }

    public void select1(View view)
    {
        disableButton();
        if(test.getAns1().equals(test.getRans()))
        {
            playSound(R.raw.right_answer);
            select1.setCardBackgroundColor(getResources().getColor(R.color.green));

            if(index <= tests.size() - 1)
            {
                Correct(select1);
            }
        }
        else
        {
            playSound(R.raw.wrong_answer);
            Wrong(select1);
        }
    }

    public void select2(View view)
    {
        disableButton();
        if(test.getAns2().equals(test.getRans()))
        {
            playSound(R.raw.right_answer);
            select2.setCardBackgroundColor(getResources().getColor(R.color.green));

            if(index <= tests.size() - 1)
            {
                Correct(select2);
            }
        }
        else
        {
            playSound(R.raw.wrong_answer);
            Wrong(select2);
        }
    }

    public void select3(View view)
    {
        disableButton();
        if(test.getAns3().equals(test.getRans()))
        {
            playSound(R.raw.right_answer);
            select3.setCardBackgroundColor(getResources().getColor(R.color.green));

            if(index <= tests.size() - 1)
            {
                Correct(select3);
            }
        }
        else
        {
            playSound(R.raw.wrong_answer);
            Wrong(select3);
        }
    }

    public void select4(View view)
    {
        disableButton();
        if(test.getAns4().equals(test.getRans()))
        {
            playSound(R.raw.right_answer);
            select4.setCardBackgroundColor(getResources().getColor(R.color.green));

            if(index <= tests.size() - 1)
            {
                Correct(select4);
            }
        }
        else
        {
            playSound(R.raw.wrong_answer);
            Wrong(select4);
        }
    }

    private void releaseMediaPlayer()
    {
        if (mMediaPlayer != null)
        {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioChangeListener);
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        releaseMediaPlayer();
    }

    public void playSound(int sound)
    {
        releaseMediaPlayer();

        int result = mAudioManager.requestAudioFocus(mOnAudioChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
        {
            mMediaPlayer = MediaPlayer.create(getApplicationContext(), sound);
            mMediaPlayer.start();
        }

        mMediaPlayer.setOnCompletionListener(mCompletionListener);
    }
}