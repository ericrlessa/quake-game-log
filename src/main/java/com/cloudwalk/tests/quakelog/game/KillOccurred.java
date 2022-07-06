package com.cloudwalk.tests.quakelog.game;

public class KillOccurred{
	
	private final User killer;

	private final User killed;
	
	private final DeathCause deathCause;
	
	public KillOccurred(User killer, User killed, DeathCause deathCause) {
		this.killer = killer;
		this.killed = killed;
		this.deathCause = deathCause;
	}

	public static KillOccurred of(User killer, User killed, DeathCause deathCause){
		return new KillOccurred(killer, killed, deathCause);
	}
	
	public User killer() {
		return killer;
	}

	public User killed() {
		return killed;
	}
	
	public DeathCause deathCause() {
		return this.deathCause;
	}

}