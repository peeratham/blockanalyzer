package main.java.visitor;

import static org.junit.Assert.*;

import java.util.Map;

import main.java.preprocess.Block;
import main.java.preprocess.CommandLoader;
import main.java.preprocess.Parser;
import main.java.preprocess.Script;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MatchTest {
	JSONParser jsonParser = new JSONParser();
	Parser parser = new Parser();
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBasicMatcher() throws Exception {
		String patternInput = "[57,161,[[\"whenGreenFlag\"],[\"say:duration:elapsed:from:\", \"message\", \"sec\"]]]";
		JSONArray patternJsonInput = (JSONArray) jsonParser.parse(patternInput);
		Script pattern = parser.loadScript(patternJsonInput);
		VisitableScriptPattern scriptPattern = new VisitableScriptPattern(pattern);
		Match m = new Match(scriptPattern);
		m.addVariable("message");
		m.addVariable("sec");
		Visitor v = new TopDown(new Try(m));
		String termInput = "[57,161,[[\"broadcast:\",\"message1\"],[\"whenGreenFlag\"],[\"say:duration:elapsed:from:\", \"Hello!\", 2]]]";
		JSONArray termJsonInput = (JSONArray) jsonParser.parse(termInput);
		Script term = parser.loadScript(termJsonInput);
		Block firstTermBlock = term.getBlocks().get(0);
		v.visitBlock(firstTermBlock);
		Map binding = (Map) m.getMaps().get(0);
		assertEquals(binding.get("message"), "Hello!");
	}
	
	@Test
	public void testMatchRecursivelyInNestedBlock() throws Exception{
		String patternInput = "[339,268,[[\"broadcast:\",\"MESSAGE\"],[\"changeGraphicEffect:by:\",\"EFFECT\",25]]]";
		JSONArray patternJsonInput = (JSONArray) jsonParser.parse(patternInput);
		Script pattern = parser.loadScript(patternJsonInput);
		VisitableScriptPattern scriptPattern = new VisitableScriptPattern(pattern);
		Match m = new Match(scriptPattern);
		m.addVariable("MESSAGE");
		m.addVariable("EFFECT");
		Visitor v = new TopDown(new Try(m));
		String termInput = "[57,161,[[\"whenGreenFlag\"],[\"say:duration:elapsed:from:\",\"Hello!\",2],[\"doIf\",[\"<\",\"1\",\"2\"],[[\"broadcast:\",\"message1\"],[\"doIf\",[\"<\",\"1\",\"2\"],[[\"broadcast:\",\"message1\"],[\"changeGraphicEffect:by:\",\"color\",25]]],[\"changeGraphicEffect:by:\",\"color\",25]]]]]";
		JSONArray termJsonInput = (JSONArray) jsonParser.parse(termInput);
		Script term = parser.loadScript(termJsonInput);
		Block firstTermBlock = term.getBlocks().get(0);
		v.visitBlock(firstTermBlock);
		Map binding = (Map) m.getMaps().get(0);
		assertEquals(binding.get("MESSAGE"), "message1");
		assertEquals(binding.get("EFFECT"), "color");
		System.out.println(m.getMaps());

	}

}
