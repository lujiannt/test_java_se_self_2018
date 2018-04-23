package com.proxy.test1;

import org.junit.Test;

public class Test1 {
	
	/**
	 * 静态代理（缺点：一旦接口新增方法，代理对象和目标对象就都要改,维护起来so trouble）
	 * @author lujian
	 * @create 2017年4月27日
	 */
	@Test
	public void test1() {
		//目标对象（被代理对象）
		User target = new User();
		//代理对象
		UserProxy userProxy = new UserProxy(target);
		userProxy.say();
	}
}
