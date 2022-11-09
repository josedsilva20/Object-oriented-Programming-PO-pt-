package prr.core;

public class TextCommunication extends Communication{
	private String _message;

	public TextCommunication(int id, Terminal from, Terminal to, String message){
		super(id, from, to, "TEXT");
		_message = message;
	}

	//all methods above are going to be overriden.
	protected double computeCost(String plan){
		double price = 10;
		if (plan.equals("NORMAL")){
			if (50 >= getSize() && getSize() < 100)
				price = 16;
			if (getSize() >= 100)
				price = 2 * getSize();
		}

		if (plan.equals("GOLD")){
			if (getSize() >= 100)
				price = 2 * getSize();
		}

		if (plan.equals("PLATINUM")){
			if (50 >= getSize() && getSize() < 100)
				price = 4;
			else if (getSize() >= 100)
				price = 4;
			else 
				price = 0;
		}
		return price;
	}

	protected int getSize(){
		return _message.length();
	}
}