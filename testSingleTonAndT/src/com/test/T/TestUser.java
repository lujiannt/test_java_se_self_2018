package com.test.T;

public class TestUser {
	private String name;
	private String pwd;

	public TestUser() {
		super();
	}

	public TestUser(String name, String pwd) {
		super();
		this.name = name;
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public void sayNameAndPwd() {
		System.out.println("my name is " + this.name);
		System.out.println("my pwd is " + this.pwd);
	}

}
