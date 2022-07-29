package com.quakelog.file;

import static com.quakelog.file.QuakeLogParser.extractKillOccured;
import static com.quakelog.file.QuakeLogParser.extractUserStarted;
import static com.quakelog.file.QuakeLogParser.initGame;
import static com.quakelog.file.QuakeLogParser.isShutdown;
import static com.quakelog.file.QuakeLogParser.killOccured;
import static com.quakelog.file.QuakeLogParser.userStarted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.quakelog.game.Game;
import com.quakelog.game.GameRepository;

public class QuakeLogFile implements GameRepository{

	private final List<Game> games;
	
	public QuakeLogFile (String fileName) {
		
		InputStream is = this.getClass().getResourceAsStream(fileName);
		
		if(is == null) {
			throw new RuntimeException("File not found: " + fileName);
		}
		
		this.games = new ArrayList<Game>();
		
		try {	
			extractAllGames(new BufferedReader( new InputStreamReader(is) ));
		} catch (IOException e) {
			throw new RuntimeException("Error when processing file!");
		}
	}
	
	private void extractAllGames(BufferedReader logFile) throws IOException {
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
				logFile.close();
			}
			
		
	}

	@Override
	public List<Game> findAll() {
		return this.games;
	}

}
