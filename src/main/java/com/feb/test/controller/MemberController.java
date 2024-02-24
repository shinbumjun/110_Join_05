package com.feb.test.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.feb.test.service.MemberService;
import com.feb.test.util.EmailUtil;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	private EmailUtil emailUtil; // 회원가입시 이메일 전송하기 위해서 주입
	
	/* 
	  	회원가입 페이지 : /join-page.do
	  	http://localhost:8088/spring/join-page.do
	  	
	*/
	@GetMapping("/join-page.do")
	public ModelAndView loginform() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	/*
	  	회원가입 : /sign-up.do
	   	name="memberId"    --->   member_id
	   	name="passwd"      --->   passwd
	   	name="email"       --->   email
	*/
	@PostMapping("/sign-up.do")
	public ModelAndView join(@RequestParam HashMap<String, Object> params, HttpServletRequest request) { 
		ModelAndView mv = new ModelAndView();
		
		System.out.println("memberId : " + params.get("memberId")); // sinbumjun
		System.out.println("passwd : " + params.get("passwd")); // 123
		System.out.println("email : " + params.get("email")); // sinbumjun123@naver.com
		
		// 회원가입 (insert를 하면 반환 타입은 int형이다)
		int result = memberService.join(params, request);
		System.out.println("result : " + result); // 회원가입 성공하면 1, 실패하면 0, 유효성검사 실패 -1
		
		
		if(result == 1) { // 1. 회원가입 성공 + 환영 이메일 보내기
			// 회원가입 완료시 환영 이메일 전송
			String email = (String)params.get("email");
			boolean emailOK = memberService.Welcomeemail(email, request); 
			if(emailOK) { 
				System.out.println("가입 환영 이메일 발송 완료! : " + emailOK);
			}else { // 이메일 발송 실패
				mv.addObject("resultMsg", request.getAttribute("errorMessage"));
				mv.setViewName("login");
				return mv;
			}
			mv.addObject("resultMsg", "회원가입 성공");
		}else if(result == -1){ // -1. 유효성검사 실패(해당 에러 메시지가 담겨져 있음)
			mv.addObject("resultMsg", request.getAttribute("errorMessage")); // result는 에러 메시지
		}else { // 0. 회원가입 실패(또 다른 에러시)
			mv.addObject("resultMsg", "회원가입 실패 관리자에 문의해 주세요");
		}

		mv.setViewName("login");
		return mv;
	}
	
	/* 
		로그인 : /login.do
	   	name="memberId"    --->   member_id
	   	name="passwd"      --->   passwd

	*/
	@PostMapping("/login.do")
	public ModelAndView login(@RequestParam HashMap<String, Object> params, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("로그인 아이디 : " + params.get("memberId")); // sinbumjun
		System.out.println("비밀번호 아이디 : " + params.get("passwd")); // 123
		
		boolean result = memberService.login(params, request); // true 또는 false 
		System.out.println("result1111111111111111 : " + result);
		
		if(result) { // result가 true면 로그인 성공 false면 로그인 실패
			mv.addObject("resultMsg", "로그 성공");
			mv.addObject("resultCode", "loginOk");
			mv.setViewName("home"); // 로그인 성공하면 home.jsp
			return mv;
		}else {
			mv.addObject("resultMsg", "로그 실패");
			mv.addObject("resultCode", "loginFail");
			mv.setViewName("login"); // 로그인 실패하면 login.jsp
		}
		return mv;
	}
}









