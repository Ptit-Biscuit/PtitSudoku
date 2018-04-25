package com.epsi.ptitsudoku.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.epsi.ptitsudoku.R;

public class GridCanvas extends View {

	private final int width = 98;
	private final int height = 98;

	private String[][] grid = new String[][]{};

	public GridCanvas(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public String[][] getGrid() {
		return grid;
	}

	public void setGrid(String[][] grid) {
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

		// draw grid numbers
		Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
		foreground.setColor(getResources().getColor(R.color.textColor));
		foreground.setStyle(Paint.Style.FILL);
		foreground.setTextSize(height * 0.75f);
		foreground.setTextScaleX(width / height);
		foreground.setTextAlign(Paint.Align.CENTER);

		Paint.FontMetrics fm = foreground.getFontMetrics();
		float x = width / 2;
		float y = height / 2 - (fm.ascent + fm.descent) / 2;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String number = "0".equals(this.grid[i][j]) ? "" : this.grid[i][j];

				canvas.drawText(number, offsetX + i * width + x, offsetY + j * height + y, foreground);
			}
		}
	}
}
