package com.bs.spring.beantest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.Data;

//pojo클래스를 생성하고 선언부에서 bean으로 등록할 수 있다
//@Component, @Controller, @Service, @Repository
//어노테이션을 이용해서 spring bean으로 등록
//@Component : 기본 spring bean으로 등록할때 사용
//@Controller, @Service, @Repository : mvc패천에 의해 역할 지정된 클래스를 등록할때 사용
@Data
@Component
public class FunctionalTest {
	private String name="test";
	
	//@Autowired
	private Animal a;
	
//	public FunctionalTest(@Qualifier("dog") Animal a) {
//		this.a=a;
//	}
	
	@Autowired
	public void setA(@Qualifier("dog")Animal a) {
		this.a=a;
	}
	public Animal getA() {
		return this.a;
	}
}
