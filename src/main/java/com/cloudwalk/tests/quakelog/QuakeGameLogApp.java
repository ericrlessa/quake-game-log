package com.cloudwalk.tests.quakelog;

import java.io.FileNotFoundException;
import java.util.List;

import com.cloudwalk.tests.quakelog.file.QuakeLogFile;
import com.cloudwalk.tests.quakelog.game.Game;
import com.cloudwalk.tests.quakelog.game.GameRepository;
import com.cloudwalk.tests.quakelog.report.DeathCauseReport;
import com.cloudwalk.tests.quakelog.report.GameReport;
import com.cloudwalk.tests.quakelog.report.PlayerRankingReport;

public class QuakeGameLogApp 
{
	public static void main( String[] args ) throws FileNotFoundException
	{
		GameRepository gameRepository = new QuakeLogFile("qgames.log");
		
		List<Game> games = gameRepository.findAll();

		GameReport.print(games);
		
		PlayerRankingReport.print(games);
		
		DeathCauseReport.print(games);
	}

}
