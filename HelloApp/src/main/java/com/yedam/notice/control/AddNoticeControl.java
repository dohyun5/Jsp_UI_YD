package com.yedam.notice.control;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class AddNoticeControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		if(service.addNotice(vo)) {
			return "noticeList.do";
		}else {
			return "main.do";
		}
		
	}

}





