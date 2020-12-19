package dengtao.Model.dao.util;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			//第一步获取sqlSessionFactory
			String resource = "resources/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
	
}
	
	
