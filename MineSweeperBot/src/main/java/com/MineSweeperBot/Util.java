package com.MineSweeperBot;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Util {

	public static Pair<Integer, Integer> getImageMatchPoint(BufferedImage field, BufferedImage target) 
	{
		final int fw = field.getWidth();
		final int fh = field.getHeight();
		final int tw = target.getWidth();
		final int th = target.getHeight();
		
		for( int fx=0; fx<fw-tw; fx++ ) {
			for( int fy=0; fy<fh-th; fy++ ) {
				BufferedImage a = field.getSubimage(fx, fy, tw, th);
				BufferedImage b = target;
				
				if( isSameImage(a, b) ) {
					return new Pair<Integer,Integer>(fx,fy);
				}
			}
		}
		
		return null;
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
    public static BufferedImage screenCatpure(final Robot robot,final GraphicsDevice[] screenDevices) throws IOException, AWTException
    {
    	Rectangle rect = new Rectangle(0, 0, 0, 0);
    	
    	for (GraphicsDevice gd : screenDevices) {
    	    rect = rect.union(gd.getDefaultConfiguration().getBounds());
    	}
    	
    	return robot.createScreenCapture(rect);
	}

}
