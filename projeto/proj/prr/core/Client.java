package prr.core;

import java.util.Set;
import java.io.Serializable;
import java.util.*;
import prr.core.exception.InvalidIdException;
import prr.core.exception.DuplTerminalKeyException;

public class Client implements Serializable{
	private String _key;
	private String _name;
	private double _payments;
	private double _debts;
	private int _taxId;
	private String _level;
	private boolean _receiveNotifications;
	private List<Terminal> _terminals;
	private TariffPlan _tariffPlan;
	private int _numberOfVideoCommunications;
	private boolean _differentFromVideo = false;


	public Client(String key, String name, int taxId){
		_key = key;
		_name = name;
		_taxId = taxId;
		_level = "NORMAL";
		_terminals = new ArrayList<Terminal>();
		_receiveNotifications = true;
	}

	public Client(){

	}

	public void setDebt(double debt){
		_debts += debt;
	}


	@Override
	public String toString(){
		String client = "CLIENT|" + _key + "|" + _name + "|" + _taxId + "|" + _level + "|";
		if (isReceiverActive()){
			client += "YES";
		} else {
			client += "NO";
		}
		if (_terminals.size() != 0)
			return client + "|" + _terminals.size() + "|" + (int)_payments + "|" + (int)_debts;
		return client + "|" + 0 + "|" + 0 + "|" + 0;
	}

	public int getActiveTerminals(){
		int active = 0;
		for (Terminal terminal : _terminals){
			if (terminal.isActive())
				active++;
		}
		return active;
	}

	public void addTerminal(Terminal terminal) throws DuplTerminalKeyException{
		for (Terminal ter : _terminals)
			if (terminal.equals(ter))
				throw new DuplTerminalKeyException();
		_terminals.add(terminal);	

	}

	public boolean equals(Client other) {
		return _key.equals(other.getKey());
	}	
	
	public Client getClientById(String id) throws InvalidIdException{
		if (_key == id)
			return this;
		throw new InvalidIdException();
	}

	public String getKey(){
		return _key;
	}

	public String getName(){
		return _name;
	}

	public String getClientLevel(){
		return _level;
	}

	public long getPayments(){
		return Math.round(_payments);
	}
	public long getDebts(){
		return Math.round(_debts);
	}

	public boolean getNotificationStatus(){
		return _receiveNotifications;
	} 

	public List<Terminal> getTerminals(){
		List<Terminal> aux = new ArrayList<Terminal>(_terminals);
		return aux;
	}

	public void activateNotifications() {
		_receiveNotifications = true;
	}

	public void disableNotifications() {
		_receiveNotifications = false;
	}

	public void iterateVideoCount(){
		if (_numberOfVideoCommunications < 5)
			_numberOfVideoCommunications++;
		else
			_numberOfVideoCommunications = 1;
			_differentFromVideo = true;
	}

	public boolean isReceiverActive(){
		return _receiveNotifications;
	}
	public void updateLevel(){
		if (getClientLevel().equals("NORMAL"))
			if((_payments - _debts) > 500)
				_level = "GOLD";
		if (getClientLevel().equals("GOLD")){
			if((_payments - _debts) < 0)
				_level = "NORMAL";
			if (((_payments - _debts) > 0) && !_differentFromVideo)
				_level = "PLATINUM";
		}
		if (getClientLevel().equals("NORMAL")){
			if((_payments - _debts) > 500)
				_level = "GOLD";
		}
	}
}