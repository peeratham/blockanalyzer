package main.java.preprocess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.java.visitor.DownUp;
import main.java.visitor.Identity;
import main.java.visitor.Printer;
import main.java.visitor.Stop;
import main.java.visitor.VisitFailure;
import main.java.visitor.Visitor;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {
	public static void main(String[] args) {
		
        JSONParser jsonParser = new JSONParser();
        ScratchProject project = null;
        
        try {
        	InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("main/resources/project03.json");
			Object obj = jsonParser.parse((new BufferedReader(new InputStreamReader(in))));
            JSONObject jsonObject = (JSONObject) obj;
            project = ScratchProject.loadProject(jsonObject);

  
        } catch (Exception e) {
            e.printStackTrace();
        }

        Visitor v = new DownUp(new Printer(), new Stop(), new Identity());
        
        try {
			project.accept(v);
		} catch (VisitFailure e) {
			e.printStackTrace();
		}
        
        
    }
	
	
}
