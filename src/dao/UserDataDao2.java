package dao;

import java.util.HashMap;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import mybatis.SqlSessionUtil;
import vo.UserData;

public class UserDataDao2 {

	static SqlSessionFactory sqlFactory = SqlSessionUtil.getSessionFactory();
	
	private static UserDataDao2 userDataDao = new UserDataDao2();
	
	public UserDataDao2() {
	}

	public static UserDataDao2 getUserDataDao() {
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

	public static Object randkingList() {
		SqlSession mapper = sqlFactory.openSession();

		List<UserData> list = null;
		list = mapper.selectList("selectAll");   //sql문 실행결과와 자동 mapping
		
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < i; j++) {
				if(list.get(i).getUserPoint()>list.get(j).getUserPoint()) {
					UserData temp;
					temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		System.out.println("랭킹처리완료");
		mapper.close();   //
		return list;
	}

	
	
	
	
	
	
}
