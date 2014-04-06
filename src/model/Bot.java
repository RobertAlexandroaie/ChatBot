package model;

import java.util.ArrayList;

import model.util.AIMLUtil;

public class Bot extends Entity {
	public Bot() {
		categories = new ArrayList<>();
	}

	public Bot(String name) {
		this();
		setName(name);
		AIMLUtil.loadAIML(this);
	}

	public String intro() {
		return "Salut. Numele meu este " + getName()
				+ ". Pe tine cum te cheama?\n";
	}
	
	public void say(String string) {
		System.out.println("Bot: - "+string);
	}
}
