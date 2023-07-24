package com.bs.spring.beantest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
	private String name;
	private int age;
	private String address;
	private int salary;
	private Department dept;
	private String hobby;
	
	public Employee(Department dept) {
		this.dept=dept;
	}
	
	public void initialMethod() {
		System.out.println(this.getClass().getName()+"클래스 생성");
	}
	public void destroyMethod() {
		System.out.println(this.getClass().getName()+"클래스 제거");
	}
}
