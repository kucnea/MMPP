package vo;

import java.util.Date;

public class CommentData {
	private int cmtIdx;
	private int mref;
	private String userId;
	private String content;
	private Date wdate;
	
	
	public CommentData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	public int getCmtIdx() {
		return cmtIdx;
	}

	public void setCmtIdx(int cmtIdx) {
		this.cmtIdx = cmtIdx;
	}

	public int getMref() {
		return mref;
	}
	public void setMref(int mref) {
		this.mref = mref;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	
	@Override
	public String toString() {
		return "CommentData [cmtIdx=" + cmtIdx + ", mref=" + mref + ", userId=" + userId
				+ ", content=" + content + ", wdate=" + wdate + "]";
	}
	
	
}
