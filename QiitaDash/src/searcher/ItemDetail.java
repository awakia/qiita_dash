package searcher;

import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ItemDetail {
	private final JsonNode detail;
	private String codeBody;
	private String codeTitle;
	private static String DEFAULT_CODE_TITLE = "no_name";
	
	public ItemDetail(String detailInString) throws JsonProcessingException, IOException {
		this(new ObjectMapper().readTree(detailInString));
	}
	
	public ItemDetail(JsonNode detailInJson) {
		this.detail = detailInJson;
	}
	
	public static ItemDetail requestBy(String uuid) throws IOException {
		return new ItemDetail(Searcher.getItem(uuid));
	}
	
	/**
	 * @return tags Sring that joint all tags by single space 
	 * (e.g., "JavaScript Dash HTML")
	 */
	public String getTags() {
		return getTags(detail.get("tags"));
	}
	
	/**
	 * @return tags Sring that joint all tags by single space 
	 * (e.g., "JavaScript Dash HTML")
	 */
	public static String getTags(JsonNode tagsJson) {
		StringBuilder tagsList = new StringBuilder();
		for (Iterator<JsonNode> tagsIterator = tagsJson.iterator(); tagsIterator
				.hasNext();) {
			JsonNode tagNode = tagsIterator.next();
			String tag = tagNode.get("name").textValue();
			if (tag.toLowerCase().equals("dash"))
				continue;
			tagsList.append(tag);
			if (tagsIterator.hasNext())
				tagsList.append(' ');
		}
		return tagsList.toString();
	}
	
	public String getCodeTitle() {
		calcCodeTitleAndBody();
		return codeTitle;
	}
	
	public String getCodeBody() {
		calcCodeTitleAndBody();
		return codeBody;
	}
	
	private void calcCodeTitleAndBody() {
		if (codeBody == null || codeTitle == null) {
			String rawBody = detail.get("raw_body").textValue();
			Pattern pattern = Pattern.compile("```([^`]+)```");
			Matcher matcher = pattern.matcher(rawBody);
			if (!matcher.find())
				return;
			String code = matcher.group(1);
			String[] splitedCode = code.split("\n", 2);
			codeTitle = DEFAULT_CODE_TITLE;
			if (splitedCode.length < 2) {
				System.err.println("cannot get title");
				codeBody = splitedCode[0];
			} else {
				codeTitle = splitedCode[0].trim();
				if (codeTitle.equals("")) {
					codeTitle = DEFAULT_CODE_TITLE;
				}
				codeBody = splitedCode[1];
			}
		}
	}
}
