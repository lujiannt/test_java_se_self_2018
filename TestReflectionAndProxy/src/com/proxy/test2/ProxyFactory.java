package com.proxy.test2;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理（不需要实现接口，但是需要指定接口类型）
 * @author lujian
 * @create 2017年4月27日
 * @version 1.0
 */
public class ProxyFactory{
	//维护一个目标对象（被代理对象）
	private Object target;
	
	public ProxyFactory() {
	
	}
	
	public ProxyFactory(Object target) {
		this.target = target;
	}
	
	//给目标对象（被代理对象）生成代理对象
	public Object getProxyInstance() {
		return Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), 
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						System.out.println(proxy.getClass().getName());
						
						
						System.out.println("代理对象新增的内容");
						return method.invoke(target, args);
					}
				});
	}
	
}
