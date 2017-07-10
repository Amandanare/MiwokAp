package com.example.admin.miwokapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        mAudioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<word> words = new ArrayList<word>();
        words.add(new word("Red", "weṭeṭṭi", R.drawable.red, R.raw.red));
        words.add(new word("Green", "chiwiiṭә", R.drawable.green, R.raw.green));
        words.add(new word("Brown", "ṭopiisә", R.drawable.brown, R.raw.brown));
        words.add(new word("Mustard yellow", "chokokki", R.drawable.mustard_yellow, R.raw.mustard_yellow));
        words.add(new word("Dusty yellow", "ṭakaakki", R.drawable.dusty_yellow, R.raw.dusty_yellow));
        words.add(new word("Gray", "ṭopoppi", R.drawable.gray, R.raw.gray));
        words.add(new word("Black", "kululli", R.drawable.black, R.raw.black));
        words.add(new word("White", "kelelli", R.drawable.white, R.raw.white));

        //words.add("Red");
        //words.add("Green");
        //words.add("Brown");
        //words.add("Mustard yellow");
        //words.add("Dusty yellow");
        //words.add("Gray");
        //words.add("Black");
        //words.add("White");
        //words.add("Pink");
        //words.add("Blue");


        //ArrayAdapter<String> itemAdapter=
        //new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,words);
        WordAdapter adapter = new WordAdapter(this, words);
        ListView listView = (ListView) findViewById(R.id.activity_color);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                word word = words.get(position);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                mMediaPlayer = MediaPlayer.create(ColorActivity.this, word.getAudioResourceId());
                mMediaPlayer.start();
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

        //Log.v("ColorActivity", "Word at index 0: " + words.get(0));
        //Log.v("ColorActivity", "Word at index 1: " + words.get(1));
        //Log.v("ColorActivity", "Word at index 2: " + words.get(2));
        //Log.v("ColorActivity", "Word at index 3: " + words.get(3));
        //Log.v("ColorActivity", "Word at index 4: " + words.get(4));
        //Log.v("ColorActivity", "Word at index 5: " + words.get(5));
       //Log.v("ColorActivity", "Word at index 6: " + words.get(6));
        //Log.v("ColorActivity", "Word at index 7: " + words.get(7));
        //Log.v("ColorActivity", "Word at index 8: " + words.get(8));
        //Log.v("ColorActivity", "Word at index 9: " + words.get(9));
    }

