import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BilingualDictionary {
	ArrayList <DictionaryStructure> dict;

	BilingualDictionary(){
		dict = new ArrayList<DictionaryStructure>();
		this.loadWikipediaTitles();
		this.loadCSVDicitionary();
		this.printDictionary();
	}
	
	void loadCSVDicitionary(){
		
		try {
			Files.walk(Paths.get("csvdict")).forEach(filePath -> {
			    if (Files.isRegularFile(filePath)) {
			    	 try {
			 	        BufferedReader in = new BufferedReader(
			 	        		new InputStreamReader(new FileInputStream(filePath.toString()), "UTF-8"));
			 	        String str;
			 	        str = in.readLine();
			 	        while ((str = in.readLine()) != null) {
			 	        	String arr[] = str.split(",");
			 	        	DictionaryStructure d = new DictionaryStructure();
			 	            if(arr.length==2)
			 	            {
			 	            	d.Word = arr[0];
			 	            	d.Meaning = arr[1];
			 	            }
			 	            dict.add(d);         
			 	        }
			 	        in.close();
			 	    } catch (IOException e) {
			 	        System.out.println("File Read Error");
			 	    }
			 	 
			    }
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Post Process - Select Word with Smallest Length..
		for(int i=0;i<dict.size();i++){
			DictionaryStructure d = dict.get(i);
			String Meanings[] = d.Meaning.split("ـ");
			if(Meanings!=null && Meanings.length>=2){
				
				d.Meaning = Meanings[0];
				dict.set(i, d);
			}
			
		}
	}
	
	void loadWikipediaTitles(){
	    try {
	        BufferedReader in = new BufferedReader(
	        		new InputStreamReader(new FileInputStream("wikititle.txt"), "UTF-8"));
	        String str;
	        str = in.readLine();
	        while ((str = in.readLine()) != null) {
	        	String arr[] = str.split("\\$");
	            DictionaryStructure d = new DictionaryStructure();
	            if(arr.length==2)
	            {
	            	d.Word = arr[1];
	            	d.Meaning = arr[0];
	            }
	            dict.add(d);         
	        }
	        in.close();
	    } catch (IOException e) {
	        System.out.println("File Read Error");
	    }
	 
	}
	
	void printDictionary(){
		   for(int i=0;i<dict.size();i++){
		    	System.out.println(dict.get(i).Word+"  ->  "+dict.get(i).Meaning);
		    }
	}
	

	
}
