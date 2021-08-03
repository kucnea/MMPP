package vo;

public class UserData {
	private String userId;
	private String userPw;
	private String userGender;
	private String userPhoneNumber;
	private long userPoint;
	private String userAcc1;
	private String userAcc1Pw;
	private String userAcc2;
	private String userAcc2Pw;
	private String userAcc3;
	private String userAcc3Pw;
	
	public UserData() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	public UserData(long userPoint) {
		super();
		this.userPoint = userPoint;
	}




	public UserData(String userId, String userPw, String userGender, String userPhoneNumber) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userGender = userGender;
		this.userPhoneNumber = userPhoneNumber;
		this.userPoint = 0;
	}




	

	public UserData(String userId, String userPw, String userGender, String userPhoneNumber, long userPoint,
			String userAcc1, String userAcc1Pw, String userAcc2, String userAcc2Pw, String userAcc3,
			String userAcc3Pw) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userGender = userGender;
		this.userPhoneNumber = userPhoneNumber;
		this.userPoint = userPoint;
		this.userAcc1 = userAcc1;
		this.userAcc1Pw = userAcc1Pw;
		this.userAcc2 = userAcc2;
		this.userAcc2Pw = userAcc2Pw;
		this.userAcc3 = userAcc3;
		this.userAcc3Pw = userAcc3Pw;
	}




	public String getUserAcc1Pw() {
		return userAcc1Pw;
	}




	public void setUserAcc1Pw(String userAcc1Pw) {
		this.userAcc1Pw = userAcc1Pw;
	}




	public String getUserAcc2Pw() {
		return userAcc2Pw;
	}




	public void setUserAcc2Pw(String userAcc2Pw) {
		this.userAcc2Pw = userAcc2Pw;
	}




	public String getUserAcc3Pw() {
		return userAcc3Pw;
	}




	public void setUserAcc3Pw(String userAcc3Pw) {
		this.userAcc3Pw = userAcc3Pw;
	}




	@Override
	public String toString() {
		return "UserData [userId=" + userId + ", userPw=" + userPw + ", userGender=" + userGender + ", userPhoneNumber="
				+ userPhoneNumber + ", userPoint=" + userPoint + ", userAcc1=" + userAcc1 + ", userAcc2=" + userAcc2
				+ ", userAcc3=" + userAcc3 + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public long getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(long userPoint) {
		this.userPoint = userPoint;
	}

	public String getUserAcc1() {
		return userAcc1;
	}

	public void setUserAcc1(String userAcc1) {
		this.userAcc1 = userAcc1;
	}

	public String getUserAcc2() {
		return userAcc2;
	}

	public void setUserAcc2(String userAcc2) {
		this.userAcc2 = userAcc2;
	}

	public String getUserAcc3() {
		return userAcc3;
	}

	public void setUserAcc3(String userAcc3) {
		this.userAcc3 = userAcc3;
	}
	
	
}
