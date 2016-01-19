package main.java.preprocess;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {
	
	JSONParser jsonParser = new JSONParser();
	Parser parser = new Parser();
	ScratchProject project;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		parser = null;
		project = null;
	}
	
	@Test
	public void testParseScript() throws Exception {
		
	}
	
	@Test
	public void testParseBlock() throws Exception {
		
	}
	
	@Test
	public void testBlockNextChild() throws Exception {
		String stringInput = "[57,161,[[\"whenGreenFlag\"],[\"say:duration:elapsed:from:\", \"message\", \"sec\"]]]";
		JSONArray jsonInput = (JSONArray) jsonParser.parse(stringInput);
		Script script = parser.loadScript(jsonInput);
		Block first = script.getBlocks().get(0);
		Block second = script.getBlocks().get(1);
		assert(second ==  first.getNextBlock());
	}
	
	@Test
	public void testNextChildOnNestedBlock() throws Exception {
		String stringInput = "[57,161,[[\"whenGreenFlag\"],[\"say:duration:elapsed:from:\",\"Hello!\",2],[\"doIf\",[\"<\",\"1\",\"2\"],[[\"broadcast:\",\"message1\"],[\"doIf\",[\"<\",\"1\",\"2\"],[[\"broadcast:\",\"message1\"],[\"changeGraphicEffect:by:\",\"color\",25]]],[\"changeGraphicEffect:by:\",\"color\",25]]]]]";
		JSONArray jsonInput = (JSONArray) jsonParser.parse(stringInput);
		Script script = parser.loadScript(jsonInput);
		Block first = script.getBlocks().get(0);
		assertEquals(first.getCommand(), "whenGreenFlag");
		
		Block next = first.getNextBlock();
		assertEquals(next.getCommand(), "say:duration:elapsed:from:");
		
		next = next.getNextBlock();
		assertEquals(next.getCommand(), "doIf");
		
		assertTrue(next.hasNestedBlocks());
		Block firstChild = next.getFirstChild();
		assertEquals(firstChild.getCommand(), "broadcast:");
		
		next = firstChild.getNextBlock();
		assertEquals(next.getCommand(), "doIf");
		
		next = next.getNextBlock();
		assertEquals(next.getCommand(), "changeGraphicEffect:by:");
	}
	
	@Test
	public void testIfThenElse() throws IOException, ParseException{
		String stringInput = Util.retrieveProjectOnline(43026762);
		project = new ScratchProject().loadProject(stringInput);
//		System.out.println(project.getScriptable("Sprite1").getScript(0));
	}

}
