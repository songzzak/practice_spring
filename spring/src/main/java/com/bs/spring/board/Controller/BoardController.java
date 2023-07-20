package com.bs.spring.board.Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.service.BoardService;
import com.bs.spring.common.PageFactory;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@SessionAttributes({ "loginMember" })
@Slf4j
public class BoardController {

	private BoardService service;

	@Autowired
	public BoardController(BoardService service) {
		this.service = service;
	}

	@RequestMapping("/selectBoardAll.do")
	public String selectBoardAll(Model m, @RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "5") int numPerpage) {

		List<Board> boards = service.selectBoardAll(Map.of("cPage", cPage, "numPerpage", numPerpage));
		int totalData = service.selectBoardCount();

		m.addAttribute("boards", boards);
		m.addAttribute("totalContents", totalData);
		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "selectBoardAll.do"));

		return "board/boardList";
	}

	@RequestMapping("/boardForm.do")
	public String boardForm() {
		return "board/insertBoard";
	}

	@PostMapping("/insertBoard.do")
	public String insertBoard(Board b, MultipartFile[] upFile, HttpSession session,Model m) {
		// log.info("{}", b);
		// log.info("{}", upFile);

		// 절대경로 가져오기
		String path = session.getServletContext().getRealPath("/resources/upload/board/");

		// 파일명 renamed규칙 설정
		// 직접 rename규칙을 만들어서 저장하기
		// yyyyMMdd_HHmmssSSS_랜덤값_오리지널네임
		
		//file의 갯수가 2개이상일때 리스트로 받아와서 처리
//		List<Attachment> files = new ArrayList<>(); Dto에서 List로 선언가능
		if (upFile != null) {
			for (MultipartFile mf : upFile) {
				if (!mf.isEmpty()) {
					String oriName = mf.getOriginalFilename();
					String ext = oriName.substring(oriName.lastIndexOf("."));
					Date today = new Date(System.currentTimeMillis());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
					int rdn = (int) (Math.random() * 10000) + 1;
					String rename = sdf.format(today) + "_" + rdn + ext;

					// 파일 저장하기 -> transferTo()메소드를 이용
					try {
						mf.transferTo(new File(path + rename));
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}

					Attachment file = Attachment.builder().originalFilename(oriName).renamedFilename(rename).build();
					b.getFile().add(file);
				}
			}
		}
		try {
			service.insertBoard(b);			
		}catch(RuntimeException e) {
			//등록 실패 시 upload파일에서 삭제
			for(Attachment a : b.getFile()) {
				File delFile = new File(path+a.getRenamedFilename());
				delFile.delete();
			}
			m.addAttribute("msg","글쓰기 등록 실패!");
			m.addAttribute("loc", "/board/boardForm.do");
			return "common/msg";
		}

		return "redirect:/board/selectBoardAll.do";
	}

	@RequestMapping("/boardView.do")
	public String selectBoardByNo(Model m, int no) {
		m.addAttribute("board", service.selectBoardByNo(no));
		return "board/boardView";
	}

	@RequestMapping("/filedownload")
	public void fileDown(String oriname, String rename,OutputStream out, 
			@RequestHeader(value="user-agent")String header,
			HttpSession session, HttpServletResponse res) {
		
		String path = session.getServletContext().getRealPath("/resources/upload/board/");
		File downloadFile = new File(path+rename);
		try(FileInputStream fis = new FileInputStream(downloadFile);
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedOutputStream bos = new BufferedOutputStream(out)) {
			
			boolean isMS = header.contains("Trident")||header.contains("MSIE");
			String encodeRename = "";
			if(isMS) {
				encodeRename = URLEncoder.encode(oriname,"UTF-8");
				encodeRename = encodeRename.replaceAll("\\+", "%20");
			}else {
				encodeRename = new String(oriname.getBytes("UTF-8"),"ISO-8859-1");
			}
			res.setContentType("application/octet-stream;charset=utf-8");
			res.setHeader("Content-Disposition", "attachment;filename=\""+encodeRename+"\"");
			
			int read=-1;
			while((read=bis.read())!=-1) {
				bos.write(read);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
