package ru.goodroads.data;

import java.lang.annotation.Retention;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Hole {
	
	@SerializedName("lat") private double lat;
	@SerializedName("lng") private double lon;
	@SerializedName("holeSize") private double size;
	@SerializedName("holeType") private int type = Type.Auto.ordinal();
	@SerializedName("Aclx") private float aclX;
	@SerializedName("Acly") private float aclY;
	@SerializedName("Aclz") private float aclZ;
	@SerializedName("speed") private float speed;
	@SerializedName("Date") private long time;
      
	public enum Type {
		Auto,
		UserDefined;
	}
	public Hole() {
		this.time = new Date().getTime();
	}
	
	public Hole setSize(double size) {
		this.size = size;
		return this;
	}

	public Hole setType(Type type) {
		this.type = type.ordinal();
		return this;
	}
	
	public Hole setSpeed(float speed) {
		this.speed = speed;
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
}
