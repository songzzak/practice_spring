package com.bs.spring.member.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails {
	
	@NotEmpty(message = "빽!!!!!")
	@Size(min = 4, message = "빽!!!!!!")
	private String userId;
	
	@Pattern(regexp = "(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[~!@#$%^&*()])[a-zA-Z~!@#$%^&*()]{8,}"
			, message = "영 소,대문자와 특수기호를 포함하여 8자 이상으로 작성해주세요.")
	private String password;

	private String name;

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 로그인한 사용자의 권한 설정
		 List<GrantedAuthority> auth = new ArrayList();
		 auth.add(new SimpleGrantedAuthority("ROLE_USER"));
		 if(userId.equals("admin")) {
			 auth.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		 }
		return auth;
	}

	@Override
	public String getUsername() {
		// 인증할 id값을 반환
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
