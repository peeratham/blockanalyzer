package main.java.preprocess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.visitor.VisitFailure;
import main.java.visitor.Visitor;



public class Block implements Visitable {
	
	private String command;
	private ArrayList<Object> args;
	private BlockSpec blockSpec;



	public Block(String command, BlockSpec blockSpec, ArrayList<Object> args) {
		this.command = command;
		this.args = args;
		this.blockSpec = blockSpec;
	}

	public BlockSpec getBlockSpec() {
		return blockSpec;
	}

	@Override
	public String toString() {
		return stringify(this);
	}
	
	private String stringify(Block obj) {
		String pattern = "(%.(?:\\.[A-z]+)?)";	//http://www.regexplanet.com/advanced/java/index.html
//		Pattern r = Pattern.compile(pattern);
//		Matcher m = r.matcher(blockSpec.getSpec());
//		while (m.find()) {
//			System.out.println(m.group());
//		}

		pattern = "%[A-z]\\.[A-z]+|%[A-z]";		
		String fmt = obj.getBlockSpec().getSpec().replaceAll(pattern, "%s");
		
		
		ArrayList<Object> args = (ArrayList<Object>) getArgs();
		ArrayList<String> argString = new ArrayList<String>();
		for(Object o : args){
				if(o instanceof String){
					argString.add("\""+o+"\"");
				}else if(o instanceof Long){
					argString.add(o+"");
				}else if(o instanceof Block){
					if(((Block) o).blockSpec.getShape().equals("boolean")){
						argString.add("("+o+")");
					}else{
						argString.add(o.toString());
					}
					
				}else if(o instanceof ArrayList){
					if(obj.getBlockSpec().getShape().equals("stack")){
						fmt += "%s";
					}
					
					String nested = "";
					for(Object el : (ArrayList<Object>)o){
						nested += "\n";
						nested += el.toString();
					}
					
					nested = nested.replace("\n", "\n    ");
					nested += "\nend";
					argString.add(nested);
					
					
				}
		}
		String[] result = argString.toArray(new String[argString.size()]);
		String formattedString = String.format(fmt,result);
//		System.out.println(formattedString);
		
		return formattedString;
		
	}
	
	public boolean hasCommand(String command){
		return this.command.equals(command);
	}

	public String getCommand() {
		return command;
	}

	public Object getArgs() {
		return this.args;
	}

	@Override
	public void accept(Visitor v) throws VisitFailure {
		v.visitBlock(this);
		
	}

}
