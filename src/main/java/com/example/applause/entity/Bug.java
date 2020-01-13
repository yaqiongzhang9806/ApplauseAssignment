package com.example.applause.entity;

public class Bug {
	public int bugId;
	public Tester_Device tester_device;
	
	public Bug(int bugId, Tester_Device tester_device) {
		this.bugId = bugId;
		this.tester_device = tester_device;
	}
}
