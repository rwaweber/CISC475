package main;

public class Session {
	
	public String sourceFile;
	public String destFile;

	public Session(String sourceFile, String destFile){
		this.sourceFile = sourceFile;
		this.destFile = destFile;
	}
	
	
	public String getSourceFile() {
		return sourceFile;
	}

	public String getDestFile() {
		return destFile;
	}

}
