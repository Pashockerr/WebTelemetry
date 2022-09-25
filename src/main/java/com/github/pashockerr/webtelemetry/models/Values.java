package com.github.pashockerr.webtelemetry.models;

public class Values {
	public float hydrogen1Voltage;
	public float hydrogen1Current;
	
	public float hydrogen2Voltage;
	public float hydrogen2Current;
	
	public float batteryVoltage;
	public float batteryCurrent;
	
	public Values(float h1v, float h1c, float h2v, float h2c, float bv, float bc) {
		hydrogen1Voltage = h1v;
		hydrogen1Current = h2c;
		
		hydrogen2Voltage = h2v;
		hydrogen2Current = h2c;
		
		batteryVoltage = bv;
		batteryCurrent = bc;
	}
}
