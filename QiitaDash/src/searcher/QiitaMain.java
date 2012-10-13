package searcher;
import java.io.IOException;
import java.util.List;

public class QiitaMain {
	public static void main(String[] args) throws IOException {
		String response = Requester.getSearchResult("Dash");
		
		List<CodeSnippet> snippets 
			= JSONResponseParser.getCodeSnippetFromSearchResult(response, 1);
		for (CodeSnippet snippet: snippets) {
			System.out.println(snippet.toString());
		}
	}
}
