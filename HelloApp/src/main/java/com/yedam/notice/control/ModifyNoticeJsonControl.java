package com.yedam.notice.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class ModifyNoticeJsonControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//no, title, content 받아와서 값을 변경.
		String no = req.getParameter("no");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		NoticeVO vo = new NoticeVO();
		vo.setNoticeNo(Integer.parseInt(no));
		vo.setNoticeTitle(title);
		vo.setNoticeContent(content);
		
		//no를 기준으로 한건 변경된 값을 조회 후 return 으로 반환.
		NoticeService service = new NoticeServiceImpl();
		Map<String, Object> map = new HashMap<>();
		Gson gson = new GsonBuilder().create();
		
		if(service.modifyNotice(vo)) {
			map.put("retCode","Success");
			map.put("retVal",service.getNotice(Integer.parseInt(no)));
		}else {
			map.put("retCode", "Fail");
			map.put("retVal", null);
		}
		
		
		
		return gson.toJson(map)+".json";
	}

}
