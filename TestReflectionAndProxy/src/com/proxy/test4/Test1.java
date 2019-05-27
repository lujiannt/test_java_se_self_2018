package com.proxy.test4;

import org.junit.Test;

import com.google.common.reflect.Reflection;


public class Test1 {

	@Test
	public void test() {
		//使用google的guava来实现动态代理，更加简单
		UserManager target = new User();
		//新建代理对象方法处理器
		MyInvocationHandler handler = new MyInvocationHandler(target);
		//这里和jdk的动态代理一样，只能传接口
		UserManager proxy = Reflection.newProxy(UserManager.class, handler);
		proxy.sayName();
	}
}
