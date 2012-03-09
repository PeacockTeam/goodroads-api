package ru.goodroads.data;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class HoleSet {
	@SerializedName("points")
	private ArrayList<Hole> holes;
	
	public HoleSet(ArrayList<Hole> holes) {
		this.holes = holes;
	}
}
