package robot;

public class DashUtilMock extends DashUtil {
	public DashUtilMock(boolean japaneseKeyboard) {
		super(japaneseKeyboard);
	}

	@Override
	public void createNewSnippet(String abbr, String content, String tags) {
		System.out.println("Abbr: " + abbr);
		System.out.println("content" + content);
		System.out.println("tags: " + tags);
	}
}
