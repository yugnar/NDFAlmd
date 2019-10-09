public class Laboratory{
	public static void main(String[] args) {
		ResultSet[][] transitionTable = new ResultSet[2][2];

		for(int i=0; i<2; i++){
			for(int j=0; j<2; j++){
				transitionTable[i][j] = new ResultSet();
 			}
		}

		for(int i=0; i<2; i++){
			for(int j=0; j<2; j++){
				System.out.print(transitionTable[i][j].states + " ,");
 			}
 			System.out.println("");
		}
	}
}