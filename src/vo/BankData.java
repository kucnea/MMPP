package vo;

public class BankData {
	private String bankNo;
	private String bankPw;
	private String bankName;
	private String bankUser;
	private long bankCash;
	
	public BankData(String bankNo, String bankPw, String bankName, String bankUser) {
		super();
		this.bankNo = bankNo;
		this.bankPw = bankPw;
		this.bankName = bankName;
		this.bankUser = bankUser;
	}

	public BankData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankPw() {
		return bankPw;
	}

	public void setBankPw(String bankPw) {
		this.bankPw = bankPw;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankUser() {
		return bankUser;
	}

	public void setBankUser(String bankUser) {
		this.bankUser = bankUser;
	}

	public long getBankCash() {
		return bankCash;
	}

	public void setBankCash(long bankCash) {
		this.bankCash = bankCash;
	}
	
	
}
