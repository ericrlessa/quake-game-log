package com.cloudwalk.tests.quakelog.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cloudwalk.tests.quakelog.game.KillOccurred;
import com.cloudwalk.tests.quakelog.game.User;

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
		assertFalse(killOccurred.killer().isWorld());
		assertEquals("killed", killOccurred.killer().getName());
		assertEquals("killed", killOccurred.killed().getName());
		assertEquals("MOD_FALLING", killOccurred.deathCause().getCause());
	}

	@Test
	public void testKillOccured() {
		
		//arrange
		String kill = " 22:06 Kill: 2 3 7: Isgalamido killed Mocinha by MOD_ROCKET_SPLASH";
		
		//act
		KillOccurred killOccurred = QuakeLogParser.extractKillOccured(kill);
		
		//verify
		assertTrue(QuakeLogParser.killOccured(kill));
		assertEquals("Isgalamido", killOccurred.killer().getName());
		assertEquals("Mocinha", killOccurred.killed().getName());
		assertEquals("MOD_ROCKET_SPLASH", killOccurred.deathCause().getCause());
		assertFalse(killOccurred.killer().isWorld());
	}

	@Test
	public void testKillOccuredWithSpace() {
		
		//arrange
		String kill = "2:11 Kill: 2 4 6: Dono da Bola killed Zeh by MOD_ROCKET";
		
		//act
		KillOccurred killOccurred = QuakeLogParser.extractKillOccured(kill);
		
		//verify
		assertTrue(QuakeLogParser.killOccured(kill));
		assertEquals("Dono da Bola", killOccurred.killer().getName());
		assertEquals("Zeh", killOccurred.killed().getName());
		assertEquals("MOD_ROCKET", killOccurred.deathCause().getCause());
		assertFalse(killOccurred.killer().isWorld());
	}

	@Test
	public void testKillOccuredByWorld() {
		
		//arrange
		String kill = "4:11 Kill: 1022 5 19: <world> killed Assasinu Credi by MOD_FALLING";
		
		//act
		KillOccurred killOccurred = QuakeLogParser.extractKillOccured(kill);
		
		//verify
		assertTrue(QuakeLogParser.killOccured(kill));
		assertTrue(killOccurred.killer().isWorld());
		assertEquals("<world>", killOccurred.killer().getName());
		assertEquals("Assasinu Credi", killOccurred.killed().getName());
		assertEquals("MOD_FALLING", killOccurred.deathCause().getCause());
		
	}


}
