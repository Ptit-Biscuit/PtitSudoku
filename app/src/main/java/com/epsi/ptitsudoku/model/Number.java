package com.epsi.ptitsudoku.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.epsi.ptitsudoku.R;

public class Number {
	private String value;

	private Point position;

	private int radius = 50;

	public Number(String value) {
		this.value = value;
	}

	public void draw(Context context, Canvas canvas, int x, int y) {
		this.position = new Point(x, y);

		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

		paint.setColor(context.getResources().getColor(R.color.textColor));
		paint.setTextSize(56f);
		paint.setTextAlign(Paint.Align.CENTER);

		Rect bounds = new Rect();
		paint.getTextBounds(this.value, 0, this.value.length(), bounds);

		Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		circlePaint.setColor(context.getResources().getColor(R.color.numberBackground));
		canvas.drawCircle(this.position.x, this.position.y - (bounds.height() / 2), radius, circlePaint);

		canvas.drawText(this.value, this.position.x, this.position.y, paint);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
