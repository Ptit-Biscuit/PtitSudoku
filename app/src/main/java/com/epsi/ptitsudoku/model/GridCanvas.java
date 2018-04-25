package com.epsi.ptitsudoku.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.epsi.ptitsudoku.R;

import java.util.ArrayList;
import java.util.List;

public class GridCanvas extends View {

	private Context context;

	private Grid grid = new Grid(new String[][]{});

	private List<Number> numbers = new ArrayList<>();

	private final int width = 98;
	private final int height = 98;

	public GridCanvas(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;

		for (int i = 1; i < 10; i++)
			numbers.add(new Number(String.valueOf(i)));
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// Draw the background
		Paint background = new Paint();
		background.setColor(getResources().getColor(R.color.background));
		canvas.drawRect(0, 0, getWidth(), getHeight(), background);

		// Offsets
		int offsetX = (getWidth() - width * 9) / 2;
		int offsetY = (getHeight() - height * 9) / 4;

		float stopX = offsetX + width * 9;
		float stopY = offsetY + width * 9;

		// Colors for sub lines
		Paint light = new Paint();
		light.setStrokeWidth(5f);
		light.setColor(getResources().getColor(R.color.subLines));

		// Draw grid lines
		for (int i = 0; i < 10; i++) {
			float startX = offsetX + i * width;
			float startY = offsetY + i * height;

			if (i % 3 != 0) {
				// sub lines
				canvas.drawLine(offsetX, startY, stopX, startY, light);
				canvas.drawLine(startX, offsetY, startX, stopY, light);
			}
		}

		// Color for main lines
		Paint dark = new Paint();
		dark.setStrokeWidth(5f);
		dark.setColor(getResources().getColor(R.color.mainLines));

		for (int i = 0; i < 10; i++) {
			float startX = offsetX + i * width;
			float startY = offsetY + i * height;

			if (i % 3 == 0) {
				// main lines
				canvas.drawLine(offsetX, startY, stopX, startY, dark);
				canvas.drawLine(startX, offsetY, startX, stopY, dark);
			}
		}

		// draw grid
		this.grid.draw(this.context, canvas, width, height, offsetX, offsetY);

		// draw numbers
		int x = getWidth() / 6;
		int y = getHeight() - 350;
		int radius = 60;

		for (int i = 0; i < 5; i++) {
			numbers.get(i).draw(this.context, canvas, x * (i + 1), y);
		}

		for (int i = 5; i < 9; i++) {
			numbers.get(i).draw(this.context, canvas, x * (i - 4), y + 225);
		}
	}
}
