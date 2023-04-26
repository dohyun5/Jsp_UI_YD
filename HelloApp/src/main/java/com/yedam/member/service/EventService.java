package com.yedam.member.service;

import java.util.List;

import com.yedam.member.domain.EventVO;

public interface EventService {
	//이벤트 목록
	public List<EventVO> events();
	//이벤트 생성
	public boolean addEvent(EventVO event);
	//이벤트 삭제
	public boolean removeEvent(EventVO event);
	
	
	
}
