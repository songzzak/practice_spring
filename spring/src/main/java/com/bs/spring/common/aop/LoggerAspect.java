package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerAspect {

	public void loggerBefore(JoinPoint jp) {
		log.debug("------------AOP loogerBefore-------------");
		Signature sig = jp.getSignature();

		log.debug(sig.getDeclaringTypeName() + " " + sig.getName());

		Object[] arg = jp.getArgs(); // 메소드가 실행될때 전달되는 매개변수의 인수값
		if (arg != null) {
			for (Object o : arg) {
				log.debug("{}", o);
			}
		}
		log.debug("-----------------------------------------------");
	}

	public void loggerAfter(JoinPoint jp) {
		log.debug("------------AOP loggerAfter-------------");
		Signature sig = jp.getSignature();
		log.debug(sig.getDeclaringTypeName() + " " + sig.getName());

		log.debug("-----------------------------------------------");
	}
}
