package com.reflection.test;

public class User {
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public void say(String userNo) {
		System.out.println("user say : "+userNo);
	}
	
	public void nosay() {
		System.out.println("user no say anything");
	}
	
	private void sayPrivate() {
		System.out.println("im praivate");
	}
}
