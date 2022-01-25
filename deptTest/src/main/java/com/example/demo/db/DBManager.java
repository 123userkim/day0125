package com.example.demo.db;

import java.io.Reader;
import java.util.List;

import javax.security.sasl.SaslServerFactory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.DeptVO;

public class DBManager {
	private static SqlSessionFactory factory;
	static {
		try {
			Reader reader
			= Resources.getResourceAsReader("com/example/demo/db/sqlMapConfig.xml");
			
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public static List<DeptVO> findAll(){
		SqlSession session = factory.openSession();
		List<DeptVO> list = session.selectList("dept.findAll");
		session.close();
		return list;
	}
	
	public static int insert(DeptVO d) {
		SqlSession session = factory.openSession();
		int re= session.insert("dept.insert", d);
		session.commit();
		session.close();
		return re;
	}
}










