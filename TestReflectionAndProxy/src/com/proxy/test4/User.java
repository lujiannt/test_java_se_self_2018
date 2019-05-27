package com.proxy.test4;

public class User implements UserManager{	
	private String name;
	
	@Override
	public void sayName() {
		System.out.println("my name is zs");
	}

}
