package com.bs.spring.demo.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.model.dto.Demo;
import com.bs.spring.demo.service.DemoService;


@Controller
public class DemoController {
	
	private Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	private DemoService service;
	
	@RequestMapping("/demo/demo.do")
	public String demo() {
		return "demo/demo";
	}

	// 메소드선언하기
	// 리턴값, 매개변수 알아보기
	// 1. 반환형
	// 1) String : ViewResilver에 의해서 view화면을 출력 *기본적으로 많이 사용
	// 2) void : HttpServletResponse객체로 직접 응답메세지를 작성할 때 사용 *doGet메소드와 비슷한 형식
	// 3) modelAndView : 화면에 전달할 데이터와 view내용을 저장하는 객체 *spring이 제공
	// 4) Class : 일반객체를 반환 / json으로 데이터를 반환할 때, Restful하게 서버를 구성했을 때 많이 사용
	// *ResponseEntity<클래스타입>

	// 2. 매개변수 타입
	// 1) HttpServletRequest : 서블릿처럼 사용 가능
	// 2) HttpServletResponse : 서블릿처럼 사용 가능
	// 3) HttpSession : session값을 가져와서 대입
	// 4) java.util.Locale : 서버의 로케일정보를 저장한 객체
	// 5) InputStream/Reader : 파일 읽어올 때 사용하는 stream
	// 6) OutputStream/Writer : 파일을 보낼 때 사용하는 stream
	// 7) 기본 자료형 변수 :
	// 클라이언트가 보낸 parameter데이터==선언한 변수 이름? 자동으로 매핑:@RequestParam어노테이션을 이용해서 연결
	// @RequestParam은 매핑, 기본값 설정, 필수여부 설정
	// 8) Class : Command라고 한다 / parameter데이터를 필드에 넣어서 객체를 전달
	// parameter 이름과 필드명이 같은 데이터를 대입해준다
	// 9) java.util.Map : @RequestParam 어노테이션이랑 같이 사용, parameter값을 map방식으로 사용
	// 10) @RequestHeader(name값)와 기본자료형을 작성하면 header정보를 받을 수 있다
	// 11) @CookieValue(name값)와 기본자료형을 작성하면 cookie에 저장된 값을 받을 수 있다
	// 12) Model : request와 비슷하게 데이터를 key:value 형식으로 저장할 수 있는 객체
	// 13) modelAndView : model과 view를 동시에 저장하는 객체

	// 메소드 어노테이션
	// @ResponseBody : Rest방식으로 클래스를 json으로 전송할때
	// @RequestBody : json방식으로 전송된 parameter를 클래스로 받을 때 사용
	// @GetMapping, @PostMapping, @DeleteMapping...

	// 서블릿방식으로 매핑메소드 이용
	@RequestMapping("/demo/demo1.do")
	public void demo1(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		logger.debug("request : {}",request);
		logger.debug("response : {}",response);
		// System.out.println(request);
		// System.out.println(response);
		String devName = request.getParameter("devName");
		int devAge = Integer.parseInt(request.getParameter("devAge"));
		String devGender = request.getParameter("devGender");
		String devEmail = request.getParameter("devEmail");
		String[] devLang = request.getParameterValues("devLang");
//		System.out.println(devName + " " + devAge + " " + devGender + " " + devEmail);
//		for (String l : devLang) {
//			System.out.println(l);
//		}
		logger.debug(devName + " " + devAge + " " + devGender + " " + devEmail);
		for (String l : devLang) {
			logger.debug(l);
		}
		Demo d = Demo.builder().devName(devName).devAge(devAge).devGender(devGender).devEmail(devEmail).devLang(devLang)
				.build();
		request.setAttribute("demo", d);
		request.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp").forward(request, response);
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out=response.getWriter();
//		out.print(devName+" "+devAge+" "+devGender+" "+devEmail);
	}

	// 1:1로 매칭하여 데이터 받기
	// 매핑메소드의 매개변수에 파라미터로 전송되는 name과 동일한 이름의 변수를 선언
	// 매개변수의 타입은 사용할 타입으로 설정 *변경이 가능해야 한다

	@RequestMapping("/demo/demo2.do")
	public String demo2(String devName, int devAge, String devGender, String devEmail, String[] devLang, Model model) {

//		System.out.println(devName+" "+devAge+" "+devGender+" "+devEmail);
//		for(String l : devLang) {
//			System.out.println(l);
//		}

		// spring에서 데이터 전송하는 객체를 제공 -> Model
		// Model에 데이터를 저장 -> model.addAttribute("key":value);
		Demo d = Demo.builder().devName(devName).devAge(devAge).devGender(devGender).devEmail(devEmail).devLang(devLang)
				.build();
		model.addAttribute("demo", d);

		return "/demo/demoResult";
	}

