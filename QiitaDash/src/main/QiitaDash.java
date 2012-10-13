package main;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import robot.DashUtil;
import robot.DashUtilMock;
import robot.KeyUtil;
import searcher.CodeSnippet;
import searcher.JSONResponseParser;
import searcher.Requester;

public class QiitaDash {
	public static void main(String[] args) throws IOException {
		String response = Requester.getSearchResult("Dash");
		
		int maxItemNumber = 1;
		List<CodeSnippet> snippets 
			= JSONResponseParser.getCodeSnippetFromSearchResult(response, maxItemNumber);
		for (CodeSnippet snippet: snippets) {
			QiitaDash qd = new QiitaDash();
			qd.createNewSnippet(snippet.getTitle(), snippet.getBody(), snippet.getTags());
		}
		
		//qd.test();
		//qd.testKeys();
	}
	
	void createNewSnippet(String abbr, String content, String tags) {
		DashUtil dash = new DashUtilMock();
		dash.open("dash");
		dash.createNewSnippet(abbr, content, tags);
	}
	
	void test() {
//		System.out.println(KeyEvent.VK_A);
//		System.out.println((int)'a');
//		System.out.println(KeyEvent.VK_PLUS);
//		System.out.println((int)'+');
//		System.out.println(KeyEvent.VK_PERIOD);
//		System.out.println((int)'.');
//		System.out.println(KeyEvent.VK_SPACE);
//		System.out.println((int)' ');
//		System.out.println(KeyEvent.VK_ENTER);
//		System.out.println((int)'\n');
//		System.out.println(KeyEvent.VK_QUOTE);
//		System.out.println((int)'\'');
//		System.out.println(KeyEvent.VK_QUOTEDBL);
//		System.out.println((int)'"');
		System.out.println(KeyEvent.VK_BACK_QUOTE);
		System.out.println((int)'`');
		System.out.println((int)'~');
	}
	
	void testKeys() {
		KeyUtil keyUtil = new KeyUtil();
		keyUtil.open("coteditor");
		for (char c = ' '; c <= '~'; c++) {
			keyUtil.inputString("" + c);
			System.out.print(c);
		}
	}
}
