package com.yedam.notice.domain;

import java.util.Date;

import lombok.Data;


@Data
public class ReplyVO {
	private int replyNo;
	private int noticeNo;
	private String reply;
	private String replyWriter;
	private Date replyDate;
}
