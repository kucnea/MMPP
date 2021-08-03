package dao;

import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlSessionUtil;

public class BankDataDao {

	static SqlSessionFactory sqlFactory = SqlSessionUtil.getSessionFactory();
	
	private static BankDataDao bankDataDao = new BankDataDao();
	
	public BankDataDao() {
	}
	
	public static BankDataDao getBankDataDao() {
		return bankDataDao;
	}
	
	
	
}
