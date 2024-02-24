package com.feb.test.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.feb.test.dao.MemberDao;
import com.feb.test.dto.EmailDto;
import com.feb.test.dto.Member;
import com.feb.test.util.DuplicateUserIdException;
import com.feb.test.util.EmailUtil;
import com.feb.test.util.InvalidUserIdException;
import com.feb.test.util.Sha512Encoder;


@Service
public class MemberService {
	
	private static final int MAX_MEMBERID_LENGTH = 18; // 최대 길이
	private static final int MIN_MEMBERID_LENGTH = 6; // 최소 길이
	
	@Autowired
	MemberDao memberDao;

	@Autowired
	private EmailUtil emailUtil;
//	public void setEmailUtil(EmailUtil emailUtil) {
//		this.emailUtil = emailUtil;
//	}
	
	public int join(HashMap<String, Object> params, HttpServletRequest request) {
		
		System.out.println("memberId22 : " + params.get("memberId")); // sinbumjun
		System.out.println("passwd22 : " + params.get("passwd")); // 123
		System.out.println("email22 : " + params.get("email")); // sinbumjun123@naver.com
		
		// *비밀번호 암호화
		Sha512Encoder encoder = Sha512Encoder.getInstance(); // 1. SHA-512 암호화를 위한 인스턴스를 얻는다
		System.out.println("encoder2 : " + encoder); // com.feb.test.util.Sha512Encoder@7c3a98fc
		
		String passwd = (String)params.get("passwd"); // 2. 브라우저에서 입력한 비밀번호
		System.out.println("passwd2 : " + passwd); // 123
		
		String encodeTxt = encoder.getSecurePassword(passwd); // 3. 비밀번호 암호화
		System.out.println("encodeTxt2 : " + encodeTxt); // 3c9909afec25354d551dae21590...
		
		params.put("passwd", encodeTxt); // 4. 암호화한 패쓰워드로 저장
		
		// 1. 회원ID 6자 이하 가입 불가, ID 중복 확인
		try {
			// 1. 회원ID 6자 이하 가입 불가 (Object값으로 받아 왔기 떄문에 String타입으로 형변환)
			System.out.println("아이디가 몇 글자인지 확인 : " + ((String)params.get("memberId")).length()); // 9
		    if (!(MIN_MEMBERID_LENGTH <= ((String)params.get("memberId")).length() && ((String)params.get("memberId")).length() <= MAX_MEMBERID_LENGTH)) {
		        throw new InvalidUserIdException("회원 ID가 6자 이상으로 가입해주세요");
		    }
		    // 2. ID 중복 확인
		    String memberId = (String)params.get("memberId");
		    if(!Loginchick(memberId)) { // ID가 중복일 경우 !(false) -> true 
		    	throw new DuplicateUserIdException("회원 ID가 중복입니다");
		    }
			
		}catch(InvalidUserIdException  e1){ // 회원 ID가 6자 이하인 경우 발생
			request.setAttribute("errorMessage", e1.getMessage());
			return -1;
		} catch (DuplicateUserIdException e2) { // 회원 ID가 이미 데이터베이스에 존재하는 경우
			request.setAttribute("errorMessage", e2.getMessage());
			return -1;
		}
		return memberDao.join(params);
	}

	// ID 중복 확인을 위한 메서드
	private boolean Loginchick(String memberId) {
		int count = memberDao.loginchick(memberId); // 0 보다 클경우 중복
		return count == 0; // 0이면 ID중복이 아니다 -> true
	}

	// 회원가입 완료시 환영 이메일 전송
	public boolean Welcomeemail(String email, HttpServletRequest request) {
		System.out.println("email2222222222 : " + email);
		int result = memberDao.Welcomeemail(email); // 가입한 email이 있으면 1 반환 -> true
		System.out.println("result2222222222222222 : " + result);
		if(result == 1) {
			EmailDto emailDto = new EmailDto();
			emailDto.setFrom("sinbumjun123@naver.com"); // 이메일을 발신하는 사람의 이메일 주소
			emailDto.setReceiver(email); // 이메일을 수신하는 사람의 이메일 주소, 회원가입한 이메일로 메일 발송
			emailDto.setText("이메일의 본문 내용"); // 이메일의 본문 내용
			emailDto.setSubject("가입을 축하합니다!!!"); // 이메일의 제목
			System.out.println("emailDto3333333333333333333 : " + emailDto.toString());
			
			try {
				// 이메일 발송 실패 시 예외처리 
				emailUtil.sendMail(emailDto);
			}catch (Exception e){
				request.setAttribute("errorMessage", "이메일 발송에 실패헸습니다");
				return false;
			}
		}
		return result==1;
	}

	// 로그인 확인
	public boolean login(HashMap<String, Object> params, HttpServletRequest request) {
		
			// 1. id비교
			String memberId = (String)params.get("memberId"); // 브라우저에서 입력한 값
			// tip : select시에 값이 없으면 null인데 JDBC는 tye-catch문으로 했었는데 mybatis에서는 if문으로 처리 가능하다
			Member member = memberDao.login(memberId); // select문 출력하기 때문에 Member타입이 필요하다
			System.out.println("member11111111111111111 : " + member); 
			// Member [memberIdx=2044, memberId=sinbumjun, passwd=3c9909afec25354d551... 
			
			// 바꼈다 원래는 없을경우 exception이 떴는데 mybatis는 그냥 null을 리턴해준다 
			// 서로 mybatis springjdbc랑 보는 객체가 1개일 때에 대한 처리기준이 서로 다르다   
			// if (member == null) {
			if(ObjectUtils.isEmpty(member)) { // null이면 true 반환
				return false;
			}
			
			// 2. 비밀번호 비교
			String passwd = (String)params.get("passwd"); // 사용자가 입력한 비밀번호
			String memberPw = member.getPasswd(); // DB에 저장되어 있는 암호화 된 비밀번호 
			Sha512Encoder encoder = Sha512Encoder.getInstance();
			
			String encodeTxt = encoder.getSecurePassword(passwd); // 사용자가 입력한 값을 암호화한 거다 
					
			return StringUtils.pathEquals(memberPw, encodeTxt); // 비교해서 틀리면 false반환
			
	}
}








