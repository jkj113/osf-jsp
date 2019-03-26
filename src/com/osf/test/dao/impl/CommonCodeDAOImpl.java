package com.osf.test.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.osf.test.dao.CommonCodeDAO;
import com.osf.test.db.DBCon;
import com.osf.test.vo.CommonCodeVO;

public class CommonCodeDAOImpl implements CommonCodeDAO {
	private static final String SELECT_LIST = "select * from common_code where cc_group = ?";

	@Override
	public List<CommonCodeVO> selectCommonCodeList(String ccGroup) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(SELECT_LIST);
			ps.setString(1, ccGroup);
			ResultSet rs = ps.executeQuery();
			List<CommonCodeVO> ccList = new ArrayList<>();
			while(rs.next()) {
				CommonCodeVO cc = new CommonCodeVO();
				cc.setCcNum(rs.getInt("cc_num"));
				cc.setCcGroup(rs.getString("cc_group"));
				cc.setCcName(rs.getString("cc_name"));
				cc.setCcCode(rs.getString("cc_code"));
				ccList.add(cc);
			}
			return ccList; //데이터타입이 List<CommonCodeVO>라서 void아니면 다 리턴해줘야 되니까.
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		CommonCodeDAO ccdao = new CommonCodeDAOImpl();
		System.out.println(ccdao.selectCommonCodeList("trans"));
	}
}
