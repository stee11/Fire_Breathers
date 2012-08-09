import javax.swing.JFrame;
public class game
{
	public static void main (String args[])
	{
		JFrame frame=new JFrame("Steven Kolln");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		map Map=new map();
		frame.getContentPane().add(Map);
		
		frame.pack();
		frame.setVisible(true);
	}
}		
		