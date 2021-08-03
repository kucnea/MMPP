package vo;

import java.util.Date;

public class ImgboardData {
	private int imgIdx;
	private String imgName;
	private String imgId;
	private String imgboardSubject;
	private String imgboardDetail;
	private String imgUserId;
	private int imgPoint;
	private Date wdate;
	
	

	

	public ImgboardData(int imgIdx, String imgName, String imgId, String imgboardSubject, String imgboardDetail,
			String imgUserId, int imgPoint, Date wdate) {
		super();
		this.imgIdx = imgIdx;
		this.imgName = imgName;
		this.imgId = imgId;
		this.imgboardSubject = imgboardSubject;
		this.imgboardDetail = imgboardDetail;
		this.imgUserId = imgUserId;
		this.imgPoint = imgPoint;
		this.wdate = wdate;
	}

	public ImgboardData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getImgIdx() {
		return imgIdx;
	}

	public void setImgIdx(int imgIdx) {
		this.imgIdx = imgIdx;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public String getImgboardSubject() {
		return imgboardSubject;
	}

	public void setImgboardSubject(String imgboardSubject) {
		this.imgboardSubject = imgboardSubject;
	}

	public String getImgboardDetail() {
		return imgboardDetail;
	}

	public void setImgboardDetail(String imgboardDetail) {
		this.imgboardDetail = imgboardDetail;
	}

	public String getImgUserId() {
		return imgUserId;
	}

	public void setImgUserId(String imgUserId) {
		this.imgUserId = imgUserId;
	}

	public long getImgPoint() {
		return imgPoint;
	}

	public void setImgPoint(int imgPoint) {
		this.imgPoint = imgPoint;
	}

	public Date getWdate() {
		return wdate;
	}

	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}

	
	@Override
	public String toString() {
		return "ImgboardData [imgIdx=" + imgIdx + ", imgName=" + imgName + ", imgId=" + imgId
				+ ", imgboardSubject=" + imgboardSubject + ", imgboardDetail=" + imgboardDetail + ", imgUserId="
				+ imgUserId + ", imgPoint=" + imgPoint + ", wdate=" + wdate + "]";
	}

	

	
	
}
