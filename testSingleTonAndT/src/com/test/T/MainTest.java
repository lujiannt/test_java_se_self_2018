package com.test.T;

public class MainTest {
	
	public static void main(String[] args) {
		//类的泛型和值的泛型要要一致，否则会报错
		Test01<Integer> t1= new Test01<>();
		t1.setName(11);
		
		
		Test01<String> t2= new Test01<>();
		t2.setName("张三啊");
		
		
		System.out.println(t1.getName());
		System.out.println(t2.getName());
	}

}
