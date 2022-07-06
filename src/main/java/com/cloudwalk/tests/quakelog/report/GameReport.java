package com.cloudwalk.tests.quakelog.report;

import java.util.List;

import com.cloudwalk.tests.quakelog.game.Game;

public class GameReport {
	
	public static void print(List<Game> games) {
		System.out.println("==========Games Report===========");
		for (Game game : games) {
			System.out.println(QuakeGameFormat.of(game).json());
		}
		System.out.println("=================================");
	}

}
