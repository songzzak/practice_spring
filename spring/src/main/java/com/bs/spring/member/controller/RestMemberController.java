package com.bs.spring.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;

@RestController
@RequestMapping("/member")
public class RestMemberController {

	private MemberService service;
	
	public RestMemberController(MemberService service) {
		this.service=service;
	}
	
	@GetMapping
//	public List<Member> selectMemberAll(){
	public ResponseEntity<List<Member>> selectMemberAll(){
		List<Member> members=service.selectMemberAll();
		ResponseEntity<List<Member>> response = new ResponseEntity<>(members, HttpStatus.BAD_REQUEST);
		return response;
	}
	
	@GetMapping("/{id}")
	public Member selectMemberById(@PathVariable("id") String id){
		return service.selectMemberById(Map.of("userId",id));
	}
	
	@PostMapping
	public int insertMember(@RequestBody Member m) {
		return service.insertMember(m);
	}
	
//	@PutMapping
//	public int updateMember(@RequestBody Member m) {
//		return service.updateMember(m);
//	}
//	
//	@DeleteMapping
//	public int deleteMember(@PathVariable("id") String id) {
//		return service.deleteMember(m);
//	}
//	
	
	//특정게시글에 댓글 가져오기
	// /board/{no}/comment/{commentno}
	
	//ResponseEntity객체 이용
}
