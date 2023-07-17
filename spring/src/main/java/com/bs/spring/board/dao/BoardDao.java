package com.bs.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.board.model.dto.Board;

public interface BoardDao {

	List<Board> selectBoardAll(SqlSessionTemplate session, Map<String, Integer> page);

	int selectBoardCount(SqlSessionTemplate session);
	
	int insertBoard(SqlSessionTemplate session, Board b);

	Board selectBoardByNo(SqlSessionTemplate session, int no);
}
