package com.osf.test.test;

public class StringBufferExam {
public static void main(String[] args) {
	long sTime = System.currentTimeMillis();
//	String str = "";
	String str = "a"+"b";
	StringBuffer sb = new StringBuffer(); //대량의 데이터를 쌓아야할 때 
	sb.append("a").append("b"); //String str = "a"+"b"; 같은 말이다
	for(int i = 1; i<=100000; i++) {
		str += i+"t";
		sb.append(i+"t");
	}
	str = sb.toString(); //sb의 데이터타입이 String이 아니므로 String에 담아줘야한다.
	long eTime = System.currentTimeMillis()-sTime;
	System.out.println("execute Time : " + eTime);
}
}
