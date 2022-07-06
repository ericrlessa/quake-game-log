package com.cloudwalk.tests.quakelog.game;

import java.util.Objects;

public class User {

	private final String name;
	
	public User(String name) {
		this.name = name;
	}

	public static User of(String name) {
		return new User(name);
	}

	public String getName() {
		return name;
	}
	
	public boolean isWorld(){
		return this.name.equals("<world>");
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return this.name;
	}
	
}
