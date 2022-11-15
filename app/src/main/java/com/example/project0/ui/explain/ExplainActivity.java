package com.example.project0.ui.explain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.project0.BuildConfig;
import com.example.project0.R;
import com.example.project0.firebase.ExplainConvDao;
import com.example.project0.firebase.ExplainSentenceDao;
import com.example.project0.firebase.ExplainVocabDao;
import com.example.project0.pojo.ExplainConvModel;
import com.example.project0.pojo.ExplainSentenceModel;
import com.example.project0.pojo.ExplainVocabModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static androidx.constraintlayout.widget.StateSet.TAG;

public class ExplainActivity extends AppCompatActivity
{
    String level, testType, activity, category;
    RecyclerView explainRecycler;
    ExplainConvAdapter explainConvAdapter; ExplainSentenceAdapter explainSentenceAdapter; ExplainVocabAdapter explainVocabAdapter;
    ExplainVocabDao vocabDao;
    ExplainSentenceDao sentenceDao;
    ExplainConvDao convDao;
    String key;
    boolean Clicked2 = true, Clicked3 = true;

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
        setContentView(R.layout.activity_explain);

        Objects.requireNonNull(getSupportActionBar()).hide();

        level = getIntent().getStringExtra("level");
        testType = getIntent().getStringExtra("type");
        activity = getIntent().getStringExtra("activity");
        category = getIntent().getStringExtra("category");

        mAudioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        explainRecycler = findViewById(R.id.explain_recycler);
        explainRecycler.setLayoutManager(new LinearLayoutManager(this));

        if(category.equals(String.valueOf(R.string.catgory3)))
        {
            explainVocabAdapter = new ExplainVocabAdapter();
            explainRecycler.setAdapter(explainVocabAdapter);
            vocabDao = new ExplainVocabDao();
            loadVocabData();
        }
        else if(category.equals(String.valueOf(R.string.catgory2)))
        {
            explainSentenceAdapter = new ExplainSentenceAdapter();
            explainRecycler.setAdapter(explainSentenceAdapter);
            sentenceDao = new ExplainSentenceDao();
            loadSentenceData();
        }
        else
            {
                explainConvAdapter = new ExplainConvAdapter();
                explainRecycler.setAdapter(explainConvAdapter);
                convDao = new ExplainConvDao();
                loadConvData();
            }
    }

    private void loadVocabData()
    {
        vocabDao.get().addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                List<ExplainVocabModel> list = new ArrayList<>();

                for(DataSnapshot data : snapshot.getChildren())
                {
                    ExplainVocabModel model = data.getValue(ExplainVocabModel.class);
                    list.add(model);

                    key = data.getKey();
                    Log.d(TAG, "Item Key:  " + key);
                }

                explainVocabAdapter.setList(list, new ExplainVocabAdapter.itemClickListener() {
                    @Override
                    public void onItemClick(ExplainVocabModel explainModel)
                    {
                    }

                    @Override
                    public void onShareClick(ExplainVocabModel explainModel)
                    {
                        try {
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                            String shareMessage= "\nLet me recommend you this application\n\n";
                            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                            startActivity(Intent.createChooser(shareIntent, "choose one"));
                        } catch(Exception e)
                        {
                            //e.toString();
                        }
                    }

                    @Override
                    public void onVoiceClick(ExplainVocabModel explainModel)
                    {
                        if(Clicked2)
                        {
                            playSound(explainModel.getGermanVoice());
                            Clicked2 = false;
                        } else
                        {
                            releaseMediaPlayer();
                            Clicked2 = true;
                        }
                     }

                    @Override
                    public void onFavoriteClick(ExplainVocabModel explainModel)
                    {
                        if(Clicked3)
                        {
                            Toast.makeText(getApplicationContext(), "Added to favorite", Toast.LENGTH_SHORT).show();
                            Clicked3 = false;
                        } else
                        {
                            Toast.makeText(getApplicationContext(), "Removed Added to favorite", Toast.LENGTH_SHORT).show();
                            Clicked3 = true;
                        }
                    }
                });
                explainVocabAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {}
        });
    }

    private void loadSentenceData()
    {
        explainRecycler.setAdapter(explainSentenceAdapter);

        sentenceDao.get().addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                List<ExplainSentenceModel> list = new ArrayList<>();

                for(DataSnapshot data : snapshot.getChildren())
                {
                    ExplainSentenceModel model = data.getValue(ExplainSentenceModel.class);
                    list.add(model);

                    key = data.getKey();
                    Log.d(TAG, "Item Key:  " + key);
                }

                explainSentenceAdapter.setList(list, new ExplainSentenceAdapter.itemClickListener()
                {
                    @Override
                    public void onItemClick(ExplainSentenceModel explainSentenceModel)
                    {
                    }
                });
                explainSentenceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {}
        });
    }

    private void loadConvData()
    {
        explainRecycler.setAdapter(explainConvAdapter);

        convDao.get().addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                List<ExplainConvModel> list = new ArrayList<>();

                for(DataSnapshot data : snapshot.getChildren())
                {
                    ExplainConvModel model = data.getValue(ExplainConvModel.class);
                    list.add(model);

                    key = data.getKey();
                    Log.d(TAG, "Item Key:  " + key);
                }

                explainConvAdapter.setList(list, new ExplainConvAdapter.itemClickListener()
                {
                    @Override
                    public void onItemClick(ExplainConvModel explainConvModel)
                    {
                    }
                });
                explainConvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {}
        });
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
    public void onBackPressed()
    {
        super.onBackPressed();
        releaseMediaPlayer();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        releaseMediaPlayer();
    }

    public void playSound(String sound)
    {
        int result = mAudioManager.requestAudioFocus(mOnAudioChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
        {
            mMediaPlayer = new MediaPlayer();
            try {
                mMediaPlayer.setDataSource(getApplicationContext(), Uri.parse(sound));
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
    }
}
