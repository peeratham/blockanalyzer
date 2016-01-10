package main.java.preprocess;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import main.java.preprocess.CommandLoader;
import main.java.preprocess.ScratchProject;
import main.java.preprocess.Scriptable;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ScratchProjectTest {
	JSONParser jsonParser = new JSONParser();
	ScratchProject project;
	@Before
	public void setUp() throws Exception {
		try {
			InputStream in = getClass().getClassLoader().getResourceAsStream("main/resources/project03.json");
			Object obj = jsonParser.parse((new BufferedReader(new InputStreamReader(in))));
            JSONObject jsonObject = (JSONObject) obj;
            project = new ScratchProject().loadProject(jsonObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoadProject() {
		Map<String, Scriptable> scriptables= project.getScriptables();
		assertEquals(3, scriptables.size());
	}

}
