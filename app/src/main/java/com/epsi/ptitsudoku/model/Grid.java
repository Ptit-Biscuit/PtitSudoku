package com.epsi.ptitsudoku.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.epsi.ptitsudoku.R;

import java.util.ArrayList;
import java.util.List;

public class Grid {
	private String[][] grid;

	private List<Point> validCells = new ArrayList<>();

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
				if ("0".equals(this.grid[i][j])) {
					validCells.add(new Point(i, j));
				} else {
					canvas.drawText(this.grid[i][j], offsetX + i * width + x, offsetY + j * height + y, foreground);
				}
			}
		}
	}

	public boolean validMove(int gridX, int gridY) {
		return validCells.contains(new Point(gridX, gridY));
	}

	public void replaceCell(int gridX, int gridY, String value) {
		this.grid[gridX][gridY] = value;
	}

	public String[][] getGrid() {
		return grid;
	}

	public void setGrid(String[][] grid) {
		this.grid = grid;
	}
}
