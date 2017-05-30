package kim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class RectangleDelete extends JFrame 
{
	public int afterStartX, afterStartY, afterEndX,afterEndY;
	public boolean isDragged = false;
	Integer BoxNum = null;
	
	
	public	Rectangle rec;

	public	Point startP;
	public	Point endP;
	public	Point moveP;



	public RectangleDelete()
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
		Vector<Boolean> clickV = new Vector<Boolean>(); //클릭되어진 사각형 확인용


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
			clickV.add(false);

			//박스 1
			startV.add(A2s);
			endV.add(A2e);
			clickV.add(false);


			//박스 2
			startV.add(A3s);
			endV.add(A3e);
			clickV.add(false);

		}
		


		public void paintComponent(Graphics g)
		{
			super.paintComponent(g); 	

			for(int i=0;i<endV.size();i++)  // 벡터에 저장된 각 사각형을 매번 그림
				{
					Point sp = startV.get(i);
					Point ep = endV.get(i);	
					if((boolean)clickV.get(i)){
						g.setColor(Color.RED);
						g.fillRect(sp.x, sp.y, ep.x-sp.x, ep.y-sp.y);
					}
					else{
						g.setColor(Color.GREEN);
						g.fillRect(sp.x, sp.y, ep.x-sp.x, ep.y-sp.y);
					}

				}								
		}
		
		class MouseListen extends MouseAdapter implements MouseMotionListener
		{
			public void mouseClicked(MouseEvent e)
			{
				
				if(e.getButton()==1) // 왼쪽 마우스 누를 때 = 선택
				{
					//벡터에 저장된 사각형 안에 커서가 있는 지 확인
					// 벡터에 저장된 각 사각형을 매번 그림. 단, 최근에 그려진 사각형이 가장 먼저 검사
					for(int i=(clickV.size()-1);i>=0;i--)  
					{
						Point sp = startV.get(i);
						Point ep = endV.get(i);	

						rec = TransPoint.pointToRec(sp,ep);
						if(rec.contains(new Point(e.getX(),e.getY())))
						{	
							for(int j=0;j<endV.size();j++)
								clickV.set(j,false);
								//아무 상자나 클릭하면 우선 전체 색깔 초기화 한후
							clickV.set(i,true);
							// 해당 상자 색만 다시설정
							BoxNum = new Integer(i);
							break;					
						}
						else // 배경 클릭하면 초기화 
						{
							for(int j=0;j<endV.size();j++)
							{
								clickV.set(j,false);
								BoxNum=null;
							}
						}
					}

				}
				else if(e.getButton()==3) // 오른쪽 마우스 누를 때 = 삭제
				{
					if(BoxNum!=null) // 선택된 박스가 있을 때만 실행
					{
						startV.remove((int)BoxNum);
						endV.remove((int)BoxNum);
						clickV.remove((int)BoxNum);
						BoxNum=null;
						repaint();
					}
				}
				
				repaint();

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
		new RectangleDelete();
	}
}
