package com.cloudwalk.tests.quakelog.game;

import java.util.Objects;

public class DeathCause {
	
	private final String cause;

	public DeathCause(String cause) {
		super();
		this.cause = cause;
	}
	
	public static DeathCause of(String cause) {
		return new DeathCause(cause);
	}

	public String getCause() {
		return cause;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cause);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeathCause other = (DeathCause) obj;
		return Objects.equals(cause, other.cause);
	}

	@Override
	public String toString() {
		return this.cause;
	}
	
}
