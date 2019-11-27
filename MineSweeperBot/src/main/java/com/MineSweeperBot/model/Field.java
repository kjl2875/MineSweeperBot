package com.MineSweeperBot.model;

import java.util.ArrayList;
import java.util.List;

public class Field
{
	private int height;
	private int width;
	private Cell [][]field;
	
	public Field(final int width, final int height)
	{
		this.height = height;
		this.width = width;
		
		field = new Cell[this.height][this.width];
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				field[y][x] = new Cell(Cell.TYPE_UNKNOWN,new Pos(x,y));
			}
		}
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public Cell getCell(final int x, final int y)
	{
		return field[y][x].clone();
	}
	
	public void setCell(final int x, final int y, final Cell cell)
	{
		field[y][x].set(cell);
	}
	
	public Cell[] getAroundCells(final int x, final int y)
	{
		List<Cell> aroundCells = new ArrayList<Cell>();
		
		Pos []ls = new Pos[8];
		ls[0] = new Pos(x-1,y-1);
		ls[1] = new Pos(x+0,y-1);
		ls[2] = new Pos(x+1,y-1);
		ls[3] = new Pos(x-1,y+0);
		ls[4] = new Pos(x+1,y+0);
		ls[5] = new Pos(x-1,y+1);
		ls[6] = new Pos(x+0,y+1);
		ls[7] = new Pos(x+1,y+1);
		
		for( int i=0; i<8; i++ )
		{
			Pos p = ls[i];
			if( 0 > p.x || p.x >= this.width ) {
				continue;
			}
			if( 0 > p.y || p.y >= this.height ) {
				continue;
			}
			
			aroundCells.add(field[p.y][p.x]);
		}
		
		return aroundCells.toArray(new Cell[0]);
	}
}
