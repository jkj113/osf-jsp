package com.osf.test.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.osf.test.db.DBCon;

public class RSTest {
	public static void main(String[] args) {
		String sql = "select * from food";
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			List<Map<String,String>> colList = new ArrayList<>();
			for(int i =1;i<=rsmd.getColumnCount();i++) { //i=1부터니까 <=로 해야된다. 0부터 하면 <로 
				String colName = rsmd.getColumnLabel(i); //0부터로 하면 i+1로 컬럼은 0부터 없다 (데이터베이스는 1부터)
				colName = colName.toLowerCase();
				int idx = colName.indexOf("_");
				String fName = colName.substring(0,idx); //잘라준다 0부터 idx까지
				String lName = colName.substring(idx+1); //idx가 그 위치부터 나오니까 +1해줘야 그 다음부터
				lName = lName.substring(0,1).toUpperCase()+lName.substring(1); //두번째 단어의 첫글자만 대문자가 오도록 해 준다.
			
				Map<String,String> col = new HashMap<>();
				col.put(colName, fName + lName);
				colList.add(col);
			}
			System.out.println(colList);
				//	System.out.println(colName);
				//System.out.println(fName+lName);
			while(rs.next()) {
				Map<String,String> row = new HashMap<>();
				for(Map<String,String> col : colList) { //이렇게 풀으면 되요
					Iterator<String> it = col.keySet().iterator();
					String key = it.next();
					String value = rs.getString(key);
					row.put(col.get(key),value);
				}
				System.out.println(row);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
