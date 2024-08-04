package simonerhardt.simplechat.api;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class MappingUtils {

	private MappingUtils() {
		// Prevent instatiation.
	}

	/**
	 * Converts a {@link LocalDateTime} to Unix epoch (number of milliseconds since 01.01.1970 UTC),
	 * using the system's default time zone.
	 *
	 * @param localDateTime The {@link LocalDateTime}.
	 * @return The number of milliseconds.
	 */
	public static long toEpochMillis(LocalDateTime localDateTime) {
		return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}
}
