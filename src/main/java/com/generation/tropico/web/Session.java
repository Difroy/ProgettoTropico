package com.generation.tropico.web;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.generation.tropico.model.entities.User;

@Service
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Session
{

	private User currentUser = User.NOONE;
	
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	
	
}
