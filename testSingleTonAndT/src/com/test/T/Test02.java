package com.test.T;

public class Test02<T> {
	private T t;
	private ? t1;
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
	public static void main(String[] args) {
		Test02<Integer> test02 = new Test02<>();
		
		// 这里使用T 只能传递和类定义的T一样的类
		test02.setT(new Integer(1));
		test02.setTclazz(Integer.class);

		// 这里使用？可以传递任何class
		test02.setClazz(String.class);
	}

}
