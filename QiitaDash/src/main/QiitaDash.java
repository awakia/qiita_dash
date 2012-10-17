package main;

import java.io.IOException;
import java.util.List;

import robot.DashUtil;
import robot.KeyUtil;
import searcher.CodeSnippet;
import searcher.ResultFilter;
import searcher.TagFilter;
import searcher.SearchResultParser;
import searcher.Searcher;

public class QiitaDash {
	private static int itemNumberLimit = 10;
	private static String additionalTag;
	private static boolean japaneseKeyboard = false;
	
	public static void main(String[] args) throws IOException {
		if (!parseArgs(args))
			return;
		
		String response = Searcher.getTagSearchResult("Dash");
		
		ResultFilter filter = null;
		if (additionalTag != null)
			filter = new TagFilter(additionalTag);
		
		List<CodeSnippet> snippets 
			= SearchResultParser.getCodeSnippetFromSearchResult(response, itemNumberLimit, filter);
		
		DashUtil dash = new DashUtil(japaneseKeyboard);
		dash.open("dash");
		
		// System.out.println(snippets.toString());
		for (CodeSnippet snippet: snippets) {
			dash.createNewSnippet(snippet.getTitle(), snippet.getBody(), snippet.getTags());
		}
		
		System.out.println("Program end");
		//qd.testKeys();
	}
	
	private static boolean parseArgs(String[] args) {
		if (args.length > 0 && ("-h".equals(args[0]) || "--help".equals(args[0]))) {
			System.out.println("usage: java -jar QiitaDash.jar"
					+"[-n limitOfItemNumber] [-t tagToFilterSearchResult] [-j|--japanese]");
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
				additionalTag = args[++i];
			} else if ("--japanese".equals(args[i]) || "-j".equals(args[i])) {
				japaneseKeyboard = true;
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
