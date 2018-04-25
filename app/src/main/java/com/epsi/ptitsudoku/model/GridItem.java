package com.epsi.ptitsudoku.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GridItem implements Parcelable {
	private int levelDifficulty;
	private int num;
	private int percentDone;

	public GridItem(int levelDifficulty, int num, int percentDone) {
		this.levelDifficulty = levelDifficulty;
		this.num = num;
		this.percentDone = percentDone;
	}

	protected GridItem(Parcel in) {
		this.levelDifficulty = in.readInt();
		this.num = in.readInt();
		this.percentDone = in.readInt();
	}

	public static final Creator<GridItem> CREATOR = new Creator<GridItem>() {
		@Override
		public GridItem createFromParcel(Parcel in) {
			return new GridItem(in);
		}

		@Override
		public GridItem[] newArray(int size) {
			return new GridItem[size];
		}
	};

	public int getLevelDifficulty() {
		return levelDifficulty;
	}

	public void setLevelDifficulty(int levelDifficulty) {
		this.levelDifficulty = levelDifficulty;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getPercentDone() {
		return percentDone;
	}

	public void setPercentDone(int percentDone) {
		this.percentDone = percentDone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		GridItem gridItem1 = (GridItem) o;

		if (levelDifficulty != gridItem1.levelDifficulty) return false;
		if (num != gridItem1.num) return false;
		return percentDone == gridItem1.percentDone;
	}

	@Override
	public int hashCode() {
		int result = levelDifficulty;
		result = 31 * result + num;
		result = 31 * result + percentDone;
		return result;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.getLevelDifficulty());
		dest.writeInt(this.getNum());
		dest.writeInt(this.getPercentDone());
	}
}
