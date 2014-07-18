package com.example.vietnamabc;

import java.util.concurrent.TimeUnit;

import com.example.vietnamabc.R;
import com.example.vietnamabc.R.id;
import com.example.vietnamabc.R.layout;
import com.example.vietnamabc.R.menu;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerActivity extends Activity {

	private MediaPlayer mediaPlayer;
	private int position;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		position = getIntent().getExtras().getInt("position");
		//playAudio(Constants.AUDIO[position]);
	}

	private void playAudio(int paramInt) {
		stopAudio();
		if (this.mediaPlayer != null)
			this.mediaPlayer.reset();
		this.mediaPlayer = MediaPlayer.create(this, paramInt);
		this.mediaPlayer.setScreenOnWhilePlaying(true);
		this.mediaPlayer.start();
		stopAudio();
	}

	private void stopAudio() {
		try {
			if (this.mediaPlayer != null) {
				if (this.mediaPlayer.isPlaying())
					this.mediaPlayer.stop();
				this.mediaPlayer.release();
				this.mediaPlayer = null;
			}
			return;
		} catch (Exception localException) {
			while (true)
				System.out.println("XML Pasing Excpetion = " + localException);
		}
	}


}
