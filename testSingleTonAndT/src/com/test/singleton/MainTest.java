package com.test.singleton;

public class MainTest {
	
	public static void main(String[] args) {
			Singleton01.instance().say();
			Singleton02.instance().say();
			Singleton03.instance().say();
			Singleton04.instance().say();
			
			//03 04 看起来似乎没问题，但实际上仍存有被反射、序列化攻击的风险
			//而枚举无法被new、反射、反序列化创建，所以没有反射攻击的风险
			Singleton05.INSTANCE.say();
			System.out.println(Singleton05.INSTANCE.ordinal());
			
			//备注：https://juejin.im/post/5d08f67f51882565e833ff43	
	}

}
