package com.epsi.ptitsudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// play listener --> select a level difficulty
		findViewById(R.id.playButton).setOnClickListener(this::play);

		// quit listener
		findViewById(R.id.quitButton).setOnClickListener(this::quit);
	}

	public void play(View v) {
		// select a level
		startActivity(new Intent(MainActivity.this, LevelSelectorActivity.class));
	}

	private void quit(View v) {
		// close the app
		this.finish();
	}
}
