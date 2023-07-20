package com.bs.spring.board.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.spring.board.dao.BoardDao;
import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
		return dao.selectBoardAll(session, page);
	}

	@Override
	public int selectBoardCount() {
		return dao.selectBoardCount(session);
	}

	@Override
	//@Transactional
	public int insertBoard(Board b) {
		// log.info("실행 전 : {}",b.getBoardNo());
		int result = dao.insertBoard(session, b);
		// log.info("실행 후 : {}",b.getBoardNo());
		if (result > 0) {
			if (b.getFile().size() > 0) {
				for (Attachment a : b.getFile()) {
					a.setBoardNo(b.getBoardNo());
					result += dao.insertAttachment(session, a);
				}
			}
		}
		// rollback처리를 원하면 RuntimeException 발생
		// if(result>0) throw new RuntimeException("억지발생 exception");
//		if (result != 0)
//			throw new RuntimeException("첨부파일 문제");
		if (result != b.getFile().size() + 1)
			throw new RuntimeException("첨부파일 문제");
		return result;
	}

	@Override
	public Board selectBoardByNo(int no) {
		return dao.selectBoardByNo(session, no);
	}

}
