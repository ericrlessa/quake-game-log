package com.cloudwalk.tests.quakelog.report;

import java.util.Map;

import org.json.JSONObject;

import com.cloudwalk.tests.quakelog.game.DeathCause;
import com.cloudwalk.tests.quakelog.game.Game;

class DeathCauseFormat {
	
	private final Game game;
	
	private final Map<DeathCause, Long> deathCauseCounting;
	
	public DeathCauseFormat(Game game, Map<DeathCause, Long> deathCauseCounting) {
		this.game = game;
		this.deathCauseCounting = deathCauseCounting;
	}
	
	public static DeathCauseFormat of(Game game, Map<DeathCause, Long> deathCauseCounting) {
		return new DeathCauseFormat(game, deathCauseCounting);
	}

	public String json() {
		JSONObject killsByMeans = new JSONObject();
		killsByMeans.put("kills_by_means", this.deathCauseCounting);
		
		JSONObject result = new JSONObject();
		result.put(game.name(), killsByMeans);
		
		return result.toString(4);
	}

}
