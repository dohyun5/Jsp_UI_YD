package com.yedam.notice.mapper;

import java.util.List;

import com.yedam.notice.domain.ReplyVO;

public interface ReplyMapper {
	public List<ReplyVO> replyList(int noticeNo);
	//댓글 등록
	public int insertReply(ReplyVO vo);
	public int deleteReply(int replyNo);
	public int updateReply(ReplyVO vo);
	//public int updateReply(@Param("replyNo") int replyId, @Param("reply") String reply);
	public ReplyVO searchReply(int replyNo);
	
	
}
