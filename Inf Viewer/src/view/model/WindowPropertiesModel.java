package view.model;

public class WindowPropertiesModel {

	private int width;
	private int height;
	private String lastLocationOfImportFiles;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public WindowPropertiesModel() {
	}

	/**
	 * 
	 * @param height
	 * @param width
	 * @param lastLocationOfImportFiles
	 */
	public WindowPropertiesModel(int width, int height, String lastLocationOfImportFiles) {
		super();
		this.width = width;
		this.height = height;
		this.lastLocationOfImportFiles = lastLocationOfImportFiles;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getLastLocationOfImportFiles() {
		return lastLocationOfImportFiles;
	}

	public void setLastLocationOfImportFiles(String lastLocationOfImportFiles) {
		this.lastLocationOfImportFiles = lastLocationOfImportFiles;
	}

}
