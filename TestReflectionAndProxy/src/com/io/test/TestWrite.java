package com.io.test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.proxy.test1.User;

public class TestWrite {
	/**
	 * 写 
	 * @author lujian
	 * @throws Exception 
	 * @create 2017年4月27日
	 */
	@Test
	public void test1() throws Exception {
		OutputStream fos = new FileOutputStream("e:/test.txt");
		
		InputStream is = new FileInputStream("e:/文件demo.txt");

		BufferedInputStream bis = new BufferedInputStream(is);
		int temp = 0;
		while((temp = bis.read()) != -1) {
			fos.write(temp);
		}
		
		bis.close();
		is.close();
		fos.flush();
		fos.close();
	}
	
	/**
	 * 写 
	 * @author lujian
	 * @throws Exception 
	 * @create 2017年4月27日
	 */
	@Test
	public void test2() throws Exception {
		OutputStream fos = new FileOutputStream("e:/user.txt");
		InputStream is = new FileInputStream("e:/文件demo.txt");
		byte[] buffer = new byte[16*1024];
		
		BufferedInputStream bis = new BufferedInputStream(is);
		int temp = 0;
		while((temp = bis.read(buffer)) != -1) {
			fos.write(buffer);
		}
		
		bis.close();
		is.close();
		fos.flush();
		fos.close();
	}
	
	/**
	 * 对象流（需要序列化对象）
	 * @author lujian
	 * @throws IOException 
	 * @create 2017年4月27日
	 */
	@Test
	public void test3() throws IOException {
		User u = new User();
		OutputStream fos = new FileOutputStream("e:/user.class");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(u);
		
		oos.flush();
		oos.close();
		fos.flush();
		fos.close();
	}
	
	/**
	 * 用字节输出对象（将对象转换成字节）
	 * @author lujian
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 * @create 2017年4月27日
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test4() throws IOException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		com.io.test.User u = new com.io.test.User();
		
		/***-将未序列化对象利用反射转成map（或者转成json等），再转成字节如果对象已实现序列化无需下段代码-***/
		/***-↓↓↓↓↓↓↓↓↓-***/
		Map<String, Object> map = new HashMap<String, Object>();    
		  
        Field[] declaredFields = u.getClass().getDeclaredFields();    
        for (Field field : declaredFields) {    
            field.setAccessible(true);  
            if(field.getName().equals("name")) {
            	field.set(u, "张三");
            }else if(field.getName().equals("age")) {
            	field.set(u, 11);
            }
            map.put(field.getName(), field.get(u));  
        }    
        /***-↑↑↑↑↑↑↑↑↑↑-***/
        
        //1.对象转成数组
        OutputStream fos = new FileOutputStream("e:/user.txt");
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(ba);
		oos.writeObject(map);
		
		byte[] bytes = ba.toByteArray();
		ba.writeTo(fos);
		System.out.println(bytes.length);
		
		//2.数组转成对象,使用entryset遍历map效率比keyset高
		ByteArrayInputStream ba1 = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(ba1);
		Object obj = ois.readObject();
		Map<String, Object> u1 = (HashMap<String, Object>) obj;
		
		Iterator<Entry<String, Object>> it = u1.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, Object> entrys = it.next();
			System.out.println(entrys.getKey()+" : "+entrys.getValue());
		}
		
		oos.close();
		ba.close();
		fos.close();
	}
}
