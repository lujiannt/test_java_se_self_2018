package com.test.T;

public class Test02<T> {
	private T t;
	private Class<?> clazz;
	private Class<T> Tclazz;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<T> getTclazz() {
		return Tclazz;
	}

	public void setTclazz(Class<T> tclazz) {
		Tclazz = tclazz;
	}

	// T是泛型, 而？是通配符
	public static void main(String[] args) throws Exception {
		Test02<Integer> test02 = new Test02<>();
		
		// 这里使用T 只能传递和类定义的T一样的类
		test02.setT(new Integer(1));
		test02.setTclazz(Integer.class);

		// 这里使用？可以传递任何class
		test02.setClazz(String.class);
		
		
		//接口中使用T和？事例，【注意】接口前要加<T>
		Tservice tservice = new TserviceImpl();
		TestUser user = tservice.getObjectByName(TestUser.class, new Class<?>[] {String.class, String.class}, new Object[] {"张三", "123456"});
		user.sayNameAndPwd();
	}

}
