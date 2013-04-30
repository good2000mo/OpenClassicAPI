package ch.spacebase.openclassic.api.gui.widget;

import java.util.ArrayList;
import java.util.List;

import ch.spacebase.openclassic.api.gui.GuiScreen;
import ch.spacebase.openclassic.api.gui.Screen;

/**
 * Represents a list consisting of buttons.
 */
public class ButtonList extends Widget {

	private List<Button> buttons = new ArrayList<Button>();
	private List<String> contents = new ArrayList<String>();
	private List<String> visible = new ArrayList<String>();
	private TextBox search;
	
	private boolean useSearch;
	private int pages = 0;
	private int index = 0;
	
	public ButtonList(int id, int parentWidth, int parentHeight, Screen parent) {
		this(id, parentWidth, parentHeight, parent, false);
	}
	
	public ButtonList(int id, int parentWidth, int parentHeight, Screen parent, boolean search) {
		super(id, 0, 0, parentWidth, parentHeight, parent);
		
		for (int button = 0; button < 5; button++) {
			this.buttons.add(new Button(button, this.width / 2 - 100, this.height / 6 + button * 24, this.parent, "---"));
			this.buttons.get(id).setVisible(false);
			this.buttons.get(id).setActive(false);
		}
		
		this.buttons.add(new Button(5, this.width / 2 - 200, this.height / 6 + 48, 50, 20, this.parent, "Back"));
		this.buttons.add(new Button(6, this.width / 2 + 150, this.height / 6 + 48, 50, 20, this.parent, "Next"));
		this.search = new TextBox(7, this.width / 2 - 100, this.height / 6 + 120, this.parent);
		this.getBackButton().setActive(false);
		this.getNextButton().setActive(false);
		this.useSearch = search;
	}
	
	/**
	 * Gets the contents of this list.
	 * @return The contents of this list.
	 */
	public List<String> getContents() {
		return this.contents;
	}
	
	/**
	 * Sets the contents of this list.
	 * @param contents The list's new contents.
	 */
	public void setContents(List<String> contents) {
		this.contents = contents;
		this.visible = contents;
		this.search.setText("");
		this.index = 0;
		this.pages = (int) Math.ceil(this.visible.size() / 5);
		if(this.pages > 0 && this.visible.size() > (this.pages - 1) * 5) {
			this.getNextButton().setActive(true);
		}
		
		this.updateContents();
	}
	
	/**
	 * Gets the current page the list is on.
	 * @return The current page.
	 */
	public int getCurrentPage() {
		return this.index;
	}
	
	/**
	 * Gets the number of pages in this list.
	 * @return The list's page count.
	 */
	public int getPages() {
		return this.pages;
	}
	
	/**
	 * Called when a button on this list is clicked.
	 * @param button Button that was clicked.
	 */
	public void onButtonClick(Button button) {
		if (button.getId() == this.getBackButton().getId()) {
			this.index--;
			button.setActive(this.index > 0);
			this.getNextButton().setActive(this.index < this.pages);
			this.updateContents();
		} else if (button.getId() == this.getNextButton().getId()) {
			this.index++;
			button.setActive(this.index < this.pages);
			this.getBackButton().setActive(this.index > 0);
			this.updateContents();
		} else if(this.parent instanceof GuiScreen) {
			((GuiScreen) this.parent).onButtonListClick(this, button);
		}
	}
	
	/**
	 * Called when this list's search box was typed in.
	 */
	public void onType() {
		if(!this.useSearch) return;
		List<String> cont = new ArrayList<String>();
		for(String content : this.contents) {
			if(content.toLowerCase().contains(this.search.getText().toLowerCase())) {
				cont.add(content);
			}
		}
		
		this.visible = cont;
		this.index = 0;
		this.pages = (int) Math.ceil(this.visible.size() / 5);
		if(this.pages > 0 && this.visible.size() > (this.pages - 1) * 5) {
			this.getNextButton().setActive(true);
		}
		
		this.updateContents();
	}
	
	private void updateContents() {
		for (int curr = this.index * 5; curr < (this.index + 1) * 5; curr++) {
			boolean content = curr <= this.visible.size() - 1 && curr >= 0 && !this.visible.get(curr).equals("");
			int button = curr - this.index * 5;
			this.getButton(button).setActive(content);
			this.getButton(button).setText(content ? this.visible.get(curr) : "-");
			this.getButton(button).setVisible(true);
		}
	}
	
	/**
	 * Gets the button with the given ID. (0 = top of page, 4 = bottom, 5 = back, 6 = next)
	 * @param button Button to look for.
	 * @return The button with the given ID.
	 */
	public Button getButton(int button) {
		return this.buttons.get(button);
	}
	
	/**
	 * Gets the back button of this list.
	 * @return The list's back button.
	 */
	public Button getBackButton() {
		return this.buttons.get(5);
	}
	
	/**
	 * Gets the next button of this list.
	 * @return The list's next button.
	 */
	public Button getNextButton() {
		return this.buttons.get(6);
	}
	
	/**
	 * Gets all the buttons belonging to this list.
	 * @return The list's buttons.
	 */
	public List<Button> getButtons() {
		return this.buttons;
	}
	
	/**
	 * Gets the search box of this button list.
	 * @return This button list's search box.
	 */
	public TextBox getSearchBox() {
		return this.search;
	}
	
	@Override
	public void onKeyPress(char c, int key) {
		this.search.onKeyPress(c, key);
	}

	@Override
	public void onMouseClick(int x, int y, int button) {
		for(Button b : this.getButtons()) {
			if (x >= b.getX() && y >= b.getY() && x < b.getX() + b.getWidth() && y < b.getY() + b.getHeight()) {
				b.onMouseClick(x, y, button);
			}
		}
		
		this.search.onMouseClick(x, y, button);
	}
	
	@Override
	public void render() {
		for(Button button : this.buttons) {
			button.render();
		}
		
		if(this.useSearch) {
			this.search.render();
		}
	}
	
}
