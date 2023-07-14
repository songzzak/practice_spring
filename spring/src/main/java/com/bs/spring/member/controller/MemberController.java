package com.bs.spring.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@SessionAttributes({"loginMember"})
@Slf4j
public class MemberController {
	
	@Autowired(required = false)
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/enrollMember.do")
	public String insertMember() {
		return "member/enrollMember";
	}
	
	@RequestMapping(value = "/insertMember.do" , method = RequestMethod.POST)
	public String insertEndMember(Member member, Model model) {
		//System.out.println(member);
		
		//회원가입 시 패스워드 암호화 처리
		String oriPassword=member.getPassword();//입력받은 패스워드
//		System.out.println(oriPassword);
		log.debug(oriPassword);
		String encodePassword=passwordEncoder.encode(oriPassword);//암호화처리 
//		System.out.println(encodePassword);
		log.debug(encodePassword);
		member.setPassword(encodePassword);//암호화 패스워드로 객체에 저장
		
		int result=service.insertMember(member);
		model.addAttribute("msg", result>0?"회원가입 성공":"회원가입 실패");
		model.addAttribute("loc","/");
		return "common/msg";
	}

	@RequestMapping(value = "/login.do" , method = RequestMethod.POST)
	public String login(@RequestParam Map<String, String> param, Model model) {
		Member m=service.selectMemberById(param);
		//System.out.println(m);
		
		//암호화된 값을 비교하기 위해서 BCriptPasswordEncoder가 제공하는 메소드를 이용
		if(m!=null&&passwordEncoder.matches((String)param.get("password"), m.getPassword())) {
			//로그인 성공
			//HttpSession객체가 아닌 Model을 사용해서 세션에 저장
			//선언부에 @SessionAttibutes 어노테이션 선언필요
			model.addAttribute("loginMember", m);
		}else {
			//로그인 실패
			model.addAttribute("msg", "로그인 실패하였습니다.");
			model.addAttribute("loc", "/");
			return "common/msg";
		}
		return "redirect:/";
	}
	
	@RequestMapping("/logout.do")
	//public String logout(HttpSession session) {
		//@SessionAttibutes로 등록된 내용 삭제하기 위해서 SessionStatus객체 이용
	public String logout(SessionStatus status) {
//		if(session!=null)
//		session.invalidate();
		if(!status.isComplete())
			status.setComplete();
		return "redirect:/";
	}
	
	@RequestMapping("/mypage.do")
//	public String mypage(String id,Model m) {
//		m.addAttribute("member",
//				service.selectMemberById(Map.of("userId",id)));
	public String myPage() {
		return "member/mypage";
		
	}
	
//	@RequestMapping("")
//	public String selectMemberAll() {
//		return "";
//	}
}
