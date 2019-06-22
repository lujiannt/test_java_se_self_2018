package com.test.T;

public interface Tservice {

	// 方法中混合使用T和？   【注意】 需要在接口前面加上<T>
	public <T> T getObjectByName(Class<T> objClass, Class<?>[] paramTypes, Object[] params) throws Exception;

}
