package com.example.applause.entity;

public class Output implements Comparable<Output>{
	public Tester tester;
	public int experience;
	
	public Output(Tester tester, int experience) {
		this.tester = tester;
		this.experience = experience;
	}
	
	@Override
	public int compareTo(Output o) {
		if(this.experience > o.experience) return -1;
		if(this.experience < o.experience) return 1;
		return 0;
	}
}
