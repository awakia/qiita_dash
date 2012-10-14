package searcher;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Searcher {
	public static final String API_END_POINT = "https://qiita.com/api/v1";
	
	public static String getTagSearchResult(String tagName) throws IOException {
		Request request = new Request(
				API_END_POINT + "/tags/" + tagName + "/items", RequestMethod.GET, null);
		return request.getResponse();
	}
	
	public static String getSearchResult(String query) throws IOException {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("q", "query");
		Request request = new Request(
				API_END_POINT + "/search", RequestMethod.GET, parameters);
		return request.getResponse();
	}
	
	public static String getItem(String uuid) throws IOException  {
		Request request = new Request(
				API_END_POINT + "/items/" + uuid, RequestMethod.GET, null);
		return request.getResponse();
	}
}
