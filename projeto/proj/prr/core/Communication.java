package prr.core;

import java.io.Serializable;
import java.util.List;
import prr.core.exception.DuplTerminalKeyException;

public abstract class Communication implements Serializable{
	private int _id;
	private Terminal _from;
	private Terminal _to;
	double _cost;
	boolean _isOngoing;
	String _type;
	String _message;
	String _status;

	//id comes from position in array of Communications in Network.java
	public Communication(int id, Terminal from, Terminal to, String type){
		_id = id;
		_from = from;
		_to = to;
		_type = type;
		_status = "FINISHED";
	}

	public Communication(){

	}

	public String getType(){
		return _type;
	}

	public void makeSMS(Terminal to, String message)  throws DuplTerminalKeyException {
		if (to.isBusy())
			throw new DuplTerminalKeyException();
		_message = message;
	}

	protected void acceptSMS(Terminal from){

	}

	public void makeVoiceCall(Terminal to){}

	//public String toString(){}



	public int getId(){
		return _id++;
	}

	public boolean equals(Communication c1){
		return (_id == c1.getId());
	}

	public String toString(){
		//type|idCommunication|idSender|idReceiver|units|price|status
		return _type + "|" + _id + "|" + _from.getId() + "|" + _to.getId() + "|" + "units" + "|" + _cost + "|" + _status;
	}
	

	protected abstract double computeCost(String plan);

	protected abstract int getSize();
}