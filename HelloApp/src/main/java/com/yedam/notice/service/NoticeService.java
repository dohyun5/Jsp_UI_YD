package com.yedam.notice.service;

import java.util.List;

import com.yedam.notice.domain.NoticeVO;

public interface NoticeService {
	// 기본적인 CRUD(create,read,update,delete) //선언!
	
	public List<NoticeVO> noticeList();
	public boolean addNotice(NoticeVO vo);
	public boolean modiftNotice(NoticeVO vo);
	public boolean removeNotice(int noticeNo);
	public NoticeVO getNotice(int noticeNo);
	
	
}