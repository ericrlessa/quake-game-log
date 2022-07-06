package com.cloudwalk.tests.quakelog.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameTest {

	@Test
	public void testKillOccured() {
		
		//arrange
		User killerIsgalamido = new User("Isgalamido");
		User killedDonoDaBola = new User("Dono da Bola");
		DeathCause deathCause = new DeathCause("MOD_FALLING");
		KillOccurred killOccurred = new KillOccurred(killerIsgalamido, killedDonoDaBola, deathCause);

		User killerIsgalamido2 = new User("Isgalamido");
		User killedZeh = new User("Zeh");
		DeathCause deathCause2 = new DeathCause("MOD_ROCKET");
		KillOccurred killOccurred2 = new KillOccurred(killerIsgalamido2, killedZeh, deathCause2);
		
		Game game = new Game(1);
		
		//act
		game.add(killOccurred);
		game.add(killOccurred2);
		
		//verify
		assertEquals(2, game.qtKillsByUser().get(killerIsgalamido).intValue()); 
	}

	@Test
	public void testKillOccuredByWorld() {
		
		//arrange
		User killerWorld = new User("<world>");
		User killedIsgalamido = new User("Isgalamido");
		DeathCause deathCause = new DeathCause("MOD_FALLING");
		KillOccurred killOccurred = new KillOccurred(killerWorld, killedIsgalamido, deathCause);
		
		User killerIsgalamido = new User("Isgalamido");
		User killedZeh = new User("Zeh");
		DeathCause deathCause2 = new DeathCause("MOD_ROCKET");
		KillOccurred killOccurred2 = new KillOccurred(killerIsgalamido, killedZeh, deathCause2);
		
		Game game = new Game(1);
		
		//act
		game.add(killOccurred);
		game.add(killOccurred2);
		
		//verify
		assertEquals(0, game.qtKillsByUser().get(killerIsgalamido).intValue()); 
	}

	@Test
	public void testKillNegative() {
		
		//arrange
		User killerWorld = new User("<world>");
		User killedIsgalamido = new User("Isgalamido");
		DeathCause deathCause = new DeathCause("MOD_FALLING");
		KillOccurred killOccurred = new KillOccurred(killerWorld, killedIsgalamido, deathCause);
		
		Game game = new Game(1);
		
		//act
		game.add(killOccurred);
		
		//verify
		assertEquals(-1, game.qtKillsByUser().get(killedIsgalamido).intValue()); 
	}

	@Test
	public void testPlayers() {
		
		//arrange
		User killerWorld = new User("<world>");
		User killedIsgalamido = new User("Isgalamido");
		User killedZeh = new User("Zeh");
		
		Game game = new Game(1);
		
		//act
		game.addPlayer(killerWorld);
		game.addPlayer(killedIsgalamido);
		game.addPlayer(killedZeh);
		
		//verify
		assertEquals(2, game.players().size()); 
		assertTrue(game.players().contains(User.of("Isgalamido"))); 
		assertTrue(game.players().contains(User.of("Zeh"))); 
		assertFalse(killedIsgalamido.isWorld());
		assertFalse(killedZeh.isWorld());
	}
	
}
