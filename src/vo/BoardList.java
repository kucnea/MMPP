package vo;

import java.util.List;


public class BoardList {
	private int currentPage;
	private int totalCount;	 
	private int pageSize;	
	
	private int totalPage;
	private int startNo;    
	private int endNo;   		
	private int startPage;  
	private int endPage;    
	
	private List<FreeboardData> list;
	private List<ImgboardData> list2;
	
	public BoardList(int currentPage,int totalCount,int pageSize) {
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		
		totalPage = (totalCount - 1) / pageSize + 1;
		this.currentPage 
			= currentPage > totalPage ? totalPage:currentPage;
		this.currentPage 
			= currentPage < 1 ? 1:currentPage;   
			
		//글목록 범위 계산	
		startNo = (this.currentPage - 1) * pageSize +1 ;  
		endNo = startNo + pageSize -1 ;  					
		
		//페이지목록 범위 계산									 
		startPage = (this.currentPage - 1) / 10 * 10 + 1;    
		endPage = startPage + 9;							
		endPage = endPage > totalPage ? totalPage : endPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public List<FreeboardData> getList() {
		return list;
	}

	public void setList(List<FreeboardData> list) {
		this.list = list;
	}

	public List<ImgboardData> getList2() {
		return list2;
	}
	
	public void setList2(List<ImgboardData> list2) {
		this.list2 = list2;
		
	}
	
	@Override
	public String toString() {
		return "BoardList [currentPage=" + currentPage + ", totalCount=" + totalCount + ", pageSize=" + pageSize
				+ ", totalPage=" + totalPage + ", startNo=" + startNo + ", endNo=" + endNo + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", list=" + list + "]";
	}

	
	

}
