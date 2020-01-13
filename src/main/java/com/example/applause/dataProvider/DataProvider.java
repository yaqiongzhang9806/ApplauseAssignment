package com.example.applause.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.applause.entity.Bug;
import com.example.applause.entity.Device;
import com.example.applause.entity.Tester;
import com.example.applause.entity.Tester_Device;

@Component
public class DataProvider {
	@Autowired
	private OpenCSVReader reader;

	public List<Tester> getAllTester(){	
		String file = "testers.csv";		
		List<String[]> data = reader.readAllDataAtOnce(file);
		List<Tester> testers = new ArrayList<>();
		if(data != null) {
			for(String[] row : data) {
				int testerId = Integer.parseInt(row[0]);
				String firstName = row[1];
				String lastName = row[2];
				String country = row[3];							
				testers.add(new Tester(testerId, firstName, lastName, country));
			}
		}		
		return testers;			
	}
	
	public List<Device> getAllDevice(){
		String file = "devices.csv";		
		List<String[]> data = reader.readAllDataAtOnce(file);
		List<Device> devices = new ArrayList<>();
		if(data != null) {
			for(String[] row : data) {
				int deviceId = Integer.parseInt(row[0]);
				String deviceName = row[1];
				devices.add(new Device(deviceId, deviceName));
			}
		}		
		return devices;
	}
	
	public List<Bug> getAllBug(){
		String file = "bugs.csv";		
		List<String[]> data = reader.readAllDataAtOnce(file);
		List<Bug> bugs = new ArrayList<>();
		if(data != null) {
			for(String[] row : data) {
				int bugId = Integer.parseInt(row[0]);		
				int deviceId = Integer.parseInt(row[1]);
				int testerId = Integer.parseInt(row[2]);
				Tester_Device td = new Tester_Device(testerId, deviceId);
				bugs.add(new Bug(bugId, td));
			}
		}		
		return bugs;
	}
	
	public List<Tester_Device> getAllTester_Device(){
		String file = "tester_device.csv";		
		List<String[]> data = reader.readAllDataAtOnce(file);
		List<Tester_Device> tds = new ArrayList<>();
		if(data != null) {
			for(String[] row : data) {
				int testerId = Integer.parseInt(row[0]);
				int deviceId = Integer.parseInt(row[1]);			
				tds.add(new Tester_Device(testerId, deviceId));
			}
		}		
		return tds;
	}
}
