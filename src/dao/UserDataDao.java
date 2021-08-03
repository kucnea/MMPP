package dao;

import java.util.HashMap;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import mybatis.SqlSessionUtil;
import vo.UserData;

public class UserDataDao {

	static SqlSessionFactory sqlFactory = SqlSessionUtil.getSessionFactory();
	
	private static UserDataDao userDataDao = new UserDataDao();
	
	public UserDataDao() {
	}

	public static UserDataDao getUserDataDao() {
		return userDataDao;
	}


	
	

	public UserData login(String id,String password) {
		UserData userdata = null;
		SqlSession mapper = sqlFactory.openSession();
		
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id",id);  
		map.put("password", password);
		
		System.out.println("map 출력 : " + map.get("id")+"," + map.get("password"));
		userdata = mapper.selectOne("login",map);
		mapper.close();				//요 로그인은 머지
		return userdata;
	}
	
	
	public int checkId(String id) {
		SqlSession mapper = sqlFactory.openSession();
		int n=mapper.selectOne("checkId",id);	//얘는 왜 인트로 받을 수 있는거지?
		
		mapper.close();
		return n;
	}

	public void insert(UserData userData) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.insert("addUserData",userData);  //insert 쿼리에 필요한 parameterType 전달 --> 2번째 인수
		mapper.commit();   //insert ok 적용
		mapper.close();
		
		
	}

	public static int checkUserId(String userId) {
		SqlSession mapper = sqlFactory.openSession();
		int n=mapper.selectOne("checkUserId",userId);
		
		mapper.close();
		return n;
		
	}

	public UserData findUser(String id, String userPhoneNumber) {
		SqlSession mapper = sqlFactory.openSession();
		UserData userData = null;
		
		
		HashMap<String, String> map = new HashMap<String, String>();
		System.out.println("find경로의 id:"+id);
		map.put("userId",id);  
		map.put("userPhoneNumber", userPhoneNumber);
		
		
		if(id=="") {
			userData = mapper.selectOne("findId",map);
		}else {
			userData = mapper.selectOne("findPw",map);
		}
		
		mapper.close();
		return userData;
	}

	public static Object selectList() {
		SqlSession mapper = sqlFactory.openSession();

		List<UserData> list = null;
		list = mapper.selectList("selectAll");   //sql문 실행결과와 자동 mapping
		
		mapper.close();   //
		return list;
	}

	public static Object rankingList() {
		SqlSession mapper = sqlFactory.openSession();

		List<UserData> list = null;
		list = mapper.selectList("selectRank");   //sql문 실행결과와 자동 mapping
		
		
		System.out.println("랭킹처리완료");
		mapper.close();   //
		return list;
	}

	public static int rankingMy(String userId) {
		SqlSession mapper = sqlFactory.openSession();
		System.out.println("나의 랭킹"+userId);
		int n = mapper.selectOne("selectRankMine", userId);
		
		System.out.println("나의랭킹처리완료");
		mapper.close();   //
		return n;
	}

	public void update(UserData userData) {
		SqlSession mapper = sqlFactory.openSession();
		System.out.println("포인트 업데이트"+userData.getUserPoint());
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userId",userData.getUserId());
		map.put("userPoint",userData.getUserPoint()+"");
		
		mapper.update("cashToPoint",map);
		mapper.commit();
		mapper.close();
	}

	public static void userDataUpdate(UserData userData) {
		SqlSession mapper = sqlFactory.openSession();
		
		mapper.update("userDataUpdate",userData);
		mapper.commit();
		mapper.close();
	}

	public static void buyUpdate(UserData userData) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.update("buyUpdate",userData);
		mapper.commit();
		mapper.close();
		
	}

	public static UserData selectOne(String userId) {
		SqlSession mapper = sqlFactory.openSession();
		UserData userData = mapper.selectOne("selectOneUser",userId);   //sql문 실행결과와 자동 mapping
		mapper.close();
		return userData;
	}

	public static void deleteUser(String userId) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.delete("deleteUser",userId);
		mapper.commit();
		mapper.close();
	}

	public static void chUpdateUserId(String exUserId, String chUserId) {
		SqlSession mapper = sqlFactory.openSession();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("exUserId",exUserId);
		map.put("chUserId",chUserId);
		
		mapper.update("UserUpdate",map);
		mapper.commit();
		mapper.close();
		
	}

	public void sellingImg(String imgUserId, int imgPoint) {
		SqlSession mapper = sqlFactory.openSession();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("imgUserId",imgUserId);
		map.put("imgPoint",imgPoint+"");
		
		mapper.update("sellingImg",map);
		mapper.commit();
		mapper.close();
	}

	public int selectSellerPoint(String imgUserId) {
		SqlSession mapper = sqlFactory.openSession();
		int point = mapper.selectOne("selectSellerPoint", imgUserId);   //sql문 실행결과와 자동 mapping
		mapper.close();
		return point;
	}

	
	
	
	
	
	
}
