package com.epsi.ptitsudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class LevelSelectorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_selector);

		// level one
		findViewById(R.id.levelOneButton).setOnClickListener(v -> this.selectLevel(1));

		// level two
		findViewById(R.id.levelTwoButton).setOnClickListener(v -> this.selectLevel(2));

		// level three
		findViewById(R.id.levelThreeButton).setOnClickListener(v -> {
			//this.selectLevel(3);
			Toast.makeText(LevelSelectorActivity.this, "Level 3 not available yet", Toast.LENGTH_SHORT).show();
		});
	}

	private void selectLevel(int level) {
		// select a grid
		Intent intent = new Intent(LevelSelectorActivity.this, GridSelectorActivity.class);
		intent.putExtra("levelDifficulty", level);
		startActivity(intent);
	}
}
