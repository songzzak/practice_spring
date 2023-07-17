package com.bs.spring.memo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bs.spring.memo.model.dto.Memo;
import com.bs.spring.memo.service.MemoService;

@Controller
@RequestMapping("/memo")
@SessionAttributes({ "loginMember" })
public class MemoController {

	// @Autowired
	private MemoService service;

	@Autowired // 3버전 이하는 선언필요
	public MemoController(MemoService service) {
		this.service = service;
	}

//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

	@RequestMapping("/selectMemoAll.do")
	public String selectMemo(Model m) {
		List<Memo> memoList = service.selectMemoAll();
		m.addAttribute("memoList", memoList);
		return "memo/memo";
	}

	@RequestMapping(value = "/insertMemo.do", method = RequestMethod.POST)
	public String insertMemo(Memo memo, Model m) {
		int result = service.insertMemo(memo);
		m.addAttribute("msg", result > 0 ? "memo등록 성공!" : "memo등록 실패...");
		m.addAttribute("loc", "/memo/selectMemoAll.do");
		return "common/msg";
	}
}
