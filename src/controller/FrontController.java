package controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BankDataDao;
import dao.BuyBasketDataDao;
import dao.CommentDataDao;
import dao.FreeboardDataDao;
import dao.ImgboardDataDao;
import dao.UserDataDao;
import vo.BoardList;
import vo.BuyBasketData;
import vo.CommentData;
import vo.FreeboardData;
import vo.ImgboardData;
import vo.UserData;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 마지막에 요청을 처리하는 방식 결정하는 값.
		boolean isRedirect = false;

		String url = "first.jsp"; // 기본값

		HttpSession session = request.getSession();

		String rootPath = request.getContextPath();
		String spath = request.getServletPath();
		System.out.println("request : " + spath);

		UserDataDao userDataDao = UserDataDao.getUserDataDao();
		BankDataDao bankDataDao = BankDataDao.getBankDataDao();
		FreeboardDataDao freeboardDataDao = FreeboardDataDao.getInstance();
		CommentDataDao commentDataDao = CommentDataDao.getInstance();
		ImgboardDataDao imgboardDataDao = ImgboardDataDao.getInstance(); 
		BuyBasketDataDao buyBasketDataDao = BuyBasketDataDao.getInstance(); 
		

		String path = "/var/webapps/upload";
		//이렇게 하면 톰캣밖으로 저장되는데 똑같이 불러와지는게 됐었는데 안되네
