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
import com.yedam.notice.domain.ReplyVO;
import com.yedam.notice.service.ReplyService;
import com.yedam.notice.service.ReplyServiceImpl;

public class ModiftReplyControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//parameter (댓글번호, 변경된 댓글내용)
		
		// update.
		String no = req.getParameter("rno");
		String reply = req.getParameter("reply");
		String replyWriter = req.getParameter("id");
		
		//boolean result = false;
		ReplyVO vo = new ReplyVO();
		vo.setReplyNo(Integer.parseInt(no));
		vo.setReply(reply);
		vo.setReplyWriter(replyWriter);
		ReplyService service = new ReplyServiceImpl();
		boolean result = service.updateReply(vo);
		
		String json = "";
		
		Map<String, Object> map = new HashMap<>();
		
		if(result) {
			vo = service.searchReply(vo.getReplyNo());
			System.out.println("바밥"+vo);
			map.put("retCode","Success");
			map.put("data", vo);
		}else {
			map.put("retCode","Fail");
		}
		Gson gson = new GsonBuilder().create();
		json = gson.toJson(map);
		return json + ".json";
	}

}
