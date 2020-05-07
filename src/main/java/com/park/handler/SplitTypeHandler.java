package com.park.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aaron
 * @since
 */
public class SplitTypeHandler extends BaseTypeHandler {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
		System.out.println("setNonNullParameter");
		List<String> subjectList = (List<String>) parameter;
		StringBuilder sb = new StringBuilder();
		for (int i1 = 0; i1 < subjectList.size(); i1++) {
			if (i1 == subjectList.size() - 1) {
				sb.append(subjectList.get(i1));
			} else {
				sb.append(subjectList.get(i1));
				sb.append(",");
			}
		}
		System.out.println(sb.toString());
		ps.setString(i, sb.toString());
	}

	@Override
	public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
		List<String> subjectList = new ArrayList<>();
		System.out.println("columnName:" + columnName);
		String string = rs.getString(columnName);
		if (string != null && !"".equals(string)) {
			String[] split = string.split(",");
			for (String s : split) {
				subjectList.add(s);
			}
		}
		System.out.println("getNullableResult 1 ");
		System.out.println("subjectList" + subjectList);
		return subjectList;
	}

	@Override
	public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		System.out.println("getNullableResult 2 ");
		return null;
	}

	@Override
	public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		System.out.println("getNullableResult 3 ");
		return null;
	}
}
