package com.geico.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {//read the file
	private String fileName;//locator
	private List<String> list;//array storage
	BufferedReader reader;//read file
	
	public ReadFile(String fileName) {//param constructor
		this.fileName=fileName;
		readerFile();
		
	}
	public String getFileName() {//getter method
		return fileName;
	}
	public List<String> getList() {//getter method
		return list;
	}
public BufferedReader readerFile() {
	try {
	reader=new BufferedReader(new FileReader(getFileName()));
	generateList(reader);
	}
	catch(IOException e) {
		
	}
	//generate();
	return reader;
	
}
public void generateList(BufferedReader reader) {

	list=new ArrayList<>();//initliazion
	String line;
	
	try {
		while((line=reader.readLine())!=null) {
			list.add(line);//to input in array
			
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
}
	
	public void getSize() {
	System.out.println("The size of the array is :"+ list.size());
	}
public void closerRsource() {
	try {
		reader.close();
	} catch (IOException e) {		
		e.printStackTrace();
	}
	
}
}


