package prr.app.client;

import prr.core.Network;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.app.exception.DuplicateClientKeyException;
import prr.core.exception.DuplClientKeyException;
import prr.core.exception.InvalidIdException;
//FIXME add more imports if needed

/**
 * Show specific client: also show previous notifications.
 */
class DoShowClient extends Command<Network> {

  DoShowClient(Network receiver) {
    super(Label.SHOW_CLIENT, receiver);
    //FIXME add command fields
    addStringField("key", Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException, UnknownClientKeyException{
    //FIXME implement command
    String key = stringField("key");
    try {
      String view = _receiver.viewClient(key);
      _display.addLine(view);
    } 
    catch(InvalidIdException iie){
      throw new UnknownClientKeyException(key);
    }

    _display.display();
  }
}
