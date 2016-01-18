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
	public Parser(){
		CommandLoader.loadCommand();
	}

	public Script loadScript(Object s) {
		Script script = new Script();
		JSONArray scriptArray = (JSONArray)s;
		int x = ((Long)scriptArray.remove(0)).intValue();
		int y = ((Long)scriptArray.remove(0)).intValue();
		
		script.setPosition(x,y);
		JSONArray jsonBlocks = (JSONArray) scriptArray.remove(0);
		
		List<Block> blocks = new ArrayList<Block>();
		
		Block previous = loadBlock(jsonBlocks.get(0));
		blocks.add(previous);	//		add first block
		
        for (int i = 1; i < jsonBlocks.size(); i++)
        {	
        	Block current  = loadBlock(jsonBlocks.get(i));
        	previous.setNextBlock(current);
        	blocks.add(current);
        	previous = current;	
        	
        }
        script.setBlocks(blocks);
        return script;
	}
	
	public Block loadBlock(Object b){
		JSONArray blockArray = (JSONArray)b;
		Block result = new Block();
		
		ArrayList<Object> args = new ArrayList<Object>();

		String command = (String) blockArray.remove(0);
		BlockSpec blockSpec = CommandLoader.COMMAND_TO_BLOCK.get(command);
		
		Object arg = null;
		for (int argi = 0; argi < blockArray.size(); argi++) {
			if(blockArray.get(argi) instanceof JSONArray){
				if(((JSONArray)blockArray.get(argi)).get(0) instanceof JSONArray){	//nested blocks
					result.hasNestedBlocks(true);
					arg = new ArrayList<Block>();//stack shape insert (nested blocks) will be list of blocks
					JSONArray blocks = (JSONArray)blockArray.get(argi);	//it's a list of blocks
					
					Block previous = loadBlock(blocks.get(0));
					((List)arg).add(previous);	//		add first block
					result.setFirstChild(previous);
					
					for (int argj = 1; argj < blocks.size(); argj++) {
						Block current  = loadBlock(blocks.get(argj));
			        	previous.setNextBlock(current);
			        	((List)arg).add(current);
			        	previous = current;	
//						((List)arg).add(loadBlock(blocks.get(argj)));
					}
					result.setNestedBlocks(arg);
					
				}else{
					arg = loadBlock(blockArray.get(argi)); //block
				}
			}else{
				arg = blockArray.get(argi); //primitive
			}
			
			args.add(arg);	
		}
		
		result.setCommand(command);
		result.setBlockSpec(blockSpec);
		result.setArgs(args);
		
		return result;
//		return new Block(command,blockSpec,args);
	}
}

