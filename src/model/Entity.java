/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author Robert
 *
 */
public abstract class Entity {
	protected String name = "";
	protected ArrayList<Category> categories;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the categories
	 */
	public ArrayList<Category> getCategories() {
		return categories;
	}
	/**
	 * @param categories the categories to set
	 */
	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}
	
	
}
