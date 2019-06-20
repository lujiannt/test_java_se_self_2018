package com.test.singleton;

public class Singleton03 {
	private static volatile Singleton03 single = null;
	
	
	private Singleton03() {
		super();
	}

	//使用锁来解决Singleton02中懒汉式单例模式的弊端
	public static Singleton03 instance() {
		synchronized (Singleton03.class) {
			if(single == null) {
				single = new Singleton03();
			}
		}
		
		return single;
	}
	
	public void say() {
		System.out.println("im 03");
	}
	
}
