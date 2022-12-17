package application.mainfunctions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.model.Target;

public class LinksFromFile {

	static private boolean isReaded = false;
	static private List<Target> targets = new ArrayList<>();

	public LinksFromFile() {
	}
	//TODO FILE
	public List<Target> getLinksList(File file) {
		//File file = new File("src/linkList.txt");
		if(!isReaded)
			readFile(file);
		List<Target> toSend = targets;
		return toSend;
	}

	public void readFile(File file) {
		if(file != null) {
			BufferedReader br = null;
	        try {
	            FileReader fr = new FileReader(file); // java.io.FileReader
	            br = new BufferedReader(fr); // java.io.BufferedReader
	            String line;
	            String[] splited = new String[1];
	            while ((line = br.readLine()) != null) {
	            	splited = line.split("\\|",2);
	            	targets.add(new Target(splited[0], splited[1]));
	            }
	          }
	          catch(IOException e) { e.printStackTrace();}
	          finally
	          {
	              try { if (br != null) br.close(); }
	              catch(IOException e) { e.printStackTrace(); }
	          }
	        isReaded = true;
		}
	}
	
	public void saveFile(File file, String links) {
		if(file != null) {
			try {
	            FileWriter fileWriter = null;
	            
	            fileWriter = new FileWriter(file);
	            fileWriter.write(links);
	            fileWriter.close();
	        } catch (IOException ex) {
	        	//TODO
	            System.out.println("Deu erro! Hahaha");
	        }
		}
		
	}
}