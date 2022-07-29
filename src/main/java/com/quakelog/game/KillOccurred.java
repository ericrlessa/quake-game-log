package com.quakelog.game;

import lombok.NonNull;
import lombok.Value;

@Value
public class KillOccurred{
	
	private @NonNull final User killer;

	private @NonNull final User killed;
	
	private @NonNull final DeathCause deathCause;
	
	public static KillOccurred of(User killer, User killed, DeathCause deathCause){
		return new KillOccurred(killer, killed, deathCause);
	}
	
}