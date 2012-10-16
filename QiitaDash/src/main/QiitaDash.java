package main;

import java.io.IOException;
import java.util.List;

import robot.DashUtil;
import robot.KeyUtil;
import searcher.CodeSnippet;
import searcher.JSONResponseParser;
import searcher.Requester;

public class QiitaDash {
	private static int itemNumberLimit = 10;
	private static String addtionalTag;
	
	public static void main(String[] args) throws IOException {
		if (!parseArgs(args))
			return;
		
		String response = Requester.getTagSearchResult("Dash");
		// String response = Requester.getTagSearchResult("Dash");
		
		List<CodeSnippet> snippets 
			= JSONResponseParser.getCodeSnippetFromSearchResult(response, itemNumberLimit);
		
		DashUtil dash = new DashUtil();
		dash.open("dash");
		
		// System.out.println(snippets.toString());
		for (CodeSnippet snippet: snippets) {
			dash.createNewSnippet(snippet.getTitle(), snippet.getBody(), snippet.getTags());
		}
		
		System.out.println("Program end");
		//qd.testKeys();
	}
	
	private static boolean parseArgs(String[] args) {
		if (args.length > 0 && "-h".equals(args[0])) {
			System.out.println("QiitaDash [-n limitOfItemNumber] [-t tagToFilterSearchResult]");
			return false;
		}
		
		for (int i = 0; i < args.length; i++) {
			if ("-n".equals(args[i])) {
				try {
					itemNumberLimit = Integer.parseInt(args[++i]);
				} catch (NumberFormatException e) {
					System.err.println("item number limit must be integer.");
					return false;
				}
			} else if ("-t".equals(args[i])) {
				addtionalTag = args[++i];
			} else {
				System.err.println("'" + args[i] + 
						"' is unknown option or parameter: Ignored.");
			}
		}
		return true;
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
