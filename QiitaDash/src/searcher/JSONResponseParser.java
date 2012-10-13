package searcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONResponseParser {
	public static List<CodeSnippet> 
			getCodeSnippetFromSearchResult(String response, int threshold) 
			throws JsonProcessingException, IOException {
		JsonNode itemArray = new ObjectMapper().readTree(response);
		List<CodeSnippet> codeSnippets = new ArrayList<CodeSnippet>();
		int itemIndex = 0;
		for (Iterator<JsonNode> iterator = itemArray.iterator();
				iterator.hasNext(); ) {
			itemIndex++;
			if (itemIndex > threshold) break;
			JsonNode node = iterator.next();
			String uuid = node.get("uuid").textValue();
			
			JsonNode tagsJson = node.get("tags");
			StringBuilder tagsList = new StringBuilder();
			for (Iterator<JsonNode> tagsIterator = tagsJson.iterator();
					tagsIterator.hasNext(); ) {
				JsonNode tagNode = tagsIterator.next();
				tagsList.append(tagNode.get("name").textValue());
				if (tagsIterator.hasNext())
					tagsList.append(' ');
			}
			
			String codeBody = getCodeBody(uuid);
			if (codeBody != null) {
				String[] splitedCodeBody = codeBody.split("\\n", 2);
				if (splitedCodeBody.length < 2) {
					System.out.println("cannot get title");
				} else {
					CodeSnippet snippet = new CodeSnippet(splitedCodeBody[0],
							splitedCodeBody[1], tagsList.toString());
					codeSnippets.add(snippet);
				}
			}
		}
		return codeSnippets;
	}
	
	public static String getCodeBody(String uuid) throws JsonProcessingException, IOException {
		String itemDetailsStr = Requester.getItem(uuid);
		JsonNode itemDetails = new ObjectMapper().readTree(itemDetailsStr);
		String codeBody = itemDetails.get("raw_body").textValue();
		Pattern pattern = Pattern.compile("```([^`]+)```");
		Matcher matcher = pattern.matcher(codeBody);
		if (matcher.find()) {
			return matcher.group(0).replace("```", "");
		} else {
			return null;
		}
	}
}
