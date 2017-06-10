package yubinConpo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class FinalRect extends JFrame 
{
	public static componetVer comTemp;
	
	public FinalRect()
	{
		comTemp = new componetVer();
		setContentPane(comTemp);
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) 
	{
		new FinalRect();
    	new SaveOpen();

	}
}
