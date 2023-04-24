package com.yedam.notice.service;

import java.util.List;

import com.yedam.notice.domain.ReplyVO;

public interface ReplyService {
	public List<ReplyVO> getReplies(int noticeNo);
	public boolean addReply(ReplyVO vo);
	public boolean delReply(ReplyVO vo);
	
	
}
