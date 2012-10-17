package robot;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;


public class KeyUtil {
	protected Robot rb = null;
	protected boolean japaneseKeyboard;
	
	public KeyUtil() {
		this(false);
	}
	
	public KeyUtil(boolean japaneseKeyboard) {
		this.japaneseKeyboard = japaneseKeyboard;
		try {
			rb = new Robot();
			rb.setAutoDelay(30);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void open(String appName) {
		spotlightOpen();
		inputString(appName);
		keyPress(KeyEvent.VK_ENTER);
		rb.delay(1000);
	}
	
	public void spotlightOpen() {
		keyPress(KeyEvent.VK_CONTROL, KeyEvent.VK_SHIFT, KeyEvent.VK_SPACE);
	}

	public void inputString(String sequence) {
		for (int c: sequence.toCharArray()) {
			if ('a' <= c && c <= 'z') {
				keyPress(Character.toUpperCase(c));
				continue;
			} else if (('A' <= c && c <= 'Z') ) {
				keyPress(KeyEvent.VK_SHIFT, c);
				continue;
			} else if (('0' <= c && c <= '9')) {
				keyPress(c);
				continue;
			}
			
			switch (c) {
	        case '`': keyPress(KeyEvent.VK_BACK_QUOTE); break;
	        case '-': keyPress(KeyEvent.VK_MINUS); break;
	        case '?': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH); break;
	        case '!': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_1); break;
	        case '#': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_3); break;
	        case '$': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_4); break;
	        case '%': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_5); break;
	        case ',': keyPress(KeyEvent.VK_COMMA); break;
	        case '.': keyPress(KeyEvent.VK_PERIOD); break;
	        case '/': keyPress(KeyEvent.VK_SLASH); break;
	        case ' ': keyPress(KeyEvent.VK_SPACE); break;
	        case '\t': keyPress(KeyEvent.VK_TAB); break;
	        case '\n': keyPress(KeyEvent.VK_ENTER); break;
	        case ';': keyPress(KeyEvent.VK_SEMICOLON); break;
			default: 
			{
				if (japaneseKeyboard) {
					switch (c) {
			        case '[': keyPress(KeyEvent.VK_CLOSE_BRACKET); break;
			        case ']': keyPress(KeyEvent.VK_BACK_SLASH); break;
			        case '{': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET); break;
			        case '}': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH); break;
					case '=': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_MINUS);break;
			        case '~': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_EQUALS);break;
			        case '@': keyPress(KeyEvent.VK_OPEN_BRACKET); break;
			        case '^': keyPress(KeyEvent.VK_EQUALS); break;
			        case '&': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_6); break;
			        case '*': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_QUOTE); break;
			        case '(': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_8); break;
			        case ')': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_9); break;
			        case '+': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_SEMICOLON); break;
			        case ':': keyPress(KeyEvent.VK_QUOTE); break;
			        case '\'': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_7); break;
			        case '"': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_2); break;
			        case '<': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_COMMA); break;
			        case '>': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_PERIOD); break;

			        // Since Japanese Yen mark and Backslash share the same key code, 
			        // Robot cannot press these key. 
			        // Instead, we do copy and paste.
			        case '_': 
			        case '\\': 
			        case '|': 
			    		copyToClipboard(Character.toString((char) c));
			    		keyPress(KeyEvent.VK_META, KeyEvent.VK_V);
			    		break;
					}
				} else {
					switch(c) {
			        case '[': keyPress(KeyEvent.VK_OPEN_BRACKET); break;
			        case ']': keyPress(KeyEvent.VK_CLOSE_BRACKET); break;
			        case '{': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET); break;
			        case '}': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET); break;
					case '=': keyPress(KeyEvent.VK_EQUALS); break;
			        case '~': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_QUOTE); break;
			        case '@': keyPress(KeyEvent.VK_AT); break;
			        case '^': keyPress(KeyEvent.VK_CIRCUMFLEX); break;
			        case '&': keyPress(KeyEvent.VK_AMPERSAND); break;
			        case '*': keyPress(KeyEvent.VK_ASTERISK); break;
			        case '(': keyPress(KeyEvent.VK_LEFT_PARENTHESIS); break;
			        case ')': keyPress(KeyEvent.VK_RIGHT_PARENTHESIS); break;
			        case '+': keyPress(KeyEvent.VK_PLUS); break;
			        case ':': keyPress(KeyEvent.VK_COLON); break;
			        case '\'': keyPress(KeyEvent.VK_QUOTE); break;
			        case '"': keyPress(KeyEvent.VK_QUOTEDBL); break;
			        case '<': keyPress(KeyEvent.VK_LESS); break;
			        case '>': keyPress(KeyEvent.VK_GREATER); break;
			        
			        case '_': keyPress(KeyEvent.VK_UNDERSCORE); break;
			        case '\\': keyPress(KeyEvent.VK_BACK_SLASH); break;
			        case '|': keyPress(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH); break;
					}
				}
			}
			}
		}
	}

	public void keyPress(int... keys) {
		for (int key : keys) {
			rb.keyPress(key);
		}
		rb.delay(10);
		for (int key : keys) {
			rb.keyRelease(key);
		}
	}
	
	public void copyToClipboard(String string) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Clipboard clip = kit.getSystemClipboard();
	    StringSelection ss = new StringSelection(string);
	    clip.setContents(ss, ss);
	}
	
	public void delay() {
		rb.delay(200);
	}
}
