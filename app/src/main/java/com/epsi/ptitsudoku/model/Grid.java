package com.epsi.ptitsudoku.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.epsi.ptitsudoku.R;

public class Grid {
	private String[][] grid;

	public Grid(String[][] grid) {
		this.grid = grid;
	}

	public void draw(Context context, Canvas canvas, int width, int height, int offsetX, int offsetY) {
		Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
		foreground.setColor(context.getResources().getColor(R.color.textColor));
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

	public String[][] getGrid() {
		return grid;
	}

	public void setGrid(String[][] grid) {
		this.grid = grid;
	}
}
