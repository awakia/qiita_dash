package searcher;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;


public class Requester {
	public static final String API_END_POINT = "https://qiita.com/api/v1";
	
	private static String getRequestResult(URL url, String requestMethod, Map<String, String> paramters) throws IOException {
		System.out.println("sending request to " + url.toString());
		HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
		urlConnection.setRequestMethod(requestMethod);
		urlConnection.setInstanceFollowRedirects(false);

		StringBuilder paramterBuilder = new StringBuilder();
		boolean isFirstElement = true;
		if (paramters != null) {
			urlConnection.setDoOutput(true);
			for (String key : paramters.keySet()) {
				if (!isFirstElement)
					paramterBuilder.append("&");
				isFirstElement = false;
				paramterBuilder.append(key);
				paramterBuilder.append("=");
				paramterBuilder.append(paramters.get(key));
			}
			PrintWriter printWriter = new PrintWriter(urlConnection.getOutputStream());
			printWriter.print(paramterBuilder.toString());
			printWriter.flush();
			printWriter.close();
		}
		
		urlConnection.connect();
		
		Map<String, List<String>> headers = urlConnection.getHeaderFields();
		if (headers.containsKey("Status"))
			System.out.println("Status: " + headers.get("Status"));
		
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append('\n');
		}
		reader.close();
		urlConnection.disconnect();
		return builder.toString();
	}
	
	public static String getTagSearchResult(String tagName) throws IOException {
		String urlInString = API_END_POINT + "/tags/" + tagName + "/items";
		URL url = new URL(urlInString);
		return getRequestResult(url, "GET", null);
	}
	
	public static String getSearchResult(String query) throws IOException {
		String urlInString = API_END_POINT + "/search" + "?q=" + query.trim().replaceAll("[\\s]+", "+");
		URL url = new URL(urlInString);
		return getRequestResult(url, "GET", null);
	}
	
	public static String getItem(String uuid) throws IOException {
		String urlInString = API_END_POINT + "/items/" + uuid;
		URL url = new URL(urlInString);
		return getRequestResult(url, "GET", null);
	}
}
