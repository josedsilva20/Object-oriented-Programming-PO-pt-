package prr.core;

public class VoiceCommunication extends Communication{
	private int _duration;

	public VoiceCommunication(int id, Terminal from, Terminal to, int duration){
		super(id, from, to, "VOICE");
		_duration = duration;
	}

	protected double computeCost(String plan){
		double price = 10;
		if (plan.equals("NORMAL")){
			price = 20;
		}
		return price;
	}

	protected int getSize(){
		return 0;
	}
}