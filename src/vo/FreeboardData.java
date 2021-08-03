package vo;

import java.util.Date;

public class FreeboardData {
	private int idx;
	private String userId;
	private String subject;
	private String content;
	private int readCount;
	private Date wdate;
	private int commentCount;
	

	

	public FreeboardData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FreeboardData(int idx, String userId, String subject, String content) {
		super();
		this.idx = idx;
		this.userId = userId;
		this.subject = subject;
		this.content = content;
	}

	public FreeboardData(int idx, String userId, String subject, String content, int readCount, Date wdate,
			int commentCount) {
		super();
		this.idx = idx;
		this.userId = userId;
		this.subject = subject;
		this.content = content;
		this.readCount = readCount;
		this.wdate = wdate;
		this.commentCount = commentCount;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public Date getWdate() {
		return wdate;
	}

	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}


	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	@Override
	public String toString() {
		return "Freeboard [idx=" + idx + ", userId=" + userId + ", subject=" + subject
				+ ", content=" + content + ", readCount=" + readCount + ", wdate=" + wdate 
				+ ", commentCount=" + commentCount + "]";
	}
	
	
	
}
