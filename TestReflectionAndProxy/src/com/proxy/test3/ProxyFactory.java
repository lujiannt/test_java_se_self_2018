package com.proxy.test3;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * cglib子类代理工厂（cglib在spring的核心core包中就有，是通过在内存中构建一个目标对象(被代理对象)的子类，从而实现目标功能的扩展）
 * 优点:使用cglib目标对象不用实现接口
 * @author lujian
 * @create 2017年4月27日
 * @version 1.0
 */
public class ProxyFactory implements MethodInterceptor{
	//维护一个目标对象（被代理对象）
	private Object target;
	
	public ProxyFactory() {
	
	}
	
	public ProxyFactory(Object target) {
		this.target = target;
	}
	
	//给目标对象（被代理对象）生成代理对象--方法1
	public Object getProxyInstance() {
		//初始化工具类
		Enhancer en = new Enhancer();
		//设置父类
		en.setSuperclass(target.getClass());
		//设置回调
		en.setCallback(this);
		//创建子类代理对象
		return en.create();
	}
	
	//给目标对象（被代理对象）生成代理对象--方法2
	@SuppressWarnings("static-access")
	public Object getProxyInstance1() {
		//初始化工具类
		Enhancer en = new Enhancer();
		
		return en.create(target.getClass(), target.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object intercept(Object arg0, Method method, Object[] args,
			MethodProxy arg3) throws Throwable {
		method.invoke(target, args);
		System.out.println("新增的say");
		return null;
	}
	
}
