public class Transition{
	private String origin;
	private String input;
	private String destination;

	public Transition(String origin, String input, String destination){
		this.origin = origin;
		this.input = input;
		this.destination = destination;
	}

	public String getOrigin(){
		return origin;
	}
	public void setOrigin(String origin){
		this.origin = origin;
	}
	public String getInput(){
		return input;
	}
	public void setInput(String input){
		this.input = input;
	}
	public String getDestination(){
		return destination;
	}
	public void setDestination(String destination){
		this.destination = destination;
	}
}