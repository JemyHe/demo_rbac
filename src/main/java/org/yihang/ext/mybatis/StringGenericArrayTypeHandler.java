package org.yihang.ext.mybatis;

import java.lang.reflect.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public abstract class StringGenericArrayTypeHandler<T> extends BaseTypeHandler<T[]> {
	private Class<T> clazz;

	public StringGenericArrayTypeHandler(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, T[] parameter, JdbcType jdbcType) throws SQLException {
		StringBuffer result = new StringBuffer();
		for (T value : parameter) {
			result.append(value).append(",");
		}
		result.deleteCharAt(result.length() - 1);
		ps.setString(i, result.toString());
	}

	@Override
	public T[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return toArray(rs.getString(columnName));
	}

	@Override
	public T[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return toArray(rs.getString(columnIndex));
	}

	@Override
	public T[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return toArray(cs.getString(columnIndex));
	}

	private T[] toArray(String string) {
		if (string == null) {
			return newArray(0);
		}
		String[] values = string.split(",");
		T[] array = newArray(values.length);
		for (int i = 0; i < values.length; i++) {
			array[i] = parse(values[i]);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	T[] newArray(int size) {
		return (T[]) Array.newInstance(clazz, size);
	}

	protected abstract T parse(String string);

}
