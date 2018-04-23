package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.parser.Parser;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.json.JSONException;
//import org.json.JSONObject;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class Test1 {
	/**
	 * java对象转json
	 * @author lujian
	 * @throws JsonProcessingException 
	 * @throws JSONException 
	 * @create 2017年5月8日
	 */
	@Test
	public void test1() throws Exception {
		User user = new User();
		user.setUserId(1);
		user.setUserName("张三");
		
		/**
		 * 1.jackson（效率很高）
		 * */
		//对象转换成json格式字符串
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonStr = objectMapper.writeValueAsString(user);  
        System.out.println(jsonStr); 
       
        //json格式字符串转换成对象
        User jacksonUser = objectMapper.readValue(jsonStr, User.class);
        System.out.println("jacksonUser : " + jacksonUser.toString());
        //目前没有找到直接将json格式字符串转成json对象的方法
        
        /**
		 * 2.gson
		 * */
        //对象转换成json格式字符串
        Gson gson = new Gson();
        String gsonStr = gson.toJson(user);
        System.out.println(gsonStr); 
        //json格式字符串转换成对象
        User gsonUser = gson.fromJson(gsonStr, User.class);
        System.out.println("gsonUser : " + gsonUser.toString());
        //json格式字符串转换成json对象
        JsonObject jsonObject = new JsonParser().parse(gsonStr).getAsJsonObject();
        System.out.println("gsonObject : " + jsonObject.get("userName"));
        System.out.println(jsonObject.toString());
        //json对象转换成java对象
        User gsonUser1 = gson.fromJson(jsonObject, User.class);
        System.out.println("gsonUser1 : " + gsonUser1.toString());
        
        /**
		 * 3.json-lib
		 * */
        //org.json.JSONObject 和 net.sf.json.JSONObject是有区别的
        //org.json.JSONObject:
        //JSONObject userjson = new JSONObject(gsonStr);
        
        //import net.sf.json.JSONObject:
        //JSONObject userjson = JSONObject.fromObject(jsonStr);
        JSONObject userjson = JSONObject.fromObject(jsonStr);
        User userobj = (User)JSONObject.toBean(userjson, User.class);
        System.out.println(userjson.get("userName") + "  " + userobj.getUserName());
        
        JSONObject userjson1 = JSONObject.fromObject(user);
        User userobj1 = (User)JSONObject.toBean(userjson1, User.class);
        System.out.println(userjson1.get("userName") + "  " + userobj1.getUserName());
	}
	
	
	/**
	 * 复杂对象转换(集合的转换)
	 * @author lujian
	 * @throws JsonProcessingException 
	 * @create 2017年5月8日
	 */
	@SuppressWarnings({ "unchecked" })
	@Test
	public void test2() throws Exception {
		User user = new User();
		user.setUserId(1);
		user.setUserName("张三");
		List<Pet> petList = new ArrayList<Pet>();
		Pet pet = new Pet(1, "小黑", "张三");
		Pet pet1 = new Pet(2, "小黃", "张三");
		petList.add(pet);
		petList.add(pet1);
		user.setPets(petList);
		
		User user1 = new User();
		user1.setUserId(2);
		user1.setUserName("李四");
		List<Pet> petList1 = new ArrayList<Pet>();
		Pet pet3 = new Pet(3, "小红", "李四");
		petList1.add(pet3);
		user1.setPets(petList1);
		
		User user2 = new User();
		user2.setUserId(3);
		user2.setUserName("王武");
		
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		userList.add(user1);
		userList.add(user2);
		
		
		/**
		 * 1.jackson（效率很高）
		 * */
		//list转成json格式字符串
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonStr = objectMapper.writeValueAsString(userList);  
        System.out.println(jsonStr); 
        
        //模拟api获取数据，因为不知道怎么转成json对象，所以测试做法
        //模拟1：模拟客户端的发送的数据 rsjosn
        ResultJson resultJson = new ResultJson();
        resultJson.setRid(1);
        resultJson.setData(userList);
        resultJson.setAttr1("测试用例");
        
        String rsjosn = objectMapper.writeValueAsString(resultJson);
        
        //模拟2: 只接受data,取出data中的list
        ResultJson resultJson1 = objectMapper.readValue(rsjosn, ResultJson.class);
        List<User> users = (ArrayList<User>)resultJson1.getData();
        System.out.println(users.toString());
        
        /**
		 * 2.gson
		 * */
        Gson gson = new Gson();
        //list转成json字符串
        String gsonStr = gson.toJson(userList);
        //json字符串转成list
        List<User> ps = gson.fromJson(gsonStr, new TypeToken<List<User>>(){}.getType()); 
        System.out.println(ps.get(0).getUserName());
        //json字符串转成jsonArray
        JsonArray jsonArray = new JsonParser().parse(gsonStr).getAsJsonArray(); 
        System.out.println(jsonArray.toString());
        //jsonArray转成list(也可以用for循环遍历jsonArray,然后把里面的jsonObject一个个的转成java对象，添进list   。。。无力吐槽)
        List<User> ps1 = gson.fromJson(jsonArray, ArrayList.class);
        System.out.println("ps1 : " + ps1.toString());
        
        /**
		 * 3.json-lib
		 * */
        //使用jsonArray
        JSONArray jSONArray = JSONArray.fromObject(gsonStr);
        System.out.println("JSONArray is : " + jSONArray.toString());
        
        
//		当集合中对象里有集合这种复杂属性时，toCollection转换会有问题，会丢失集合中对象里的集合，会报如下错
//      java.lang.ClassCastException: net.sf.ezmorph.bean.MorphDynaBean cannot be cast to com.test.Pet
//      ArrayList<User> list = (ArrayList<User>) JSONArray.toCollection(jSONArray, User.class);
//		两种方法解决报错问题        
        
        //方法1：遍历
        List<User> list = new ArrayList<User>();
        for(int i=0;i<jSONArray.size();i++) {
        	JSONObject jo = jSONArray.getJSONObject(i);
        	User u = (User) JSONObject.toBean(jo, User.class);
        	if(jo.get("pets")!=null) {
        		JSONArray petsArray = jo.getJSONArray("pets");
        		List<Pet> petss = (ArrayList<Pet>)JSONArray.toCollection(petsArray, Pet.class);
        		u.setPets(petss);
        	}
        	list.add(u);
        }
        
        System.out.println(list.get(0).getPets().get(0).getPetName());		
        
        //方法2：设置classMap
        Map<String, Object> classMap = new HashMap<String, Object>();
		classMap.put("pets", Pet.class);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setClassMap(classMap);
		jsonConfig.setRootClass(User.class);
		List<User> list1 = (ArrayList<User>) JSONArray.toCollection(jSONArray, jsonConfig);
        System.out.println(list1.get(0).getPets().get(0).getPetName());		
        
        //这是错误的
        ArrayList<User> list2 = (ArrayList<User>) JSONArray.toCollection(jSONArray, User.class);
        System.out.println(list2.get(0).getPets().get(0).getPetName());		
        
	}
}
