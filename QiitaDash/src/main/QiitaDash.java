package main;

import java.io.IOException;
import java.util.List;

import robot.DashUtil;
import robot.DashUtilMock;
import robot.KeyUtil;
import searcher.CodeSnippet;
import searcher.JSONResponseParser;
import searcher.Searcher;

public class QiitaDash {
	public static void main(String[] args) throws IOException {
		String response = Searcher.getTagSearchResult("Dash");
		// String response = Requester.getTagSearchResult("Dash");
		
		int maxItemNumber = 3;
		List<CodeSnippet> snippets 
			= JSONResponseParser.getCodeSnippetFromSearchResult(response, maxItemNumber);
		
		DashUtil dash = new DashUtilMock();
		dash.open("dash");
		
		// System.out.println(snippets.toString());
		for (CodeSnippet snippet: snippets) {
			dash.createNewSnippet(snippet.getTitle(), snippet.getBody(), snippet.getTags());
		}
		
		System.out.println("Program end");
		//qd.testKeys();
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
