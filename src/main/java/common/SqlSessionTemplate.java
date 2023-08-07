package common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {

	public static SqlSession getSqlSession() {		// 공유하기 위해 static에 올려두기
		String resource = "mybatis-config.xml";		// 파일 변수지정
		
		SqlSession session = null;
		
		try {
			InputStream is = Resources.getResourceAsStream(resource);			// 파일 가져오기
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();	// 연결공장을 만드는 빌더 생성
			SqlSessionFactory factory = builder.build(is);						// 빌더가 파일 가져온 것을 연결공장에서 참고하여
			session = factory.openSession();									// 연결공장에서 연결 생성
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return session;
	}
}
