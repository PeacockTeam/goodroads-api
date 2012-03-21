package ru.goodroads.data;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Hole {
	
	@SerializedName("lat") private double lat;
	@SerializedName("lng") private double lon;
	@SerializedName("holeSize") private double size;
	@SerializedName("holeType") private Type type;
	@SerializedName("aclX") private float aclX;
	@SerializedName("aclY") private float aclY;
	@SerializedName("aclZ") private float aclZ;
	@SerializedName("speed") private float speed;
	@SerializedName("date") private long time;
	
	public enum Type {
	    Auto,
	    UserDefined
	}
      
	public Hole() {
		this.time = new Date().getTime();
	}
	
	public Hole setSize(double size) {
		this.size = size;
		return this;
	}

	public Hole setType(Type type) {
		this.type = type;
		return this;
	}

	public Hole setLocation(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
		return this;
	}
	
	public Hole setAccelerometer(float aclX, float aclY, float aclZ) {
		this.aclX = aclX;
		this.aclY = aclY;
		this.aclZ = aclZ;
		return this;
	}

	public Hole setSpeed(float speed) {
		this.speed = speed;
		return this;
	}
}
