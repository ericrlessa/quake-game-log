package com.cloudwalk.tests.quakelog.file;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import com.cloudwalk.tests.quakelog.game.Game;
import com.cloudwalk.tests.quakelog.game.GameRepository;
import com.cloudwalk.tests.quakelog.report.GameReport;

public class QuakeLogFileTest{

	@Test
	public void testGameInFile() throws FileNotFoundException {
		
		GameRepository gameRepository = new QuakeLogFile("qgames.log");
		
		List<Game> games = gameRepository.findAll();

		GameReport.print(games);

		
	}

}
