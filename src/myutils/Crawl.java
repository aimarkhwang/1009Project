package myutils;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import vo.MovieVO;

public class Crawl {
	public static ArrayList<String> daumIssue() {
		ArrayList<String> list = new ArrayList<>();
		
		StringBuffer sb = new StringBuffer();
		String URL = "https://www.daum.net";
		String selector = ".txt_issue a";
		
		try {
			Document doc = Jsoup.connect(URL).get();
			Elements elem = doc.select(selector);
			int i = 1 ;
			for(Element element: elem) { // 3. Elemntes 길이만큼 반복한다.
	            //System.out.println(element.text()); // 4. 원하는 요소가 출력된다.
				if (i++ % 2 == 0) {
					list.add(element.text());
				}
				if (list.size() == 10) {
					break;
				}
	        } 

//			for (String s : list) {
//				System.out.println(s);
//			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list; 

	}
	
	// 다음 영화 가져오기
	public static ArrayList<MovieVO> daumMovie() {
		ArrayList<MovieVO> list = new ArrayList<>();  // 글자 담는 리스트
		
		String URL = "https://movie.daum.net/boxoffice/weekly";
		String selector = ".info_movie";
		
		try {
			Document doc = Jsoup.connect(URL).get();
			Elements elemImg = doc.select(selector + " img");
			Elements elemA = doc.select(selector + " a");
			
			for(Element element: elemA) {
				System.out.println(element.attr("href"));
			}
			
			int i = 1; // 순위 증가값
			for(Element element: elemImg) {
				MovieVO vo = new MovieVO();
				vo.setRank(i++);
				vo.setTitle(element.attr("alt"));
				vo.setImg(element.attr("src"));
//				vo.setLink(link);
				list.add(vo);
	        } 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list; 

	}
	
}
