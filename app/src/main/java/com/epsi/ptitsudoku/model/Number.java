package com.epsi.ptitsudoku.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.epsi.ptitsudoku.R;

public class Number {
	private String value;
	private int radius = 50;

	public Number(String value) {
		this.value = value;
	}

	public void draw(Context context, Canvas canvas, int x, int y) {
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

		paint.setColor(context.getResources().getColor(R.color.textColor));
		paint.setTextSize(48f);
		paint.setTextAlign(Paint.Align.CENTER);

		Rect bounds = new Rect();
		paint.getTextBounds(this.value, 0, this.value.length(), bounds);

		circlePaint.setColor(Color.RED);

		canvas.drawCircle(x, y - (bounds.height() / 2), bounds.width() + radius, circlePaint);
		canvas.drawText(this.value, x, y, paint);
	}
}
