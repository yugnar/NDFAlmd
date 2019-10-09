import java.util.*;

public class ResultSet{

	boolean emptyList = true;
	public LinkedList<Integer> states = new LinkedList<Integer>();

	public ResultSet(boolean isLambda, int parentColumn){
		if(!isLambda){
			states.add(-1);
		}
		else{
			states.add(parentColumn);
			emptyList = false;
		}
	}

	public void addToList(int myState){
		if(emptyList){
			states.pop();
			emptyList = false;
			checkForRepeat(myState);
		}
		else{
			checkForRepeat(myState);
		}
	}

	public void checkForRepeat(int integerCheck){
		boolean foundCoincidence = false;
		for(int i=0; i<states.size();i++){
			if(states.get(i) == integerCheck){
				foundCoincidence = true;
			}
		}
		if(!foundCoincidence){
			states.add(integerCheck);
		}
	}
}