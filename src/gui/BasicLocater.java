package gui;

import game.Model;
import game.PathGrid;
import net.CmdSender;

import org.newdawn.slick.Graphics;

import data.Sprite;

public abstract class BasicLocater extends PlayerMouse.Mode{

	Sprite spr;
	int state;
	
	/**
	 * Decide what happens when you click the button
	 */
	public abstract void execute(int x, int y, CmdSender sndr, int[] is, Model m);

	/**
	 * Using the model, decide if
	 */
	public abstract int evaluate(int x, int y, Model m, PathGrid pg);
	
	@Override
	public void draw(Graphics g, int camX, int camY, Model m) {
		if(!hidden){
			spr.draw(state, Mouse.i().x, Mouse.i().y);
		}
	}

	@Override
	public void update(int dt, int camX, int camY, Model m, PathGrid pg,
			CmdSender sndr, Hud hd, PlayerMouse pm) {
		
		state = evaluate(Mouse.i().x - camX, Mouse.i().y - camY, m, pg);
		
		if(Mouse.i().buttons[0]){
			execute(Mouse.i().x - camX,Mouse.i().y - camY, sndr, hd.getSelectedUnits(), m);
			pm.defaultMode();
		}
		if(Mouse.i().buttons[1]){
			pm.defaultMode();
		}
	}

	public PlayerMouse.Mode setSpr(Sprite spr){
		this.spr = spr;
		return this;
	}
	
	public boolean unlocked() {
		return true;
	}

	
}
