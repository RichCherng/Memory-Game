package Memory;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu extends JPanel
{
	public Menu()
	{
		tfNotification = new JTextField("Pick A Card",25);
		
		add(tfNotification,BorderLayout.PAGE_END);
	}
	
	public void match()
	{
		tfNotification.setText("You Found A Match!!");
	}
	
	public void noMatch()
	{
		tfNotification.setText("No Match! Try Again!");
	}
	
	public void won()
	{
		tfNotification.setText("You Won!!");
	}
	
	public void pick()
	{
		tfNotification.setText("Pick A Card");
	}
	private JTextField tfNotification;
}
