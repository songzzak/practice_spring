package com.bs.spring.member.model.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	@NotEmpty(message = "빽!!!!!")
	@Size(min = 4, message = "빽!!!!!!")
	private String userId;
	
	@Pattern(regexp = "(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[~!@#$%^&*()])[a-zA-Z~!@#$%^&*()]{8,}"
			, message = "영 소,대문자와 특수기호를 포함하여 8자 이상으로 작성해주세요.")
	private String password;

	private String userName;

	private String gender;
	
	@Min(14)
	@Max(100)
	private int age;
	
	@Email
	private String email;
	
	@NotEmpty
	private String phone;

	private String address;

	private String[] hobby;

	private Date enrollDate;
}
