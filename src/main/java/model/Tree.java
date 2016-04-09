package model;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Tree extends GitObject{
	
	ArrayList<String> objectsName;
	ArrayList<String> objectsId;
	
	public Tree(String hash, byte[] b) {
		super(hash, "");
		this.type = GitObjectType.Tree;

		//Decode les données spécifiques aux trees
		StringBuffer buffer = new StringBuffer();
		boolean trad = false;
		int cpt = 0;
		for(byte by :b){
			if(by == 0){
				if(!trad){
					buffer.append((char)32);
					cpt = 20;
				}
				trad = !trad;
			}else{
				if(trad){
					String string = Integer.toHexString(by);
					if (string.length() > 2) // TODO:vérifier l'utilité de cette ligne
						string = string.substring(string.length() - 2);
					buffer.append(string);
					cpt --;
					if (cpt <= 0){
						trad = !trad;
						buffer.append("\n");
					}
				}else{
					buffer.append((char)by);
				}
			}
		}
		this.setRawData(buffer.toString());
	}
	
	public void setDataContent(String rawData){
		Pattern lines = Pattern.compile("\n");
		String[] data = lines.split(rawData);
		
		for (int i = 0; i < data.length; i++) {
			
		}
		
	}

}
