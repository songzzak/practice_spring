package com.bs.spring.common.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.bs.spring.common.excepion.AuthenticationException;
import com.bs.spring.member.model.dto.Member;

@Component
@Aspect
public class AuthenticationCheckAop {

	@Before("execution(* com.bs.spring.memo..*(..))")
	public void checkcheck(JoinPoint jp) {
		// 로그인 정보를 확인하여 아이디가 admin이면 통과 아니면 차단
		// spring이 제공하는 RequestContextHolder클래스를 이용해서 session값 가져오기
		HttpSession session = (HttpSession) RequestContextHolder.currentRequestAttributes()
				.resolveReference(RequestAttributes.REFERENCE_SESSION);
		Member loginMember = (Member) session.getAttribute("loginMember");
		if (loginMember == null || !loginMember.getUserId().equals("admin")) {
			//차단로직
			throw new AuthenticationException("이용 권한 부족");
		}

	}

}
