package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.exception.TerminalIsOffException;
import prr.core.exception.TerminalIsBusyException;
import prr.core.exception.UnsopportedFromComException;
import prr.core.exception.UnsopportedComToException;
import prr.core.exception.SendNotificationException;
import prr.core.exception.InvalidIdException;
import prr.core.exception.DestinatioOffException;


/**
 * Command for starting communication.
 */
class DoStartInteractiveCommunication extends TerminalCommand {

  DoStartInteractiveCommunication(Network context, Terminal terminal) {
    super(Label.START_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
    addStringField("to", Message.terminalKey());
    addOptionField("type", Message.commType(), "VOICE", "VIDEO");
  }
  
  @Override
  protected final void execute() throws CommandException {
    String type = optionField("type");
    String to = stringField("to");
    try {
      _network.startInteractiveCommunication(type, _receiver.getId(), to);
    }
    catch (InvalidIdException iie){
      throw new UnknownTerminalKeyException(to);
    }
    catch(SendNotificationException sne){
      _display.addLine(Message.originIsBusy(_receiver.getId()));
    }
    catch (TerminalIsBusyException tibe){
      _display.addLine(Message.destinationIsBusy(to));
    }
    catch (TerminalIsOffException tioe){
      _display.addLine(Message.originIsOff(_receiver.getId()));
    }

    catch (DestinatioOffException doe){
      _display.addLine(Message.destinationIsOff(to));
    }

    catch (UnsopportedComToException ucte){
      _display.addLine(Message.unsupportedAtDestination(to, type));
    }
    _display.display();
  }
}
//FIX NAO PODE RECEBER COMUNICACOES DE VOZ
