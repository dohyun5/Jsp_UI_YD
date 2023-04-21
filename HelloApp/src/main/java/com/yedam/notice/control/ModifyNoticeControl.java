package com.yedam.notice.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class ModifyNoticeControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get방식
		if (req.getMethod().equals("GET")) {

			String nNo = req.getParameter("nNo");
			NoticeService service = new NoticeServiceImpl();
			//NoticeVO vo = service.getNotice(Integer.parseInt(nNo));
			req.setAttribute("noticeInfo", service.getNotice(Integer.parseInt(nNo)));
			
			return "notice/noticeModify.tiles";
						
		}
		//post방식
		else if (req.getMethod().equals("POST")) {
			String nNo = req.getParameter("nNo");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String writer = req.getParameter("writer");
			String page = req.getParameter("pageNum");
			System.out.println(nNo);
			NoticeVO vo = new NoticeVO();
			vo.setNoticeNo(Integer.parseInt(nNo));
			vo.setNoticeTitle(title);
			vo.setNoticeContent(content);
			vo.setNoticeWriter(writer);
			req.setAttribute("pageNum", page);
			NoticeService service = new NoticeServiceImpl();
			service.modifyNotice(vo);
//			if() {
//				return "noticeList.do";
//			}else {
//				return "main.do";
//			}
		}
		
		return "noticeList.do";
	}

}
