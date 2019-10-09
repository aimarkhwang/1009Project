package setvlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import myutils.Crawl;
import vo.IssueVO;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/issue")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// 한글 설정
		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		setAccessControlHeaders(response);
		PrintWriter out = response.getWriter();

		// 내가 만든 크롤러 부름
		ArrayList<String> list = Crawl.daumIssue();    // 크롤링을 해온 데이터 넣기
		ArrayList<IssueVO> voList = new ArrayList<>(); // json 으로 뿌리기 위한 list
		
		int i = 1;
		for (String s : list) {
			IssueVO vo = new IssueVO(); 
			vo.setRank(i++);
			vo.setTitle(s);
			voList.add(vo);
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(voList);
		out.println(json);
		
//		out.println("브라우저 화면에 출력(요청에 응답)<br>");
//		out.println("다음 실시간 순위<br>");
//		out.println("<br>");
//		int i = 1;
//		for (String s : list) {
//			out.println(i++ + ". " + s + "<br>");	
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void setAccessControlHeaders(HttpServletResponse resp) {
	      resp.setHeader("Access-Control-Allow-Origin", "*");
	      resp.setHeader("Access-Control-Allow-Methods", "GET, POST");
	      resp.setHeader("Access-Control-Max-Age", "3600");
	      resp.setHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
	  }

}
