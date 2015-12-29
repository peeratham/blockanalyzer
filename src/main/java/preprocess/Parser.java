package main.java.preprocess;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**Example json file
 *http://projects.scratch.mit.edu/internalapi/project/25857120/get/ 
 */


public class Parser {
//	public static ScratchProject project = new ScratchProject();

	public static Script loadScript(Object s) {
		Script script = new Script();
		JSONArray scriptArray = (JSONArray)s;
		int x = ((Long)scriptArray.remove(0)).intValue();
		int y = ((Long)scriptArray.remove(0)).intValue();
		
		script.setPosition(x,y);
		JSONArray jsonBlocks = (JSONArray) scriptArray.remove(0);
		
		List<Block> blocks = new ArrayList<Block>();
		
        for (int i = 0; i < jsonBlocks.size(); i++)
        {
        	blocks.add(loadBlock(jsonBlocks.get(i)));
        }
        script.setBlocks(blocks);
        return script;
	}
	
	public static Block loadBlock(Object b){
		JSONArray blockArray = (JSONArray)b;
		
		ArrayList<Object> args = new ArrayList<Object>();

		String command = (String) blockArray.remove(0);
		BlockSpec blockSpec = CommandLoader.COMMAND_TO_BLOCK.get(command);
		
		Object arg = null;
		for (int argi = 0; argi < blockArray.size(); argi++) {
			if(blockArray.get(argi) instanceof JSONArray){	
				if(((JSONArray)blockArray.get(argi)).get(0) instanceof JSONArray){	//nested blocks
					arg = new ArrayList<Block>();//stack shape insert (nested blocks) will be list of blocks
					JSONArray blocks = (JSONArray)blockArray.get(argi);	//it's a list of blocks
					for (int argj = 0; argj < blocks.size(); argj++) {
						((List)arg).add(loadBlock(blocks.get(argj)));
					}
				}else{
					arg = loadBlock(blockArray.get(argi)); //block
				}
			}else{
				arg = blockArray.get(argi); //primitive
			}
			args.add(arg);	
		}
		
		return new Block(command,blockSpec,args);
	}
}

