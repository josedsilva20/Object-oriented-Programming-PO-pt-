package prr.core;

public class TextCommunication extends Communication{
	private String _message;

	public TextCommunication(int id, Terminal from, Terminal to, String message){
		super(id, from, to, "TEXT", message.length());
		_message = message;
	}

	public boolean equals(TextCommunication other) {
		return this.getId() == other.getId();
	}

	//all methods above are going to be overriden.
	@Override
	protected double computeCost(String plan){
		double price = 10;
		if (plan.equals("NORMAL")){
			if (getSize() >= 50 && getSize() < 100)
				price = 16;
			if (getSize() >= 100)
				price = 2 * getSize();
		}

		if (plan.equals("GOLD")){
			if (getSize() >= 100)
				price = 2 * getSize();
		}

		if (plan.equals("PLATINUM")){
			if (getSize() >= 50 && getSize() < 100)
				price = 4;
			else if (getSize() >= 100)
				price = 4;
			else 
				price = 0;
		}
		setCost(Math.round(price));
		return price;
	}

	protected int getSize(){
		return _message.length();
	}
}