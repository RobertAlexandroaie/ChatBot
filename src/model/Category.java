package model;

public class Category {
	private String template;
	private String pattern;
	
	
	public Category(){
		
	}
	
	public Category(String pattern, String template) {
		this.pattern=pattern;
		this.template=template;
	}
	
	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}
	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}
	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}
	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}	
}
