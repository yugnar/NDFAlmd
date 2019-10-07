import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class fileRead{

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
		String stateSet;
		String alphabetSet;
		String iniStateSet;
		String finStateSet;
		LinkedList<String> transitions = new LinkedList<String>();
		fileRead fReader = new fileRead("samplehahae.txt");
		System.out.println("State Set: " + stateSet + "\nAlphabet Set: " + alphabetSet + "\n initial State Set: " + iniStateSet + "\n final State Set: " + finStateSet + "\n transition Sets: " + transitions);
	}
}