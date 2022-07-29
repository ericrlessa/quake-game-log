package com.quakelog.game;

import lombok.NonNull;
import lombok.Value;

@Value
public class User {

	private @NonNull final String name;

	public boolean isWorld(){
		return this.name.equals("<world>");
	}
	
}
