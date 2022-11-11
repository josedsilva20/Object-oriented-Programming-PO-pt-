package prr.core;

import java.io.Serializable;
import java.io.IOException;
import prr.core.exception.InvalidIdException;
import prr.core.exception.UnrecognizedEntryException;
import java.util.*;
import java.io.Serializable;
import prr.core.exception.ExistingTermKeyException;
import prr.core.exception.DuplTerminalKeyException;
import prr.core.exception.ClientNotificationException;
import prr.core.exception.TerminalIsOffException;
import prr.core.exception.TerminalIsBusyException;
import prr.core.exception.UnsopportedFromComException;
import prr.core.exception.UnsopportedComToException;
import prr.core.exception.SendNotificationException;
import prr.core.exception.DestinatioOffException;

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;

   // FIXME define attributes
  private List<Client> _clients;
  private List<Communication> _communications;
  private List<Terminal> _terminals;
  private Parser _parser;


  // FIXME define contructor(s)
  public Network(){
      _clients = new ArrayList<>();
      _communications = new ArrayList<>();
      _terminals = new ArrayList<>();
  }

  //________________________________________________________________
  // Client code

  public List<String> viewAllClients(){
    List<String> clients = new ArrayList<>();
    for (Client client : _clients){
      clients.add(client.toString());
    }
    return clients;
  }

  public String viewClient(String id) throws InvalidIdException{
    Client client = getClientById(id);
    if (client.getKey() == null)
      throw new InvalidIdException();
    return client.toString();
  }
  

  public void registerClient(String key, String name, int taxNumber) throws ExistingTermKeyException{
    Client cl = new Client(key, name, taxNumber);
    for (Client client : _clients)
      if (client.equals(cl))
        throw new ExistingTermKeyException();
    _clients.add(cl);
  }

  public void disableClientNotifications(String id) throws ClientNotificationException, InvalidIdException{
    Client cl = getClientById(id);
    if (cl.getKey() == null) 
      throw new InvalidIdException();
    if (!cl.isReceiverActive())
      throw new ClientNotificationException();
    cl.disableNotifications();
  }

  public void activateClientNotifications(String id) throws InvalidIdException, ClientNotificationException{
    Client cl = getClientById(id);
    if (cl.getKey() == null) 
      throw new InvalidIdException();
    if (cl.isReceiverActive())
      throw new ClientNotificationException();
    cl.activateNotifications();
  }
  public Client getClientById(String id) {
    Client cl = new Client();
    for (Client client : _clients){
      if (client.getKey().equals(id))
        return client;
    }
    return cl;
  }
  
  public long getClientPayments(String id) throws InvalidIdException{
    Client cl =  getClientById(id);
    if (cl.getKey() == null)
      throw new InvalidIdException();
    return cl.getPayments();
  }

  public long getClientDebts(String id) throws InvalidIdException{
    Client cl =  getClientById(id);
    if (cl.getKey() == null)
      throw new InvalidIdException();
    return cl.getDebts();
  }

  public List<String> getClientsBalanceStatus(String type){
    List<String> clients = new ArrayList<>();
    for(Client c : _clients){
      if (type.equals("negative") && (c.getPayments() - c.getDebts()) < 0)
        clients.add(c.toString());
      if(type.equals("positive") && (c.getPayments() - c.getDebts() >= 0))
        clients.add(c.toString());
    }

    return clients;
  }


  //________________________________________________________________
  // Terminals/Terminal code

  /*public List<Terminal> getTerminals(){
    List<Terminal> ret = new ArrayList<>(_terminals);
    return ret;
  }*/

  public List<String> showAllTerminals(){
    List<String> terminals = new ArrayList<>();
    for (Terminal t : _terminals){
      terminals.add(t.toString());
    }
    return terminals;
  }


  public Terminal getTerminalByIdAux(String id) {
    Terminal aux = new Terminal();
    for (Terminal t : _terminals){
      if (t.getId().equals(id))
        return t;
    }
    return aux;
  }

  public Terminal getTerminalById(String id) throws InvalidIdException{
    if (getTerminalByIdAux(id).getId() == null)
      throw new InvalidIdException();
    return getTerminalByIdAux(id);
  }

  public void registerTerminal(String id, String type, String ownerId) //
  throws InvalidIdException, IllegalArgumentException, //
  ExistingTermKeyException, DuplTerminalKeyException{
    Client owner = getClientById(ownerId);
    Terminal terminal = new Terminal(id, owner, type);

    for(Terminal t : _terminals)
      if (t.equals(terminal)) 
        throw new ExistingTermKeyException();

    if (getClientById(ownerId).getKey() == null)
      throw new IllegalArgumentException();

    else if (id.length() != 6)
      throw new InvalidIdException();

    _terminals.add(terminal);
    owner.addTerminal(terminal);
  }

  public void addFriend(Terminal terminal, String id)  throws InvalidIdException{
    if (!_terminals.contains(getTerminalById(id)))
      throw new InvalidIdException();
    terminal.addFriendTerminal(getTerminalById(id));
  }

  public void removeFriend(Terminal terminal, String id)  throws InvalidIdException{
    if (!_terminals.contains(getTerminalById(id)))
      throw new InvalidIdException();
    terminal.removeFriendTerminal(getTerminalById(id));
  }

  public List<String> getTerminalsWithPositiveBalance(){
    List<String> terminals = new ArrayList<>();
    for(Terminal t : _terminals){
      if ((t.getPayments() - t.getDebts()) > 0)
        terminals.add(t.toString());
    }
    return terminals;
  }

  public List<String> getUnusedTerminals(){
    List<String> terminals = new ArrayList<>();
    for(Terminal t : _terminals){
      if (t.getMadeCommunications() == 0)
        terminals.add(t.toString());
    }
    return terminals;
  }
  public void silenceTerminal(Terminal terminal) throws InvalidIdException, SendNotificationException{
    if (terminal.isSilente())
      throw new InvalidIdException();
    if (terminal.isIdle()) {
      try{
        terminal.setSilence();
      } catch(SendNotificationException sne){
        throw sne;
      }
    }
    if (terminal.isBusy()) {
      try{
        terminal.setSilence();
      } catch(SendNotificationException sne){
        throw sne;
      }
      //end communication
    }
  }

  public void turnOffTerminal(Terminal terminal) throws InvalidIdException{
    if (terminal.isOff())
      throw new InvalidIdException();
    
    if (terminal.isIdle()) {
      terminal.setOff();
    }
    if (terminal.isSilente()) {
      terminal.setOff();
    }
  }

  public void turnOnTerminal(Terminal terminal) throws InvalidIdException{
    if (terminal.isOn())
      throw new InvalidIdException();
    terminal.setOn();
    //terminal.setIdle();
  }

  public void idleTerminal(Terminal terminal) throws InvalidIdException, SendNotificationException{
    if (terminal.isIdle())
      throw new InvalidIdException();
    try{
      terminal.setIdle();
    } catch(SendNotificationException sne){
      throw sne;
    }
  }
  
  public void sendTextMessage(Terminal from, String to, String msg) throws InvalidIdException, SendNotificationException {
    Communication com;
    try {
      Terminal t = getTerminalById(to);
      com = from.sendTextCommunication(t, msg);
    }
    catch(InvalidIdException iie){
      throw iie;
    }
    catch(SendNotificationException sne){
      throw sne;
    }
    _communications.add(com);
  }

  public List<String> showAllCommunications(){
    List<String> lst = new ArrayList<String>();
    for (Communication c : _communications){
      lst.add(c.toString());
    }
    return lst;
  }

  public void startInteractiveCommunication(String type,String from, String to)throws SendNotificationException, InvalidIdException, //
    TerminalIsBusyException, TerminalIsOffException, UnsopportedComToException, UnsopportedComToException, InvalidIdException,
    DestinatioOffException{
      
      try{
        Terminal fr = getTerminalById(from);
        Terminal t = getTerminalById(to);
      }
      
      catch(InvalidIdException iie){
        throw iie;
      }

      if(getTerminalById(from).isBusy() || getTerminalById(from).isOff())
        throw new SendNotificationException();

      else if (getTerminalById(from).isOff())
        throw new TerminalIsOffException();

      else if(getTerminalById(to).isBusy())
        throw new TerminalIsBusyException();
      
      else if(getTerminalById(to).isOff())
        throw new DestinatioOffException();

      else if(!(getTerminalById(to).getType().equals("FANCY")))
        throw new UnsopportedComToException();
      
      if (type.equals("VOICE"))
        startVoiceCall(getTerminalById(from), getTerminalById(to));
      else
        startVideoCall(getTerminalById(from), getTerminalById(to));
    }

  public void startVideoCall(Terminal from, Terminal to) {
    VoiceCommunication v = new VoiceCommunication(_communications.size(), from, to);
    v.setStatus("ONGOING");
    from.addMadeCommunication(v);
    to.addReceivedCommunication(v);
    _communications.add(v);
    from.getClient().iterateVideoCount();
  }

  public void startVoiceCall(Terminal from, Terminal to) {
    VideoCommunication v = new VideoCommunication(_communications.size(), from, to);
    v.setStatus("ONGOING");
    from.addMadeCommunication(v);
    to.addReceivedCommunication(v);
    _communications.add(v);
  }


  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   */
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    //FIXME implement method
    Parser parse = new Parser(this);
    parse.parseFile(filename);
  }

  
}