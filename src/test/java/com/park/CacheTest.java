package com.park;

import com.park.domain.Content;
import com.park.mapper.ContentMapper;
import org.apache.ibatis.cache.decorators.LruCache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 缓存测试
 *
 * @author Aaron
 * @since
 */
public class CacheTest {
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void before() throws IOException {
		String source = "mybatis-config.xml";
		InputStream resourceAsStream = Resources.getResourceAsStream(source);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
	}

	@Test
	public void firstCacheTest() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ContentMapper mapper = sqlSession.getMapper(ContentMapper.class);

		Content contentOne = mapper.selectById(2000);
		System.out.println(contentOne);

		Content contentTwo = mapper.selectById(2000);
		System.out.println(contentTwo);
	}


	@Test
	public void secondCacheTest() {
		SqlSession sqlSessionOne = sqlSessionFactory.openSession();
		ContentMapper mapperOne = sqlSessionOne.getMapper(ContentMapper.class);

		Content contentOne = mapperOne.selectById(2000);
		System.out.println(contentOne);

		sqlSessionOne.close();

		SqlSession sqlSessionTwo = sqlSessionFactory.openSession();
		ContentMapper mapperTwo = sqlSessionTwo.getMapper(ContentMapper.class);
		Content contentTwo = mapperTwo.selectById(2000);
		System.out.println(contentTwo);
	}

}
