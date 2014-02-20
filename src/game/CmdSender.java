package game;

import java.util.LinkedList;
import java.util.List;

import commands.Command;

public abstract class CmdSender {
	private List<Command> cmds;
	
	public CmdSender(){
		emptyCmds();
	}
	
	private void emptyCmds() {
	//Not sure if LinkedList > Array list in this use case...
	//We don't *know* if we iterate more than once, but we know
	//That we might add several times.
		cmds = new LinkedList<Command>();
	}

	public void rcv(Command c){
		cmds.add(c);
	}
	public void updateTick(){
		pushToServer(cmds);
		emptyCmds();
	}
	
	/**
	 * Push any commands to the server; otherwise send a keepalive signal.
	 */
	public abstract void pushToServer(List<Command> cmds);
}