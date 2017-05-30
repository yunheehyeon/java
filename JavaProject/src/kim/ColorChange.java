package kim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class ColorChange extends JFrame 
{
	//마우스 오프셋좌표
	public int offX, offY;
	public int afterStartX, afterStartY, afterEndX,afterEndY;
	public boolean isDragged = false;
	int BoxNum = 2;
	
	
	public	Rectangle rec;

	public	Point startP;
	public	Point endP;
	public	Point moveP;



	public ColorChange()
	{
		setContentPane(new ColorSq());
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		startP = new Point();
		endP = new Point();
		moveP = new Point();
	}



	
	class ColorSq extends JPanel
	{
		
		Vector<Point> startV = new Vector<Point>(); // 시작점
		Vector<Point> endV = new Vector<Point>(); // 끝
		Vector<Boolean> click = new Vector<Boolean>();
		boolean[] tempB = new boolean[10];

		Point A1s = new Point(0,0);
		Point A1e = new Point(100,100);
		Point A2s = new Point(0,150);
		Point A2e = new Point(100,250);
		Point A3s = new Point(150,0);
		Point A3e = new Point(250,100);


		public ColorSq()
		{
			MouseListen ml = new MouseListen();
			
			this.addMouseListener(ml); 
			this.addMouseMotionListener(ml);
			//박스 0
			startV.add(A1s);
			endV.add(A1e);
			tempB[0] = true;

			//click.add(temp);

			//박스 1
			startV.add(A2s);
			endV.add(A2e);
			tempB[1] = true;
			//click.add(temp);


			//박스 2
			startV.add(A3s);
			endV.add(A3e);
			tempB[2] = true;
			//click.add(temp);

		}
		


		public void paintComponent(Graphics g)
		{
			super.paintComponent(g); 	

			for(int i=0;i<endV.size();i++)  // 벡터에 저장된 각 사각형을 매번 그림
				{
					Point sp = startV.get(i);
					Point ep = endV.get(i);	
					if(tempB[i]/*click.get(i).booleanValue()*/)
						{g.setColor(Color.GREEN);}
					else
						{g.setColor(Color.RED);}

					g.fillRect(sp.x, sp.y, ep.x-sp.x, ep.y-sp.y);
				}								
		}
		
		class MouseListen extends MouseAdapter implements MouseMotionListener
		{
			public void mouseClicked(MouseEvent e)
			{
				//벡터에 저장된 사각형 안에 커서가 있는 지 확인
				for(int i=0;i<endV.size();i++)  
				{
					Point sp = startV.get(i);
					Point ep = endV.get(i);	
					rec = TransPoint.pointToRec(sp,ep);

					if(rec.contains(new Point(e.getX(),e.getY())))
					{	
						//Boolean tempB = new Boolean(true);
						tempB[i]=false;
						break;

					//	click.setElementAt(tempB,i) ;
					}
				}
			}



			public void mousePressed(MouseEvent e){}
			public void mouseReleased(MouseEvent e){}			
			public void mouseDragged(MouseEvent e){}			
			public void mouseMoved(MouseEvent e){}			
			public void mouseEntered(MouseEvent e){}
			public void mouseExited(MouseEvent e){}
		}
	}
	
	public static void main(String[] args) 
	{
		new ColorChange();
	}
}

