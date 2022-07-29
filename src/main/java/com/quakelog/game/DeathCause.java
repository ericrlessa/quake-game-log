package com.quakelog.game;

import lombok.NonNull;
import lombok.Value;

@Value
public class DeathCause {
	private @NonNull final String cause;
}
