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
		
		List<String> stateList = Arrays.asList(stateSet.split("\\s*,\\s*"));
		//System.out.println("Array of states: " + stateList + " where the first state is exclusively: " + stateList.get(0) + " and the second one is: " + stateList.get(1));
		List<String> alphabetList = Arrays.asList(alphabetSet.split("\\s*,\\s*"));
		List<String> iniStateList = Arrays.asList(iniStateSet.split("\\s*,\\s*"));
		List<String> finStateList = Arrays.asList(finStateSet.split("\\s*,\\s*"));
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

		int[][] transitionTable = new int[stateList.size()][alphabetList.size()+1];
		for(int i=0; i<stateList.size(); i++){
			for(int j=0; j<alphabetList.size()+1; j++){
				transitionTable[i][j] = -1;
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
			transitionTable[rowIndex][columnIndex] = functionResult;
		}

		for(int i=0; i<stateList.size(); i++){
			for(int j=0; j<alphabetList.size()+1; j++){
				System.out.print(transitionTable[i][j] + " ,");
 			}
 			System.out.println("");
		}

		//System.out.println("Okay, so for the third transition received the parameters should be as follows:\nOrigin: " + transitionList[2].getOrigin() + "\nInput: " + transitionList[2].getInput() + "\nDestination: " + transitionList[2].getDestination());

		//Validate user's input string

		//System.out.println("Please enter the string to validate:");
		//userString = in.nextLine();


	}
}