package com.example.vietnamabc;

import com.example.vietnamabc.Grid.GridBoardAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class Activity2 extends Activity {

	private MediaPlayer mediaPlayer;
	private int idLocal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2);
		idLocal = getIntent().getExtras().getInt("id");

		GridView grid = (GridView) findViewById(R.id.gridView);
		grid.setAdapter(new GridBoardAdapter(this));
		
		//playAudio(R.raw.sound2);

		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				// Toast.makeText(Activity2.this,""+position,Toast.LENGTH_SHORT).show();
				if (idLocal == 1) {
					try {
						playAudio(Constants.WORD_SOUND[position]);
						Intent intent = new Intent(Activity2.this,
								Activity3.class);
						intent.putExtra("position", position);
						startActivity(intent);

					} catch (Exception e) {
						// TODO: handle exception
						Toast.makeText(Activity2.this, e.getMessage(),
								Toast.LENGTH_SHORT).show();
					}
				}
				if (idLocal == 2) {
					try {
						playAudio(Constants.WORD_SOUND[position]);
						Intent intent = new Intent(Activity2.this,
								Activity4.class);
						intent.putExtra("position", position);
						startActivity(intent);

					} catch (Exception e) {
						// TODO: handle exception
						Toast.makeText(Activity2.this, e.getMessage(),
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});

	}

	private void playAudio(int paramInt) {
		stopAudio();
		if (this.mediaPlayer != null)
			this.mediaPlayer.reset();
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

}
