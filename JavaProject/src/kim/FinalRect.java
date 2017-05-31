package kim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class FinalRect extends JFrame 
{
	public int afterStartX, afterStartY, afterEndX,afterEndY;
	public boolean isDragged = false;
	int BoxNum = 0;
	boolean ModeClick = false;
	
	
	public	Rectangle rec;

	public	Point startP;
	public	Point endP;
	public	Point moveP;



	public FinalRect()
	{
		setContentPane(new DrawRec());

		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		startP = new Point();
		endP = new Point();
		moveP = new Point();
	}



	
	class DrawRec extends JPanel
	{
		
		Vector<Point> startV = new Vector<Point>(); // 시작점
		Vector<Point> endV = new Vector<Point>(); // 끝
		Vector<Boolean> clickV = new Vector<Boolean>(); //클릭되어진 사각형 확인용


		public DrawRec()
		{
			MouseListen ml = new MouseListen();

			this.addMouseListener(ml); 
			this.addMouseMotionListener(ml);

			//박스 0 아무것도 안가릴때 Null 상자
			startV.add(null);
			endV.add(null);
			clickV.add(false);

		}
		


		public void paintComponent(Graphics g)
		{
			super.paintComponent(g); 	

			for(int i=1;i<endV.size();i++)  // 벡터에 저장된 각 사각형을 매번 그림
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
				////	if(startP != null)
					//	g.drawRect(startP.x, startP.y, endP.x-startP.x, endP.y-startP.y);

				}								
		}
		
		class MouseListen extends MouseAdapter implements MouseMotionListener
		{
			public void mouseClicked(MouseEvent e)
			{
				if(ModeClick){
				if(e.getButton()==1) // 왼쪽 마우스 누를 때 = 선택
				{
					//벡터에 저장된 사각형 안에 커서가 있는 지 확인
					// 벡터에 저장된 각 사각형을 매번 그림. 단, 최근에 그려진 사각형이 가장 먼저 검사
					if(clickV.size()==1)//생성된 상자가 없으면 그냥 그림 
						{
							startP = e.getPoint();		
							startV.add(e.getPoint());
							clickV.add(false);
						}
					else
					{
						for(int i=(clickV.size()-1);i>0;i--) 
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
								BoxNum = i;
								break;					
							}
							else // 배경 클릭하면 초기화 
							{
								for(int j=1;j<endV.size();j++)
								{
									clickV.set(j,false);
									BoxNum=0;
								}
								break;
							}
						}
					}
				}
				else if(e.getButton()==3) // 오른쪽 마우스 누를 때 = 삭제
				{
					if(BoxNum!=0) // 선택된 박스가 있을 때만 실행
					{
						startV.remove(BoxNum);
						endV.remove(BoxNum);
						clickV.remove(BoxNum);
						BoxNum=0; // 선택된 박스 해제
						repaint();
					}
				}
				
				repaint();	
			}

			}
			public void mousePressed(MouseEvent e)
			{
				if(!ModeClick)
				{

				// 초기화와 동시에 새로운 그림 만듦
				startP = e.getPoint();		
				startV.add(e.getPoint());
				clickV.add(false);
				}
				
			}
			public void mouseReleased(MouseEvent e)
			{
				if(!ModeClick){
					if(clickV.size()==4)
						ModeClick = true;


				endV.add(e.getPoint());
				endP = e.getPoint();
				repaint();
}
			}
			
			public void mouseDragged(MouseEvent e)
			{
				if(!ModeClick){
				endP = e.getPoint();
				repaint();
}
			}			
			public void mouseMoved(MouseEvent e){}			
			public void mouseEntered(MouseEvent e){}
			public void mouseExited(MouseEvent e){}
		}
		
	}
	
	public static void main(String[] args) 
	{
		new FinalRect();
	}
}
