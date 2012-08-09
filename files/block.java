import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
public class block
{
	private static int blockNum=0;
	private char blockType;
	private ImageIcon image;
	Random gen=new Random();
	public block()
	{
		//randomly generates which block will be used
		int num=0;
		num=gen.nextInt(100)+1;
		char letter='a';
		if (num>0&&num<50)
			letter='a';
		if (num>49&&num<80)
			letter='b';
		if (num>79&&num<101)
			letter='c';
		switch (letter)
		{
			case 'a':
				image=new ImageIcon ("grass.gif");
				blockType='g';
				break;
			case 'b':
				image=new ImageIcon ("dirt.gif");
				blockType='d';
				break;
			case 'c':
				image=new ImageIcon ("water.gif");
				blockType='w';
				break;
		}
		blockNum+=1;
	}
	public ImageIcon getImage()
	{
		return image;
	}
}