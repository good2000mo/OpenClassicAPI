package ch.spacebase.openclassic.api.translate;

import java.io.File;
import java.io.InputStream;

import ch.spacebase.openclassic.api.config.Configuration;

/**
 * A language pack for use in a translator.
 */
public class Language {

	private String name;
	private Configuration lang;
	
	public Language(String name, String file) {
		this.name = name;
		this.lang = new Configuration(new File(file));
		this.lang.load();
	}
	
	public Language(String name, InputStream in) {
		this.name = name;
		this.lang = new Configuration(in);
		this.lang.load();
	}
	
	/**
	 * Gets the name of this language.
	 * @return This language's name.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Adds a translation to this language instance.
	 * @param key Key to use for the translation.
	 * @param translation Translation to add.
	 */
	public void addTranslation(String key, String translation) {
		this.lang.setValue(key, translation);
	}
	
	/**
	 * Translates the given key to text from this language.
	 * @param key Key to translate.
	 * @return The translated text.
	 */
	public String translate(String key) {
		if(this.lang.contains(key)) {
			return this.lang.getString(key);
		} else {
			return "<missing translation>";
		}
	}
	
}
