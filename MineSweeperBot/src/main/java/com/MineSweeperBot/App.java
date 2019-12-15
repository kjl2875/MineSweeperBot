package com.MineSweeperBot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import com.MineSweeperBot.model.Cell;
import com.MineSweeperBot.model.Field;
import com.MineSweeperBot.model.Pos;

public class App 
{
	public static Field field;
	
    public static void main( String[] args ) throws Exception
    {
    	runDemo();
    	
    	// etc - 화면캡쳐
    	Toolkit toolkit = Toolkit.getDefaultToolkit();
    	Rectangle rect = new Rectangle(toolkit.getScreenSize());
    	BufferedImage img = new Robot().createScreenCapture(rect);
    }

	private static void runDemo()
	{
		/*
			1. 필드값을 UNKNOWN으로 초기화 한다.
			[ UNKNOWN] [ UNKNOWN] [ UNKNOWN]
			[ UNKNOWN] [ UNKNOWN] [ UNKNOWN]
			[ UNKNOWN] [ UNKNOWN] [ UNKNOWN]
			
			2. 가운데 셀값을 8로 정한다.
			[ UNKNOWN] [ UNKNOWN] [ UNKNOWN]
			[ UNKNOWN] [  NUMBER] [ UNKNOWN]
			[ UNKNOWN] [ UNKNOWN] [ UNKNOWN]
			
			3. 주변의 남은 필드수와 주변의 남은 지뢰수가 동일한 것을 확인해서 모두 지뢰로 처리한다.
		 	[    MINE] [    MINE] [    MINE] 
			[    MINE] [  NUMBER] [    MINE] 
			[    MINE] [    MINE] [    MINE] 

		 * */
		final int width = 3;
		final int height = 3;
		field = new Field(width,height);
        
		final int centerX = 1;
		final int centerY = 1;
        
        field.setCell(centerX, centerY, new Cell(Cell.TYPE_NUMBER,8,new Pos(1,1)));
        Cell[] aroundCells = field.getAroundCells(centerX, centerY);
        Cell targetCell = field.getCell(centerX, centerY);
        int leftCells = 0;
        for( Cell c : aroundCells )
        {
        	if( c.getType() == Cell.TYPE_UNKNOWN ) {
        		++leftCells;
        	}
        }
        
        if( leftCells == targetCell.getValue() )
        {
        	for( Cell c : aroundCells )
        	{
        		field.setCell(c.getX(), c.getY(), c.setType(Cell.TYPE_MINE));
        	}
        }
        
        for( int x=0; x<width; x++ )
        {
        	for( int y=0; y<height; y++ )
        	{
        		String type = "";
        		Cell c = field.getCell(x, y);
        		switch( c.getType() )
        		{
        		case Cell.TYPE_MINE:
        			type = "MINE";
        			break;
        		case Cell.TYPE_NUMBER:
        			type = "NUMBER";
        			break;
        		case Cell.TYPE_UNKNOWN:
        			type = "UNKNOWN";
        			break;
        		}
        		
        		System.out.print( String.format("[%8s] ",type) );
        	}
        	
        	System.out.println();
        }
	}
}
