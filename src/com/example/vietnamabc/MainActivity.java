package com.example.vietnamabc;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener {

	ImageButton btnAlphabet, btnWrite, btnRead;
	int id;
	private MediaPlayer mediaPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnAlphabet = (ImageButton)findViewById(R.id.btnAlphabet);
		btnWrite = (ImageButton)findViewById(R.id.btnWrite);
		btnRead = (ImageButton)findViewById(R.id.btnRead);
		btnAlphabet.setOnClickListener(this);
		btnWrite.setOnClickListener(this);
		btnRead.setOnClickListener(this);
		playAudio(R.raw.soundstart);
	}

	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btnAlphabet){
			Intent intent = new Intent(MainActivity.this,Activity2.class);
			intent.putExtra("id", 1);
			startActivity(intent);
			stopAudio();
		}
		if(v.getId()==R.id.btnWrite){
			Intent intent = new Intent(this,Activity2.class);
			intent.putExtra("id", 2);
			startActivity(intent);
		}
		if(v.getId()==R.id.btnRead){
			Intent intent = new Intent(this,Activity5.class);
			startActivity(intent);
			stopAudio();
		}
			
	}

	private void playAudio(int paramInt) {
		this.mediaPlayer = MediaPlayer.create(this, paramInt);
		this.mediaPlayer.setScreenOnWhilePlaying(true);
		this.mediaPlayer.start();
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
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		stopAudio();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		stopAudio();
	}
}
