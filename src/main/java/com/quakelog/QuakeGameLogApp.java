package com.quakelog;

import java.io.FileNotFoundException;
import java.util.List;

import com.quakelog.file.QuakeLogFile;
import com.quakelog.game.Game;
import com.quakelog.game.GameRepository;
import com.quakelog.report.DeathCauseReport;
import com.quakelog.report.GameReport;
import com.quakelog.report.PlayerRankingReport;

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
