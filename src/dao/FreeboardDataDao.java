package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import vo.FreeboardData;

import dao.FreeboardDataDao;
import mybatis.SqlSessionUtil;

public class FreeboardDataDao {
	
	
	static SqlSessionFactory sqlFactory = SqlSessionUtil.getSessionFactory();
	
	private static FreeboardDataDao fdao = new FreeboardDataDao();
	
	private FreeboardDataDao() { }
	
	public static FreeboardDataDao getInstance() {
		return fdao;
	}

	public int getCount() {
		SqlSession mapper = sqlFactory.openSession();
		int cnt = mapper.selectOne("getCount");  
		mapper.close();
		return cnt;
	}
	
	public List<FreeboardData> getList(int starNo,int endNo){
		List<FreeboardData> list;
		SqlSession mapper = sqlFactory.openSession();
		
		//HashMap 객체에 2개 이상의 타입이 동일한 변수값을 저장할 수 있다.
		Map<String,Integer> map= new HashMap<>();
		map.put("startNo", starNo);   //key,value 한 쌍으로 저장
		map.put("endNo", endNo);
		
		list=mapper.selectList("getList",map);   //id를 getList2로 바꿔서도 실행해보세요.
		mapper.close();
		return list;
	}

	public void updateCount(int idx) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.update("updateCount", idx);
		mapper.commit();
		mapper.close();
	}

	public FreeboardData getOne(int idx) {
		SqlSession mapper = sqlFactory.openSession();
		FreeboardData bean = mapper.selectOne("selectByIdx", idx);   //첫번째는 매퍼xml id값, 두번째 인자는 파라미터
		mapper.close();
		return bean;
	}

	public static void insert(FreeboardData freeboardData) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.selectOne("insert", freeboardData);   //첫번째는 매퍼xml id값, 두번째 인자는 파라미터
		mapper.close();
	}

	public static void update(FreeboardData bean) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.update("update",bean );
		mapper.commit();
		mapper.close();
		
	}

	public static void delete(int idx) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.delete("delete",idx );
		mapper.commit();
		mapper.close();
	}

	public static int getCounSD(String search, String option) {
		SqlSession mapper = sqlFactory.openSession();

		Map<String,String> map= new HashMap<>();
		map.put("option", option);
		map.put("search", search);
		
		
		int cnt = mapper.selectOne("getCountSD",map);
		mapper.close();
		return cnt;
	}
	

	public static List<FreeboardData> selectSD(int startNo, int endNo, String search, String option) {
		List<FreeboardData> list;
		SqlSession mapper = sqlFactory.openSession();
		
		String start = startNo+"";
		String end = endNo+"";
		
		
		Map<String,String> map= new HashMap<>();
		map.put("startNo", start);   //key,value 한 쌍으로 저장
		map.put("endNo", end);
		map.put("search", search);
		map.put("option", option);
		
		
		list=mapper.selectList("getListSD",map);   //id를 getList2로 바꿔서도 실행해보세요.
		mapper.close();
		return list;
	}

}
