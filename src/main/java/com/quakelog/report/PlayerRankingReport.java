package com.quakelog.report;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.quakelog.game.Game;
import com.quakelog.game.User;

public class PlayerRankingReport {

	public static void print(List<Game> games) {
		System.out.println("==========Player Ranking===========");
		
		Map<User, Integer> rankingMap = sumKillsByUser(games);
		
		List<Rank> rankingList = createRankingList(rankingMap);
		
		System.out.println(rankingList);
		
		System.out.println("===================================");
	}

	private static List<Rank> createRankingList(Map<User, Integer> rankingMap) {
		List<Rank> rankingList = rankingMap.entrySet().stream().map(e -> Rank.of(e.getKey(), e.getValue())).collect(Collectors.toList());
		
		rankingList.sort(Comparator.reverseOrder());
		
		return rankingList; 
	}

	private static Map<User, Integer> sumKillsByUser(List<Game> games) {
		return games.stream().flatMap(g -> g.qtKillsByUser().entrySet().stream()).collect(groupingBy(Map.Entry::getKey, summingInt(Map.Entry::getValue)));
	}	
}
