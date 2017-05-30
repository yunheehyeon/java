package kim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class RectangleResize extends JFrame 
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

	public RectangleResize()
	{
		setContentPane(new ResizeSq());
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		startP = new Point();
		endP = new Point();
		moveP = new Point();
	}



	
	class ResizeSq extends JPanel
	{
		
		Vector<Point> startV = new Vector<Point>(); // 시작점
		Vector<Point> endV = new Vector<Point>(); // 끝
		

		Point A1s = new Point(0,0);
		Point A1e = new Point(100,100);
		Point A2s = new Point(0,150);
		Point A2e = new Point(100,250);
		Point A3s = new Point(150,0);
		Point A3e = new Point(250,100);


		public ResizeSq()
		{
			MouseListen ml = new MouseListen();
			
			this.addMouseListener(ml); 
			this.addMouseMotionListener(ml);
			//박스 0
			startV.add(A1s);
			endV.add(A1e);

			//박스 1
			startV.add(A2s);
			endV.add(A2e);

			//박스 2
			startV.add(A3s);
			endV.add(A3e);
		}
		


		public void paintComponent(Graphics g)
		{
			super.paintComponent(g); 	

			for(int i=0;i<startV.size();i++)  // 벡터에 저장된 각 사각형을 매번 그림
				{
					Point sp = startV.get(i);
					Point ep = endV.get(i);	
					g.drawRect(sp.x, sp.y, ep.x-sp.x, ep.y-sp.y);
				}									
		}
		
		class MouseListen extends MouseAdapter implements MouseMotionListener
		{
			public void mousePressed(MouseEvent e)
			{
				rec = TransPoint.EndToTempRec(endV.get(BoxNum),10);
				// 임시 사각형(우측하단 모서리 근처) 안에 커서가 있을 경우
				if(rec.contains(new Point(e.getX(),e.getY())))
				{			
					//드래그 시작을 표시
					isDragged = true;
				}
			}


			
			public void mouseReleased(MouseEvent e)
			{
				//마우스 버튼이 릴리즈되면 드래그 모드 종료
				isDragged = false;		
			}
			
			public void mouseDragged(MouseEvent e)
			{
				//드래그 모드인 경우에만 사각형 이동시킴
				if(isDragged)
				{
					endP.move(e.getX(),e.getY());
					endV.setElementAt(endP,BoxNum);
				}
				repaint();
			}
			
			public void mouseMoved(MouseEvent e){}
			public void mouseClicked(MouseEvent e){}
			public void mouseEntered(MouseEvent e){}
			public void mouseExited(MouseEvent e){}
		}
	}
	
	public static void main(String[] args) 
	{
		new RectangleResize();
	}
}
