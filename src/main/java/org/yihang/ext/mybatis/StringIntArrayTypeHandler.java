package org.yihang.ext.mybatis;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes({int[].class, Integer[].class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class StringIntArrayTypeHandler extends StringGenericArrayTypeHandler<Integer>{

	public StringIntArrayTypeHandler() {
		super(Integer.class);
	}

	@Override
	protected Integer parse(String string) {
		return Integer.parseInt(string);
	}

}
