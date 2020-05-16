package com.park;

import com.park.domain.Content;
import com.park.mapper.ContentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aaron
 * @since
 */
public class BatchInsertTest {
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void before() throws IOException {
		String source = "mybatis-config.xml";
		InputStream resourceAsStream = Resources.getResourceAsStream(source);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
	}

	@Test
	public void normalBatchInsert() {
		// 循环创建 10000 条数据
		List<Content> contentList = new ArrayList<>(10000);
		for (int i = 2000; i < 12000; i++) {
			Content content = new Content();
			content.setId(i);
			content.setContent("content:" + i);
			contentList.add(content);
		}

		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ContentMapper mapper = sqlSession.getMapper(ContentMapper.class);
		long startTime = System.currentTimeMillis();
		mapper.batchInsert(contentList);
		sqlSession.commit();
		sqlSession.close();
		long endTime = System.currentTimeMillis();
		System.out.println("耗时:" + (endTime - startTime));
	}

	@Test
	public void badBatchInsert() {
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ContentMapper mapper = sqlSession.getMapper(ContentMapper.class);
		long startTime = System.currentTimeMillis();

		// 循环创建 10000 条数据
		for (int i = 2000; i < 12000; i++) {
			Content content = new Content();
			content.setId(i);
			content.setContent("content:" + i);
			mapper.insert(content);
		}
		sqlSession.commit();
		sqlSession.close();
		long endTime = System.currentTimeMillis();
		System.out.println("耗时:" + (endTime - startTime));

	}
}
