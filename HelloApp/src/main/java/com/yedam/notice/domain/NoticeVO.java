package com.yedam.notice.domain;

import java.util.Date;

import lombok.Data;

@Data

public class NoticeVO {
	private int noticeNo;
	private String noticeWriter;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeDate;
	private int hitCount;
	private String attachFile;
	
}
