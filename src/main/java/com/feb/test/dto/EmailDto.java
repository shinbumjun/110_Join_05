package com.feb.test.dto;

public class EmailDto {
	  private String from; // 이메일을 발신하는 사람의 이메일 주소
	  private String receiver; // 이메일을 수신하는 사람의 이메일 주소
	  private String text; // 이메일의 본문 내용
	  private String subject; // 이메일의 제목
	  
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	} 
}