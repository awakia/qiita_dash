package robot;

import java.awt.event.KeyEvent;


public class DashUtil extends KeyUtil {
	public DashUtil(boolean japaneseKeyboard) {
		super(japaneseKeyboard);
	}

	/**
	 * 
	 * @param abbr Abbreviation for dash snippet command
	 * @param content Content of dash snippet
	 * @param tags tags separated by space(' ')
	 */
	public void createNewSnippet(String abbr, String content, String tags) {
		abbr += "#";
		keyPress(KeyEvent.VK_META, KeyEvent.VK_N);
		delay();
		inputString(abbr);
		delay();
		keyPress(KeyEvent.VK_TAB);
		delay();
		copyToClipboard(content);
		keyPress(KeyEvent.VK_META, KeyEvent.VK_V);
		delay();
		keyPress(KeyEvent.VK_META, KeyEvent.VK_L);
		delay();
		keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_TAB);
		delay();
		inputString(tags);
		delay();
		keyPress(KeyEvent.VK_META, KeyEvent.VK_S);
		delay();
	}
}
