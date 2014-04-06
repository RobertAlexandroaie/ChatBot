/**
 * 
 */
package main;

import model.Bot;
import model.ChatEngine;
import model.User;

/**
 * @author Robert
 *
 */
public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Bot bot = new Bot("bot");
		 User user = new User();
		 ChatEngine chatEngine = new ChatEngine(bot, user);
		 
		 chatEngine.startChat();
	}

}
