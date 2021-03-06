package com.reflection.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;

/**
 * 反射练习demo
 * @author lujian
 *
 */
public class Test1 {
	
	/**
	 * 通过反射获取对象
	 * @throws Exception
	 * @author lujian
	 * @create 2017年4月26日
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void test1() throws Exception {
		//获取类型三种方法
		//1.class.forName
		Class class1 = Class.forName("com.reflection.test.User");
		//2. Object.class
		Class<User> class2 = User.class;
		//2. 对象的getClass()方法
		User user = new User();
		Class<? extends User> class3 = user.getClass();
		
		//通过newInstance()方法来实例化对象
		User user1 = (User) class1.newInstance();
		User user2 = class2.newInstance();
		User user3 = class3.newInstance();

		user1.say("user1");
		user2.say("user2");
		user3.say("user3");
		System.out.println(user == user3);
	}
	
	/**
	 * 通过反射获取所有属性
	 * @throws Exception
	 * @author lujian
	 * @create 2017年4月27日
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void test2() throws Exception {
		//1.获取所有属性 class.getDeclaredFields
		Class<User> class1 = User.class;
		Field[] fields = class1.getDeclaredFields();
		
		StringBuffer sb = new StringBuffer();
		sb.append(Modifier.toString(class1.getModifiers()) + "\t" + class1.getName()+"{\n");
		
		for(Field field : fields) {
			sb.append(Modifier.toString(field.getModifiers())+"\t");
			sb.append(field.getType().getSimpleName()+"\t");
			sb.append(field.getName()+"\n");
		}
		sb.append("}");
		System.out.println(sb.toString());
	}
	
	/**
	 * 通过反射获取指定属性(反射可以打破属性封装)
	 * @throws Exception
	 * @author lujian
	 * @create 2017年4月27日
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void test3() throws Exception {
		Class<User> class1 = User.class;
		//根据属性名获取到属性
		Field field = class1.getDeclaredField("name");
		
//		StringBuffer sb = new StringBuffer();
//		sb.append(Modifier.toString(class1.getModifiers()) + "\t" + class1.getName()+"{\n");
//		sb.append(Modifier.toString(field.getModifiers())+"\t");
//		sb.append(field.getType().getSimpleName()+"\t");
//		sb.append(field.getName()+"\n");
//		sb.append("}");
//		System.out.println(sb.toString());
		
		//反射可以打破封装
		User user = class1.newInstance();
		field.setAccessible(true);//打破属性封装
		field.set(user, "张三");
		
		System.out.println(field.get(user));
		System.out.println(user.getName());
	}
	
	/**
	 * 通过反射获取所有方法
	 * @throws Exception
	 * @author lujian
	 * @create 2017年4月27日
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void test4() throws Exception {
		Class<User> class1 = User.class;
		Method[] methods = class1.getDeclaredMethods();
		
		StringBuffer sb = new StringBuffer();
		sb.append(Modifier.toString(class1.getModifiers()) + "\t" + class1.getName()+"{\n");
		
		for(Method method : methods) {
			sb.append(Modifier.toString(method.getModifiers())+"\t");
			sb.append(method.getReturnType().getSimpleName()+"\t");
			if(method.getParameterTypes().length > 0) {
				sb.append(method.getParameterTypes()[0].getName()+"\t");
			}
			sb.append(method.getName()+"\n");
		}
		sb.append("}");
		System.out.println(sb.toString());
	}
	
	/**
	 * 通过反射获取指定方法
	 * @throws Exception
	 * @author lujian
	 * @create 2017年4月27日
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void test5() throws Exception {
		Class<User> class1 = User.class;
		//根据方法名，方法参数获取到属性,使用invoke反射
		Method method = class1.getDeclaredMethod("say", String.class);
		Object obj = class1.newInstance();
		method.invoke(obj, "15");
		
		Method method1 = class1.getDeclaredMethod("nosay");
		method1.invoke(obj);
	}
	
	
	/**
	 * 通过反射获取指定私有方法
	 * @throws Exception
	 * @author lujian
	 * @create 2017年4月27日
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void test6() throws Exception {
		Class<User> class1 = User.class;
		Object obj = class1.newInstance();
		//根据方法名，方法参数获取到属性,使用invoke反射
		Method method = class1.getDeclaredMethod("sayPrivate");
		//打破封装
		method.setAccessible(true);
		method.invoke(obj);
	}
	
	/**
	 * 通过获取构造方法创建对象
	 * @throws Exception
	 * @author lujian
	 * @create 2017年4月26日
	 */
	@Test
	public void test7() throws Exception {
		Class<Pet> class1 = Pet.class;
		//获取构造方法
		Constructor<Pet> constructor =  class1.getConstructor(String.class);
		//初始化对象
		Pet pet1 =  constructor.newInstance("朱春杰");
		Pet pet2 =  instanceObj(Pet.class, new Class<?>[] {String.class}, new Object[] {"zcj"});
		
		pet1.sayName();
		pet2.sayName();
	}
	
	//反射方法创建对象，此方法主要是更好明白T和？的使用场景
	@SuppressWarnings("unused")
	public static <T> T instanceObj(Class<T> clazz, Class<?>[] consParamClazz, Object[] params) throws Exception {
		Constructor<T> constructor = clazz.getConstructor(consParamClazz);
		return constructor.newInstance(params);
	}
}
