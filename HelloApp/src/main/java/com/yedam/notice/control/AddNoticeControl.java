package com.yedam.notice.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class AddNoticeControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String job = req.getParameter("job");
		job = job == null ? "multi" : "ajax";
		
		
		if(job.equals("ajax")) {
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String writer = req.getParameter("writer");
			String attach = req.getParameter("attach"); //input:file 인 경우 
			//사용자 입력값을 NoticeVO입력.
			NoticeVO vo = new NoticeVO();
			vo.setAttachFile(attach);
			vo.setNoticeTitle(title);
			vo.setNoticeContent(content);
			vo.setNoticeWriter(writer);
			
			NoticeService service = new NoticeServiceImpl();
			//정상처리 ->목록으로 이동 / 정상처리가 되지 않으면 main으로이동
			// map(key,value) => {retCode:Success, retVal:vo}
			// map => {retCode:Fail, retVal:null}
			Map<String, Object> map = new HashMap<>();
			
			Gson gson = new GsonBuilder().create();
			
			if(service.addNotice(vo)) {
				map.put("retCode", "Success");
				map.put("retVal", vo);
				//return "Success.json";
			}else {
				map.put("retCode", "Fail");
				map.put("retVal", "알 수 없는 에러 발생");
				//return "Fail.json";
			}
			return gson.toJson(map)+".json"; //객체 => json문자열
			
			
		}else {
			
		
		//파일업로드 /db입력처리/ 목록이동.
		//멀티파트요청 : 요청정보(req), 저장경로, 최대파일사이즈, 인코딩(한글파일), 리네임정책인스턴스(기존파일 삭제하지 않는것)
		String saveDir = req.getServletContext().getRealPath("images");
		int maxSize = 5*1024*1024; //5mb
		String encoding = "UTF-8";
		DefaultFileRenamePolicy rn = new DefaultFileRenamePolicy();
		MultipartRequest multi
		= new MultipartRequest(req, saveDir, maxSize, encoding, rn);
		
//		Enumeration<?> enu = multi.getFileNames();
//		while(enu.hasMoreElements()) {
//			String file = (String) enu.nextElement();
//			System.out.println("file :" + file);
//		}
		
		String writer = multi.getParameter("writer");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String attach = multi.getFilesystemName("attach");//변경된 이름으로 넣어줘야 하기때문
		//사용자 입력값을 NoticeVO입력.
		NoticeVO vo = new NoticeVO();
		vo.setAttachFile(attach);
		vo.setNoticeTitle(title);
		vo.setNoticeContent(content);
		vo.setNoticeWriter(writer);
		
		NoticeService service = new NoticeServiceImpl();
		//정상처리 ->목록으로 이동 / 정상처리가 되지 않으면 main으로이동
//		if(service.addNotice(vo)) {
//			return "noticeList.do";
//		}else {
//			return "main.do";
//		}
		
		Map<String, Object> map = new HashMap<>();
		
		Gson gson = new GsonBuilder().create();
		
		if(service.addNotice(vo)) {
			map.put("retCode", "Success");
			map.put("retVal", vo);
			//return "Success.json";
		}else {
			map.put("retCode", "Fail");
			map.put("retVal", "알 수 없는 에러 발생");
			//return "Fail.json";
		}
		return gson.toJson(map)+".json"; //객체 => json문자열
		
		
		
		}
	}

}