//		String path = "/img_path/";
//		String path="c:\\upload";
		int size=10*1024*1024;
		
		
		if (spath.equals("/login.do")) {

			String id = request.getParameter("userId");
			String password = request.getParameter("userPw");

			UserData userData = userDataDao.login(id, password);
			System.out.println("왜 여길 안오지?");
			request.setCharacterEncoding("utf-8");
			if (userData != null) { // 로그인 성공
				System.out.println("로그인성공");
				/* session.setAttribute("loginNec", "y"); */
				session.setAttribute("userData", UserDataDao.selectOne(userData.getUserId()));
				url = "home.do";
				isRedirect = true;
			} else {
				// 로그인 정보 불일치 : 원래는 alert
				url = "home.do?loginResult=n"; // alert처리 //**response.sendRedirect 로 갈때는 애트리뷰트X, 파라미터O
				isRedirect = true;
			}

		} else if (spath.equals("/logout.do")) {
			session.invalidate();
			
			url = "home.do";
			isRedirect = true;

		} else if (spath.equals("/join.do")) {
			request.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("utf-8");
			if(request.getParameter("check").equals("true")) {
				System.out.println("join level 1");
				UserData userData = setUserData(request, 1);
				userData.setUserAcc1(request.getParameter("userAcc"));
				userDataDao.insert(userData);
				System.out.println("check 위치"+url);
				url = rootPath + "/home.do";
				isRedirect = true;	
			}else {
				System.out.println("join level 2");
				url = "userJoin.jsp?fail=y";
				if(request.getParameter("userPhoneNumber")!=null) {
					request.setAttribute("userPhoneNumber", request.getParameter("userPhoneNumber"));
				}
				if(request.getParameter("userId")!=null) {
					request.setAttribute("userId", request.getParameter("userId"));
				}
				isRedirect = false;
			}
			
			
		} else if (spath.equals("/idCheck.do")) {
			request.setCharacterEncoding("utf-8");
			String userId = request.getParameter("userId");
			int n = UserDataDao.checkUserId(userId);
			boolean check = false;
			System.out.println("idcheck에 도착");
			if (n == 0) {
				System.out.println("idCheck level : 1");
				request.setAttribute("msg", "이것은 사용가능한 아이디다.");
				check = true;
				request.setAttribute("ch", check);
				request.setAttribute("userId", userId);
				url="userJoin.jsp?check=y";
				isRedirect = false;
			} else {
				System.out.println("idCheck level : 1");
				request.setAttribute("msg", "이것은 사용 불가능한 아이디다.");
				request.setAttribute("ch", check);
				request.setAttribute("userId", userId);
				url="userJoin.jsp?check=n";
				isRedirect = false;
			}
		} else if (spath.equals("/findId.do")) {
			request.setCharacterEncoding("UTF-8");
			
			String userId = "";
			String userPhoneNumber = "";
			UserData result = null;
			System.out.println("findId.do 입장");
			if (request.getParameter("userPhoneNumber1") == null && request.getParameter("userPhoneNumber2") == null) {
				url = "userFind.jsp";
				isRedirect = true;
			}else {
				if(request.getParameter("find_radio").equals("id_radio")) {
					userPhoneNumber = request.getParameter("userPhoneNumber1");
					
					result = userDataDao.findUser("",userPhoneNumber);
					request.setAttribute("resultId", result.getUserId());
					url="user/idFind.jsp";
					isRedirect = false;
				}else {
					userPhoneNumber = request.getParameter("userPhoneNumber2");
					userId = request.getParameter("userId");
					
					result = userDataDao.findUser(userId, userPhoneNumber);
					System.out.println("findPw 마지막의 전 단계.");	//왜 위의 아이디는 갖고오는데 얘는 못 갖고오지?
					System.out.println("findPw 마지막의 전 단계. result.getUserPw : "+result.getUserPw());
					request.setAttribute("resultPw", result.getUserPw());
					url="user/idFind.jsp";
					isRedirect = false;
				}
			}
		}else if(spath.equals("/selectGame.do")){
			if(session.getAttribute("userData")!=null) {
				
				url = "gainPoint/selectGame.jsp";
				isRedirect = true;	
			}else {
				session.setAttribute("loginState", "n");
				url = "home.do?longinState=n";
				isRedirect = true;
						
			}
			
		}else if(spath.equals("/pointCheck.do")) {
			
			UserData userData = (UserData)session.getAttribute("userData");
			
			
			if(session.getAttribute("userData")==null) {
				request.setAttribute("list", UserDataDao.rankingList());
				System.out.println(UserDataDao.rankingList());
				url = "point/pointCheck.jsp";
				isRedirect = false;	
			}else {
				String userId = userData.getUserId();
				
				request.setAttribute("list", UserDataDao.rankingList());
				request.setAttribute("myranking", UserDataDao.rankingMy(userId));
			
				System.out.println(UserDataDao.rankingList());
				url = "point/pointCheck.jsp";
				isRedirect = false;	
			}
		}else if(spath.equals("/pointExchange.do")) {
			
			UserData userData = (UserData)session.getAttribute("userData");
			
			if(session.getAttribute("userData")==null) {
				url = "home.do?longinState=n";
				isRedirect = true;	
			}else {
				String userId = userData.getUserId();
				
				url = "point/pointExchange.jsp";
				isRedirect = true;	
			}
		}else if(spath.equals("/cashToPoint.do")) {
			
			request.setCharacterEncoding("UTF-8");
			UserData userData = (UserData)session.getAttribute("userData");
			long prePoint = userData.getUserPoint();
			long chPoint = Long.parseLong(request.getParameter("insertCoin"));
			
			if(userData.getUserAcc1().equals(request.getParameter("t_accList")) && userData.getUserAcc1Pw().equals(request.getParameter("t_accPw"))) {
				userData.setUserPoint(userData.getUserPoint()+chPoint);
				userDataDao.update(userData);
				System.out.println("cashToPoint case1");
				url="point/cashToPoint.jsp?fail=n";
				isRedirect = true;
				//bank를 조건문으로 넣어서 bank 금액이 충분하면 진행 할 수 있도록.
			}else {
				System.out.println("cashToPoint case2");
				url = "point/cashToPoint.jsp?fail=y"; // 왜 안되지?
				isRedirect = true;	
			}
			System.out.println("cashToPoint case3");
			url = "point/cashToPoint.jsp?fail=n";
			isRedirect = true;	
		}else if(spath.equals("/pointToCash.do")) {
			
			request.setCharacterEncoding("UTF-8");
			UserData userData = (UserData)session.getAttribute("userData");
			long prePoint = userData.getUserPoint();
			long chPoint = Long.parseLong(request.getParameter("insertCoin"));
			System.out.println("pointToCash level 1");
			System.out.println("prePoint : "+prePoint);
			System.out.println("chPoint : "+chPoint);
			System.out.println("pointToCash level 1 end");
			if(prePoint > chPoint) {
				if(userData.getUserAcc1().equals(request.getParameter("t_accList")) && userData.getUserAcc1Pw().equals(request.getParameter("t_accPw"))) {
					System.out.println("pointToCash level 2");
					System.out.println("prePoint : "+userData.getUserPoint());
					System.out.println("chPoint : "+chPoint);
					System.out.println("result : "+ (userData.getUserPoint() - chPoint));
					userData.setUserPoint(userData.getUserPoint()-chPoint);
					userDataDao.update(userData);
					url = "point/pointToCash.jsp?fail=n";
					isRedirect = true;	
					//포인트당 0.8원
				}else {
					System.out.println("pointToCash level 3");
					url = "point/pointToCash.jsp?fail=y";
					isRedirect = true;	
				}
			}else {
				System.out.println("pointToCash level 4");
				System.out.println("chPoint가 더 크다구... prePoint : "+prePoint+"chPoint : "+chPoint);
				url = "point/pointToCash.jsp?fail2=y";
				isRedirect = true;
			}
			System.out.println("pointToCash level 5");
			url = "point/pointToCash.jsp?fail=n";
			isRedirect = true;	
		}else if(spath.equals("/freeBoardData.do")) {
			System.out.println("freeBoardData.do 입장");
			int totalcount = freeboardDataDao.getCount();
			System.out.print("totalcount : ");	
			System.out.println(totalcount);
			int currentPage = 1;  //기본값
			
			String temp=null;
			if(request.getParameter("pno")!=null) {
				temp = request.getParameter("pno");
			}else {
				temp = (String)request.getAttribute("pno");
			}
			
			if(temp != null)  //url이 listAction.jsp 이면 파라미터 null
				currentPage = Integer.parseInt(temp);
			
			BoardList blists = new BoardList(currentPage,totalcount,20);   //세번쨰 20은 페이지크기
			//실행 순간 필요한 모든 값이 초기화 ->BoardList 객체에 저장.
			
			
			//**startNo ~ endNo 까지 글 리스트 선택한 것을 BoardList list 멤버로 설정한다.
			blists.setList(freeboardDataDao.getList(blists.getStartNo(), blists.getEndNo()));
			
			request.setAttribute("blists", blists);
			url = "freeboard/freeboardData.jsp";
			isRedirect = false;	
		}else if(spath.equals("/freeboardDetail.do")) {
			int idx = 0;
			if(request.getParameter("idx")==null) {
				idx = (Integer)request.getAttribute("idx");
			}else {
				idx = Integer.parseInt(request.getParameter("idx"));
			}
			
			UserData userData = (UserData)session.getAttribute("userData");
			request.setAttribute("userData", userData);
			
			int currentPage = Integer.parseInt(request.getParameter("pno"));
			System.out.println(spath+"위치 1번.");
			int cmtcnt = commentDataDao.getCmtCnt(idx);  //현재 글의 댓글 개수 구함.- 댓글추가와 삭제시에는 값이 변경됩니다.
			commentDataDao.updateCmtCnt(cmtcnt, idx);     //위 실행결과로 테이블 업데이트
				
			System.out.println(spath+"위치 2번.");
			List<CommentData> cmtlist = commentDataDao.getCmtList(idx);
			System.out.println(spath+"위치 3번.");
			if(request.getParameter("nocnt")==null)   //댓글 입력 후 detailAction.jsp 실행할 때는 조회수 증가 안함.
				freeboardDataDao.updateCount(idx);
			System.out.println(spath+"위치 4번.");
			FreeboardData bean = freeboardDataDao.getOne(idx);
			request.setAttribute("userData", userData);
			request.setAttribute("pno", currentPage);
			request.setAttribute("bean", bean);
			request.setAttribute("cmtlist", cmtlist);
			
			request.setAttribute("cr", "\n");
			
			url = "freeboard/freeboardDetail.jsp";
			isRedirect = false;	
		}else if(spath.equals("/freeboard_insert.do")) {
			request.setCharacterEncoding("utf-8");
			UserData userData = (UserData)session.getAttribute("userData");
			
			if(session.getAttribute("userData")==null) {
				request.setAttribute("fail", "y");
				url = "freeBoardData.do";
				isRedirect = false;	
			}else {
				if(request.getParameter("subject")==null){
					request.setAttribute("userData", userData);
					url = "freeboard/freeboardInsert.jsp";
					isRedirect = true;	
				}else {
					
					int idx = 0;
					String userId = userData.getUserId();
					String subject = request.getParameter("subject");
					String content = request.getParameter("content");
					Date sysdate = new Date(System.currentTimeMillis());
					
					FreeboardData freeboardData = new FreeboardData(idx, userId, subject, content);
					freeboardData.setReadCount(0);
					freeboardData.setWdate(sysdate);
					freeboardData.setCommentCount(0);
					FreeboardDataDao.insert(freeboardData);
					url = "freeBoardData.do";
					isRedirect = false;	
				}
				
			}
		}else if(spath.equals("/commentInsert.do")) {
			
			UserData userData = null;
			request.setCharacterEncoding("utf-8");
			if((UserData)session.getAttribute("userData")==null) {
				String idx = request.getParameter("mref");
				String pno = request.getParameter("pno");
				url = "freeboardDetail.do?longinState=n&idx="+idx+"&pno="+pno;
				isRedirect = true;	
			}else {
				userData = (UserData)session.getAttribute("userData");
				int idx = Integer.parseInt(request.getParameter("mref"));
				int cmtidx=0;
				int mref = Integer.parseInt(request.getParameter("mref"));
				String userId = request.getParameter("userId_comment");
				String content = request.getParameter("content");
				Date swdate = new Date(System.currentTimeMillis());
				
				CommentData commentData = new CommentData();
				commentData.setCmtIdx(cmtidx);
				commentData.setMref(mref);
				commentData.setUserId(userId);
				commentData.setContent(content);
				commentData.setWdate(swdate);
				commentDataDao.insert(commentData);
				
				request.setAttribute("idx", idx);
				url = "freeboardDetail.do";
				isRedirect = false;	
			}
		}else if(spath.equals("/detailUpdate.do")) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			request.setCharacterEncoding("utf-8");
			System.out.println("idx : "+idx);
			String pno = request.getParameter("pno");
			UserData userData = (UserData)session.getAttribute("userData");
			FreeboardData bean = freeboardDataDao.getOne(idx);
			System.out.println("디테일 업데이트0");
			if(request.getParameter("password")==null) {
				System.out.println("디테일 업데이트1");
				request.setAttribute("pno", pno);
				request.setAttribute("bean", bean);
				url="detailUpdate.jsp";
				isRedirect = false;
			}else {
				System.out.println("password : "+request.getParameter("password"));
				System.out.println("userPw : "+userData.getUserPw());
				if(request.getParameter("password").equals(userData.getUserPw())) {
					System.out.println("디테일 업데이트2");
					String subject = request.getParameter("subject");
					String content = request.getParameter("content");
					
					bean.setSubject(subject);
					bean.setContent(content);
					FreeboardDataDao.update(bean);
					url = "freeboardDetail.do?pno="+pno+"&idx="+idx;
					isRedirect = true;	
				}
			}
		}else if(spath.equals("/detailDelete.do")) {
	
			System.out.println("idx : "+request.getParameter("idx").trim());
			int idx = Integer.parseInt(request.getParameter("idx").trim());
			int pno = Integer.parseInt(request.getParameter("pno"));
			commentDataDao.deleteFreeboard(idx);
			FreeboardDataDao.delete(idx);
			request.setAttribute("pno", pno);
			url = "freeBoardData.do";
			isRedirect = false;	
			
		}else if(spath.equals("/userDataUpdate.do")) {
			
			UserData userData = (UserData)session.getAttribute("userData");
			request.setCharacterEncoding("utf-8");
			if(userData==null) {
				url = "home.do?longinState=n";
				isRedirect = true;	
			}else {
				if(request.getParameter("userPw")==null) {
					request.setAttribute("bean", userData);
					url="user/userDataUpdate.jsp";
					isRedirect = false;
				}else {
					String userId = userData.getUserId();
					String userPw = request.getParameter("userPw");
					String userGender = request.getParameter("userGender");
					String userPhoneNumber = request.getParameter("userPhoneNumber");
					long userPoint = userData.getUserPoint();
					String userAcc1 = request.getParameter("userAcc1");
					String userAcc1Pw = request.getParameter("userAcc1Pw");
					String userAcc2 = "";
					String userAcc2Pw = "";
					String userAcc3 = "";
					String userAcc3Pw = "";
					
					System.out.println("userId : "+userId);
					
					
					if(request.getParameter("userAcc2")!=null){
						userAcc2= request.getParameter("userAcc2");
						userAcc2Pw = request.getParameter("userAcc2Pw");
					}
					if(request.getParameter("userAcc3")!=null){
						userAcc2= request.getParameter("userAcc3");
						userAcc2Pw = request.getParameter("userAcc3Pw");
					}
					
					userData = new UserData(userId, userPw, userGender, userPhoneNumber, userPoint, userAcc1, userAcc1Pw, userAcc2, userAcc2Pw, userAcc3, userAcc3Pw);
					userDataDao.userDataUpdate(userData);
					session.setAttribute("userData", UserDataDao.selectOne(userData.getUserId()));
					url="home.do";
					isRedirect = true;
				}
			}
		}else if(spath.equals("/imgboardData.do")) {
			System.out.println("imgboardData.do 입장");
			
			int imgTotalcount = imgboardDataDao.getCount();
			System.out.print("imgboardData totalcount : ");	
			System.out.println(imgTotalcount);
			int currentPage = 1;
			
			String temp=null;
			if(request.getParameter("pno")!=null) {
				temp = request.getParameter("pno");
			}else {
				temp = (String)request.getAttribute("pno");
			}
			
			if(temp != null)
				currentPage = Integer.parseInt(temp);
			
			BoardList blists = new BoardList(currentPage,imgTotalcount,20);
			
			
			blists.setList2(imgboardDataDao.getList(blists.getStartNo(), blists.getEndNo()));
			System.out.println("통과하였는가. blists의 타입은 : " + blists.getClass().getName());
			System.out.println("통과하였는가. list2의 타입은 : " + blists.getList2().getClass().getName());
			request.setAttribute("blists", blists);
			url = "imgboard/imgboardData.jsp";
			isRedirect = false;	
		}else if(spath.equals("/imgboardDetail.do")) {
			int imgIdx = 0;
			if(request.getParameter("imgIdx")==null) {
				imgIdx = (Integer)request.getAttribute("imgIdx");
			}else {
				imgIdx = Integer.parseInt(request.getParameter("imgIdx"));
			}
			
			UserData userData = (UserData)session.getAttribute("userData");
			request.setAttribute("userData", userData);
			
			int currentPage = Integer.parseInt(request.getParameter("pno"));
			System.out.println(spath+"위치 1번.");
				
			ImgboardData bean = imgboardDataDao.getOne(imgIdx);
			request.setAttribute("pno", currentPage);
			request.setAttribute("bean", bean);
			request.setAttribute("cr", "\n");
			
			url = "imgboardDetail.jsp";
			isRedirect = false;	
		} else if(spath.equals("/imgDetailDelete.do")) {
			int imgIdx = Integer.parseInt(request.getParameter("imgIdx").trim());
			int pno = Integer.parseInt(request.getParameter("pno"));
			
			ImgboardDataDao.delete(imgIdx);
			request.setAttribute("pno", pno);
			url="imgboardData.do";
			isRedirect= false;
		}else if(spath.equals("/imgboardDataInsert.do")) {
			
			UserData userData = (UserData)session.getAttribute("userData");
			request.setCharacterEncoding("utf-8");
			try {
			File upDir = new File(path);
			if(!upDir.exists()) {
				upDir.mkdirs();
			}
			MultipartRequest multi_request = new MultipartRequest(request,path,size,"UTF-8",
																		new DefaultFileRenamePolicy());
				//여기
			System.out.println("img insert level 1");
			if(session.getAttribute("userData")==null) {
				request.setAttribute("fail", "y");
				url = "imgboardData.do";
				isRedirect = false;	
			}else {
				
					System.out.println("img insert level 3");
					int imgIdx = 0;
					
					System.out.println("insert level 3 파해치지 : "+multi_request.getFilesystemName("pic"));
					String imgName = multi_request.getFilesystemName("pic");
					
					UUID uuidTemp = UUID.randomUUID();
					String imgId = uuidTemp.toString().replace("-", "");
					
					System.out.println("insert level 3 제목 : "+multi_request.getParameter("imgboardSubject"));
					String imgboardSubject = multi_request.getParameter("imgboardSubject");
					System.out.println("insert level 3 내용 : "+multi_request.getParameter("imgboardDetail"));
					String imgboardDetail = multi_request.getParameter("imgboardDetail");
					String imgUserId = userData.getUserId();
					System.out.println("insert level 3 포인트 : "+multi_request.getParameter("imgPoint"));
					int imgPoint = Integer.parseInt(multi_request.getParameter("imgPoint"));
					Date sysdate = new Date(System.currentTimeMillis());
					Date wdate = sysdate;
					System.out.print("insert level 3 date : ");
					System.out.println(wdate);
					ImgboardData imgboardData = new ImgboardData(imgIdx, imgName, imgId, imgboardSubject, imgboardDetail, imgUserId, imgPoint, wdate);
					System.out.println(imgboardData.toString());
					ImgboardDataDao.insert(imgboardData);
					System.out.println("img insert level 4");
					url = "imgboardData.do";
					isRedirect = false;	
					
				}
			}catch(Exception e) {
				System.out.println("insert Exception : "+e.getMessage());
				
				System.out.println("img insert level 2");
				request.setAttribute("userData", userData);
				url = "imgboard/imgboardInsert.jsp";
				isRedirect = true;	
			}
			
		}else if(spath.equals("/imgDetailUpdate.do")) {
			UserData userData = (UserData)session.getAttribute("userData");
			request.setCharacterEncoding("utf-8");
			try {
				
				MultipartRequest multi_request = new MultipartRequest(request,path,size,"UTF-8",
																		new DefaultFileRenamePolicy());
				
			System.out.println("imgIdx : "+request.getParameter("imgIdx"));
			
			int imgIdx = 0;
			int pno = 0;
			if(request.getParameter("imgIdx")==null) {
				imgIdx = Integer.parseInt(multi_request.getParameter("imgIdx"));
				System.out.println("multi_request pno :"+multi_request.getParameter("pno"));
				pno = Integer.parseInt(multi_request.getParameter("pno"));
			}else {
				imgIdx = Integer.parseInt(request.getParameter("imgIdx"));
				pno = Integer.parseInt(request.getParameter("pno")); 
			}
			System.out.println("imgIdx multi_request : "+multi_request.getParameter("imgIdx"));
			System.out.println("img Update level 1");
			System.out.println("userPw update level 1 state. "+request.getParameter("userPw"));
			if(session.getAttribute("userData")==null) {
				request.setAttribute("fail", "y");
				url = "imgboardData.do";
				isRedirect = false;	
			}else {
				if(request.getParameter("userPw")==null && multi_request.getParameter("userPw")==null){
					System.out.println("img Update level 2");
					request.setAttribute("userData", userData);
					System.out.println(imgIdx);
					ImgboardData bean = imgboardDataDao.getOne(imgIdx);
					request.setAttribute("imgIdx", imgIdx);
					request.setAttribute("pno", pno);
					request.setAttribute("bean", bean);
					System.out.println(bean.getImgboardSubject());
					url = "imgboardUpdate.jsp";
					isRedirect = false;	
				}else {
					if(multi_request.getParameter("userPw").equals(userData.getUserPw())) {
						System.out.println("img Update level 3"); 	//왜 안넘어오냥..
						
							
						String imgName = multi_request.getFilesystemName("pic");
						UUID uuidTemp = UUID.randomUUID();
						String imgId = uuidTemp.toString().replace("-", "");
						String imgboardSubject = multi_request.getParameter("imgboardSubject");
						String imgboardDetail = multi_request.getParameter("imgboardDetail");
						String imgUserId = userData.getUserId();
						int imgPoint = Integer.parseInt(multi_request.getParameter("imgPoint"));
						Date sysdate = new Date(System.currentTimeMillis());
						Date wdate = sysdate;
						
						ImgboardData imgboardData = new ImgboardData(imgIdx, imgName, imgId, imgboardSubject, imgboardDetail, imgUserId, imgPoint, wdate);
						
						ImgboardDataDao.update(imgboardData);
						System.out.println("img Update level 4");
						url = "imgboardData.do";
						isRedirect = false;
					}else {
						request.setAttribute("updateFail", "y");
						url = "imgboardUpdate.do";
						isRedirect = false;	
					}
						
					}
				}
			}catch(Exception e) {
				System.out.println("imgInsert 중 오류 : "+e.getMessage());
				
				int imgIdx = Integer.parseInt(request.getParameter("imgIdx"));
				int pno = Integer.parseInt(request.getParameter("pno")); 
				
				System.out.println("img Update level 2");
				request.setAttribute("userData", userData);
				System.out.println(imgIdx);
				ImgboardData bean = imgboardDataDao.getOne(imgIdx);
				request.setAttribute("imgIdx", imgIdx);
				request.setAttribute("pno", pno);
				request.setAttribute("bean", bean);
				System.out.println(bean.getImgboardSubject());
				url = "imgboardUpdate.jsp";
				isRedirect = false;	
			}
		}else if(spath.equals("/imgBuyBasketInsert.do")) {
			
			UserData userData = (UserData)session.getAttribute("userData");
			
			
			int buyIdx = 0;
			String userId = userData.getUserId();
			int imgIdx = Integer.parseInt(request.getParameter("imgIdx"));
			String imgName = request.getParameter("imgName");
			int imgPoint = Integer.parseInt(request.getParameter("imgPoint"));
			String imgSubject = request.getParameter("imgSubject");
			String imgDetail = request.getParameter("imgDetail");
			String pno = request.getParameter("pno");
			String selling = "n";
//			if(buyBasketDataDao.getcheck(imgIdx, userId)) {
//				request.setAttribute("fail", "y");
//				url="imgboardDetail.do?imgIdx="+imgIdx+"&pno="+pno;
//				isRedirect=false;
//			}else {
			BuyBasketData buyBasketData = new BuyBasketData(buyIdx, userId, imgIdx, imgName, imgPoint, imgSubject, imgDetail, selling);
			BuyBasketDataDao.insert(buyBasketData);
			
			request.setAttribute("buyBasket", "y");
			url = "imgboardDetail.do";
			isRedirect = false;
//			}

		}else if(spath.equals("/imgBuyBasket.do")) {
			
			if(session.getAttribute("userData")==null) {
				url = "home.do?longinState=n";
				isRedirect = true;	
			}else {
				UserData userData = (UserData)session.getAttribute("userData");

				String userId = userData.getUserId();
				int totalcountByUser = buyBasketDataDao.getCount(userId);
				int totalPoint = buyBasketDataDao.getTotalPoint(userId);
				long userPoint = userData.getUserPoint();
				List<BuyBasketData> list = buyBasketDataDao.getList(userId); 
				request.setAttribute("list", list);
				request.setAttribute("totalcountByUser", totalcountByUser);
				request.setAttribute("totalPoint", totalPoint);
				request.setAttribute("userPoint", userPoint);
				url = "imgboard/imgBuyBasket.jsp";
				isRedirect = false;
			}
		}else if(spath.equals("/payment.do")) {
			
			UserData userData = (UserData)session.getAttribute("userData");
			int totalPoint = 0;
			String[] list = null;
			
			if(request.getParameterValues("checkBuyBasket")==null) {
				request.setAttribute("fail", "y");
				url ="imgBuyBasket.do";
				isRedirect = false;
			}else {
				list = request.getParameterValues("checkBuyBasket");
			
				int[] buyList = new int[list.length];
				
				for (int i = 0; i < list.length; i++) {
					buyList[i] = Integer.parseInt(list[i]);
				}
				
				try{
					for (int i = 0; i < buyList.length; i++) {
						totalPoint += imgboardDataDao.getPoint(buyList[i]);	
					}
					System.out.println(totalPoint);
					System.out.println(userData.getUserPoint());
					if(userData.getUserPoint() < totalPoint) {
						request.setAttribute("buy", "0");
						url = "imgBuyBasket.do";
						isRedirect = false;	
					}else {
						userData.setUserPoint(userData.getUserPoint() - totalPoint);
						UserDataDao.buyUpdate(userData);
						
						
						for (int i = 0; i < buyList.length; i++) {
							BuyBasketDataDao.buyUpdate(userData.getUserId(), buyList[i]);
							String imgUserId = imgboardDataDao.getUserId(buyList[i]);
							int sellerPoint = userDataDao.selectSellerPoint(imgUserId);
							int imgPoint = imgboardDataDao.getPoint(buyList[i]);
							int resultPoint = sellerPoint + imgPoint;
							userDataDao.sellingImg(imgUserId, resultPoint);
							System.out.println("imgUserId : "+imgUserId);
							System.out.println("resultPoint : "+resultPoint);
						}
						
						
						
						request.setAttribute("buy", "1");
						request.setAttribute("totalProduct", list.length);
						request.setAttribute("userPoint", userData.getUserPoint());
						request.setAttribute("totalPoint", totalPoint);
						url = "imgBuyBasket.do?totalPoint="+totalPoint+"&userPoint="+userData.getUserPoint()+"&totalProduct="+list.length;
						isRedirect = false;	
					}
				}catch(Exception e){
					request.setAttribute("goneProduct", "y");
					url ="imgBuyBasket.do";
					isRedirect = false;
				}
			}
		}else if(spath.equals("/Product.do")) {
			
			UserData userData = null;
			
			
			if((UserData)session.getAttribute("userData") == null) {
				url = "home.do?longinState=n";
				isRedirect = true;	
			}else{
				userData = (UserData)session.getAttribute("userData");
				
				String userId = userData.getUserId();
				
				List<BuyBasketData> list = BuyBasketDataDao.sellingselect(userId);
				int totalcountByUser = buyBasketDataDao.sellingCount(userId);
				
				request.setAttribute("list", list);
				request.setAttribute("totalcountByUser", totalcountByUser);
				url = "imgboard/product.jsp";
				isRedirect = false;
			}
			
		}else if(spath.equals("/productDetail.do")) {
			String imgName = request.getParameter("imgName");
			request.setAttribute("imgName", imgName);
			url="imgboard/productDetail.jsp";
			isRedirect = false;
		}else if(spath.equals("/adminPage.do")) {
			UserData userData = (UserData)session.getAttribute("userData");
			if(userData.getUserId().equals("admin")) {
				url="adminPage.jsp";
				isRedirect = true;	
			}else {
				url="home.do";
				isRedirect = true;
			}
		}else if(spath.equals("/deleteUserData.do")) {
			
			String userId = request.getParameter("userId");
			System.out.println(userId);
			if((UserData)(UserDataDao.selectOne(userId))==null) {
				request.setAttribute("fail", "y");
				System.out.println("user delete level 1");
				url="adminPage1.jsp?fail=y";
				isRedirect = true;
			}else {
				UserDataDao.deleteUser(userId);
				request.setAttribute("fail", "n");
				System.out.println("user delete level 2");
				url="adminPage1.jsp?fail=n";
				isRedirect = true;
			}
		}else if(spath.equals("/updateUserData.do")) {
			
			String userId = "";
			UserData userData = null;
			request.setCharacterEncoding("utf-8");
			if(request.getParameter("check").equals("1")) {
				System.out.println("업데이트 테스트");
				userId = request.getParameter("userId");
				System.out.println(userId);
				userData = (UserData)UserDataDao.selectOne(userId);
				System.out.println(userData.getUserId());
				request.setAttribute("user", userData);
				url="adminPage2.jsp";
				isRedirect = false;
			}else if(request.getParameter("check").equals("2")) {
				userId = request.getParameter("userid");
				userData = UserDataDao.selectOne(userId);
				String exUserId = userId;
				String chUserId = request.getParameter("chUserId");
				String chUserGender = request.getParameter("chUserGender");
				String chUserPhoneNumber = request.getParameter("chUserPhoneNumber");
				long chUserPoint = Long.parseLong(request.getParameter("chUserPoint"));
				
				if(request.getParameter("chUserAcc1")!=null) {
					String chUserAcc1 = request.getParameter("chUserAcc1");
					userData.setUserAcc1(chUserAcc1);
				}
				if(request.getParameter("chUserAcc2")!=null) {
					String chUserAcc2 = request.getParameter("chUserAcc2");
					userData.setUserAcc1(chUserAcc2);
				}
				if(request.getParameter("chUserAcc3")!=null) {
					String chUserAcc3 = request.getParameter("chUserAcc3");
					userData.setUserAcc1(chUserAcc3);
				}
				
				
				userData.setUserGender(chUserGender);
				userData.setUserPhoneNumber(chUserPhoneNumber);
				userData.setUserPoint(chUserPoint);
				UserDataDao.userDataUpdate(userData);
				userData.setUserId(chUserId);
				UserDataDao.chUpdateUserId(exUserId,chUserId);
				
				url="adminPage2.jsp?fail=n";
				isRedirect = false;
				
			}
		}else if(spath.equals("/updateGallery.do")) {
			
			int imgIdx = 0;
			ImgboardData imgboardData = null;
			request.setCharacterEncoding("utf-8");
			if(request.getParameter("check").equals("1")) {
				imgIdx = Integer.parseInt(request.getParameter("imgIdx"));
				imgboardData = ImgboardDataDao.getOne(imgIdx);
				request.setAttribute("img", imgboardData);
				
				url="adminPage3.jsp";
				isRedirect = false;
			}else if(request.getParameter("check").equals("2")) {
				imgIdx = Integer.parseInt(request.getParameter("imgIdx"));
				imgboardData = ImgboardDataDao.getOne(imgIdx);
				
				imgboardData.setImgName(request.getParameter("chImgName"));
				imgboardData.setImgboardSubject(request.getParameter("chImgSubject"));
				imgboardData.setImgboardDetail(request.getParameter("chImgDetail"));
				imgboardData.setImgPoint(Integer.parseInt(request.getParameter("chImgPoint")));
				
				ImgboardDataDao.update(imgboardData);
				
				url="adminPage3.jsp?fail=n";
				isRedirect = false;
			}else if(request.getParameter("check").equals("3")) {
				imgIdx = Integer.parseInt(request.getParameter("imgIdx"));
				
				ImgboardDataDao.delete(imgIdx);
				
				url="adminPage3.jsp?del=y";
				isRedirect = false;
			}
		}else if(spath.equals("/listSearch.do")) {
			request.setCharacterEncoding("utf-8");
			System.out.println("search level 1");
			String option = request.getParameter("searchList");
			String search = request.getParameter("search");
			System.out.println("search level 2");
			int totalCount = 0;
			int currentPage = 1;
			BoardList blists = null;
			System.out.println("search level 3");
			String temp=null;
			if(request.getParameter("pno")!=null) {
				temp = request.getParameter("pno");
			}else {
				temp = (String)request.getAttribute("pno");
			}
			
			if(temp != null)  //url이 listAction.jsp 이면 파라미터 null
				currentPage = Integer.parseInt(temp);
			
			System.out.println("search level 4");
			System.out.println("option : "+option);
			System.out.println("search level 5 search : "+search);
			System.out.println("search level 5 option : "+option);
			totalCount = FreeboardDataDao.getCounSD(search, option);
			System.out.println("search level 5 totalCount : "+totalCount);
			blists = new BoardList(currentPage,totalCount,20);
			System.out.println("search level 6");
			blists.setList(FreeboardDataDao.selectSD(blists.getStartNo(), blists.getEndNo(), search, option));
			System.out.println("search level 7");
			request.setAttribute("blists", blists);
			url = "freeboard/freeboardData.jsp";
			isRedirect = false;	
		}else if(spath.equals("/selfDelete.do")) {
			
			String userId = request.getParameter("userId");
			System.out.println(userId);
			if((UserData)(UserDataDao.selectOne(userId))==null) {
				System.out.println("self delete level 1");
				url="user/userDataUpdate.jsp";
				isRedirect = true;
			}else {
				UserDataDao.deleteUser(userId);
				session.invalidate();
				System.out.println("self delete level 2");
				url="home.do";
				isRedirect = true;
			}
		}else if(spath.equals("/productDelete.do")) {
			
			UserData userData = (UserData)session.getAttribute("userData");
				String[] list = null;
				if(request.getParameterValues("checkProduct")==null) {
					request.setAttribute("fail", "y");
					url="Product.do";
					isRedirect=false;
				}else {
					list = request.getParameterValues("checkProduct");
					
					for (int i = 0; i < list.length; i++) {
						BuyBasketDataDao.sellUpdate(userData.getUserId(), list[i]);
					}
					request.setAttribute("fail", "n");
					url="Product.do";
					isRedirect=false;
				}
			
			
		}else if(spath.equals("/commentDelete.do")) {
			
			String cmtidx = request.getParameter("cmtidx");
			String pno = request.getParameter("pno");
			String mref = request.getParameter("mref");
			System.out.println("cmtidx : "+cmtidx);
			System.out.println("mref : "+mref);
			commentDataDao.commentDelete(mref, cmtidx);
			
//			request.setAttribute("idx", mref);
			url="freeboardDetail.do?idx="+mref+"&pno="+pno;
			isRedirect = true;
		}else if(spath.equals("/updateComment.do")) {
			if(request.getParameter("newContent")==null) {
				System.out.println("updateComment level 1");
				System.out.println(request.getParameter("cmtidx"));
				System.out.println(request.getParameter("userId"));
				System.out.println(request.getParameter("content"));
				request.setAttribute("cmtidx", request.getParameter("cmtidx"));
				request.setAttribute("userId", request.getParameter("userId"));
				request.setAttribute("content", request.getParameter("content"));
				url="freeboard/updateComment.jsp";
				isRedirect=false;
			}else {
				System.out.println("updateComment level 2");
				String cmtidx = request.getParameter("cmtidx");
				String userId = request.getParameter("userId");
				String newContent = request.getParameter("newContent");
				
				System.out.println(cmtidx);
				System.out.println(userId);
				System.out.println(newContent);
				
				commentDataDao.updateCmt(cmtidx, userId, newContent);
				request.setAttribute("cmtidx", cmtidx);
				request.setAttribute("userId", userId);
				request.setAttribute("content", newContent);
				url="freeboard/updateComment.jsp";
				isRedirect=false;
			}
		}else if(spath.equals("/buyBasketDelete.do")) {
			String userId = request.getParameter("userId");
			String imgIdx = request.getParameter("imgIdx");
			
			buyBasketDataDao.deleteList(userId, imgIdx);
			url="imgBuyBasket.do";
			isRedirect=true;
		}else if(spath.equals("/directBuy.do")) {
			UserData userData = (UserData)session.getAttribute("userData");
			int imgPoint = Integer.parseInt(request.getParameter("imgPoint"));
			int imgIdx = Integer.parseInt(request.getParameter("imgIdx"));
			int pno = Integer.parseInt(request.getParameter("pno"));
			
			if(userData.getUserPoint() < imgPoint) {
				request.setAttribute("buy", "0");
				url = "imgboardDetail.do?imgIdx="+imgIdx+"&pno="+pno;
				isRedirect = false;	
			}else {
				
				int buyIdx = 0;
				String userId = userData.getUserId();
				String imgName = request.getParameter("imgName");//
				String imgSubject = request.getParameter("imgboardSubject");//
				String imgDetail = request.getParameter("imgboardDetail");//
				String selling = "n";
				String imgUserId = request.getParameter("imgUserId");
				
				BuyBasketData buyBasketData = new BuyBasketData(buyIdx, userId, imgIdx, imgName, imgPoint, imgSubject, imgDetail, selling);
				BuyBasketDataDao.insert(buyBasketData);
				
				userData.setUserPoint(userData.getUserPoint() - imgPoint);
				UserDataDao.buyUpdate(userData);
				BuyBasketDataDao.buyUpdate(userData.getUserId(), imgIdx);
				
				System.out.println("imgPoint : "+imgPoint);
				int sellerPoint = userDataDao.selectSellerPoint(imgUserId);
				System.out.println("sellerPoint : "+sellerPoint);
				
				imgPoint += sellerPoint;
				System.out.println("last Point : "+imgPoint);
				userDataDao.sellingImg(imgUserId, imgPoint);
				
				request.setAttribute("buy", "1");
				url = "imgboardDetail.do?imgIdx="+imgIdx+"&pno="+pno;
				isRedirect = false;	
			}
		}
		
		
		
		
		
		
		
		
			
		
		
		
		
		
		
		
		// pageContext.forward 또는 response.sendRedirect 중에 선택.
		if (isRedirect) {
			response.sendRedirect(url); // url(요청)을 바꾸기 .변수명 url은 xxx.do
		} else {
			RequestDispatcher rd = request.getRequestDispatcher(url); // 변수명 url은 xxx.jsp
			rd.forward(request, response); // url(요청)변경없이 request 유지(전달)하고 view만 변경한다.
		}

	}

		
	

	
	
	
	UserData setUserData(HttpServletRequest request, int mode) {

		String userId = request.getParameter("userId");
		String userPw = "";
		long userPoint = 0;

		// mode 1 : 회원가입, mode 2 : 회원정보 수정, mode 3 : 포인트 획득, mode 4: c->p 포인트, mode 5 : p->c 포인트
		if (mode == 1) {
			userPw = request.getParameter("userPw");
			
		} else if (mode == 2) {

		} else if (mode == 4) {
			userPoint = Long.parseLong(request.getParameter("userPrePoint")) + Long.parseLong(request.getParameter("insertCoin"));
		} else if (mode == 5) {
			userPoint = Long.parseLong(request.getParameter("userPrePoint")) - Long.parseLong(request.getParameter("insertCoin"));
		}

		String userGender = request.getParameter("userGender");
		String userPhoneNumber = request.getParameter("userPhoneNumber");
		String userAcc = request.getParameter("userAcc");
		String userAccPw = request.getParameter("userAccPw");

		System.out.println(userId);
		System.out.println(userPw);
		System.out.println(userGender);
		System.out.println(userPhoneNumber);
		System.out.println(userPoint);
		
		UserData userData = new UserData(userId, userPw, userGender, userPhoneNumber);
		userData.setUserPoint(userPoint);
		userData.setUserAcc1(request.getParameter(userAcc));
		userData.setUserAcc1Pw(userAccPw);

		return userData;
	}

}
