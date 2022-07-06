package com.cloudwalk.tests.quakelog.file;

import static com.cloudwalk.tests.quakelog.file.QuakeLogParser.extractKillOccured;
import static com.cloudwalk.tests.quakelog.file.QuakeLogParser.extractUserStarted;
import static com.cloudwalk.tests.quakelog.file.QuakeLogParser.initGame;
import static com.cloudwalk.tests.quakelog.file.QuakeLogParser.isShutdown;
import static com.cloudwalk.tests.quakelog.file.QuakeLogParser.killOccured;
import static com.cloudwalk.tests.quakelog.file.QuakeLogParser.userStarted;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.cloudwalk.tests.quakelog.game.Game;
import com.cloudwalk.tests.quakelog.game.GameRepository;

public class QuakeLogFile implements GameRepository{

	private final BufferedReader logFile;
	
	private final List<Game> games;
	
	public QuakeLogFile (String fileName) throws FileNotFoundException {
		InputStreamReader streamReader =
                new InputStreamReader(this.getClass().getResourceAsStream(fileName));
		
		logFile = new BufferedReader( streamReader );
		
		this.games = new ArrayList<Game>();
		
		extractAllGames();
	}
	
	private void extractAllGames() {
		try {
			
			int gameId = 1;

			String line = null;
			
			try {
				Game game = null;
				while ((line = logFile.readLine()) != null) {
					if (initGame(line)) {
						if(game != null) {
							games.add(game);
						}
						game = new Game(gameId++);
					} else if (userStarted(line)) {
						game.addPlayer(extractUserStarted(line));
					}else if (killOccured(line)) {
						game.add(extractKillOccured(line));
					}else if(isShutdown(line)) {
						games.add(game);
						game = null;
					}
				}
			}finally {
				this.logFile.close();
			}
			
		} catch (IOException e) {
			throw new RuntimeException("Error when processing file!", e);
		}
	}

	@Override
	public List<Game> findAll() {
		return this.games;
	}

}
