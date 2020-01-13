package com.example.applause.entity;

public class Tester_Device {
	private int testerId;
	private int deviceId;
	
	public Tester_Device(int testerId, int deviceId){
		this.testerId = testerId;
		this.deviceId = deviceId;
	}
	
	public int getTesterId() {
		return this.testerId;
	}
	
	public int getDeviceId() {
		return this.deviceId;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if(o instanceof Tester_Device) {
	    	if(((Tester_Device) o).testerId == this.testerId && ((Tester_Device) o).deviceId == this.deviceId) {
	    		return true;
	    	}
	    }
	    return false;
	}

	@Override
	public int hashCode() {
	    return testerId * 100 + deviceId;
	}
}
