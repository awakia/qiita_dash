package searcher;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;


public class Request {
	private final String baseUrl;
	private final RequestMethod requestMethod;
	private final Map<String, String> parameters;
	
	public Request(String baseURL, RequestMethod requestMethod, Map<String, String> parameters) {
		this.baseUrl = baseURL;
		this.requestMethod = requestMethod;
		this.parameters = parameters;
	}
	
	/**
	 * Fire request and return response as String
	 */
	public String getResponse() throws IOException {
		URL url = null;
		if (requestMethod != RequestMethod.POST && parameters != null && parameters.size() > 0) {
			url = new URL(baseUrl + "?" + getParameterString());
		} else {
			url = new URL(baseUrl);
		}
		
		System.out.println("sending request to " + url.toString());
		HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
		urlConnection.setRequestMethod(requestMethod.toString());
		urlConnection.setInstanceFollowRedirects(false);

		if (requestMethod != RequestMethod.POST && parameters != null && parameters.size() > 0) {
			urlConnection.setDoOutput(true);
			String postParameters = getParameterString();
			OutputStream stream = urlConnection.getOutputStream();
			stream.write(postParameters.getBytes("UTF-8"));
			stream.flush();
			stream.close();
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
			builder.append(line).append('\n');
		}
		reader.close();
		urlConnection.disconnect();
		return builder.toString();
	}
	
	private String getParameterString() {
		StringBuilder parameterBuilder = new StringBuilder();
		boolean isFirstElement = true;
		for (String key : parameters.keySet()) {
			if (!isFirstElement)
				parameterBuilder.append("&");
			isFirstElement = false;
			parameterBuilder.append(key);
			parameterBuilder.append("=");
			parameterBuilder.append(parameters.get(key));
		}
		return parameterBuilder.toString();
	}
}
