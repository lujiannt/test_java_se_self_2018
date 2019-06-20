package com.test.singleton;

public class Singleton01 {
	private static Singleton01 single = new Singleton01();
	
	
	private Singleton01() {
		super();
	}

	//饿汉式单例模式，没有做到延迟加载，不管有没有用都已经初始化了
	public static Singleton01 instance() {
		return single;
	}
	
	
	public void say() {
		System.out.println("im 01");
	}
}
