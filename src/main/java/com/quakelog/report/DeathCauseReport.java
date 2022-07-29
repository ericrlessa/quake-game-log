package com.quakelog.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.quakelog.game.DeathCause;
import com.quakelog.game.Game;
import com.quakelog.game.KillOccurred;

public class DeathCauseReport {

	public static void print(List<Game> games) {
		System.out.println("==========Death Cause Report===========");
		
		List<String> result = new ArrayList<String>();
		for (Game game : games) {
			Map<DeathCause, Long> deathCauseCounting = groupByDeathCauseCount(game);
			
			result.add(DeathCauseFormat.of(game, deathCauseCounting).json());
		}
	
		
		result.forEach(System.out::println);

		System.out.println("=======================================");
	}

	private static Map<DeathCause, Long> groupByDeathCauseCount(Game game) {
		return game.killsOccured().stream().collect(Collectors.groupingBy(KillOccurred::getDeathCause, Collectors.counting()));
	}
	
	
	
}
