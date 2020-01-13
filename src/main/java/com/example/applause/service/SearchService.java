package com.example.applause.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.applause.dataprovider.DataProvider;
import com.example.applause.entity.Bug;
import com.example.applause.entity.Device;
import com.example.applause.entity.Output;
import com.example.applause.entity.SearchCriteria;
import com.example.applause.entity.Tester;
import com.example.applause.entity.Tester_Device;

@Service
public class SearchService {
	@Autowired
	private DataProvider dataProvider;
	public List<String> getAllCountry(){
		List<Tester> allTester = dataProvider.getAllTester();
		Set<String> set = new HashSet<String>();
		for(Tester tester : allTester) {			
			set.add(tester.country);
		}
		List<String> allCountry = new ArrayList<String>(set);
		return allCountry;
	}
	
	public List<Device> getAllDevice(){
		return dataProvider.getAllDevice();
	}
	
	private List<String> getAllDeviceName(){		
		List<Device> allDevice = getAllDevice();
		List<String> deviceNameList = new ArrayList<String>();
		for(Device device : allDevice) {
			deviceNameList.add(device.descripton);
		}
		return deviceNameList;
	}
	
	private Map<Tester_Device, List<Integer>> getSearchMap(){
		List<Bug> allBug = dataProvider.getAllBug();		
		Map<Tester_Device, List<Integer>> map = new HashMap<>();
		for(Bug bug: allBug) {
			Tester_Device td = bug.tester_device;
			if(map.containsKey(td)) {
				map.get(td).add(bug.bugId);
			}else {
				map.put(td, new ArrayList<Integer>());
				map.get(td).add(bug.bugId);
			}
		}
		return map;
	}
	
	private List<Tester> getTesterByCountry(List<String> countryList){
		List<Tester> testerList = new ArrayList<>();
		if(countryList == null || countryList.size() == 0) return testerList;
		List<Tester> allTester = dataProvider.getAllTester();
		Set<String> set = new HashSet<String>(countryList);
		for(Tester tester : allTester) {
			if(set.contains(tester.country)) {
				testerList.add(tester);
			}
		}
		return testerList;
	}
	
	private List<Integer> getDeviceIdByDeviceName(List<String> deviceNameList){
		List<Integer> idList = new ArrayList<>();
		if(deviceNameList == null || deviceNameList.size() == 0) return idList;
		List<Device> allDevice = dataProvider.getAllDevice();
		Set<String> set = new HashSet<String>(deviceNameList);
		for(Device device : allDevice) {
			if(set.contains(device.descripton)) {
				idList.add(device.deviceId);
			}
		}
		return idList;
	}
	
	private List<Output> getSearchResult(List<Tester> testerList, List<Integer> deviceIdList){
		List<Output> result = new ArrayList<>();
		if(testerList == null || testerList.size() == 0 || deviceIdList == null || deviceIdList.size() == 0) return result;
		Map<Tester_Device, List<Integer>> map = getSearchMap();
		for(Tester tester: testerList) {
			int experience = 0;
			for(int deviceId: deviceIdList) {
				Tester_Device td = new Tester_Device(tester.testId, deviceId);				
				if(map.containsKey(td)) {
					List<Integer> bugs = map.get(td);
					experience += bugs.size();					
				}
			}
			result.add(new Output(tester, experience));
		}	
		Collections.sort(result);
		return result;
	}
	
	private List<Output> Search(List<String> countryList, List<String> deviceNameList){
		List<Output> result = new ArrayList<>();
		if(countryList == null || countryList.size() == 0 || deviceNameList == null || deviceNameList.size() == 0) return result;
		Set<String> setCountry = new HashSet<String>(countryList);
		Set<String> setDevice = new HashSet<String>(deviceNameList);
		if(setCountry.contains("ALL")) {
			countryList = getAllCountry();
		}
		if(setDevice.contains("ALL")) {
			deviceNameList = getAllDeviceName();
		}
		List<Tester> testerList = getTesterByCountry(countryList);
		List<Integer> deviceIdList = getDeviceIdByDeviceName(deviceNameList);
		result = getSearchResult(testerList, deviceIdList);
		return result;
	}
	
	public List<Output> Search(SearchCriteria searchCriteria){
		if(searchCriteria == null) return new ArrayList<Output>();
		String inputCountryList = searchCriteria.getInputCountryList();
		String inputDeviceList = searchCriteria.getInputDeviceList();		
		List<String> countryList = inputCountryList == null? new ArrayList<String>() : Arrays.asList(inputCountryList.split("\\s*,\\s*"));
		List<String> deviceList = inputDeviceList == null ? new ArrayList<String>() : Arrays.asList(inputDeviceList.split("\\s*,\\s*"));	
		return Search(countryList, deviceList);		
	}
}
