package com.feb.test.dto;

import org.apache.ibatis.type.Alias;

public class Member {
	
	Integer memberIdx;
	private String memberId;
	private String passwd;
	private String nickname;
	private String membername;
	private String email;
	private String joindtm;
	
	public Member() {} // 기본 생성자

	public Integer getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(Integer memberIdx) {
		this.memberIdx = memberIdx;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJoindtm() {
		return joindtm;
	}

	public void setJoindtm(String joindtm) {
		this.joindtm = joindtm;
	}

	@Override
	public String toString() {
		return "Member [memberIdx=" + memberIdx + ", memberId=" + memberId + ", passwd=" + passwd + ", nickname="
				+ nickname + ", membername=" + membername + ", email=" + email + ", joindtm=" + joindtm + "]";
	}
}