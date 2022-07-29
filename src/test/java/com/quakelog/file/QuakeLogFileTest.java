package com.quakelog.file;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import com.quakelog.game.Game;
import com.quakelog.game.GameRepository;

public class QuakeLogFileTest{

	@Test
	public void testGameInFile() throws FileNotFoundException {

		//arrange
		GameRepository gameRepository = new QuakeLogFile("qgames.log");
		
		//act
		List<Game> games = gameRepository.findAll();
		Game game = games.iterator().next();

		//verify
		assertEquals(1, games.size());
		assertEquals(8, game.players().size());
		assertEquals(19, game.totalKills());
	}

}
