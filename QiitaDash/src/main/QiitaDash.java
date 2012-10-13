package main;

import java.io.IOException;
import java.util.List;

import robot.DashUtil;
import robot.KeyUtil;
import searcher.CodeSnippet;
import searcher.JSONResponseParser;
import searcher.Requester;

public class QiitaDash {
	public static void main(String[] args) throws IOException {
		// String response = Requester.getSearchResult("Dash");
		String response = Requester.getTagSearchResult("Dash");
		
		int maxItemNumber = 1;
		List<CodeSnippet> snippets 
			= JSONResponseParser.getCodeSnippetFromSearchResult(response, maxItemNumber);
		
		DashUtil dash = new DashUtil();
		dash.open("dash");
		
		for (CodeSnippet snippet: snippets) {
			dash.createNewSnippet(snippet.getTitle(), snippet.getBody(), snippet.getTags());
		}
		
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
