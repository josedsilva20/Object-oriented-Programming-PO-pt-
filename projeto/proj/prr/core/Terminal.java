package prr.core;

import java.io.Serializable;
import java.util.*;
import prr.core.exception.ExistingTermKeyException;
import prr.core.exception.InvalidFriendException;
import prr.core.exception.InvalidIdException;
import prr.core.Network;
import prr.core.exception.TerminalIsOffException;

import prr.core.exception.SendNotificationException;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
public class Terminal implements Serializable, Observer/* FIXME maybe addd more interfaces */{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;
  
  // FIXME define attributes
  private List<Communication> _madeCommunications;
  private List<Communication> _receivedCommunications;
  private Client _owner;
  private List<Terminal> _friends;
  private List<Client> _toNotify;
  private String _id;
  private TerminalMode _mode;
  private double _debts;
  private double _payments;
  private double _balance;
  private String _type;
  private List<Observer> observers = new ArrayList<Observer>();
  private Terminal _sendNotification;


  // FIXME define contructor(s)
  public Terminal(String id, Client owner, String type){
    _id = id;
    _owner = owner;
    _type = type;
    _friends = new ArrayList<Terminal>();
    _toNotify = new ArrayList<Client>();
    _mode = TerminalMode.IDLE;
    _madeCommunications = new ArrayList<>();
    _receivedCommunications = new ArrayList<>();
  }

  public Terminal(){

  }

  // FIXME define methods
  public boolean equals(Terminal terminal){
    return _id.equals(terminal.getId());
  }

  public long getPayments(){
    return Math.round(_payments);
  }

  public void addDebt(double value){
    _debts += value;
    _owner.setDebt(value);
  }

  public long getDebts(){
    return Math.round(_debts);
  }

  public void updateBalance(){
    _balance = _payments - _debts;
  }

  public boolean isActive(){
    return (_mode != TerminalMode.OFF);
  }

  public TerminalMode getTerminalMode(){
    return _mode;
  }

  public long getBalance(){
    return Math.round((_balance));
  }

  public void addFriendTerminal(Terminal friend){
    _friends.add(friend);
  }

  public void removeFriendTerminal(Terminal friend){
    _friends.remove(friend);
  }
  public List<Terminal> getOrderedFriends(){
    List<Terminal> orderedFriends = new ArrayList<Terminal>(_friends);
    orderedFriends.sort(new FriendTerminalByIdComparator());
    return orderedFriends;
  }

  public void addMadeCommunication(VideoCommunication v){
    _madeCommunications.add(v);
  }

  public void addMadeCommunication(VoiceCommunication v){
    _madeCommunications.add(v);
  }

  public void addMadeCommunication(TextCommunication v){
    _madeCommunications.add(v);
  }

  public void addReceivedCommunication(TextCommunication v){
    _receivedCommunications.add(v);
  }

  public void addReceivedCommunication(VoiceCommunication v){
    _receivedCommunications.add(v);
  }

  public void addReceivedCommunication(VideoCommunication v){
    _receivedCommunications.add(v);
  }

  public String getId(){
    return _id;
  }

  public int getMadeCommunications(){
    return _madeCommunications.size();
  }

  public int getReceivedCommunications(){
    return _receivedCommunications.size();
  }

  public String getType() {
    return _type;
  }

  public String toString() {
    String out = "";
    String aux = _type + "|" + _id + "|" + _owner.getKey() + "|" + _mode + "|" + (int)_payments + "|" + (int)_debts;
    List<Terminal> friends = new ArrayList<>(_friends);
    // add all friend terminals separated by ","
    int sizeOfList = friends.size();
    for ( Terminal t : friends) {
        if (sizeOfList !=0 //&& sizeOfList >= 2
        ) 
          out += t.getId() + ",";
        else
          out += t.getId();
      }
      if(sizeOfList != 0)
        out = out.substring(0, (out.length()-1));  
    if (friends.size() > 0)
        return aux + "|" + out;
    return aux;
  }


  public Client getClient(){
    return _owner;
  }
  /**
   * Checks if this terminal can end the current interactive communication.
   *
   * @return true if this terminal is busy (i.e., it has an active interactive communication) and
   *          it was the originator of this communication.
   **/
  public boolean canEndCurrentCommunication() {
    // FIXME add implementation code
    return false;
  }
  
  /**
   * Checks if this terminal can start a new communication.
   *
   * @return true if this terminal is neither off neither busy, false otherwise.
   **/
  public boolean canStartCommunication() {
    return true;
  }
  public void setSilence()throws SendNotificationException{
    if (isBusy()){
      try {
        Notification nbs = new Notification(this, NotificationType.B2S);
        notifyObservers(nbs);
      } catch(SendNotificationException sne){
        throw sne;
      }

    }
    if (isOff()){
      try {
        Notification nos = new Notification(this, NotificationType.O2S);
        notifyObservers(nos);
      } catch(SendNotificationException sne){
        throw sne;
      }
    }
    _mode = TerminalMode.SILENCE;
  }
  public boolean isSilente(){
    return _mode == TerminalMode.SILENCE;
  }

  public void setOff(){
    _mode = TerminalMode.OFF;
  }
  public boolean isOff(){
    return _mode == TerminalMode.OFF;
  }
  public void setOn(){
    _mode = TerminalMode.ON;
  }
  public boolean isOn(){
    return _mode == TerminalMode.ON;
  }
  public void setBusy(){
    _mode = TerminalMode.BUSY;
  }
  public boolean isBusy(){
    return _mode == TerminalMode.BUSY;
  }

  public boolean isIdle(){
    return _mode == TerminalMode.IDLE;
  }

  public void setIdle() throws SendNotificationException{
    if (isBusy()){
      try {
        Notification nbi = new Notification(this, NotificationType.B2I);
        notifyObservers(nbi);
      } catch(SendNotificationException sne){
        throw sne;
      }
    }
    if (isOff() || isSilente()){
      try {
        Notification noi = new Notification(this, NotificationType.O2I);
        notifyObservers(noi);
      } catch(SendNotificationException sne){
        throw sne;
      }

    _mode = TerminalMode.IDLE;
    }
  }

  public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Notification notification) throws SendNotificationException{
      try{
        for (Observer ob : observers) {
          ob.update(notification);
        }
      } catch(SendNotificationException sne){
        throw sne;
      }
    }

    public void update(Notification notification) throws SendNotificationException{
        //_notifications.add(notification);
      throw new SendNotificationException();
    }


    //corrigir _madeCommunications para id que vem do network.
    public Communication sendTextCommunication(Terminal to, String msg) throws SendNotificationException {
      if (to.isOff())
        throw new SendNotificationException();
      long price = 0;
      TextCommunication t = new TextCommunication(_madeCommunications.size(), this, to, msg);
      if(!isOff() && !isBusy()){
        if (_friends.contains(to))
          price = Math.round(t.computeCost(_owner.getClientLevel())/2);
        else  
          price = Math.round(t.computeCost(_owner.getClientLevel()));
      }
      addDebt(price);
      return t;
    }
    
    
}