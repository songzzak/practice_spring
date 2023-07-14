package com.bs.spring.common.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.Controller.DemoController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//반환형이 true면 매핑메소드 실행, false면 실행X
		log.debug("----------Interceptor preHandle 실행-----------");
		log.debug(request.getRequestURI());
		
		Map params = request.getParameterMap();
		for(Object key : params.keySet()) {
			System.out.println(key);
		}
		log.debug("--------------------------------------------------------");
		//response.sendRedirect(request.getContextPath()); 계속 메인으로 이동
		//handler -> 실행되는 controller클래스, 실행되는 메소드 확인
		HandlerMethod hm = (HandlerMethod)handler;
		log.debug("{}",hm.getBean());
		DemoController demo = (DemoController)hm.getBean();
		
		log.debug("{}",hm.getMethod());
		Method m = hm.getMethod();
//		m.invoke(m, null)
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.debug("----------Interceptor postHandle 실행-----------");
		log.debug("{}",modelAndView.getViewName());
		
		Map modelData = modelAndView.getModel();
		log.debug("{}",modelData);
		log.debug("--------------------------------------------------------");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.debug("----------Interceptor afterCompletion 실행-----------");
		log.debug("요청주소 {} : ",request.getRequestURI());
		log.debug("에러메세지 {} : ",ex!=null?ex.getMessage():"응답성공");
		log.debug("--------------------------------------------------------");
	}
	
	
}
