package com.cloudwalk.tests.quakelog.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cloudwalk.tests.quakelog.game.DeathCause;
import com.cloudwalk.tests.quakelog.game.Game;
import com.cloudwalk.tests.quakelog.game.KillOccurred;

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
		return game.killsOccured().stream().collect(Collectors.groupingBy(KillOccurred::deathCause, Collectors.counting()));
	}
	
	
	
}
