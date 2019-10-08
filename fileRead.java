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
		System.out.println("State Set: " + stateSet + "\nAlphabet Set: " + alphabetSet + "\nInitial State Set: " + iniStateSet + "\nFinal State Set: " + finStateSet + "\nTransition Sets: " + transitions);
		
		List<String> stateList = Arrays.asList(stateSet.split("\\s*,\\s*"));
		System.out.println("Array of states: " + stateList + " where the first state is exclusively: " + stateList.get(0) + " and the second one is: " + stateList.get(1));
		List<String> alphabetList = Arrays.asList(alphabetSet.split("\\s*,\\s*"));
		List<String> iniStateList = Arrays.asList(iniStateSet.split("\\s*,\\s*"));



	}
}