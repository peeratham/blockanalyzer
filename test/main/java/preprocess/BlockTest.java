package main.java.preprocess;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BlockTest {
	JSONParser jsonParser = new JSONParser();
	Parser parser = new Parser();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStringifyNoBlockKind() throws Exception {
		String input = "[\"say:duration:elapsed:from:\", \"Hello!\", 2]";
		JSONArray jsonInput = (JSONArray) jsonParser.parse(input);
		Block b = parser.loadBlock(jsonInput);
		assertEquals(b.toString(), "say \"Hello!\" for 2 secs");
		
	}
	
	@Test
	public void testStringifyWithBlockKind() throws Exception {
		String input = "[\"changeGraphicEffect:by:\", \"color\", 25]";
		JSONArray jsonInput = (JSONArray) jsonParser.parse(input);
		Block b = parser.loadBlock(jsonInput);
		assertEquals(b.toString(), "change \"color\" effect by 25");
	}
	
	@Test
	public void testStringifyOnNestedBlock() throws Exception {
		String input = "[\"doIf\", [\"<\", \"1\", \"2\"], [[\"broadcast:\", \"message1\"], [\"changeGraphicEffect:by:\", \"color\", 25]]]";
		JSONArray jsonInput = (JSONArray) jsonParser.parse(input);
		Block b = parser.loadBlock(jsonInput);
		String expectResult = "if (\"1\" < \"2\") then\n    broadcast \"message1\"\n    change \"color\" effect by 25\nend";
		assertEquals(b.toString(), expectResult);
	}

	@Test
	public void testToStringOnDoubleNestedBlock() throws Exception {
		String input = "[\"doIf\", [\"<\", \"1\", \"2\"], \n  "
				+ "[[\"broadcast:\", \"message1\"],\n  "
				+ "[\"doIf\", [\"<\", \"1\", \"2\"], \n    "
				+ "[[\"broadcast:\", \"message1\"],"
				+ "[\"changeGraphicEffect:by:\", \"color\", 25]]],"
				+ "\n\t  [\"changeGraphicEffect:by:\", \"color\", 25]]]";
		JSONArray jsonInput = (JSONArray) jsonParser.parse(input);
		Block b = parser.loadBlock(jsonInput);
		
		String expectResult = "if (\"1\" < \"2\") then\n    broadcast \"message1\"\n    if (\"1\" < \"2\") then\n        broadcast \"message1\"\n        change \"color\" effect by 25\n    end\n    change \"color\" effect by 25\nend";
		assertEquals(b.toString(), expectResult);
	}
	
	@Test
	public void testBlockEquals() throws Exception {
		String inputRHS = "[\"say:duration:elapsed:from:\", \"Hello!\", 2]";
		JSONArray jsonInputRHS = (JSONArray) jsonParser.parse(inputRHS);
		Block rhs = parser.loadBlock(jsonInputRHS);
		
		String inputLHS = "[\"say:duration:elapsed:from:\", \"Hello!\", 2]";
		JSONArray jsonInputLHS = (JSONArray) jsonParser.parse(inputLHS);
		Block lhs = parser.loadBlock(jsonInputLHS);
		
		assertEquals(lhs, rhs);
	}
	
	@Test
	public void testNestedBlockEquals() throws ParseException {
		//TODO add testNestedBlockEquals()
	}
	
	
	
}
