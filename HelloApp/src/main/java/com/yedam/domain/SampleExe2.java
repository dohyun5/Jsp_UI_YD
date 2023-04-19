package com.yedam.domain;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.notice.mapper.NoticeMapper;
import com.yedam.notice.domain.NoticeVO;

public class SampleExe2 {
	public static void main(String[] args) {
		SqlSessionFactory sqlSessionFactory = com.yedam.common.DataSource.getInstance();
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
			
			NoticeVO nvo = new NoticeVO();
			nvo.setNoticeNo(6);
			nvo.setNoticeWriter("11user00");
			nvo.setNoticeTitle("변경영번째 글");
			nvo.setNoticeContent("변경영번째글 내용입니다");
			
			//mapper.insertNotice(nvo);
			//System.out.println(nvo);
			
			//mapper.updateNotice(nvo);
			//System.out.println(nvo);
			
			//mapper.deleteNotice(4);
			//System.out.println(nvo);

			//System.out.println(mapper.searchNotice(3));
			
			
			for(NoticeVO vo : mapper.noticeList()) {
				System.out.println(vo);
			}
			
//			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
//			// Employee emp = mapper.getEmp(355); //단건조회
//			Employee empl = new Employee();
//			empl.setEmployeeId(322);
//			empl.setLastName("do2d2o");
//			empl.setFirstName("d2a2da");
//			empl.setEmail("do1121@sad.com");
//			empl.setJobId("IT_PROG");
//			
//			mapper.addEmp(empl);
//			session.commit();//commit해줘야 반영이 됨. 위에 openSession에 true넣거나 커밋 작성.
//			
//			Employee emp = mapper.getEmp(322);
//			System.out.println(emp);
		}

	}
}
