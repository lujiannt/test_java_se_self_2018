package com.test.singleton;

public class Singleton04 {
	
	private static class SingletonSon{
		private static Singleton04 single = new Singleton04();
	}
	
	private Singleton04() {
		super();
	}

	//使用内部类来实现，内部类只有在instance方法执行时才会初始化
	public static Singleton04 instance() {
		return SingletonSon.single;
	}
	
	public void say() {
		System.out.println("im 04");
	}
	
}
