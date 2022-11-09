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

	@Override
	public String toString(){
		String client = "CLIENT|" + _key + "|" + _name + "|" + _taxId + "|" + _level + "|";
		if (isReceiverActive()){
			client += "YES";
		} else {
			client += "NO";
		}
		if (_terminals.size() != 0)
			return client + "|" + getActiveTerminals() + "|" + (int)_payments + "|" + (int)_debts;
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

	public boolean isReceiverActive(){
		return _receiveNotifications;
	}
}