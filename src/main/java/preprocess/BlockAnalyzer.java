package main.java.preprocess;

import java.io.FileReader;
import java.io.IOException;

import main.java.visitor.BlockCounter;
import main.java.visitor.DownUp;
import main.java.visitor.Identity;
import main.java.visitor.Stop;
import main.java.visitor.VisitFailure;
import main.java.visitor.Visitor;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BlockAnalyzer {
	JSONParser jsonParser;
	ScratchProject project;
	Visitor visitor;
	String input;
	
	public BlockAnalyzer(){
		jsonParser = new JSONParser();
        
	}

	public void setVisitor(Visitor v) {
		visitor = v;
	}
	
	public void analyze() {	
        try {
			project.accept(visitor);
		} catch (VisitFailure e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setInputPath(String path){
		try {
            Object obj = jsonParser.parse(new FileReader(
                    path));
            JSONObject jsonObject = (JSONObject) obj;
            project = ScratchProject.loadProject(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void setStringInput(String input){
		this.input = input;
		try {
			Object obj = jsonParser.parse(input);
			JSONObject jsonObject = (JSONObject) obj;
            project = ScratchProject.loadProject(jsonObject);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public ScratchProject getProject(){
		return project;
	}
	
	public static void main(String[] args){
		BlockAnalyzer blockAnalyzer = new BlockAnalyzer();
		Visitor v = new BlockCounter();
		blockAnalyzer.setVisitor(new DownUp(v, new Stop(), new Identity()));
		try {
			blockAnalyzer.setStringInput(Util.readFile("src/main/resources/project03.json"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		blockAnalyzer.analyze();
		System.out.println(blockAnalyzer.getProject().projectID);
		System.out.println(((BlockCounter)v).getCount());
	}
}
