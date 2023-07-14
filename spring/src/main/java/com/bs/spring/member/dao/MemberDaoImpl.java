package com.bs.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.member.model.dto.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public int insertMember(SqlSessionTemplate session, Member m) {
		return session.insert("member.insertMember",m);
	}

	@Override
	public List<Member> selectMemberAll(SqlSessionTemplate session) {
		return session.selectList("member.selectMemberAll");
	}

	@Override
	public Member selectMemberById(SqlSessionTemplate session, Map<String, String> param) {
		return session.selectOne("member.selectMemberById", param);
	}

}
