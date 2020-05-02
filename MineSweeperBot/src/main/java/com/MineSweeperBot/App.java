package com.MineSweeperBot;

import java.awt.AWTException;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import com.MineSweeperBot.model.Cell;
import com.MineSweeperBot.model.Field;
import com.MineSweeperBot.model.Pos;

public class App 
{
	public static Field field;
	public static int cellWidth = 10;
	public static int cellHeight = 8;
	
    public static void main( String[] args ) throws Exception
    {
//    	runDemo();
//    	catpureDemo();
    	
    	Robot robot = new Robot();
    	
    	Rectangle screenRectangle = ImageProcessor.getFullRectangle();
    	BufferedImage monitorImage = robot.createScreenCapture(screenRectangle);
    	BufferedImage screenImage = ImageIO.read(new File("res/screen.png"));
    	Pair<Integer, Integer> screenOffset = Util.getImageMatchPoint(monitorImage, screenImage);
    	
    	if( screenOffset == null )
    	{
//    		throw new Exception("Can not found first screen."); // TODO
    		screenOffset = new Pair<Integer,Integer>(247,357); // DEBUG
    	}
    	
    	Properties prop = new Properties();
    	prop.load(new FileInputStream(new File("res/config.properties")));
    	
    	ImageProcessor imgp = new ImageProcessor( robot, new Rectangle(screenOffset.first,screenOffset.second,screenImage.getWidth(),screenImage.getHeight()), prop );
    	imgp.updateCells();
    }

	

	private static void catpureDemo() throws HeadlessException, IOException, AWTException
	{
		File fieldImgFile = new File("res/field.png");
		BufferedImage fieldImg = ImageIO.read(fieldImgFile);
		Pair<Integer,Integer> src = getSrc(fieldImgFile);
    	
    	if( null == src ) {
    		throw new IOException("Can not found field");
    	}

    	System.out.println(src);
    	
    	int w = fieldImg.getWidth() / cellWidth;
    	int h = fieldImg.getHeight() / cellHeight;
    	for( int y=0; y<cellHeight; y++ )
    	{
    		for( int x=0; x<cellWidth; x++ )
        	{
    			BufferedImage img = fieldImg.getSubimage(x*w, y*h, w, h);
    			
    			if( Util.isSameImage(img, ImageIO.read(new File("res/cell_blank_1.png"))) )
    			{
    				// TODO
    			}
    			else if( Util.isSameImage(img, ImageIO.read(new File("res/cell_blank_2.png"))) )
    			{
    				// TODO
    			}
    			else
    			{
    				File f = new File(String.format("debug/%d_%d.png", x,y));
    				ImageIO.write(img, "png", f);
    				throw new IOException("Unknown cell: " + f.getAbsolutePath());
    			}
        	}	
    	}
    	
	}



	private static Pair<Integer,Integer> getSrc(final File field) throws HeadlessException, IOException, AWTException
	{
		BufferedImage catpure = Util.screenCatpure(new Robot(), GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices());
    	BufferedImage target = ImageIO.read(field);
    	Pair<Integer,Integer> src = Util.getImageMatchPoint(catpure,target);
    	
    	return src;
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
		field = new Field(width,height,cellWidth,cellHeight);
        
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
