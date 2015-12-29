package main.java.preprocess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.visitor.VisitFailure;
import main.java.visitor.Visitor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ScratchProject implements Visitable{
	int projectID;
	private Map<String, Scriptable> scriptables;

	public void addScriptable(String name, Scriptable obj) {
		this.scriptables.put(name, obj);
	}

	public ScratchProject(){
		scriptables = new HashMap<String,Scriptable>();
		
	}
	
	public Scriptable getScriptable(String name) {
		return scriptables.get(name);
	}
	
	

	@Override
	public String toString() {
		return "ScratchProject [scriptables=" + scriptables + "]";
	}

	public static ScratchProject loadProject(JSONObject jsonObject) {
		ScratchProject project = new ScratchProject();
		CommandLoader.loadCommand();
		JSONObject stageObj = jsonObject;
		
		if(stageObj.containsKey("scripts")){
			JSONArray stageScripts = (JSONArray)stageObj.get("scripts");
			Scriptable stage = new Scriptable();
			for (int j = 0; j < stageScripts.size(); j++) {
				Script scrpt = Parser.loadScript(stageScripts.get(j));
				stage.setName("Stage");
				stage.addScript(scrpt);
			}
			project.addScriptable("Stage", stage);
		}
		
		JSONArray children = (JSONArray)jsonObject.get("children");
		for (int i = 0; i < children.size(); i++) {
			JSONObject sprite = (JSONObject) children.get(i);
			if(!sprite.containsKey("objName")){ //not a sprite
				continue;
			}
			Scriptable s = new Scriptable();
			String spriteName = (String)sprite.get("objName");
			JSONArray scripts = (JSONArray)sprite.get("scripts");
			for (int j = 0; j < scripts.size(); j++) {
				Script scrpt = Parser.loadScript(scripts.get(j));
				s.setName(spriteName);
				s.addScript(scrpt);
			}
			project.addScriptable(spriteName, s);
		}
		return project;
	}

	public Map<String, Scriptable> getScriptables() {
		return scriptables;
		
	}

	@Override
	public void accept(Visitor v) throws VisitFailure {
		v.visitProject(this);
		
	}

}
