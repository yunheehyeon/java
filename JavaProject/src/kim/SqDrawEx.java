package kim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class SqDrawEx extends JFrame 
{
	public boolean moveSq = false;
	public int v=0;//벡터 값 저장

	public SqDrawEx()
	{
		setContentPane(new DrawSq());
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	class DrawSq extends JPanel
	{
		
		Point startP=null;
		Point endP=null;
		Point tempP=null;
		
		Vector<Point> startV = new Vector<Point>(); // 시작점
		Vector<Point> endV = new Vector<Point>(); // 끝점

		public DrawSq()
		{
			MyMouseListener ml = new MyMouseListener();
			
			this.addMouseListener(ml); // 
			this.addMouseMotionListener(ml);
		}
		


		public void paintComponent(Graphics g){
			super.paintComponent(g); 			


			if(startV.size() != 0)
			{
				for(int i=0;i<endV.size();i++)  // 벡터에 저장된 각 사각형을 매번 그림
				{
					Point sp = startV.get(i);
					Point ep = endV.get(i);	
					g.drawRect(sp.x, sp.y, ep.x-sp.x, ep.y-sp.y);
				}
			}
			if(startP != null)
				g.drawRect(startP.x, startP.y, endP.x-startP.x, endP.y-startP.y);	
							
		}
		
		class MyMouseListener extends MouseAdapter implements MouseMotionListener{
			public void mousePressed(MouseEvent e)
			{
				startP = e.getPoint();		
				startV.add(e.getPoint());
				
			}
			public void mouseReleased(MouseEvent e)
			{
				endV.add(e.getPoint());
				endP = e.getPoint();
				repaint();

			}
			
			public void mouseDragged(MouseEvent e)
			{
				endP = e.getPoint();
				repaint();

			}
			
			public void mouseMoved(MouseEvent e)
			{
				
			}
		}
	}
	
	public static void main(String[] args) 
	{
		new SqDrawEx();
	}
}