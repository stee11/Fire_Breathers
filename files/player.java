import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class player
{
	int health,power,x,y, num, redtime1=0, redtime2=0;
	ImageIcon current, left1, left2,right1,right2;
	ImageIcon lefthurt=new ImageIcon ("lefthurt.gif");
	ImageIcon righthurt=new ImageIcon ("righthurt.gif");
	char dir;
	boolean lost=false;
	public player(int one)
	{
		left1=new ImageIcon ("1left.gif");
		left2=new ImageIcon ("2left.gif");
		right1=new ImageIcon ("1right.gif");
		right2=new ImageIcon ("2right.gif");
		//chooses what color and stats the player will have if player one or two
		if (one==1)
		{
			current=right1;
			x=0;
			y=510;
			dir='r';
		}
		if (one==2)
		{
			current=left2;
			x=1120;
			y=510;
			dir='l';
		}
		health=3;
		power=1;
		num=one;
	}
	public int getHealth()
	{
		return health;
	}
	//loses one health and if not dead go into readtime which makes the player flash
	public void damage()
	{
		health-=1;
		if (health==0)
			lost=true;
		if (getPlayer()==1)
			redtime1=250;
		else
			redtime2=250;
		
	}
	public int getPower()
	{
		return power;
	}
	public int powerUp()
	{
		power+=1;
		return power;
	}
	public ImageIcon setImage(ImageIcon image)
	{
		current=image;
		return current;
	}
	//move methods of the players
	public int moveUp()
	{
		y-=20;
		if (y<0)
			y=0;
		if (y>510)
			y=510;
		return y;
	}
	public int moveDown()
	{
		y+=20;
		if (y<0)
			y=0;
		if (y>510)
			y=510;
		return y;
	}
	public int moveLeft()
	{
		if (num==1)
		{
			current=left1;
			dir='l';
		}
		if (num==2)
		{
			dir='l';
			current=left2;
		}
		x-=20;
		if (x<0)
			x=0;
		if (x>1120)
			x=1120;
		return x;
	}
	public int moveRight()
	{
		if (num==1)
		{
			current=right1;
			dir='r';
		}
		if (num==2)
		{
			current=right2;
			dir='r';
		}
		x+=20;
		if (x<0)
			x=0;
		if (x>1120)
			x=1120;
		return x;
	}
	public int getx()
	{
		return x;
	}
	public int gety()
	{
		return y;
	}
	public ImageIcon getImage()
	{
		return current;
	}
	public int getPlayer()
	{
		return num;
	}
	public void setLocation (int newx, int newy)
	{
		x=newx;
		y=newy;
	}
	public void red()
	{
		redtime1-=1;
		redtime2-=1;
	}
}		