package com.osf.test.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Test{
	private String str;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	@Override
	public String toString() {
		return "Test [str=" + str + "]";
	}
}
public class ReflectionTest {

	public static void main(String[] args) {
		try {
			Class<?> clazz = Class.forName("com.osf.test.test.Test");
			//class가 예약어이기 때문에 보통 clazz로 한다.
		//	Method[] methods = clazz.getMethods();
			Method[] methods = clazz.getDeclaredMethods();
			Object obj = clazz.newInstance();
			for(Method m : methods) {
		//		System.out.println(m.getName());
				String mName = m.getName();
				int idx = mName.indexOf("set");
				m.invoke(obj, "abc");
			}
			Test t = (Test)clazz.newInstance(); //object기 때문에 강제로 캐스팅
			System.out.println(t); //error가 나지 않은 이유는 Test t = new Test(); 이 과정이 실행되었다. clazz.newInstance가 생성자를 호출했다.
			//이 메소드를 통해서 인스턴스화 시킨 애한테 입력할 수 있다????
			
			Test t1 = new Test();
			System.out.println(t1);
			
			t1.getStr();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
