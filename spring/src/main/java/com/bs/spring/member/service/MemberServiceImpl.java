package com.bs.spring.member.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.member.dao.MemberDao;
import com.bs.spring.member.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao dao;
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public int insertMember(Member m) {
		return dao.insertMember(session, m);
	}

	@Override
	public List<Member> selectMemberAll() {
		return dao.selectMemberAll(session);
	}

	@Override
	public Member selectMemberById(Map<String, String> param) {
		return dao.selectMemberById(session, param);
	}

}
