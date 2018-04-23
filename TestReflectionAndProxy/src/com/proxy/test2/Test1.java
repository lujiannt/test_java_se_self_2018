package com.proxy.test2;

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
		UserManager target = new User();
		//代理对象
		ProxyFactory proxyFactory = new ProxyFactory(target);
		UserManager userproxy = (UserManager)proxyFactory.getProxyInstance();
		userproxy.say();
	}
	
	/**
	 * 测试接口中实现方法
	 * @author lujian
	 * @create 2017年4月27日
	 */
	@Test
	public void test2() {
		new UserManager() {
			
			@Override
			public void say() {
				System.out.println("原来接口还可以这么玩儿");
			}
		};
	}
}
