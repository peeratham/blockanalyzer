package main.java.analyzer;

import static org.junit.Assert.*;

import java.io.File;

import main.java.preprocess.ScratchProject;
import main.java.preprocess.Util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MetadataVisitorTest {
	UnreachableCodeAnalyzer analyzer;
	ScratchProject project;
	File[] dataset;
	
	
	@Before
	public void setUp() throws Exception {
		project = ScratchProject.loadProject(Util.readFile("test/main/testdata/93160218.json"));
		analyzer = new UnreachableCodeAnalyzer(project);
		dataset = Util.getFileListing("test/main/testdata/");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		for (File file : dataset) {
			
		}
	}

}
