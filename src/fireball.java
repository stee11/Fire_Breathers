import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class fireball
{
	int x,y;
	int time=100;
	ImageIcon leftfire=new ImageIcon ("leftfire.gif");
	ImageIcon rightfire=new ImageIcon ("rightfire.gif");
	ImageIcon left1=new ImageIcon ("1left.gif");
	ImageIcon left2=new ImageIcon ("2left.gif");
	ImageIcon right1=new ImageIcon ("1right.gif");
	ImageIcon right2=new ImageIcon ("2right.gif");	
	ImageIcon current;
	public fireball(int player, int xcord, int ycord, ImageIcon image)
	{
		ImageIcon leftfire=new ImageIcon ("leftfire.gif");
		ImageIcon rightfire=new ImageIcon ("rightfire.gif");
		ImageIcon left1=new ImageIcon ("1left.gif");
		ImageIcon left2=new ImageIcon ("2left.gif");
		ImageIcon right1=new ImageIcon ("1right.gif");
		ImageIcon right2=new ImageIcon ("2right.gif");
		if (player==1)
		{
			if (image.equals(left1))
			{
				x=xcord-1;
				y=ycord;
				current=leftfire;
				time=100;
			}
			else
			{
				x=xcord+1;
				y=ycord;
				current=rightfire;
				time=100;
			}
		}
		else
		{
			if (image.equals(right2))
			{
				x=xcord+1;
				y=ycord;
				current=rightfire;
				time=100;
			}
			else
			{
				x=xcord-1;
				y=ycord;
				current=leftfire;
				time=100;
			}
		}
	}
	private ImageIcon getImage()
	{
		return current;
	}
	public int move()		
	{
		if (getImage().equals(leftfire))
		{	
			x-=1;
			return x;
		}
		else
		{
		x+=1;
		return x;
		}
	}
	public int getTime()
	{
		return time;
	}
	public int getx()
	{
		return x;
	}
	public int gety()
	{
		return y;
	}
	public void setImage(ImageIcon image)
	{
		current=image;
	}
	public void setLocation(int curx, int cury, char direction)
	{
		
		if (direction=='l')
			current=leftfire;
		else
			current=rightfire;
		x=curx;
		y=cury;
	}
}
			