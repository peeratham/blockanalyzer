package main.java.analyzer;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import main.java.preprocess.Block;
import main.java.preprocess.BlockAnalyzer;
import main.java.preprocess.ScratchProject;
import main.java.preprocess.Scriptable;
import main.java.preprocess.Util;
import main.java.visitor.All;
import main.java.visitor.BlockCounter;
import main.java.visitor.DownUp;
import main.java.visitor.Identity;
import main.java.visitor.Stop;
import main.java.visitor.TopDown;
import main.java.visitor.Visitor;

import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnreachableCodeAnalyzerTest {
	UnreachableCodeAnalyzer analyzer;
	ScratchProject project;
	File[] dataset;
	
	@Before
	public void setUp() throws Exception {
		
//		dataset = Util.getFileListing("C:\\Users\\Peeratham\\workspace\\Crawler\\data");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws ParseException, IOException {
//		for (File file : dataset) {
//			project = ScratchProject.loadProject(Util.readFile(file.toString()));
//			analyzer = new UnreachableCodeAnalyzer(project);
//			analyzer.analyze();
//		}
		
		project = ScratchProject.loadProject(Util.readFile("test/main/testdata/93160218.json"));
		analyzer = new UnreachableCodeAnalyzer(project);
		analyzer.analyze();
	}

}
