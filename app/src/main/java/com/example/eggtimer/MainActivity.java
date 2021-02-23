package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button startEndButton;
    TextView timeTextView;
    SeekBar timeSeekBar;
    CountDownTimer eggTimer;
    MediaPlayer mp;
    boolean timerIsOn = false;

    public void initiateClick(View view){

        if(!timerIsOn){
            timerIsOn = true;
            Log.i("Timer is on",String.valueOf(timerIsOn));
            startEndButton.setText("STOP");
            timeSeekBar.setEnabled(false);

            eggTimer = new CountDownTimer(timeSeekBar.getProgress() * 1000,1000){

                public void onTick(long millisecondsUntilDone){

                    int minutes = (int) millisecondsUntilDone / 1000 / 60;
                    int seconds = (int) (millisecondsUntilDone / 1000) % 60;

                    String stringSeconds = String.valueOf(seconds);

                    if(seconds <=9)
                        stringSeconds = "0" + stringSeconds;

                    Log.i("Seconds left", String.valueOf(millisecondsUntilDone/ 1000));
                    timeTextView.setText(minutes+" : "+stringSeconds);
                }

                public void onFinish(){
                    mp.start();
                    Log.i("Done!","No more countdown");
                    timerIsOn = false;
                    startEndButton.setText("START");
                    timeSeekBar.setEnabled(true);

                }
            };

            eggTimer.start();

        }
        else{
            timerIsOn = false;
            Log.i("Timer is on",String.valueOf(timerIsOn));
            startEndButton.setText("START");
            eggTimer.cancel();
            timeSeekBar.setEnabled(true);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startEndButton = (Button) findViewById(R.id.myButton);
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        timeSeekBar = (SeekBar) findViewById(R.id.timeSeekBar);
        mp = MediaPlayer.create(this, R.raw.analogalarm);

        timeSeekBar.setMax(3599);
        timeSeekBar.setProgress(0);

        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int minutes = progress / 60;
                int seconds = progress % 60;

                String stringSeconds = String.valueOf(seconds);

                if(seconds <=9)
                    stringSeconds = "0" + stringSeconds;

                if(!timerIsOn){
                    timeTextView.setText(minutes+" : "+stringSeconds);

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
