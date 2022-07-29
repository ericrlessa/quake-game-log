package com.quakelog.report;

import java.util.stream.Collectors;

import org.json.JSONObject;

import com.quakelog.game.Game;
import com.quakelog.game.User;

class QuakeGameFormat {
	
	private final Game game;
	
	public QuakeGameFormat(Game game) {
		this.game = game;
	}
	
	public static QuakeGameFormat of(Game game) {
		return new QuakeGameFormat(game);
	}

	public String json() {
		JSONObject gObj = new JSONObject();
		
		gObj.put("players", game.players().stream().map(User::getName).collect(Collectors.toList()));
		
		JSONObject killsObj = new JSONObject(game.qtKillsByUser());
		
		gObj.put("kills", killsObj);
		
		gObj.put("total_kills", game.totalKills());
		
		JSONObject result = new JSONObject();
		result.put(game.name(), gObj);
		
		return result.toString(4);
	}

}