	// 파라미터 데이터를 받을 때 @RequestParam어노테이션을 이용해서 옵션 설정 가능
	@RequestMapping("/demo/demo3.do")
	public String requestParamUse(@RequestParam(value = "devName") String name,
			@RequestParam(value = "devAge", defaultValue = "3") int age,
			@RequestParam(value = "devGender") String gender, @RequestParam(required = false) String devEmail,
			String[] devLang, Model model) {

		Demo d = Demo.builder().devName(name).devAge(age).devGender(gender).devEmail(devEmail).devLang(devLang).build();
		model.addAttribute("demo", d);

		return "/demo/demoResult";
	}

	// DTO/VO객체로 직접 parameter값 받아오기
	// 매개변수로 전달되는 parameter이름과 동일한 필드를 갖는 객체를 선언한다
	// *클래스 타입 주의 / Date형은 java.sql.Date로 import
	@RequestMapping("/demo/demo4.do")
	public String commandMapping(Demo demo, Model m) {
		System.out.println(demo);

		m.addAttribute("demo", demo);
		return "/demo/demoResult";
	}

	// Map으로 parameter데이터 받아오기
	// @RequestParam 어노테이션 설정 후 Map으로 받아옴
	@RequestMapping("/demo/demo5.do")
	public String mapMapping(@RequestParam Map param, String[] devLang, Model m) {
		System.out.println(param);
		param.put("devLang", devLang);
		m.addAttribute("demo", param);
		return "/demo/demoResult";
	}

	// 추가데이터 받아오기
	// Cookie Header Session
	// Cookie : @CookieValue(value="key", required = ) String data
	// Header : @RequestHeader(value="name")String header
	// Session : @SessionAttribute(value="key") Strnig id
	@RequestMapping("demo/demo6.do")
	public String extraData(
			@CookieValue(value = "testData", required = false, defaultValue = "rest-time") String data,
			@RequestHeader(value = "User-agent") String userAgent,
			@SessionAttribute(value = "sessionId") String sessionId,
			@RequestHeader(value = "Referer")String referer) {
		System.out.println("cookie : " + data);
		System.out.println("header : " + userAgent);
		System.out.println("session : " + sessionId);
		System.out.println("Referer : " + referer);
		return "index";
	}
	
	//modelAndView객체를 이용해서 반환
	@RequestMapping("/demo/demo7.do")
	public ModelAndView modelAndViewReturn(Demo d, ModelAndView mv) {
		//ModelAndView는 model설정과 view설정을 같이 할 수 있는 객체
		//view : setViewName()메소드를 이용
		//model : addObject("key",value)메소드 이용해서 저장
		mv.addObject("demo",d);
		mv.setViewName("/demo/demoResult");
		
		return mv;
	}
	
	//자료형에 대해 반환하기 -> Data만 응답할때 사용 -> jackson 라이브러리 사용
	//메소드 선언부 @ResponseBody어노테이션 사용
	//Restful메소드를 구현했을때 사용
	@RequestMapping("demo/demo8.do")
	@ResponseBody
	public String datareturn(){
		return "가나다라마바사아자차";
	}
	
	//Request요청 메소드(get, post)를 필터링하기
	//@RequestMapping (value="url주소" method=RequestMethod.GET||RequestMethod.POST)
//	@RequestMapping(value="demo/demo9.do", method = RequestMethod.POST)
	@PostMapping("demo/demo9.do")
	public String methodCheck(Demo d, Model m) {
		m.addAttribute("demo", d);
		return "/demo/demoResult";
	}
	//간편하게 사용할 수 있게 Mapping어노테이션을 지원
//	@GetMapping
//	@PostMapping
//	@DeleteMapping
//	@PutMapping
		
	
	//mapping주소를 설정할때 중괄호 {}를 사용할 수 있다
	// /board/boardview?no=1 (권장X)
	// /board/1 method=GET
	@GetMapping("/board/{no}")
	public String searchBoard(@PathVariable(value = "no") int number) {
		return null;
	}
	
	@RequestMapping(value =  "demo/insertDemo.do", method = RequestMethod.POST)
	public String insertDemo(Demo demo,Model m) {
		int result=service.insertDemo(demo);
		//System.out.println(demo);
		m.addAttribute("msg",result>0?"저장성공":"저장실패");
		m.addAttribute("loc", "/demo/demo.do");
		//sendRedirect로 변경하는 방법
		//prefix redirect:요청할주소(매핑주소)
		return "common/msg";
				/*+ "demo/demo";*/
	}
	
	@RequestMapping("demo/selectDemoAll.do")
	public ModelAndView selectDemoAll(ModelAndView mv) {
		List<Demo> demoList=service.selectDemoAll();
		mv.addObject("demoList",demoList);
		mv.setViewName("/demo/demoList");
		return mv;
	}
	
	@RequestMapping("demo/updateDemo.do")
	public String updateDemo(Demo d,Model m) {
		m.addAttribute("demo", d);
		return "/demo/demoUpdate";
	}
}