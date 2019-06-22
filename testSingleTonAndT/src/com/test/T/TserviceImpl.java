package com.test.T;

import java.lang.reflect.Constructor;

public class TserviceImpl implements Tservice {

	@Override
	public <T> T getObjectByName(Class<T> objClass, Class<?>[] paramTypes, Object[] params) throws Exception {
		Constructor<T> constructor = null;
		constructor = objClass.getConstructor(paramTypes);
		return constructor.newInstance(params);
	}

}
