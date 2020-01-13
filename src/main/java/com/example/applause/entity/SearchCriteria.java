package com.example.applause.entity;

public class SearchCriteria {
	private String inputCountryList;
	
	private String inputDeviceList;		
	
	public void setInputDeviceList(String inputDeviceList) {
		this.inputDeviceList = inputDeviceList;
	}
	
	public String getInputDeviceList() {
		return this.inputDeviceList;
	}
	
	public void setInputCountryList(String inputCountryList) {
		this.inputCountryList = inputCountryList;
	}
	
	public String getInputCountryList() {
		return this.inputCountryList;
	}	
}
