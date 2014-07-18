package com.example.vietnamabc;

import com.example.vietnamabc.DetectShake.GestureFilter;
import com.example.vietnamabc.DetectShake.GestureFilter.SimpleGestureListener;
import com.example.vietnamabc.View.DrawView;

import android.app.Activity;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity4 extends Activity{

	private DrawView drawView;
	private MediaPlayer mediaPlayer;
	private Button btnClear, btnPrevious, btnNext;
	private int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity4);
		position = getIntent().getExtras().getInt("position");
		drawView = (DrawView) findViewById(R.id.canvas);
		drawView.setBackgroundResource(Constants.WRITE_IMAGES[position]);
		btnPrevious = (Button) findViewById(R.id.btnPrev);
		btnClear = (Button) findViewById(R.id.btnClear);
		btnNext = (Button) findViewById(R.id.btnNex);

		btnPrevious.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Activity4.this, "Previous", Toast.LENGTH_SHORT)
						.show();
				drawView.clear();
				if (position > 0) {
					updateActivity(position - 1);
					position = position - 1;
				} else {
					updateActivity((Constants.WRITE_IMAGES.length - 1));
					position = Constants.WRITE_IMAGES.length - 1;
				}
			}
		});
		btnClear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Clear board!
				Toast.makeText(Activity4.this, "Xoa Bang", Toast.LENGTH_SHORT)
						.show();
				drawView.clear();

			}
		});
		btnNext.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Activity4.this, "Next", Toast.LENGTH_SHORT)
						.show();
				drawView.clear();
				if (position < (Constants.WRITE_IMAGES.length - 1)) {
					updateActivity(position + 1);
					position = position + 1;
				} else {
					updateActivity(0);
					position = 0;
				}
			}
		});

	}

	private void updateActivity(int pos) {
		drawView.setBackgroundResource(Constants.WRITE_IMAGES[pos]);
		playAudio(Constants.WORD_SOUND[pos]);
	}

	// Play audio
	private void playAudio(int paramInt) {
		stopAudio();
		if (this.mediaPlayer != null)
			this.mediaPlayer.reset();
		this.mediaPlayer = MediaPlayer.create(this, paramInt);
		this.mediaPlayer.setScreenOnWhilePlaying(true);
		this.mediaPlayer.start();
	}

	// Stop Audio
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


	protected void onPause() {
		super.onPause();
	}


}