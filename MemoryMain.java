package Memory;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MemoryMain extends JFrame implements ActionListener
{
	public MemoryMain()
	{
		
		m = new Menu();
		c = new Card(m);
		
		this.setLayout(new BorderLayout());
		reset = new JButton("Reset");
		reset.addActionListener(this);
		m.add(reset);
		
		add(c,BorderLayout.CENTER);
		add(m,BorderLayout.SOUTH);
		
		
		setBounds(50, 50, 800, 800);
		setTitle("Memory Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
		
		//frame.setSize(400, 125);
	    //frame.setLocation(400, 300);
	}

	public static void main(String[] args)
	{
		new MemoryMain();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		c.reset();
	}
	
	
	private JButton reset;
	private Menu m;
	private Card c;
}
