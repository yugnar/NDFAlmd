import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class fileRead{

	static String stateSet;
	static String alphabetSet;
	static String iniStateSet;
	static String finStateSet;
	static LinkedList<String> transitions = new LinkedList<String>();

	static List<String> stateList;
	static List<String> alphabetList;
	static List<String> iniStateList;
	static List<String> finStateList;

	public fileRead(String filename){
		try{
			FileInputStream fStream = new FileInputStream(filename);
			try(BufferedReader br = new BufferedReader(new InputStreamReader(fStream))){
				String strLine;
				int ndx = 0;
				while((strLine = br.readLine()) != null){
					switch(ndx){
						case 0:
						stateSet = strLine;
						ndx++;
						break;
						case 1:
						alphabetSet = strLine;
						ndx++;
						break;
						case 2:
						iniStateSet = strLine;
						ndx++;
						break;
						case 3:
						finStateSet = strLine;
						ndx++;
						case 4:
						ndx++;
						break;
						default:
						transitions.add(strLine);
						ndx++;
					}
				}
			}
			stateList = Arrays.asList(stateSet.split("\\s*,\\s*"));
			alphabetList = Arrays.asList(alphabetSet.split("\\s*,\\s*"));
			iniStateList = Arrays.asList(iniStateSet.split("\\s*,\\s*"));
			finStateList = Arrays.asList(finStateSet.split("\\s*,\\s*"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		fileRead fReader = new fileRead("samplehahae.txt");
		Scanner in = new Scanner (System.in);
		String userString;
		//System.out.println("State Set: " + stateSet + "\nAlphabet Set: " + alphabetSet + "\nInitial State Set: " + iniStateSet + "\nFinal State Set: " + finStateSet + "\nTransition Sets: " + transitions);
		
		Transition[] transitionList = new Transition[transitions.size()];

		System.out.println(stateSet + " & " + alphabetSet + " & " + iniStateSet + " & " + finStateSet);
		System.out.println("The size of LinkedList transitions is: " + transitions.size() + "\nThe object contains: " + transitions);

		for(int i=1; i<=transitions.size(); i++){
			List<String> testingList = Arrays.asList(transitions.get(i-1).split("\\s*,\\s*|=>"));
			Transition t1 = new Transition(testingList.get(0), testingList.get(1), testingList.get(2));
			transitionList[i-1] = t1;
		}

		System.out.println("Transition number 1:\nOrigin: " + transitionList[0].getOrigin() + "\nInput: " + transitionList[0].getInput() + "\nDestination: " + transitionList[0].getDestination());
		System.out.println("Transition number 3:\nOrigin: " + transitionList[2].getOrigin() + "\nInput: " + transitionList[2].getInput() + "\nDestination: " + transitionList[2].getDestination());
		System.out.println("Transition number 5:\nOrigin: " + transitionList[4].getOrigin() + "\nInput: " + transitionList[4].getInput() + "\nDestination: " + transitionList[4].getDestination());
		

		//Construction of NDFA-lambda

		//Construction of Transition Table

		ResultSet[][] transitionTable = new ResultSet[stateList.size()][alphabetList.size()+1];

		for(int i=0; i<stateList.size(); i++){
			for(int j=0; j<alphabetList.size()+1; j++){
				if(j == alphabetList.size()){
					transitionTable[i][j] = new ResultSet(true, i);
				}
				else{
					transitionTable[i][j] = new ResultSet(false, i);
				}
 			}
		}

		for(int i=0; i<transitionList.length; i++){
			//Origin transitionList[i].getOrigin()   transitionList[i].getInput()
			StringBuilder sb = new StringBuilder(transitionList[i].getOrigin());
			sb.deleteCharAt(0);
			int rowIndex = Integer.parseInt(sb.toString());
			int columnIndex = -1;
			for(int k=0; k<alphabetList.size(); k++){
				if(alphabetList.get(k).equals(transitionList[i].getInput())){
					columnIndex = k;
				}
			}
			if(transitionList[i].getInput().equals("lmd")){
				columnIndex = alphabetList.size();
			}
			sb = new StringBuilder(transitionList[i].getDestination());
			sb.deleteCharAt(0);
			int functionResult = Integer.parseInt(sb.toString());
			transitionTable[rowIndex][columnIndex].addToList(functionResult);
		}

		for(int i=0; i<stateList.size(); i++){
			for(int j=0; j<alphabetList.size()+1; j++){
				System.out.print(transitionTable[i][j].states + " ,");
 			}
 			System.out.println("");
		}

		System.out.println("Enter a string to validate.");
		userString = in.nextLine();

		if(extendedTransFunction(userString, transitionTable)){
			System.out.println("String is accepted by the automaton.");
		}
		else{
			System.out.println("String is rejected by the automaton.");
		}
	}


	public static ResultSet transitionFunction(ResultSet initialState, String processChar, ResultSet[][] transitionTable){
		ResultSet tfResult = new ResultSet(false, 0);
		for(int i=0; i<initialState.states.size(); i++){
			int columnIndex = -1;
			for(int k=0; k<alphabetList.size(); k++){
				if(alphabetList.get(k).equals(processChar)){
					columnIndex = k;
				}
			}
			if(processChar.equals("lmd")){
				columnIndex = alphabetList.size();
			}
			ResultSet tTableRes = transitionTable[initialState.states.get(i)][columnIndex];
			for(int j=0; j<tTableRes.states.size(); j++){
				tfResult.addToList(tTableRes.states.get(j));
			}
		}
		return tfResult;
	}

	public static boolean extendedTransFunction(String processString, ResultSet[][] transitionTable){
		boolean acceptString = false;
		StringBuilder sb = new StringBuilder(iniStateList.get(0));
		sb.deleteCharAt(0);
		ResultSet activeStates = new ResultSet(true, Integer.parseInt(sb.toString()));
		for(int i=0; i<processString.length(); i++){
			activeStates = transitionFunction(activeStates, "lmd", transitionTable);
			activeStates = transitionFunction(activeStates, Character.toString(processString.charAt(i)), transitionTable);
		}

		//Parse ending state list
		int[] stateCheck = new int[finStateList.size()];
		for(int i=0; i<finStateList.size(); i++){
			sb = new StringBuilder(finStateList.get(i));
			sb.deleteCharAt(0);
			stateCheck[i] = Integer.parseInt(sb.toString());
		}

		//Validate the final states with activeStates ResultSet
		for(int i=0; i<activeStates.states.size(); i++){
			for(int j=0; j<stateCheck.length; j++){
				if(stateCheck[j] == activeStates.states.get(i)){
					acceptString = true;
				}
			}
		}
		return acceptString;
	}



}