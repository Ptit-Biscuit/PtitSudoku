package com.epsi.ptitsudoku;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.epsi.ptitsudoku.model.Grid;
import com.epsi.ptitsudoku.model.GridCanvas;
import com.epsi.ptitsudoku.model.GridItem;

public class GridActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);

		GridItem gridItem = getIntent().getParcelableExtra("grid");
		Toast.makeText(GridActivity.this,
				"GridItem n°" + gridItem.getNum() + " -- " + gridItem.getPercentDone() + "%",
				Toast.LENGTH_SHORT).show();

		String gridLines = getIntent().getStringExtra("gridLines");
		String[][] grid = createGrid(gridLines);

		((GridCanvas) findViewById(R.id.canvas)).setGrid(new Grid(grid));
		((GridCanvas) findViewById(R.id.canvas)).invalidate();
	}

	public String[][] createGrid(String gridLines) {
		String[][] grid = new String[9][9];

		for (int i = 0; i < 81; i += 9) {
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
