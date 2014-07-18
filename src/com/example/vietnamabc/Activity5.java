package com.example.vietnamabc;

import com.example.vietnamabc.ListView.ListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Activity5 extends Activity {

	private ListView list;
	private int idlocal;
	private MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity5);
		ListAdapter adapter = new ListAdapter(Activity5.this);
		list = (ListView) findViewById(R.id.list);
		mp = new MediaPlayer();
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Activity5.this, Activity6.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}
		});
	}

	private void playAudio(int paramInt) {
		this.mp = MediaPlayer.create(Activity5.this, paramInt);
		mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
		this.mp.start();
	}

}
