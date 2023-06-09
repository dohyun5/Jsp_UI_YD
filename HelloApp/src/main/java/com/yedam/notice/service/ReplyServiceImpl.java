package com.yedam.notice.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.notice.domain.ReplyVO;
import com.yedam.notice.mapper.ReplyMapper;

public class ReplyServiceImpl implements ReplyService{
	
	SqlSession session = DataSource.getInstance().openSession(true);
	ReplyMapper mapper = session.getMapper(ReplyMapper.class);
	
	
	@Override
	public List<ReplyVO> getReplies(int noticeNo) {
		
		return mapper.replyList(noticeNo);
	}


	@Override
	public boolean addReply(ReplyVO vo) {
		
		return mapper.insertReply(vo) == 1;
	}


	@Override
	public boolean delReply(int replyNo) {
		return mapper.deleteReply(replyNo) == 1;
	}


	@Override
	public boolean updateReply(ReplyVO vo) {
		return mapper.updateReply(vo) == 1;
	}


	@Override
	public ReplyVO searchReply(int replyNo) {
		return mapper.searchReply(replyNo);
	}


	
	
}
