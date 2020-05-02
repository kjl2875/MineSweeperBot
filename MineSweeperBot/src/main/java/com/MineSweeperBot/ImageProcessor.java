package com.MineSweeperBot;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.Properties;

public class ImageProcessor
{
	private Robot robot;
	private Rectangle screenRectangle;
	private int offsetFieldHeight;
	
	public ImageProcessor(final Robot robot, final Rectangle screenRectangle, Properties properties)
	{
		this.robot = robot;
		this.screenRectangle = screenRectangle;
		
		offsetFieldHeight = Integer.parseInt(properties.getProperty("offset_field_height"));
    	System.out.println("offsetFieldHeight: " + offsetFieldHeight);
	}
	
	public static boolean isSameImage(BufferedImage a, BufferedImage b)
	{
		if( a.getWidth() != b.getWidth() || a.getHeight() != b.getHeight() ) {
			return false;
		}
		
		for( int y=0; y<a.getHeight(); y++ )
		{
			for( int x=0; x<a.getWidth(); x++ )
			{
				if( a.getRGB(x, y) != b.getRGB(x, y) ) {
					return false;
				}
			}
		}
		
		return true;
	}
	
    
    public static Rectangle getFullRectangle()
    {
    	Rectangle rect = new Rectangle(0, 0, 0, 0);
    	GraphicsDevice[] screenDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
    	
    	for (GraphicsDevice gd : screenDevices) {
    	    rect = rect.union(gd.getDefaultConfiguration().getBounds());
    	}
    	
    	return rect;
    }

	public void updateCells()
	{
		BufferedImage screenImg = robot.createScreenCapture(screenRectangle);
		BufferedImage statusImg = screenImg.getSubimage(0, 0, screenImg.getWidth(), offsetFieldHeight);
		BufferedImage fieldImg = screenImg.getSubimage(0, offsetFieldHeight, screenImg.getWidth(), screenImg.getHeight()-offsetFieldHeight);
	}
	
}
