package com.quakelog.report;

import com.quakelog.game.User;

class Rank implements Comparable<Rank>{

	private final User user;
	
	private final Integer qtKills;

	public Rank(User user, Integer qtKills) {
		super();
		this.user = user;
		this.qtKills = qtKills;
	}
	
	public static Rank of(User user, Integer qtKills) {
		return new Rank(user, qtKills);
	}

	@Override
	public String toString() {
		return String.format("%s: %s", this.user.getName(), this.qtKills);
	}

	@Override
	public int compareTo(Rank o) {
		return this.qtKills.compareTo(o.qtKills);
	}
	
}
