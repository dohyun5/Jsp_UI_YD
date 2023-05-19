package com.yedam.notice.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.notice.mapper.NoticeMapper;
import com.yedam.notice.domain.NoticeVO;

public class NoticeServiceImpl implements NoticeService { //인터페이스 구현
	
	SqlSession session = DataSource.getInstance().openSession(true);
	NoticeMapper mapper = session.getMapper(NoticeMapper.class);
	
	@Override
	public List<NoticeVO> noticeList(int page) {
		
		//return mapper.noticeList();
		return mapper.noticeWithPage(page);
	}
	@Override
	public List<NoticeVO> noticeListJson() {
		return mapper.noticeList();
	}

	@Override
	public boolean addNotice(NoticeVO vo) {
		
		return mapper.insertNotice(vo)==1;
	}

	@Override
	public boolean modifyNotice(NoticeVO vo) {
		
		return mapper.updateNotice(vo)==1;
	}

	@Override
	public boolean removeNotice(int noticeNo) {
		
		return mapper.deleteNotice(noticeNo)==1;
	}

	@Override
	public NoticeVO getNotice(int noticeNo) {
		//조회수 증가.
		mapper.updateCount(noticeNo);
		return mapper.searchNotice(noticeNo);
	}
	@Override
	public int totalCount() {
		return mapper.getCount();
	}
	@Override
	public boolean modifyNoticeFile(NoticeVO vo) {
		return mapper.updateNoticeFile(vo) == 1;
	}

	
}
