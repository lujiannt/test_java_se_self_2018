package com.test.singleton;

public class Singleton02 {
	private static Singleton02 single = null;
	
	
	private Singleton02() {
		super();
	}

	//懒汉式单例模式，没有考虑到线程安全问题，多线程时可能会初始化多个对象
	public static Singleton02 instance() {
		if(single == null) {
			single = new Singleton02();
		}
		return single;
	}
	
	public void say() {
		System.out.println("im 02");
	}
	
}
