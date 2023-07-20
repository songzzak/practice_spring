package com.bs.spring.ajax.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ajax")
@Slf4j
public class AjaxController {

	@Autowired(required = false)
	private MemberService memberservice;
	
	
	@GetMapping("/basicTest.do")
	public void basic(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Board b = Board.builder()
				.boardTitle("냉무")
				.boardContent("냉무")
				.build();
//		res.setContentType("text/csv;charset=utf-8");
//		res.getWriter().write("test");
		ObjectMapper mapper = new ObjectMapper();
		res.setContentType("application/json;charset=utf-8");
		res.getWriter().write(mapper.writeValueAsString(b));
	}
	
	//리턴값에 반환할 객체를 선언
	//@ResponseBody -> json으로 반환할 수 있게 처리하는 어노테이션
	@GetMapping("/converter")
	@ResponseBody
	public Board convertTest() {
		return Board.builder()
				.boardTitle("converter")
				.boardContent("testing")
				.build();
	}
	
	@GetMapping("/checkId.do")
	@ResponseBody
	public Member checkId(@RequestParam Map param) {
		log.info("{}",param);
		return  memberservice.selectMemberById(param);
	}
	
	@GetMapping("/basic2")
	public String basic2() {
		return "demo/demo";
	}
	
	@GetMapping("/memberList")
	@ResponseBody
	public List<Member> memberList() {
		return  memberservice.selectMemberAll(); 
	}
	
	@PostMapping("/insertData.do")
	@ResponseBody
	public Member insertData(@RequestBody Member m) {
		log.info("{}",m);
		return m;
	}
	
	//REST API, RESTful - > session/cookie 관리 하지말자 (stateless!!)
	//web에서 url주소를 설계할 때 설계하는 내용에 대해서 주소만 보고도 기능을 짐작할 수 있게
	//메소드 방식에 따라서 아키텍처를 쓰자
	//->복잡도 개선
	//URL을 설정할 때 간편하게 서비스를 알아볼 수 있는 방식으로 구현하자
	//URL주소를 설정할 때 행위에 대한 표현을 빼자 -> method를 보고 결정하자
	//method?
	// GET : Data를 조회하는 서비스는 GET방식으로 처리
	// POST : Data를 저장하는 서비스는 POST방식으로 처리
	// PUT : Data를 수정
	// DELETE : Data를 삭제
	//URL주소는 명사로 작성한다
	//ex) 회원관리 서비스 
	// 1. GET localhost:9090/spring/member -> 전체회원 조회
	// 1-1. GET localhost:9090/spring/member/no||id -> 회원 1명 조회
	// 2. POST localhost:9090/spring/member -> 회원 추가
	// 3. PUT localhost:9090/spring/member -> 회원 수정
	// 4. DELETE  localhost:9090/spring/member -> 회원 탈퇴
	
	
	
	
	
	
}
