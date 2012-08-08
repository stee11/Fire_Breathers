import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class map extends JPanel
{
	//length and height refer to there being 12 blocks of height and 12 blocks of length
	final int length=12, height=12;
	//int time1=100, time2=100;
	private final ImageIcon left1, left2, right1, right2;
	private boolean on=false;
	private Timer timer;
	private int count1=1, count2=1, redtime1=0, redtime2=0;
	//two demensional array of the map
	private ImageIcon[][] blocks=new ImageIcon[length][height];
	private ImageIcon leftfire=new ImageIcon ("leftfire.gif");
	private ImageIcon rightfire=new ImageIcon ("rightfire.gif");
	private ImageIcon lefthurt=new ImageIcon ("lefthurt.gif");
	private ImageIcon righthurt=new ImageIcon ("righthurt.gif");
	private player player1=new player(1);
	private player player2=new player(2);
	private fireball fire21=new fireball (2,-100, -100, player2.getImage());
	private fireball fire22=new fireball (2,-100, -100, player2.getImage());
	private fireball fire11=new fireball (2,-100, -100, player1.getImage());
	private fireball fire12=new fireball (2,-100, -100, player1.getImage());
	private ImageIcon win1=new ImageIcon ("win1.gif");
	private ImageIcon win2=new ImageIcon ("win2.gif");
		
	public map()
	{
		for (int l=0;l<length;l++)
		{
			for (int w=0;w<height;w++)
			{
				//stores blocks into half the map
				block BlockMaker=new block();
				blocks[l][w]=BlockMaker.getImage();
				w++;
			}
			l++;
			
		}
	addKeyListener (new DirectionListener());
	left1=new ImageIcon ("1left.gif");
	left2=new ImageIcon ("2left.gif");
	right1=new ImageIcon ("1right.gif");
	right2=new ImageIcon ("2right.gif");
	
	////1350*750
	timer=new Timer(0, new fireballListener());
	setPreferredSize (new Dimension (1366,610));
	setBackground (Color.black);
	setFocusable(true);
	timer.start();
	}
	public void paintComponent (Graphics page)
	{
		//paints the page with the blocks in the array. Paints half the page, flips the array over and paints the other half to create two equals sides
		super.paintComponent (page);
		for (int l=0;l<length;l++)
		{
			for (int w=0;w<height;w++)
			{
				blocks[l][w].paintIcon (this, page, (l*50), (w*50));
				//blocks[l][w].paintIcon (this, page, (1100-(l*50)), (500-(w*50)));
				blocks[l][w].paintIcon (this, page, (1100-(l*50)), ((w*50)));
				w++;
			}
			l++;
		}
		//paints player locations
		player2.current.paintIcon (this, page, player2.x,player2.y);
		player1.current.paintIcon (this, page, player1.x,player1.y);
		fire21.current.paintIcon (this, page, fire21.x, fire21.y );
		fire22.current.paintIcon (this, page, fire22.x, fire22.y );
		fire11.current.paintIcon (this, page, fire11.x, fire11.y );
		fire12.current.paintIcon (this, page, fire12.x, fire12.y );
		//image displayed when a winner is decided
		if (player1.lost==true)
		{
			win2.paintIcon (this, page, 300, 0);
		}
		if (player2.lost==true)
		{
			win1.paintIcon (this, page, 300,0);
		}
	}
	private class DirectionListener implements KeyListener
	{
		public void keyPressed (KeyEvent event)
		{
			switch (event.getKeyCode())
			{
				case KeyEvent.VK_W:
					player1.moveUp();
					break;
				case KeyEvent.VK_A:
					player1.moveLeft();
					break;
				case KeyEvent.VK_D:
					player1.moveRight();
					break;
				case KeyEvent.VK_S:
					player1.moveDown();
					break;
				case KeyEvent.VK_SPACE:
				//Alternates between the two fireballs being used
					if (player1.lost==false)
					{
					if (count1==1)
					{
						fire11.setLocation (player1.getx(), player1.gety(), player1.dir);
						count1=2;
					}
					else
					{
						fire12.setLocation (player1.getx(), player1.gety(), player1.dir);
						count1=1;
					}
					}
					break;
				case KeyEvent.VK_UP:
					player2.moveUp();
					break;
				case KeyEvent.VK_DOWN:
					player2.moveDown();
					break;
				case KeyEvent.VK_LEFT:
					player2.moveLeft();
					break;
				case KeyEvent.VK_RIGHT:
					player2.moveRight();
					break;
				case KeyEvent.VK_NUMPAD0:
				//Alternates between the two fireballs being used
					if (player2.lost==false)
					{
					if (count2==1)
					{
						fire21.setLocation(player2.getx(), player2.gety(), player2.dir);
						count2=2;
					}
					else
					{
						fire22.setLocation (player2.getx(), player2.gety(), player2.dir);
						count2=1;
					}
					}
					break;
					
			}
			repaint();
		}
		public void keyTyped (KeyEvent event) {}
		public void keyReleased (KeyEvent event) {}
	}
	private class fireballListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{
			//if either player has lost, sets the location of the fireballs off the map to prevent further shooting
			if (player1.lost==true||player2.lost==true)
			{
				fire21.setLocation(1,1000, 'l');
				fire22.setLocation(1,1000, 'l');
				fire11.setLocation(1,1000, 'l');
				fire12.setLocation(1,1000, 'l');
			}
			//moves the fireballs
			fire21.move();
			fire22.move();
			fire11.move();
			fire12.move();
			//for loops check if the fireballs are on location of a player, if so they take damage and will set their red time value to 250. They will flash red until
			//this values reaches zero again
			for (int j=(fire21.y-70);j<(fire21.y+10);j++)
			{
				if (player1.x==fire21.x&&player1.y==j)
				{
					//sets red time to 250
					player1.damage();
					if (player1.dir=='l')
						player1.setImage(lefthurt);
					else
						player1.setImage(righthurt);
				}
			}
			for (int j=(fire22.y-70);j<(fire22.y+10);j++)
			{
				if (player1.x==fire22.x&&player1.y==j)
				{
					//sets red time to 250
					player1.damage();
					if (player1.dir=='l')
						player1.setImage(lefthurt);
					else
						player1.setImage(righthurt);
				}
			}
			for (int j=(fire11.y)-70;j<(fire11.y+10);j++)
			{
				if (player2.x==fire11.x&&player2.y==j)
				{
					//sets red time to 250
					player2.damage();
					if (player2.dir=='l')
						player2.setImage(lefthurt);
					else
						player2.setImage(righthurt);
				}
			}
			for (int j=(fire12.y-70);j<(fire12.y+10);j++)
			{
				if (player2.x==fire12.x&&player2.y==j)
				{
					//sets red time to 250
					player2.damage();
					if (player2.dir=='l')
						player2.setImage(lefthurt);
					else
						player2.setImage(righthurt);
				}
			}
			if (player1.redtime1>0&&((player1.redtime1<50)||(player1.redtime1>100&&player1.redtime1<150)||(player1.redtime1>200&&player1.redtime1<250)))
			{
				if (player1.dir=='l')
					player1.setImage(lefthurt);
				else
					player1.setImage(righthurt);
			}
			else
			{
				if (player1.dir=='l')
					player1.setImage(left1);
				else
					player1.setImage(right1);
			}
			if (player2.redtime2>0&&((player2.redtime2<50)||(player2.redtime2>100&&player2.redtime2<150)||(player2.redtime2>200&&player2.redtime2<250)))
			{
				if (player2.dir=='l')
					player2.setImage(lefthurt);
				else
					player2.setImage(righthurt);
			}
			else
			{
				if (player2.dir=='l')
					player2.setImage(left2);
				else
					player2.setImage(right2);
			}
			player1.red();
			player2.red();
			repaint();
			}
		}
	}
	