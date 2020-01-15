package com.generic.crud.paginacao;

import com.security.auth.User;

public interface HasUser {
	public User getUser();
	public void setUser(User user);

}
