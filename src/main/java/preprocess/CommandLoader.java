package main.java.preprocess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CommandLoader {
	public static Map<String, String> SHAPE_FLAGS = new HashMap<String, String>();
	public static Map<Integer, String> CATEGORY_IDS = new HashMap<Integer, String>();
	public static Map<String, BlockSpec> COMMAND_TO_BLOCKSPEC = new HashMap<String,BlockSpec>();
	public static Map<String, BlockSpec> COMMAND_TO_CUSTOM_BLOCKSPEC = new HashMap<String,BlockSpec>();
	
	public static void initShapFlags(){
		SHAPE_FLAGS.put(" ", "stack");
		SHAPE_FLAGS.put("b", "boolean");
		SHAPE_FLAGS.put("c", "stack"); //cblock
		SHAPE_FLAGS.put("r", "reporter");
		SHAPE_FLAGS.put("e", "stack");	//eblock
		SHAPE_FLAGS.put("cf", "cap");	//cblock
		SHAPE_FLAGS.put("f", "cap");
		SHAPE_FLAGS.put("h", "hat");
	}
	
	public static void initCategoryID(){
		CATEGORY_IDS.put(1,  "motion");
		CATEGORY_IDS.put(2,  "looks");
		CATEGORY_IDS.put(3,  "sound");
		CATEGORY_IDS.put(4,  "pen");
		CATEGORY_IDS.put(5,  "events");
		CATEGORY_IDS.put(6,  "control");
		CATEGORY_IDS.put(7,  "sensing");
		CATEGORY_IDS.put(8,  "operators");
		CATEGORY_IDS.put(9,  "variables");
		CATEGORY_IDS.put(10, "more blocks");
		CATEGORY_IDS.put(12, "list");
		CATEGORY_IDS.put(20, "sensor");
		CATEGORY_IDS.put(21, "wedo");
		CATEGORY_IDS.put(30, "midi");
		CATEGORY_IDS.put(91, "midi");
		CATEGORY_IDS.put(98, "obsolete");
		CATEGORY_IDS.put(99, "obsolete");
		//for stage
		CATEGORY_IDS.put(102, "looks");
		CATEGORY_IDS.put(104, "pen");
		CATEGORY_IDS.put(106, "control");
		CATEGORY_IDS.put(107, "sensing");
	}
	

	
	public static void loadCommand(){
		initShapFlags();
		initCategoryID();
		JSONParser jsonParser = new JSONParser();
        Object obj = null;
		try {
			ClassLoader cl = ClassLoader.getSystemClassLoader();
			InputStream in = cl.getResourceAsStream("main/resources/commands_src.json");
		
	        
			
			if(in !=null){
				obj = jsonParser.parse((new BufferedReader(new InputStreamReader(in))));
			} else {
				System.out.println("null input");
			}
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray blockSpecs = (JSONArray) jsonObject.get("blockSpecs");
        for(int i = 0; i < blockSpecs.size();i++ ){
        	JSONArray blockSpec = (JSONArray)blockSpecs.get(i);
        	if(blockSpec.size()>1){
            	String spec = (String)blockSpec.get(0);
            	String flag = (String)blockSpec.get(1);
            	Integer categoryID = ((Long)blockSpec.get(2)).intValue();
            	String name = (String)blockSpec.get(3);
            	List<Object> defaults = blockSpec.subList(4, blockSpec.size());
            	String shape = SHAPE_FLAGS.get(flag);
            	String category = CATEGORY_IDS.get(categoryID);
            	
            	
            	if(flag.contains("c")){
					spec += "%s";
				}
				
				if(flag.equals("e")){
					spec += "%s\nelse%s";
				}
            	COMMAND_TO_BLOCKSPEC.put(name, new BlockSpec(category, flag, shape, name, spec, defaults));
        	}
        }
        
	}
	
	public static void main(String[] args){
		loadCommand();
		System.out.println(COMMAND_TO_BLOCKSPEC);
	}
}
