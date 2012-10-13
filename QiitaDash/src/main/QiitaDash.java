package main;

import java.awt.event.KeyEvent;

import robot.DashUtil;
import robot.KeyUtil;

public class QiitaDash {
	public static void main(String[] args) {
		QiitaDash qd = new QiitaDash();
		qd.start();
		//qd.test();
		//qd.testKeys();
	}
	
	void start() {
		DashUtil dash = new DashUtil();
		dash.open("dash");
		dash.createNewSnippet("cpp_hello_world#",
				"#include <iostream>\nusing namespece std;\n\nint main() {\n  cout << \"hello world!\" << endl;\n}\n",
				"cpp hello");
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
