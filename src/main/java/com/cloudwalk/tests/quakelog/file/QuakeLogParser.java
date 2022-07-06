package com.cloudwalk.tests.quakelog.file;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cloudwalk.tests.quakelog.game.DeathCause;
import com.cloudwalk.tests.quakelog.game.KillOccurred;
import com.cloudwalk.tests.quakelog.game.User;

class QuakeLogParser {

	static final Pattern PATTERN_KILL_LINE = Pattern.compile("\\d+:\\d+\\sKill:\\s\\d+\\s\\d+\\s\\d+:\\s(.+\\s)+killed\\s(.+\\s)by\\s(.+)", Pattern.MULTILINE);
	
	static boolean killOccured(String line) {
		final Matcher matcher = PATTERN_KILL_LINE.matcher(line);
		return matcher.find();
	}
	
	static KillOccurred extractKillOccured(String line) {
		final Matcher matcher = PATTERN_KILL_LINE.matcher(line);

		if(!matcher.find()) {
			throw new LogFormatException("Error in log format when extracting kill -> line: " + line);
		}

		User killer = User.of(matcher.group(1).trim());
		User killed = User.of(matcher.group(2).trim());
		DeathCause deathCause = DeathCause.of(matcher.group(3).trim());

		return KillOccurred.of(killer, killed, deathCause);
	}
	
	static final Pattern PATTERN_SHUTDOWN = Pattern.compile("\\d+:\\d+ ShutdownGame:");

	static boolean isShutdown(String line) {
		final Matcher matcher = PATTERN_SHUTDOWN.matcher(line);
		return matcher.find();
	}

	static final Pattern PATTERN_USER_STARTED = Pattern.compile("\\d+:\\d+\\sClientUserinfoChanged:\\s\\d+\\sn\\\\(.+\\s?)+\\\\t\\\\\\d");

	static User extractUserStarted(String line) {
		final Matcher matcher = PATTERN_USER_STARTED.matcher(line);
		if(!matcher.find()) {
			throw new LogFormatException("Error in log format when extracting ClientUserinfoChanged -> line: " + line);
		}
		return User.of(matcher.group(1));
	}
	
	static boolean userStarted(String line) {
		final Matcher matcher = PATTERN_USER_STARTED.matcher(line);
		return matcher.find();
	}
	
	static final Pattern PATTERN_INIT_GAME = Pattern.compile("\\d+:\\d+ InitGame: .+");
	
	static boolean initGame(String line) {
		final Matcher matcher = PATTERN_INIT_GAME.matcher(line);
		return matcher.find();
	}
	
}
