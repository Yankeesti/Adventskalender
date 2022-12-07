package defaultPackage;

import java.util.ArrayList;

public class Directory {
	String name;
	Directory parent;
	ArrayList<File> files;
	ArrayList<Directory> directorys;
	
	 public Directory(String name) {
		this.name = name;
		files = new ArrayList<File>();
		directorys = new ArrayList<Directory>();
	}
	public Directory(String name,Directory parent) {
		this(name);
		this.parent = parent;//if null its the outermost directory
		
	}
	
	public void add(File file) {
		if(!alreadyIn(file))
		files.add(file);
	}
	
	private void add(Directory directory) {
		directory.setParent(this);
		directorys.add(directory);
	}
	
	public void setParent(Directory directory) {
		parent = directory;
	}
	
	public String getName() {
		return name;
	}
	
	public Directory getParent() {
		return parent;
	}
	
	/**
	 * 
	 * @param name - name of Directory
	 * @return the Directory with the name name when there is no such directory it will be added
	 */
	public Directory getDirectory(String name) {
		for(int i = 0 ; i< directorys.size();i++) {
			if(directorys.get(i).getName().equals(name))
			return directorys.get(i);
		}
		Directory toAdd = new Directory(name);
		add(toAdd);
		return toAdd;
		
	}
	
	/**
	 * 
	 * @return All sub Directorys 
	 */
	public Directory[] getSubDirectorys() {
		ArrayList<Directory> outPut = new ArrayList<Directory>();
		for(int i = 0; i< directorys.size();i++) {
			outPut.add(directorys.get(i));
			Directory[] subDirectorys = directorys.get(i).getSubDirectorys();
			for(Directory p: subDirectorys) {
				outPut.add(p);
			}
		}
		Directory[] outPutArray = new Directory[outPut.size()];
		for(int i = 0; i< outPutArray.length;i++) {
			outPutArray[i] = outPut.get(i);
		}
		return outPutArray;
	}
	
	/**
	 * 
	 * @return the total size of the directory
	 */
	public int getSize() {
		int outPut = 0;
		for(int i = 0; i<files.size();i++) {
			outPut += files.get(i).size;
		}
		
		for(int i = 0; i< directorys.size();i++) {
			outPut += directorys.get(i).getSize();
		}
		return outPut;
	}
	
	/**
	 * 
	 * @param p
	 * @return true when there is already a Directory with the name of p in this directory else false
	 */
	private boolean alreadyIn(Directory p){
		for(int i = 0 ; i< directorys.size();i++) {
			if(directorys.get(i).getName().equals(p.getName()));
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param p
	 * @return true when there is already a File with the name of p in this directory else false
	 */
	private boolean alreadyIn(File p){
		for(int i = 0 ; i< files.size();i++) {
			if(files.get(i).getName().equals(p.getName()))
			return true;
		}
		return false;
	}
	
	
}
