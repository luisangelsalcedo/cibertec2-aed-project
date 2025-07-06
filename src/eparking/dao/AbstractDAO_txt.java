package eparking.dao;

public abstract class AbstractDAO_txt {
	
	protected final String directory = "data/";
	protected String filePath;
	
	public AbstractDAO_txt(String pathFile) {
		setFilePath(directory + pathFile);
	}
	
	// methods
	abstract void loadDataFromFile();
	abstract void writeDataToFile();

	// getters
	public String getFilePath() {
		return filePath;
	}
	// setters
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


}
