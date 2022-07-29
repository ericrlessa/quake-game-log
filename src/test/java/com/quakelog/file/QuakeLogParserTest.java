package com.quakelog.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.quakelog.file.QuakeLogParser;
import com.quakelog.game.KillOccurred;
import com.quakelog.game.User;

public class QuakeLogParserTest {
	
	@Test
	public void testInitGame() {
		
		//arrange
		String kill = "12:13 InitGame: \\sv_floodProtect\\1\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\sv_minRate\\0\\sv_hostname\\Code Miner Server\\g_gametype\\0\\sv_privateClients\\2\\sv_maxclients\\16\\sv_allowDownload\\0\\bot_minplayers\\0\\dmflags\\0\\fraglimit\\20\\timelimit\\15\\g_maxGameClients\\0\\capturelimit\\8\\version\\ioq3 1.36 linux-x86_64 Apr 12 2009\\protocol\\68\\mapname\\q3dm17\\gamename\\baseq3\\g_needpass\\0";
		
		//act - verify
		assertTrue(QuakeLogParser.initGame(kill));
	}
	
	@Test
	public void testShutdown() {
		
		//arrange
		String kill = "12:13 ShutdownGame:";
		
		//act - verify
		assertTrue(QuakeLogParser.isShutdown(kill));
	}
	
	@Test
	public void testUserStarted() {
		
		//arrange
		String kill = " 12:14 ClientUserinfoChanged: 2 n\\Dono da Bola\\t\\0\\model\\sarge\\hmodel\\sarge\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\95\\w\\0\\l\\0\\tt\\0\\tl\\0";
		
		//act
		User user = QuakeLogParser.extractUserStarted(kill);
		
		//verify
		assertTrue(QuakeLogParser.userStarted(kill));
		assertEquals("Dono da Bola", user.getName());
	}

	@Test
	public void testUserStarted2() {
		
		//arrange
		String kill = "  0:04 ClientUserinfoChanged: 3 n\\Dono da Bola\\t\\3\\model\\sarge\\hmodel\\sarge\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\95\\w\\0\\l\\0\\tt\\0\\tl\\0";
		
		//act
		User user = QuakeLogParser.extractUserStarted(kill);
		
		//verify
		assertTrue(QuakeLogParser.userStarted(kill));
		assertEquals("Dono da Bola", user.getName());
	}
	
	@Test
	public void testKillOccuredWithKeyName() {
		
		//arrange
		String kill = " 4:11 Kill: 1022 5 19: killed killed killed by MOD_FALLING";
		
		//act
		KillOccurred killOccurred = QuakeLogParser.extractKillOccured(kill);
		
		//verify
		assertTrue(QuakeLogParser.killOccured(kill));
		assertFalse(killOccurred.getKiller().isWorld());
		assertEquals("killed", killOccurred.getKiller().getName());
		assertEquals("killed", killOccurred.getKilled().getName());
		assertEquals("MOD_FALLING", killOccurred.getDeathCause().getCause());
	}

	@Test
	public void testKillOccured() {
		
		//arrange
		String kill = " 22:06 Kill: 2 3 7: Isgalamido killed Mocinha by MOD_ROCKET_SPLASH";
		
		//act
		KillOccurred killOccurred = QuakeLogParser.extractKillOccured(kill);
		
		//verify
		assertTrue(QuakeLogParser.killOccured(kill));
		assertEquals("Isgalamido", killOccurred.getKiller().getName());
		assertEquals("Mocinha", killOccurred.getKilled().getName());
		assertEquals("MOD_ROCKET_SPLASH", killOccurred.getDeathCause().getCause());
		assertFalse(killOccurred.getKiller().isWorld());
	}

	@Test
	public void testKillOccuredWithSpace() {
		
		//arrange
		String kill = "2:11 Kill: 2 4 6: Dono da Bola killed Zeh by MOD_ROCKET";
		
		//act
		KillOccurred killOccurred = QuakeLogParser.extractKillOccured(kill);
		
		//verify
		assertTrue(QuakeLogParser.killOccured(kill));
		assertEquals("Dono da Bola", killOccurred.getKiller().getName());
		assertEquals("Zeh", killOccurred.getKilled().getName());
		assertEquals("MOD_ROCKET", killOccurred.getDeathCause().getCause());
		assertFalse(killOccurred.getKiller().isWorld());
	}

	@Test
	public void testKillOccuredByWorld() {
		
		//arrange
		String kill = "4:11 Kill: 1022 5 19: <world> killed Assasinu Credi by MOD_FALLING";
		
		//act
		KillOccurred killOccurred = QuakeLogParser.extractKillOccured(kill);
		
		//verify
		assertTrue(QuakeLogParser.killOccured(kill));
		assertTrue(killOccurred.getKiller().isWorld());
		assertEquals("<world>", killOccurred.getKiller().getName());
		assertEquals("Assasinu Credi", killOccurred.getKilled().getName());
		assertEquals("MOD_FALLING", killOccurred.getDeathCause().getCause());
		
	}


}
