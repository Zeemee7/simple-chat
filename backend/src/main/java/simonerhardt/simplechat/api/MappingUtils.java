package simonerhardt.simplechat.api;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class MappingUtils {

	private MappingUtils() {
	}

	public static long toEpochMillis(LocalDateTime localDateTime) {
		return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}
}
