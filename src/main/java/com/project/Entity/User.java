package com.project.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
	
	@Id
	private String username;
	
	private String password;
	
	@Builder
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

}
