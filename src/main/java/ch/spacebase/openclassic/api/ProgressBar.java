package ch.spacebase.openclassic.api;

public interface ProgressBar {

	/**
	 * Gets the text the progress bar is showing.
	 * @return The progress bar's text.
	 */
	public String getText();
	
	/**
	 * Sets the text of the progress bar.
	 * @param text Text to set.
	 */
	public void setText(String text);
	
	/**
	 * Gets the title the progress bar is showing.
	 * @return The progress bar's title.
	 */
	public String getTitle();
	
	/**
	 * Sets the title of the progress bar.
	 * @param title Title to set.
	 */
	public void setTitle(String title);
	
	/**
	 * Gets the progress bar's progress.
	 * @return The progress bar's progress.
	 */
	public int getProgress();
	
	/**
	 * Sets the progress bar's progress.
	 * @param progress Progress to set.
	 */
	public void setProgress(int progress);

	/**
	 * Returns true if the progress bar is currently visible.
	 * @return True if the progress bar is visible.
	 */
	public boolean isVisible();
	
	/**
	 * Sets whether the progress bar is visible.
	 * @param visible Whether the progress bar is visible.
	 */
	public void setVisible(boolean visible);
	
}
