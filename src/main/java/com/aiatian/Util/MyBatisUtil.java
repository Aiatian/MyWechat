package com.aiatian.Util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
	private static SqlSessionFactory sessionFactory;
	
	static{
		SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream("mybatis.cfg.xml");
			sessionFactory = factoryBuilder.build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}finally{
			if (inputStream!=null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new ExceptionInInitializerError(e);
				}
			}
		}
		
	}
	
	protected MyBatisUtil(){
		
	}
	
	public static SqlSession getSqlSession(){
		return sessionFactory.openSession();
	}
	
	public static void reBuild(String resources) throws IOException{
		sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resources));
	}
}
