package searcher;

import com.fasterxml.jackson.databind.JsonNode;

public interface ResultFilter {
	/**
	 * @return true if passed item satisfy some condition.
	 */
	public boolean match(JsonNode item);
}
