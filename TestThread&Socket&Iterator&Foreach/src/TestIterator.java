import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import java.util.Set;

public class TestIterator {
	protected static Map<String, Object> map = new HashMap<String, Object>();
	protected static List<User> userList = new ArrayList<User>();
	
	static {
		System.out.println("--------------静态代码块----------------");

		map.put("1", "我是1");
		map.put("2", "我是2");
		map.put("3", "我是3");
		
		
		User u1 = new User();
		u1.setId(1);
		u1.setName("用户1");
		User u2 = new User();
		u2.setId(2);
		u2.setName("用户2");
		User u3 = new User();
		u3.setId(3);
		u3.setName("用户3");
		
		userList.add(u1);
		userList.add(u2);
		userList.add(u3);
		System.out.println("size:"+userList.size());
	}
	
	//静态代码块在构造函数前执行
	public TestIterator() {
		super();
		System.out.println("--------------构造函数----------------");
	}
	
	/**
	 * entrySet遍历Map
	 * @author lujian
	 * @create 2018年4月20日 上午9:13:33
	 * @rerurn void
	 */
	@Test
	public void test1() {
		Set<Entry<String, Object>> entrySet = map.entrySet();
		
		//1.普通遍历
		System.out.println("-------------普通遍历-------------");
		
		for(Entry<String, Object> entry : entrySet) {
			System.out.println("key = " + entry.getKey() + " value = " + (String)entry.getValue());
		}
		
		//2.用iterator遍历
		System.out.println("-------------用iterator遍历-------------");
		
		Iterator<Entry<String, Object>> it = entrySet.iterator();
		
		while(it.hasNext()) {
			Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String value = (String)entry.getValue();
			System.out.println("key = "+key +", value = "+value);
		}
	}
	
	/**
	 * keySet遍历Map
	 * @author lujian
	 * @create 2018年4月20日 上午9:17:30
	 * @rerurn void
	 */
	@Test
	public void test2() {
		Set<String> keyset = map.keySet();
		
		//1.普通遍历
		System.out.println("-------------普通遍历-------------");
		
		for(String key : keyset) {
			System.out.println("key = " + key + " value = " + map.get(key));
		}
		
		//2.用iterator遍历
		System.out.println("-------------用iterator遍历-------------");
		
		Iterator<String> it = keyset.iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			System.out.println("key = " + key + " value = " + map.get(key));
		}
	}
	
	/**
	 * 增强for和iterator的区别
	 * @author lujian
	 * @create 2018年4月20日 上午9:33:58
	 * @rerurn void
	 */
	@Test
	public void test3() {
		//区别
		//增强for里面可以修改user，但不可以删除,删除会报错
		//iterator中可以使用自己的remove方法删除,在一个方法中一个iterator只能使用一次
		System.out.println("-------------修改前");
		for(User u : userList) {
			System.out.println(u.getId()+"  "+u.getName());
		}
		System.out.println("-------------修改后");
		for(User u : userList) {
			u.setName(u.getName()+"new");
			System.out.println(u.getId()+"  "+u.getName());
		}
		
		//----------------------------------------------------
		System.out.println("-------------iterator");
		Iterator<User> it = userList.iterator();
		while(it.hasNext()) {
			User u = it.next();
			System.out.println(u.getId()+"  "+u.getName());
		}
	}
	
	/**
	 * 测试集合删除
	 * @author lujian
	 * @create 2018年4月20日 上午9:59:19
	 * @rerurn void
	 */
	@Test
	public void test4() {
		Iterator<User> it = userList.iterator();
		//测试删除
		while(it.hasNext()) {
			User u = it.next();
			if(u.getId()==1) {
				it.remove();
			}
		}
		
		System.out.println(userList.toString());
	}
}
