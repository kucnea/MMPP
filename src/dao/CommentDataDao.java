package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.CommentDataDao;

import mybatis.SqlSessionUtil;
import vo.CommentData;

public class CommentDataDao {
	SqlSessionFactory sqlFactory = SqlSessionUtil.getSessionFactory();
	
	private static CommentDataDao cdao = new CommentDataDao();
	private CommentDataDao() {}
	public static CommentDataDao getInstance() {
		return cdao;
	}
	public int getCmtCnt(int mref) {
		SqlSession mapper = sqlFactory.openSession();
		int n = mapper.selectOne("getCmtCnt", mref);
		mapper.close();
		return n;
	}
	public void updateCmtCnt(int cmtcnt, int idx) {
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("cmtcnt",cmtcnt);
		map.put("idx",idx);
		SqlSession mapper = sqlFactory.openSession();
		mapper.update("updateCmtCnt",map);
		mapper.commit();
		mapper.close();
		
	}
	public List<CommentData> getCmtList(int mref) {
		SqlSession mapper = sqlFactory.openSession();
		List<CommentData> list = mapper.selectList("getCmtList", mref);
		mapper.close();
		return list;	
	}
	public void insert(CommentData commentData) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.insert("cmtInsert", commentData);
		mapper.commit();
		mapper.close();
	}
	public void commentDelete(String mref, String cmtidx) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("mref",mref);
		map.put("cmtIdx",cmtidx);
		SqlSession mapper = sqlFactory.openSession();
		mapper.delete("commentDelete",map);
		mapper.commit();
		mapper.close();
		
	}
	public void deleteFreeboard(int idx) {
		SqlSession mapper = sqlFactory.openSession();
		mapper.delete("deleteFreeboard", idx);
		mapper.commit();
		mapper.close();
	}
	public void updateCmt(String cmtidx, String userId, String newContent) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("cmtidx",cmtidx);
		map.put("userId",userId);
		map.put("newContent", newContent);
		
		SqlSession mapper = sqlFactory.openSession();
		mapper.update("updateCmt",map);
		mapper.commit();
		mapper.close();
	}
	
}
