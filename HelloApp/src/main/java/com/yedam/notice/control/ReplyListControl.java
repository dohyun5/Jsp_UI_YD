package com.yedam.notice.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.notice.domain.ReplyVO;
import com.yedam.notice.service.ReplyService;
import com.yedam.notice.service.ReplyServiceImpl;

public class ReplyListControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//json데이터 생성해서 반환(return).
		//[{"replyNo":5, "noticeNo":98,"reply":"98번댓글",.."replyDate":"2023-05-05"}..]
		String nNo = req.getParameter("nNo");
		ReplyService service = new ReplyServiceImpl();
		String json = "[";
		List<ReplyVO> list = service.getReplies(Integer.parseInt(nNo));	
		
		for(int i=0;i<list.size();i++) {
			json +="{\"replyNo\":"+list.get(i).getReplyNo()+",";
			json +="\"noticeNo\":"+list.get(i).getNoticeNo()+",";
			json +="\"reply\":\""+list.get(i).getReply()+"\",";
			json +="\"replyWriter\":\""+list.get(i).getReplyWriter()+"\",";
			json +="\"replyDate\":\""+list.get(i).getReplyDate()+"\"}";
			if(i+1 != list.size()) {
				json +=",";
			}
		}
		json += "]";
		return json+".json";
	}

}
