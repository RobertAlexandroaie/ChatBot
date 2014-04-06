package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import model.util.AIMLUtil;

public class ChatEngine {
	private Bot bot;
	private User user;

	public ChatEngine(Bot bot, User user) {
		this.bot = bot;
		this.user = user;
	}

	public void startChat() {
		bot.say(bot.intro());
		user.say();

		String input = readLine();
		String name = "";
		StringTokenizer strTokenizer = new StringTokenizer(input);

		while (strTokenizer.hasMoreTokens()) {
			name = strTokenizer.nextToken();
		}

		if (name != "") {
			user.setName(name);
			File f = new File(name + ".aiml");
			if (f.exists()) {
				AIMLUtil.loadAIML(user);
				bot.say("Bine ai revenit, " + name);
			} else {
				bot.say("Bun venit, " + name);
			}
		} else {
			name = "NuStiuCumMaCheama";
			user.setName(name);
		}

		keepChatting(input);
	}

	private void keepChatting(String input) {
		Boolean isNewCategory;
		Boolean isNewCategoryForUser;
		Boolean isStaying = false;

		isStaying = !(input.toUpperCase().contains("PA")
				|| input.toUpperCase().contains("PLEC") || input.toUpperCase()
				.contains("LA REVEDERE"));

		while (isStaying) {
			isNewCategory = true;
			isNewCategoryForUser = true;

			user.say();
			input = readLine();
			
			isStaying = !(input.toUpperCase().contains("PA")
					|| input.toUpperCase().contains("PLEC") || input
					.toUpperCase().contains("LA REVEDERE"));
			
			if (isStaying) {
				for (Category category : bot.getCategories()) {
					if (input.toUpperCase().contains(category.getPattern())) {
						// raspuns bot, daca are
						bot.say(category.getTemplate() + " " + input);
						Category newCategory = new Category();

						newCategory.setPattern(category.getPattern());

						for (Category userCategory : user.getCategories()) {
							if (userCategory.getPattern().equals(
									newCategory.getPattern())) {
								bot.say("Tu ai spune: " + userCategory.getTemplate()+", nu?");

//								user.say();
//								input = readLine();
//
//								userCategory.setTemplate(input);
								
								isNewCategoryForUser = false;								
								break;
							}
						}

						if (isNewCategoryForUser) {
							user.say();
							input = readLine();

							newCategory.setTemplate(input);
							user.getCategories().add(newCategory);
						}

						isNewCategory = false;
						break;
					}
				}

				if (isNewCategory) {
					Category newCategory = new Category();
					newCategory.setPattern(input.toUpperCase());

					bot.say("Nu imi dau seama ce vrei. Cum ar trebui sa raspund?");
					user.say();
					input = readLine();
					newCategory.setTemplate(input);

					bot.getCategories().add(newCategory);
					user.getCategories().add(newCategory);
				}
			} else {
				bot.say("La revedere");
			}
		}
		AIMLUtil.writeAIML(bot);
		AIMLUtil.writeAIML(user);
	}

	private String readLine() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}
