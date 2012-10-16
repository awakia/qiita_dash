package searcher;

import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;

public class TagFilter implements ResultFilter {
	private String tag;
	
	public TagFilter(String tag) {
		this.tag = tag;
	}

	@Override
	public boolean match(JsonNode item) {
		JsonNode tagsJson = item.get("tags");
		StringBuilder tagsList = new StringBuilder();
		for (Iterator<JsonNode> tagsIterator = tagsJson.iterator(); 
				tagsIterator.hasNext();) {
			JsonNode tagNode = tagsIterator.next();
			if (tag.equals(tagNode.get("name").textValue())) {
				return true;
			}
		}
		return false;
	}
}
