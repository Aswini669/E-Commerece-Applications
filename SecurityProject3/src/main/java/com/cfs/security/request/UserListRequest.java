package com.cfs.security.request;

import java.util.List;

public class UserListRequest {

	private List<UserRequest> users;

	public List<UserRequest> getUsers() {
		return users;
	}

	public void setUsers(List<UserRequest> users) {
		this.users = users;
	}

}
