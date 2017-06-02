package kim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class FinalRect extends JFrame 
{
	public FinalRect()
	{
		setContentPane(new FinalRecJPanel());
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) 
	{
		new FinalRect();
	}
}
