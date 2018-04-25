package com.epsi.ptitsudoku.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.epsi.ptitsudoku.R;
import com.epsi.ptitsudoku.model.GridItem;

import java.util.List;

public class GridViewAdapter extends ArrayAdapter<GridItem> {
	private Activity context;

	private static class ViewHolder {
		public TextView levelHolder;
		public TextView numHolder;
		public TextView percentDoneHolder;
	}

	public GridViewAdapter(Activity context, List<GridItem> items) {
		super(context, R.layout.grid_item, items);
		this.context = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;

		if (convertView == null) {
			convertView = context.getLayoutInflater().inflate(R.layout.grid_item, null);
			viewHolder = new ViewHolder();

			viewHolder.levelHolder = (TextView) convertView.findViewById(R.id.levelDifficulty);
			viewHolder.numHolder = (TextView) convertView.findViewById(R.id.num);
			viewHolder.percentDoneHolder = (TextView) convertView.findViewById(R.id.percentDone);

			convertView.setTag(viewHolder);
		} else
			viewHolder = (ViewHolder) convertView.getTag();

		int color = Color.GREEN;
		int percentDone = getItem(position).getPercentDone();

		viewHolder.levelHolder.setText("Level " + getItem(position).getLevelDifficulty());
		viewHolder.numHolder.setText("GridItem n°" + getItem(position).getNum());
		viewHolder.percentDoneHolder.setText(percentDone + "%");
		if (percentDone < 50)
			color = Color.RED;

		viewHolder.percentDoneHolder.setTextColor(color);

		return convertView;
	}
}
