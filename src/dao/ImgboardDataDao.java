package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlSessionUtil;
import vo.ImgboardData;

public class ImgboardDataDao {

	
	static SqlSessionFactory sqlFactory = SqlSessionUtil.getSessionFactory();
	
	private static ImgboardDataDao idao = new ImgboardDataDao();
	
	private ImgboardDataDao() {}
	
	public static ImgboardDataDao getInstance() {
		return idao;
	}
	
	public int getCount() {
		SqlSession mapper = sqlFactory.openSession();
		int cnt = mapper.selectOne("imgGetCount");  
		mapper.close();
		return cnt;
	}
	
	public List<ImgboardData> getList(int starNo,int endNo){
		List<ImgboardData> list;
		SqlSession mapper = sqlFactory.openSession();
		
		//HashMap 객체에 2개 이상의 타입이 동일한 변수값을 저장할 수 있다.
		Map<String,Integer> map= new HashMap<>();
		map.put("startNo", starNo);   //key,value 한 쌍으로 저장
		map.put("endNo", endNo);
		
		list=mapper.selectList("imgGetList",map);   //id를 getList2로 바꿔서도 실행해보세요.
		mapper.close();
		return list;
	}

	public static ImgboardData getOne(int imgIdx) {
		SqlSession mapper = sqlFactory.openSession();
		ImgboardData bean = mapper.selectOne("imgSelectByIdx", imgIdx);   //첫번째는 매퍼xml id값, 두번째 인자는 파라미터
		mapper.close();
		return bean;
	}

	public static void delete(int imgIdx) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.delete("imgDelete",imgIdx);
		mapper.commit();
		mapper.close();
	}

	public static void insert(ImgboardData imgboardData) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.selectOne("imgInsert", imgboardData);   //첫번째는 매퍼xml id값, 두번째 인자는 파라미터
		mapper.close();
	}

	public static void update(ImgboardData imgboardData) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.update("imgUpdate",imgboardData );
		mapper.commit();
		mapper.close();
		
	}

	public int getPoint(int imgIdx) {
		SqlSession mapper = sqlFactory.openSession();
		int point = mapper.selectOne("selectPointByImgIdx", imgIdx);   //첫번째는 매퍼xml id값, 두번째 인자는 파라미터
		mapper.close();
		return point;
	}

	public String getUserId(int imgIdx) {
		SqlSession mapper = sqlFactory.openSession();
		String imgUserId = mapper.selectOne("getUserId", imgIdx);   //첫번째는 매퍼xml id값, 두번째 인자는 파라미터
		mapper.close();
		return imgUserId;
	}



	
	
	
	
	
	
	
	
	
	
	
	
}
