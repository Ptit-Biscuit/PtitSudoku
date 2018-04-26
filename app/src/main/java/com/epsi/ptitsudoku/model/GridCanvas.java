package com.epsi.ptitsudoku.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.epsi.ptitsudoku.R;

import java.util.ArrayList;
import java.util.List;

public class GridCanvas extends View {

	private Context context;

	private Grid grid = new Grid(new String[][]{});

	private List<Number> numbers = new ArrayList<>();

	private Number numberSelected;

	private int width = 98;

	private int height = 98;

	private boolean isMoved = false;

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
				canvas.drawLine(offsetX, startY, stopX, startY, dark);
				canvas.drawLine(startX, offsetY, startX, stopY, dark);
			}
		}

		// draw grid
		this.grid.draw(this.context, canvas, width, height, offsetX, offsetY);

		// draw numbers
		int x = getWidth() / 10;
		int y = getHeight() - 250;

		for (int i = 0; i < 9; i++) {
			numbers.get(i).draw(this.context, canvas, x * (i + 1), y);
		}

		if (this.numberSelected != null) {
			Point position = this.numberSelected.getPosition();
			numberSelected.draw(this.context, canvas, position.x, position.y);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				for (Number num : numbers) {
					Point position = num.getPosition();
					int radius = num.getRadius();

					int startX = position.x - radius;
					int stopX = position.x + radius;
					int startY = position.y - radius;
					int stopY = position.y + radius;

					if (y >= startY && y <= stopY) {
						if (x >= startX && x <= stopX) {
							this.numberSelected = new Number(num.getValue());
						}
					}
				}

				break;
			case MotionEvent.ACTION_MOVE:
				if (this.numberSelected != null) {
					this.numberSelected.setPosition(new Point(x + 50, y - 50));

					this.isMoved = true;

					this.invalidate();
				}

				break;
			case MotionEvent.ACTION_UP:
				if (this.isMoved) {
					// Offsets
					int offsetX = (getWidth() - width * 9) / 2;
					int offsetY = (getHeight() - height * 9) / 4;

					if (y >= offsetY && y <= offsetY + width * 9) {
						if (x >= offsetX && x <= offsetX + width * 9) {
							int gridX = (x - offsetX) / width;
							int gridY = (y - offsetY) / height;

							if (this.grid.validMove(gridX, gridY)) {
								this.grid.replaceCell(gridX, gridY, this.numberSelected.getValue());
							}
						}
					}

					this.numberSelected = null;
					this.isMoved = false;
					this.invalidate();
				}

				break;
		}

		return true;
	}
}
