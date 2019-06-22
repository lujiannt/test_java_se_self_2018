package com.reflection.test;

public class Pet {
	private String name;

	public Pet() {
		super();
	}

	public Pet(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void sayName() {
		System.out.println("宠物的名字是 ：" + name);
	}

}
