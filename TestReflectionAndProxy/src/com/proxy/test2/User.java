package com.proxy.test2;

/**
 * 目标对象（被代理对象）
 * @author lujian
 * @create 2017年4月27日
 * @version 1.0
 */
public class User implements UserManager{
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
	
	@Override
	public void say() {
		System.out.println("目标（被代理对象）自己的内容");
	}
	
	
	
}
