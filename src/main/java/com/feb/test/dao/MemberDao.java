package com.feb.test.dao;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.feb.test.dto.Member;

@Repository
public interface MemberDao {

	// 회원가입
	int join(HashMap<String, Object> params);

	// 로그인 중복 확인
	int loginchick(String memberId);

	// 회원가입 성공하면 환영 이메일 발송 (이메일 존재하는지 확인)
	int Welcomeemail(String email);

	// 로그인
	Member login(String memberId);
}