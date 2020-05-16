package com.park;

import com.github.pagehelper.PageHelper;
import com.park.domain.*;
import com.park.mapper.BlogMapper;
import com.park.mapper.CardMapper;
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
 * 批量操作 Demo
 *
 * @author Aaron
 * @since
 */
public class MyBatisTest {
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void before() throws IOException {
		String source = "mybatis-config.xml";
		InputStream resourceAsStream = Resources.getResourceAsStream(source);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
	}

	/**
	 * 一对一，一篇文章对应一个作者
	 * 嵌套结果，不存在N+1问题
	 */
	@Test
	public void associationSelect() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
		BlogAndAuthor blogAndAuthor = mapper.selectBlogWithAuthorResult(1);
		System.out.println(blogAndAuthor);
	}

	/**
	 * 一对一，一篇文章对应一个作者
	 * 嵌套查询，会有N+1的问题
	 */
	@Test
	public void testSelectBlogWithAuthorQuery() throws IOException {
		SqlSession session = sqlSessionFactory.openSession();
		BlogMapper mapper = session.getMapper(BlogMapper.class);

		BlogAndAuthor blog = mapper.selectBlogWithAuthorQuery(1);
		System.out.println("-----------:" + blog.getClass());
		// 如果开启了延迟加载(lazyLoadingEnabled=true)，会在使用的时候才发出SQL
		// equals,clone,hashCode,toString也会触发延迟加载
		System.out.println("-----------调用toString方法:" + blog);
		System.out.println("-----------getAuthor:" + blog.getAuthor().toString());
		// 如果 aggressiveLazyLoading = true ，也会触发加载，否则不会
		//System.out.println("-----------getName:"+blog.getName());
	}

	/**
	 * 一对多关联查询：一篇文章对应多条评论
	 *
	 * @throws IOException
	 */
	@Test
	public void testSelectBlogWithComment() throws IOException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			BlogMapper mapper = session.getMapper(BlogMapper.class);
			BlogAndComment blog = mapper.selectBlogWithCommentById(1);
			System.out.println(blog);
		} finally {
			session.close();
		}
	}

	/**
	 * 多对多关联查询：作者的文章的评论
	 *
	 * @throws IOException
	 */
	@Test
	public void testSelectAuthorWithBlog() throws IOException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			BlogMapper mapper = session.getMapper(BlogMapper.class);
			List<AuthorAndBlog> authors = mapper.selectAuthorWithBlog();
			for (AuthorAndBlog author : authors) {
				System.out.println(author);
			}
		} finally {
			session.close();
		}
	}

	/**
	 * 分页插件测试
	 *
	 * @throws IOException
	 */
	@Test
	public void testPageHelper() throws IOException {
		SqlSession session = sqlSessionFactory.openSession();
		CardMapper cardMapper = session.getMapper(CardMapper.class);
		PageHelper.startPage(1, 10);
		List<Card> cards = cardMapper.selectAll();
		System.out.println(cards);
	}
}
