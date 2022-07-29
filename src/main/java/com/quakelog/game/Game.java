package com.quakelog.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Game {
	
	private int id;

	private String name;
	
	private List<KillOccurred> killsOccurred;
	
	private Set<User> players;
	
	private Map<User, Integer> mapQtKillsByUser;
	
	public Game(int i) {
		this.id = i;
		this.name = "game_" + i;
		this.players = new HashSet<User>();
		this.killsOccurred = new ArrayList<KillOccurred>();
		this.mapQtKillsByUser = new HashMap<User, Integer>(); 
	}
	
	public void add(KillOccurred killOccurred) {
		this.killsOccurred.add(killOccurred);
		countKills(killOccurred);
	}
	
	public void addPlayer(User user) {
		if(user.isWorld()) {
			return;
		}
		
		this.players.add(user);
		int qtKills = this.mapQtKillsByUser.getOrDefault(user, 0);
		this.mapQtKillsByUser.put(user, qtKills);
	}
	
	private void countKills(KillOccurred killOccurred) {
		if(killOccurred.getKiller().isWorld()) {
			int qtKills = this.mapQtKillsByUser.getOrDefault(killOccurred.getKilled(), 0);
			qtKills--;
			this.mapQtKillsByUser.put(killOccurred.getKilled(), qtKills);
		}else {
			int qtKills = this.mapQtKillsByUser.getOrDefault(killOccurred.getKiller(), 0);
			qtKills++;
			this.mapQtKillsByUser.put(killOccurred.getKiller(), qtKills);
		}	
	}

	public int id() {
		return id;
	}
	
	public String name() {
		return this.name;
	}

	public int totalKills() {
		return this.killsOccurred.size();
	}
	
	public List<KillOccurred> killsOccured(){
		return this.killsOccurred;
	}

	public Set<User> players() {
		return this.players;
	}
	
	public Map<User, Integer> qtKillsByUser() {
		return mapQtKillsByUser;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", killsOccurred=" + killsOccurred + ", players=" + players
				+ ", mapQtKills=" + mapQtKillsByUser + "]";
	}

}