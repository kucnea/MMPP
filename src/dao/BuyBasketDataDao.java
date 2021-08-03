package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlSessionUtil;
import vo.BuyBasketData;

public class BuyBasketDataDao {

	static SqlSessionFactory sqlFactory = SqlSessionUtil.getSessionFactory();
	
	private static BuyBasketDataDao bdao = new BuyBasketDataDao();

	private BuyBasketDataDao() { }
	
	public static BuyBasketDataDao getInstance() {
		return bdao;
	}

	public static void insert(BuyBasketData buyBasketData) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.selectOne("basketInsert", buyBasketData);
		mapper.close();
	}

	public int getCount(String userId) {
		SqlSession mapper = sqlFactory.openSession();
		int cnt = mapper.selectOne("buyBasketCountByUser",userId);  
		mapper.close();
		return cnt;
	}

	public List<BuyBasketData> getList(String userId) {
		SqlSession mapper = sqlFactory.openSession();
		List<BuyBasketData> list = mapper.selectList("getBasketList", userId);
		mapper.close();
		return list;	
	}

	public static void buyUpdate(String userId, int imgIdx) {
		SqlSession mapper = sqlFactory.openSession();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userId",userId);
		map.put("imgIdx",imgIdx+"");
		
		mapper.update("buyBasketUpdate", map);
		mapper.commit();
		mapper.close();
	}

	public static List<BuyBasketData> sellingselect(String userId) {
		SqlSession mapper = sqlFactory.openSession();
		List<BuyBasketData> list = mapper.selectList("sellingList", userId);
		mapper.close();
		return list;
	}

	public int sellingCount(String userId) {
		SqlSession mapper = sqlFactory.openSession();
		int cnt = mapper.selectOne("sellingCount",userId);  
		mapper.close();
		return cnt;
	}

	public static void sellUpdate(String userId, String imgIdx) {
		SqlSession mapper = sqlFactory.openSession();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userId",userId);
		map.put("imgIdx",imgIdx);
		
		mapper.update("sellBasketUpdate", map);
		mapper.commit();
		mapper.close();
		
	}

	public boolean getcheck(int imgIdx, String userId) {
		SqlSession mapper = sqlFactory.openSession();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userId",userId);
		map.put("imgIdx",imgIdx+"");
		
		List<BuyBasketData> list = mapper.selectList("getcheck", map);
		mapper.close();
		boolean chk = false;
		if(list != null) {
			chk = true;
		}else {
			chk = false;
		}
		return chk;
	}

	public void deleteList(String userId, String imgIdx) {
		SqlSession mapper = sqlFactory.openSession();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userId",userId);
		map.put("imgIdx",imgIdx);
		
		mapper.delete("deleteList", map);
		mapper.commit();
		mapper.close();
	}

	public int getTotalPoint(String userId) {
		SqlSession mapper = sqlFactory.openSession();
		int cnt = 0;
		if(mapper.selectOne("totalPoint",userId)==null) {
			cnt = 0;
		}else {
			cnt = mapper.selectOne("totalPoint",userId);
		}
		mapper.close();
		return cnt;
	}
	
	
	
	
}
