package prr.core;

public class VideoCommunication extends Communication{
	private int _duration;

	public VideoCommunication(int id, Terminal from, Terminal to, int duration){
		super(id, from, to, "VIDEO");
		_duration = duration;
	}

	protected double computeCost(String plan){
		double price = 10;
		if (plan.equals("NORMAL"))
			price = 30;

		if (plan.equals("GOLD"))
			price = 20;

		return price;
	}

	protected int getSize(){
		return 0;
	}
}