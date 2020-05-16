package com.park.plugins;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Properties;

/**
 * 自定义插件
 *
 * @author Aaron
 * @since
 */
@Intercepts(
		{
				@Signature(type = org.apache.ibatis.executor.Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
				@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
		}
)
public class MyPlugins implements Interceptor {
	/**
	 * 干活的方法
	 *
	 * @param invocation
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		MappedStatement ms = (MappedStatement) args[0];
		Object parameter = args[1];
		RowBounds rowBounds = (RowBounds) args[2];
		ResultHandler resultHandler = (ResultHandler) args[3];
		Executor executor = (Executor) invocation.getTarget();

		System.out.println("自定义插件 执行查询前进行拦截");
		List<Object> query = executor.query(ms, parameter, rowBounds, resultHandler);
		System.out.println("自定义插件 执行查询后进行拦截");
		return query;
	}

	/**
	 * 创建代理对象的方法
	 *
	 * @param target
	 * @return
	 */
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * 注册插件时设置的属性
	 *
	 * @param properties
	 */
	@Override
	public void setProperties(Properties properties) {

	}
}
