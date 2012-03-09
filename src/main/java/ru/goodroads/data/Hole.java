package ru.goodroads.data;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Hole {
	private double lat;
	private double lng;
	@SerializedName("holeSize")
	private double size;
	@SerializedName("holeType")
	private int type;
	
	public Hole(double lat, double lon, double size, int type) {
		this.lat = lat;
		this.lng = lon;
		this.size = size;
		this.type = type;
	}
}
