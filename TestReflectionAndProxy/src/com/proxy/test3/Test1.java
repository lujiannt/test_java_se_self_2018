package com.proxy.test3;

import org.junit.Test;

public class Test1 {
	
	/**
	 * 动态代理（目标对象要实现接口）
	 * @author lujian
	 * @create 2017年4月27日
	 */
	@Test
	public void test1() {
		//目标对象（被代理对象）
		User target = new User();
		//代理对象
		ProxyFactory proxyFactory = new ProxyFactory(target);
		User userproxy = (User)proxyFactory.getProxyInstance();
		userproxy.say();
	}
	
	@Test
	public void test2() {
		//目标对象（被代理对象）
		User target = new User();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		User userproxy = (User)proxyFactory.getProxyInstance1();
		userproxy.say();
	}
	
}
