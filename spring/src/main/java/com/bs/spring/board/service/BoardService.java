package com.bs.spring.board.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.board.model.dto.Board;

public interface BoardService {
	List<Board> selectBoardAll(Map<String, Integer> page);

	int selectBoardCount();
	
	int insertBoard(Board b);
	
	Board selectBoardByNo(int no);
	
}
