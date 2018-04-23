package com.proxy.test1;

/**
 * 代理对象
 * @author lujian
 * @create 2017年4月27日
 * @version 1.0
 */
public class UserProxy implements UserManager{
	private UserManager target;
	
	public UserProxy() {
	
	}
	
	public UserProxy(UserManager target) {
		this.target = target;
	}
	
	@Override
	public void say() {
		target.say();
		System.out.println("代理对象扩展的内容");
	}
}
