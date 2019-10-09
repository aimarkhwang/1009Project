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
import vo.MovieVO;

/**
 * Servlet implementation class MovieServlet
 */
@WebServlet("/movie")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 설정
		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 내가 만든 크롤러 부름
		ArrayList<MovieVO> list = Crawl.daumMovie();    // 크롤링을 해온 데이터 넣기
		ArrayList<IssueVO> voList = new ArrayList<>(); // json 으로 뿌리기 위한 list
		
//		int i = 1;
//		for (String s : list) {
//			IssueVO vo = new IssueVO(); 
//			vo.setRank(i++);
//			vo.setTitle(s);
//			voList.add(vo);
//		}
//		
//		Gson gson = new Gson();
//		String json = gson.toJson(voList);
//		out.println(json);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
