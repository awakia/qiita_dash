package searcher;

public class CodeSnippet {
	private final String title;
	private final String body;
	private final String tags;
	
	public CodeSnippet(String title, String body, String tags) {
		this.title = title;
		this.body = body;
		this.tags = tags;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public String getTags() {
		return tags;
	}
	
	public String toString() {
		return "title: " + title + "\n"
				+ "body: " + body + "\n"
				+ "tags: " + tags + "\n";
	}
}
