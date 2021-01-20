package com.example.mymultimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    // create a new Android Resource Directory with type = "raw"
    TextView tv;
    Button playBtn,stopBtn;
    MediaPlayer player;
    Boolean playing;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.textView);
        playBtn=findViewById(R.id.button2);
        stopBtn=findViewById(R.id.button);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSong();
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSong();
            }
        });

        videoView=findViewById(R.id.video);
        // video 1: video available on device:
        //videoView.setVideoURI(Uri.parse("android.resource://com.example.mymultimedia/"+R.raw.movie));
        // video 2: video available online (can't play youtube cause it is not raw video file):
        String uriString="https://developers.google.com/training/images/tacoma_narrows.mp4";
        videoView.setVideoURI(Uri.parse(uriString));
        MediaController mediaController=new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();
    }

    private void stopSong() {
        player.stop();
        player.release();
        tv.setText("Song stopped ...");
        playing=null;
        playBtn.setText("Play");
    }


    private void playSong() {
        if(playing==null){
            player=MediaPlayer.create(this,R.raw.song);
            playing=false;
        }
        if(playing==false){
            player.start();
            tv.setText("Song playing ...");
            playBtn.setText("Pause");
            playing=true;
        }else{
            player.pause();
            playBtn.setText("Resume");
            tv.setText("Song paused ...");
            playing=false;
        }

    }
}