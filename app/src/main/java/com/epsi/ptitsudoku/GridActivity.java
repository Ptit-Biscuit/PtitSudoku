package com.epsi.ptitsudoku;

import android.app.Activity;
import android.os.Bundle;

import com.epsi.ptitsudoku.model.GridCanvas;

import java.util.Arrays;

public class GridActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);

		String gridLines = getIntent().getStringExtra("gridLines");
		String[][] grid = createGrid(gridLines);

		((GridCanvas) findViewById(R.id.canvas)).setGrid(grid);
		((GridCanvas) findViewById(R.id.canvas)).invalidate();
	}

	public String[][] createGrid(String gridLines) {
		String[][] grid = new String[9][9];

		for (int i = 0; i < 81; i += 9){
			String[] row = new String[9];
			int index = 0;

			for (char c : gridLines.substring(i, i + 9).toCharArray()) {
				row[index++] = String.valueOf(c);
			}

			grid[i / 9] = row;
		}

		return grid;
	}
}
