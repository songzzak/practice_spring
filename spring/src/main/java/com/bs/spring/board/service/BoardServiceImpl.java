package com.bs.spring.board.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.board.dao.BoardDao;
import com.bs.spring.board.model.dto.Board;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardDao dao;
	
	private SqlSessionTemplate session;
	
	@Autowired
	public BoardServiceImpl(BoardDao dao, SqlSessionTemplate session) {
		this.dao = dao;
		this.session = session;
	}

	@Override
	public List<Board> selectBoardAll(Map<String, Integer> page) {
		return dao.selectBoardAll(session,page);
	}

	@Override
	public int selectBoardCount() {
		return dao.selectBoardCount(session);
	}

	@Override
	public int insertBoard(Board b) {
		return dao.insertBoard(session, b);
	}

	@Override
	public Board selectBoardByNo(int no) {
		return dao.selectBoardByNo(session,no);
	}

}
