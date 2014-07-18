package com.example.vietnamabc;

import com.example.vietnamabc.DetectShake.GestureFilter;
import com.example.vietnamabc.DetectShake.GestureFilter.SimpleGestureListener;

import android.app.Activity;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Activity3 extends Activity implements SimpleGestureListener{

	private ImageView imgChu, imgAnh;
	private MediaPlayer mediaPlayer;
	private GestureFilter detector;
	private int position;
	private long lastUpdate = -1;
	private float x, y, z;
	private float last_x, last_y, last_z;
	private static final int SHAKE_THRESHOLD = 800;
	private Button btnPrevious, btnNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity3);

		position = getIntent().getExtras().getInt("position");
		imgChu = (ImageView) findViewById(R.id.imgChuCai);
		imgAnh = (ImageView) findViewById(R.id.imgAnh);
		detector = new GestureFilter(this, this);
		
		imgChu.setImageResource(Constants.WORD_IMAGES[position]);
		imgAnh.setImageResource(Constants.LARGE_IMAGES[position]);

		imgChu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Play word audio when tap in Word Images
				playAudio(Constants.WORD_SOUND[position]);
			}
		});
		imgAnh.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Play audio when tap in Images
				playAudio(Constants.IMAGES_SOUND[position]);
			}
		});
		
		btnPrevious = (Button)findViewById(R.id.btnPrev3);
		btnNext = (Button)findViewById(R.id.btnNex3);
		
		btnPrevious.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Activity3.this, "Previous", Toast.LENGTH_SHORT).show();
				if (position > 0) {
					updateActivity(position - 1);
					position = position - 1;
				} else {
					updateActivity((Constants.WORD_IMAGES.length - 1));
					position = Constants.WORD_IMAGES.length - 1;
				}
			}
		});
		
		btnNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Activity3.this, "Next", Toast.LENGTH_SHORT).show();
				if (position < (Constants.WORD_IMAGES.length - 1)) {
					updateActivity(position + 1);
					position = position + 1;
				} else {
					updateActivity(0);
					position = 0;
				}
			}
		});
	}

	private void updateActivity(final int position) {
		imgChu.setImageResource(Constants.WORD_IMAGES[position]);
		imgAnh.setImageResource(Constants.LARGE_IMAGES[position]);
		playAudio(Constants.WORD_SOUND[position]);

		imgChu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Play word audio when tap in Word Images
				playAudio(Constants.WORD_SOUND[position]);
			}
		});
		imgAnh.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Play audio when tap in Images
				playAudio(Constants.IMAGES_SOUND[position]);
			}
		});
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

	// Stop audio
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

	@Override
	public boolean dispatchTouchEvent(MotionEvent me) {
		// Call onTouchEvent of SimpleGestureFilter class
		this.detector.onTouchEvent(me);
		return super.dispatchTouchEvent(me);
	}

	@Override
	public void onSwipe(int direction) {
		// TODO Auto-generated method stub
		switch (direction) {

		case GestureFilter.SWIPE_RIGHT:
			if (this.position < (Constants.WORD_IMAGES.length - 1)) {
				updateActivity(position + 1);
				position = position + 1;
			} else {
				updateActivity(0);
				position = 0;
			}
			break;
		case GestureFilter.SWIPE_LEFT:
			if (this.position > 0) {
				updateActivity(position - 1);
				position = position - 1;
			} else {
				updateActivity((Constants.WORD_IMAGES.length - 1));
				position = Constants.WORD_IMAGES.length - 1;
			}
			break;
		}

	}

	protected void onPause() {
		super.onPause();
	}

}