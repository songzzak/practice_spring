package com.bs.spring.board.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.service.BoardService;
import com.bs.spring.common.PageFactory;
import com.bs.spring.member.model.dto.Member;

@Controller
@RequestMapping("/board")
@SessionAttributes({ "loginMember" })
public class BoardController {

	private BoardService service;

	@Autowired
	public BoardController(BoardService service) {
		this.service = service;
	}


	@RequestMapping("/selectBoardAll.do")
	public String selectBoardAll(Model m, @RequestParam(value = "cPage", defaultValue = "1" ) int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "5")int numPerpage) {
		
		List<Board> boards = service.selectBoardAll(Map.of("cPage",cPage,"numPerpage",numPerpage));
		int totalData = service.selectBoardCount();
		
		m.addAttribute("boards", boards);
		m.addAttribute("totalContents", totalData);
		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "selectBoardAll.do"));
		
		return "board/boardList";
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(Model m) {
		if(m==null) {
			m.addAttribute("msg", "로그인 후 이용가능합니다.");
			m.addAttribute("loc", "/");
			return "common/msg";
		}
		return "board/insertBoard";
		
	}
	
	@RequestMapping("/boardView.do")
	public String selectBoardByNo(Model m, int no) {
		m.addAttribute("board",service.selectBoardByNo(no));
		return "board/boardView";
	}
	
}
