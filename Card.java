package Memory;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Card extends JPanel implements ActionListener
{
	JButton[][] buttons = new JButton[4][4];
	Integer[][] bValue = new Integer[4][4];
	boolean[] check = new boolean[8]; 
	
	Image[] img = new Image[8];
	
	String[] imageURL = {"doge.png","Genius-meme.png",
			"images.jpg","moonmoon.jpg","pedobear.png",
			"sloth.jpg","please.png","face.jpg"};
	
	private boolean first,second;
	private JButton clicked,sClicked;
	private Menu m;
	private int count;
	private int value_firstClick,value_SecondClick;
	
	public Card(Menu menu)
	{
		// JButton b = new JButton(new ImageIcon("java.gif"));
		m = menu;
		setLayout(new  GridBagLayout());
		createButtons();
		getImage();
		first = false;
		second = false;
	}
	
	public void reset()
	{
		try 
		{
			Image img = ImageIO.read(new File("back.gif"));
			for(int y = 0; y < buttons.length;y++)
			{
				for(int x = 0; x < buttons.length; x++)
				{
					buttons[x][y].setIcon(new ImageIcon(img));
				}
			}
		} catch (IOException e) 
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		setValue();
		first = true;
		count = 0;
	}
	
	public void createButtons()
	{
		GridBagConstraints c = new GridBagConstraints();
		for(int y = 0; y < buttons.length;y++)
		{
			for(int x = 0; x < buttons.length; x++)
			{
				buttons[x][y] = new JButton();
				c.gridx = x ;
				c.gridy = y;
				c.weightx = .5;
				c.weighty = .5;
				buttons[x][y].setPreferredSize(new Dimension(100,150));
				buttons[x][y].addActionListener(this);
				try 
				{
					Image img = ImageIO.read(new File("back.gif"));
					buttons[x][y].setIcon(new ImageIcon(img));

					this.add(buttons[x][y],c);
				} catch (IOException e) 
					{
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				}
			}
		setValue();
	}

	public void setValue()
	{
		ArrayList<Integer> value = new ArrayList<Integer>();
		for(int i=0; i<8; i++)
		{
			for(int ii =0; ii<2; ii++)
			{
				value.add(i);
			}
		}
		Collections.shuffle(value);
		
		for(int y=0; y<bValue.length; y++)
		{
			for(int x=0; x<bValue.length;x++)
			{
				bValue[x][y] = value.get(0);
				value.remove(0);
			}
		}
		
		for(int i=0; i<check.length;i++)
		{
			check[i] = false;
		}
		
		
	}
	
	public void getImage()
	{
		for(int i =0; i < img.length; i++)
		{
			try {
				img[i] = ImageIO.read(new File(imageURL[i]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//if(e.getSource() == buttons[0][0])
		m.pick();
		if(!first)  // if first is opened
		{
			//show picture
			for(int y = 0; y < buttons.length;y++)
			{
				for(int x = 0; x < buttons.length; x++)
				{
					if(!check[bValue[x][y]])
					{
						try {
							Image imgB = ImageIO.read(new File("back.gif"));
							buttons[x][y].setIcon(new ImageIcon(imgB));
							
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if(e.getSource() == buttons[x][y])
					{
						((JButton) buttons[x][y]).setIcon(new ImageIcon(img[bValue[x][y]]));
						value_firstClick = bValue[x][y];
						clicked = buttons[x][y];
					}
				}
			}
			first = true;
			
		}
		else //Second Click
		{
			if(e.getSource() != clicked) //not click the same picture
			{
				for(int y = 0; y < buttons.length;y++)
				{
					for(int x = 0; x < buttons.length; x++)
					{
						if(e.getSource() == buttons[x][y])
						{	
							((JButton) buttons[x][y]).setIcon(new ImageIcon(img[bValue[x][y]]));
							value_SecondClick = bValue[x][y];
							sClicked = buttons[x][y];
								
						}
					}
				}
				first = false;
				if(value_firstClick == value_SecondClick) //Not Same img 
				{
					count++;
					m.match();
					check[value_firstClick] = true;
				}
				else
				{
					m.noMatch();
				}
			}
		}
		
		
		if(count == 8)
			m.won();
		
		
	}
	
	public void redo()
	{
		
	}
}
