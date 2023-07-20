package com.bs.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Override
	public List<Board> selectBoardAll(SqlSessionTemplate session, Map<String, Integer> page) {
		int cPage = page.get("cPage");
		int numPerpage = page.get("numPerpage");
		RowBounds rb = new RowBounds((cPage-1)*numPerpage,numPerpage);
		return session.selectList("board.selectBoardAll",null, rb);
	}

	@Override
	public int selectBoardCount(SqlSessionTemplate session) {
		return session.selectOne("board.selectBoardCount");
	}

	@Override
	public int insertBoard(SqlSessionTemplate session, Board b) {
		return session.insert("board.insertBoard",b);
	}

	@Override
	public Board selectBoardByNo(SqlSessionTemplate session, int no) {
		return session.selectOne("board.selectBoardByNo",no);
	}

	@Override
	public int insertAttachment(SqlSessionTemplate session, Attachment file) {
		return session.insert("board.insertAttachment", file);
	}

}
