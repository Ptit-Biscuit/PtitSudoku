package com.epsi.ptitsudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.epsi.ptitsudoku.adapter.GridViewAdapter;
import com.epsi.ptitsudoku.model.GridItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GridSelectorActivity extends Activity {
	private List<GridItem> gridItems = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_selector);

		int level = getIntent().getIntExtra("levelDifficulty", 1);

		// get file corresponding to level
		InputStream file;

		switch (level) {
			default:
			case 1:
				file = this.getResources().openRawResource(R.raw.grids);
				break;
			case 2:
				file = this.getResources().openRawResource(R.raw.grids2);
				break;
//			case 3:
//				file = this.getResources().openRawResource(R.raw.grids3);
//				break;
		}

		List<String> gridsFromFile = getGridFromFile(file);

		for (int i = 0; i < 100; i++) {
			gridItems.add(new GridItem(level, i, new Random().nextInt(101)));
		}

		// populate the list with proper adapter
		((ListView) findViewById(R.id.list_grid_view)).setAdapter(
				new GridViewAdapter(GridSelectorActivity.this, gridItems));

		// add listener for each item
		((AdapterView) findViewById(R.id.list_grid_view)).setOnItemClickListener(
				(parent, view, position, id) -> {
					GridItem gridItem = gridItems.get(position);

					startActivity(
							new Intent(GridSelectorActivity.this, GridActivity.class)
									.putExtra("grid", gridItem)
									.putExtra("gridLines", gridsFromFile.get(new Random().nextInt(gridsFromFile.size()))));
				}
		);
	}

	public List<String> getGridFromFile(InputStream file) {
		Scanner sc = new Scanner(file);
		List<String> gridsLines = new ArrayList<>();

		try {
			while (sc.hasNext()) {
				gridsLines.add(sc.nextLine());
			}

			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return gridsLines;
	}
}
