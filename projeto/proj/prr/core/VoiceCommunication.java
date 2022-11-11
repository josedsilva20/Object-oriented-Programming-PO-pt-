package prr.core;

public class VoiceCommunication extends Communication{
	private int _duration;

	public VoiceCommunication(int id, Terminal from, Terminal to){
		super(id, from, to, "VOICE");
	}

	public boolean equals(VoiceCommunication c1){
		return this.getId() == c1.getId();
	}

	protected double computeCost(String plan){
		double price = 10;
		if (plan.equals("NORMAL")){
			price = 20;
		}
		setCost(Math.round(price));
		return price;
	}

	public void setDuration(int duration){
		_duration = duration;
	}

	public int getDuration(){
		return _duration;
	}

	protected int getSize(){
		return 0;
	}
}