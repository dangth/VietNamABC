package com.example.vietnamabc;

import java.util.concurrent.TimeUnit;

import com.example.vietnamabc.R;
import com.example.vietnamabc.R.id;
import com.example.vietnamabc.R.layout;
import com.example.vietnamabc.R.menu;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class Activity6 extends Activity implements OnCompletionListener,
		OnSeekBarChangeListener {

	private MediaPlayer mp;
	private int currentPosition;
	public TextView title;
	public double startTime = 0;
	public double finalTime = 0;
	private Handler mHandler = new Handler();
	private int forwardTime = 5000;
	private int backwardTime = 5000;
	private SeekBar seekBar;
	private ImageButton btnPrevious, btnBackward, btnPlay, btnFoward, btnNext;
	public static int oneTimeOnly = 0;
	private Utilities utils;
	private TextView songCurrentDurationLabel;
	private TextView songTotalDurationLabel;
	private ImageView images;
	private int playbackPosition = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mediaplayer);
		currentPosition = getIntent().getExtras().getInt("position");
		title = (TextView) findViewById(R.id.songTitle);
		seekBar = (SeekBar) findViewById(R.id.songProgressBar);
		btnBackward = (ImageButton) findViewById(R.id.btnBackward);
		btnFoward = (ImageButton) findViewById(R.id.btnForward);
		btnPlay = (ImageButton) findViewById(R.id.btnPlay);
		btnNext = (ImageButton) findViewById(R.id.btnNext);
		btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
		songCurrentDurationLabel = (TextView) findViewById(R.id.songCurrentDurationLabel);
		songTotalDurationLabel = (TextView) findViewById(R.id.songTotalDurationLabel);
		images = (ImageView) findViewById(R.id.pictureView);
		utils = new Utilities();
		mp = new MediaPlayer();
		seekBar.setOnSeekBarChangeListener(this);
		mp.setOnCompletionListener(this);
		seekBar.setClickable(false);
		updateAudio(currentPosition);

		/**
		 * Play button click event plays a song and changes button to pause
		 * image pauses a song and changes button to play image
		 * */
		btnPlay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Check for already playing
				if (mp.isPlaying()) {
					if (mp != null) {
						mp.pause();
						// Changing button image to play button
						btnPlay.setImageResource(R.drawable.btn_play);
					}
				} else {
					// Resume audio
					if (mp != null) {
						mp.start();
						// Changing button image to pause button
						btnPlay.setImageResource(R.drawable.btn_pause);

					}
				}
			}
		});

		/**
		 * Forward button click event Forwards song specified seconds
		 * */
		btnFoward.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// get CurrentPosition
				Toast.makeText(Activity6.this, "Foward", Toast.LENGTH_SHORT)
						.show();
				int currentPosition = mp.getCurrentPosition();
				if (currentPosition + forwardTime <= mp.getDuration()) {
					mp.seekTo(currentPosition + forwardTime);
				} else {
					mp.seekTo(mp.getDuration());

				}
			}
		});
		/**
		 * Backward button click event Backward song to specified seconds
		 * */
		btnBackward.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(Activity6.this, "Backward", Toast.LENGTH_SHORT)
						.show();
				// get current song position
				int currentPosition = mp.getCurrentPosition();
				// check if seekBackward time is greater than 0 sec
				if (currentPosition - backwardTime >= 0) {
					// forward song
					mp.seekTo(currentPosition - backwardTime);
				} else {
					// backward to starting position
					mp.seekTo(0);
				}

			}
		});
		/**
		 * Next button click event Plays next song by taking currentSongIndex +
		 * 1
		 * */
		btnNext.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// check if next song is there or not
				Toast.makeText(Activity6.this, "Next", Toast.LENGTH_SHORT)
						.show();
				if (currentPosition < (Constants.AUDIO_BOOK.length - 1)) {
					stopAudio();
					updateAudio(currentPosition + 1);
					currentPosition = currentPosition + 1;
				} else {
					// play first song
					stopAudio();
					updateAudio(0);
					currentPosition = 0;
				}

			}
		});
		/**
		 * Back button click event Plays previous song by currentSongIndex - 1
		 * */
		btnPrevious.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(Activity6.this, "Previous", Toast.LENGTH_SHORT)
						.show();
				if (currentPosition > 0) {
					stopAudio();
					updateAudio(currentPosition - 1);
					currentPosition = currentPosition - 1;
				} else {
					// play last song
					stopAudio();
					updateAudio(Constants.AUDIO_BOOK.length - 1);
					currentPosition = Constants.AUDIO_BOOK.length - 1;
				}

			}
		});

	}
	//Update Audio when tab next or previos..
	private void updateAudio(int position) {
		images.setImageResource(Constants.PICTURES[position]);
		title.setText(Constants.TITLE[position]);
		playAudio(Constants.AUDIO_BOOK[position]);
		btnPlay.setImageResource(R.drawable.btn_pause);

	}
	//Play Audio
	private void playAudio(int paramInt) {
		mp = MediaPlayer.create(this, paramInt);
		mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mp.start();
		this.mp.start();
		//Run seekBar
		seekBar.setProgress((int) startTime);
		mHandler.postDelayed(mUpdateTimeTask, 100);

	}
	//Stop Audio
	private void stopAudio() {
		try {
			if (this.mp != null) {
				if (this.mp.isPlaying())
					this.mp.stop();
				this.mp.reset();
				this.mp.release();
				this.mp = null;
				seekBar.setProgress(0);
				mHandler.removeCallbacks(mUpdateTimeTask);
			}
			return;
		} catch (Exception localException) {
			while (true)
				System.out.println(localException);
		}
	}
	//Pause audio
	private void pauseAudio() {
		if (this.mp != null && mp.isPlaying()) {
			this.playbackPosition = mp.getCurrentPosition();
			mp.pause();
		}
	}
	//Restart Audio
	private void restartAudio() {
		if (mp != null && !mp.isPlaying()) {
			mp.seekTo(playbackPosition);
			mp.start();
			Toast.makeText(this, "Restart Player", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		if (currentPosition < (Constants.AUDIO_BOOK.length - 1)) {
			updateAudio(currentPosition + 1);
			currentPosition = currentPosition + 1;
		} else {
			// play first song
			updateAudio(0);
			currentPosition = 0;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		stopAudio();
		mHandler.removeCallbacks(mUpdateTimeTask);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		restartAudio();
	}

	/**
	 * Update timer on seekbar
	 * */
	public void updateProgressBar() {
		mHandler.postDelayed(mUpdateTimeTask, 100);
	}

	/*
	 * Backgroud Runnable Thread
	 */
	private Runnable mUpdateTimeTask = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			long totalDuration = mp.getDuration();
			long currentDuration = mp.getCurrentPosition();
			// Displaying Total Duration time
			songTotalDurationLabel.setText(""
					+ utils.milliSecondsToTimer(totalDuration));
			// Displaying time completed playing
			songCurrentDurationLabel.setText(""
					+ utils.milliSecondsToTimer(currentDuration));

			// Updating progress bar
			int progress = (int) (utils.getProgressPercentage(currentDuration,
					totalDuration));
			// Log.d("Progress", ""+progress);
			seekBar.setProgress(progress);

			// Running this thread after 100 milliseconds
			mHandler.postDelayed(this, 100);
		}
	};

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		// remove message Handler from updating progress bar
		mHandler.removeCallbacks(mUpdateTimeTask);
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		mHandler.removeCallbacks(mUpdateTimeTask);
		int totalDuration = mp.getDuration();
		int currentPosition = utils.progressToTimer(seekBar.getProgress(),
				totalDuration);

		// forward or backward to certain seconds
		mp.seekTo(currentPosition);

		// update timer progress again
		updateProgressBar();
	}

}
