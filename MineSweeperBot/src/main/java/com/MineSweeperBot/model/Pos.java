package com.MineSweeperBot.model;

public class Pos {
	public int x;
	public int y;
	
	public Pos(final Pos pos) {
		this(pos.x,pos.y);
	}
	
	public Pos clone() {
		return new Pos(this.x,this.y);
	}
	
	public Pos() {
		this(0,0);
	}
	
	public Pos(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public void set(final Cell cell) {
		this.x = cell.getX();
		this.y = cell.getY();
	}
}
