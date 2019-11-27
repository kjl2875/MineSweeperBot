package com.MineSweeperBot.model;

public class Cell
{
	public static final int TYPE_NUMBER = 0;
	public static final int TYPE_MINE = 1;
	public static final int TYPE_UNKNOWN = 2;
	
	private int type;
	private int value;
	private Pos pos;
	
	public Cell(final int type) {
		this(type,0);
	}
	
	public Cell(final int type, final Pos pos) {
		this(type,0,pos);
	}
	
	public Cell(final int type, final int value) {
		this(type,value,new Pos());
	}
	
	public Cell(final int type, final int value, final Pos pos) {
		set(type,value,new Pos(pos));
	}
	
	public void set(final Cell cell) {
		set(cell.getType(),cell.getValue(),new Pos(cell.getX(),cell.getY()));
	}
	public void set(final int type, final int value) {
		set(type,value,new Pos());
	}
	public void set(final int type, final int value, final Pos pos) {
		this.type = type;
		this.value = value;
		this.pos = pos.clone();
	}
	
	public Cell clone() {
		return new Cell(this.type,this.value,pos.clone());
	}
	
	public int getType() {
		return this.type;
	}
	
	public int getX() {
		return this.pos.x;
	}
	
	public int getY() {
		return this.pos.y;
	}
	
	public int getValue() {
		return this.value;
	}

	public Cell setType(final int type) {
		this.type = type;
		return this;
	}
}
