package vo;

public class BuyBasketData {
	private int buyIdx;
	private String userId;
	private int imgIdx;
	private String imgName;
	private int imgPoint;
	private String imgSubject;
	private String imgDetail;
	private String selling;
	
	

	public BuyBasketData(int buyIdx, String userId, int imgIdx, String imgName, int imgPoint, String imgSubject,
			String imgDetail, String selling) {
		super();
		this.buyIdx = buyIdx;
		this.userId = userId;
		this.imgIdx = imgIdx;
		this.imgName = imgName;
		this.imgPoint = imgPoint;
		this.imgSubject = imgSubject;
		this.imgDetail = imgDetail;
		this.selling = selling;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	

	public int getImgPoint() {
		return imgPoint;
	}

	public void setImgPoint(int imgPoint) {
		this.imgPoint = imgPoint;
	}


	public String getImgSubject() {
		return imgSubject;
	}

	public void setImgSubject(String imgSubject) {
		this.imgSubject = imgSubject;
	}

	public String getImgDetail() {
		return imgDetail;
	}

	public void setImgDetail(String imgDetail) {
		this.imgDetail = imgDetail;
	}

	public BuyBasketData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getBuyIdx() {
		return buyIdx;
	}

	public void setBuyIdx(int buyIdx) {
		this.buyIdx = buyIdx;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getImgIdx() {
		return imgIdx;
	}

	public void setImgIdx(int imgIdx) {
		this.imgIdx = imgIdx;
	}

	public String getSelling() {
		return selling;
	}

	public void setSelling(String selling) {
		this.selling = selling;
	}

	@Override
	public String toString() {
		return "BuyBasketData [buyIdx=" + buyIdx + ", userId=" + userId + ", imgIdx=" + imgIdx + ", imgName=" + imgName
				+ ", imgPoint=" + imgPoint + ", imgSubject=" + imgSubject + ", imgDetail=" + imgDetail + ", selling="
				+ selling + "]";
	}

	
	

	

	
	
	
}
