package com.example.demo.db;

import java.io.InputStream;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.RoomVO;


public class DBManager {
	
	public static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		} catch(Exception e) {
			System.out.println("예외발생: " + e.getMessage());
		}
	}
	
	public static List<RoomVO> findAll() {
		SqlSession session = sqlSessionFactory.openSession();
		List<RoomVO> list = session.selectList("room.findAll");
		session.close();
		return list;
	}
	
	public static List<RoomVO> getAvailable(Map map) {
		System.out.println("메니저 동작함"+map);
		
		SqlSession session = sqlSessionFactory.openSession();
		List<RoomVO> list = session.selectList("room.getAvailable",map);
		System.out.println("테이터수:"+list.size());
		session.close();
		return list;
	}

}
