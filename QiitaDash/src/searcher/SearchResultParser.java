package searcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SearchResultParser {
	public static List<CodeSnippet> getCodeSnippetFromSearchResult(
			String response, int threshold) throws JsonProcessingException,
			IOException {
		JsonNode itemArray = new ObjectMapper().readTree(response);
		List<CodeSnippet> codeSnippets = new ArrayList<CodeSnippet>();
		for (Iterator<JsonNode> iterator = itemArray.iterator(); iterator
				.hasNext();) {
			if (codeSnippets.size() >= threshold) break;
			JsonNode node = iterator.next();
			String uuid = node.get("uuid").textValue();

			String tags = ItemDetail.getTags(node.get("tags"));

			ItemDetail itemDetail = ItemDetail.requestBy(uuid);
			if (itemDetail.getCodeBody() != null) {
				CodeSnippet snippet = new CodeSnippet(itemDetail.getCodeTitle(),
						itemDetail.getCodeBody(), tags);
				codeSnippets.add(snippet);
			}
		}
		return codeSnippets;
	}
}
