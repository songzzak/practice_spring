package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class AnnoLoggerAspect {

	//pointcut설정
	@Pointcut("within(com.bs.spring.member..*)")
	public void loggerTest() {
		
	}
	
	//advisor설정
	@Before(value = "loggerTest()")
	public void loggerBefore(JoinPoint jp) {
		log.debug("------------annotation aop before-----------");
		
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		
		log.debug("-------------------------------------------");
	}
	
	@Pointcut("execution(* com.bs.spring.memo..*(..))")
	public void memoLogger() {
		
	}
	@After("memoLogger()")
	public void afterLogger(JoinPoint jp) {
		log.debug("------------annotation aop after-----------");
		
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		
		log.debug("-------------------------------------------");
	}
	
	
	//메소드 실행 전,후에 특정 로직 실행
	//pointcut없이 한줄로 선언해줄 수 있다
	@Around("execution(* com.bs.spring..*DaoImpl.*(..))")
	public Object daoLogger(ProceedingJoinPoint join) throws Throwable {
		//전,후 처리 로직은 한번에 설정할 수 있다
		//전, 후 구분은 ProceedingJoinPoint클래스가 제공하는 proceed()메소드 이용
		//proceed()메소드가 호출한 다음 라인을 후처리로 한다. 호출전 라인을 전처리
		//prodees()메소드는  Object를 반환
		
		//메소드 실행시간 체크하기
		StopWatch stop = new StopWatch();
		stop.start();
		log.debug("----------annotation aop around-------------");
		log.debug("-----------전처리-----------");
		log.debug("----------------------------------------------------");
		Signature sig = join.getSignature();
		String ClassMathod = sig.getDeclaringType().getName()+sig.getClass();
		Object obj = join.proceed();
		stop.stop();
		log.debug("-----------후처리-----------");
		log.debug("실행시간 : "+stop.getTotalTimeMillis()+"ms");
		log.debug("----------------------------------------------------");
		
		return obj;
	}
	
	@AfterThrowing(pointcut = "loggerTest()", throwing = "e")
	public void afterTrowingLogger(JoinPoint jp, Throwable e) {
		log.debug("!!에러발생!!");
		Signature sig = jp.getSignature();
		log.debug("{}",sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("{}",e.getMessage());
//		log.debug("{}",e.getStackTrace());
		StackTraceElement[] stacktrace =  e.getStackTrace();
		for(StackTraceElement element : stacktrace) {
			log.debug("{}",element);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
