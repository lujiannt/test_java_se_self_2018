package com.proxy.test4;

import java.lang.reflect.Method;

import com.google.common.reflect.AbstractInvocationHandler;

public class MyInvocationHandler extends AbstractInvocationHandler{
	private Object target;
	

	public MyInvocationHandler(Object target) {
		super();
		this.target = target;
	}


	@Override
	protected Object handleInvocation(Object proxy, Method method, Object[] params) throws Throwable {
		System.out.println("im guava proxy handler");
		System.out.println("methodName is " + method.getName());
		method.invoke(target, params);
		return null;
	}
	
	
	
}
