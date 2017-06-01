package kim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class FinalRecJPanel extends JPanel
{
	public ArrayList<Point> startV = new ArrayList<Point>(); // 시작점 모음 배열 
	public ArrayList<Point> endV = new ArrayList<Point>(); // 끝점 모음 배열 
	public ArrayList<Boolean> clickV = new ArrayList<Boolean>(); //클릭되어진 사각형 확인용

	public int BoxNum = 0;
	// 현재 선택된 박스 번호


	// 외부 조회용
//-------------------------------------------------------------------------------------------
	//내부적으로만 사용하는 변수
	boolean sizeDragged = false; //움직이고 싶은 사각형 안에서 눌렀을때 true로 변함.
	boolean moveDragged = false; //크기를 바꾸고 싶은 사각형의 꼭지점을 눌렀을때 true로 변함.
	boolean ModeClick = false; // false : 그리기 모드, true : 선택모드
	
	Point startP = new Point();
	Point endP = new Point();
	//생성할 때 움직임을 보이게 만들어주는 용도

	//움직일때 사용하는 사각형 구조체와 Point구조체
	Rectangle rec;
	Point moveP = new Point();

	public FinalRecJPanel()
	{
		MouseListen ml = new MouseListen();
		this.addMouseListener(ml); 
		this.addMouseMotionListener(ml);
		//마우스 리스너 등록

		//박스0. 생성. 아무것도 안 가리킬때 사용하는 Null 상자
		startV.add(null);
		endV.add(null);
		clickV.add(false);
	}
	

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); 	

		for(int i=1;i<endV.size();i++)  // 배열에 저장된 각 사각형을 매번 하나씩 전부
		{
			Point sp = startV.get(i);
			Point ep = endV.get(i);	

			//clickV 배열에 저장된 정보를 사용해서 몇번째 상자가 선택되어져있는지 확인함. 선택된 상자는 빨간색
			if((boolean)clickV.get(i)){
				g.setColor(Color.RED);
				g.fillRect(sp.x, sp.y, ep.x-sp.x, ep.y-sp.y);
			}
			else{
				g.setColor(Color.GREEN);
				g.fillRect(sp.x, sp.y, ep.x-sp.x, ep.y-sp.y);
			}
		}
		if(startP != null)
		{
			g.setColor(Color.GREEN);
			g.drawRect(startP.x, startP.y, endP.x-startP.x, endP.y-startP.y);
		}
	}



	
	class MouseListen extends MouseAdapter implements MouseMotionListener
	{
		public void mouseClicked(MouseEvent e)
		{
			Rectangle tempRecClick;
			if(ModeClick)//선택 모드 
			{
				if(e.getButton()==1) // 왼쪽 마우스 누를 때 = 선택하기
				{
					//벡터에 저장된 사각형 안에 커서가 있는 지 확인
					// 벡터에 저장된 각 사각형을 매번 그림. 단, 최근에 그려진 사각형이 가장 먼저 검사
					for(int i=(endV.size()-1);i>0;i--) 
					{
						Point sp = startV.get(i);
						Point ep = endV.get(i);	
						tempRecClick = TransPoint.pointToRec(sp,ep); // 시작, 끝점 활용해 임시상자 생성
						if(tempRecClick.contains(new Point(e.getX(),e.getY()))) // 상자 안에 커서가 있으면
						{	
							for(int j=1;j<endV.size();j++)
								clickV.set(j,false);
								//아무 상자나 클릭하면 우선 전체 색깔 초기화 한후
							clickV.set(i,true);
								// 해당 상자 색만 다시설정
							BoxNum = i;//선택되어진 현재 상자 번호 저장
							break;					
						}
						else // 배경 클릭하면 초기화 
						{
							for(int j=1;j<endV.size();j++)
							{
								clickV.set(j,false);
								BoxNum=0;
							}
						}
					}												
				}
				else if(e.getButton()==3) // 오른쪽 마우스 누를 때 = 선택된 상자 삭제
				{
					if(BoxNum!=0) // 선택된 박스가 있을 때만 실행
					{
						startV.remove(BoxNum);
						endV.remove(BoxNum);
						clickV.remove(BoxNum);
						BoxNum=0; // 선택된 박스 해제
					}
				}						
				repaint();	
			}

		}


		public void mousePressed(MouseEvent e)
		{
			Rectangle tempRecSize; 
			//사이즈 변경할 때 쓰는 임시 사각형

			if(e.getButton()==2) // 마우스 가운데 바튼 누르면 만들기 모드 <-> 선택모드 선택 가능
			{
				if(ModeClick)
					ModeClick = false;
				else
					ModeClick = true;
			}
			else{}//구분용 신경 ㄴㄴ


			if(!ModeClick)//그리기 모드
			{
				startP = e.getPoint();	 // 생성 시 그려질때 나오는 임시 사각형	

				//현재 커서 좌표 저장 및 clickV에 해당 도형 번호 생성후 false로 초기화
				startV.add(e.getPoint());
				clickV.add(false);
			}
			else 		//선택모드
			{
				if(BoxNum!=0) //상자가 1개라도 있어야 작동함.(0번은 Null상자임으로 없는 셈 침)
				{
					tempRecSize = TransPoint.EndToTempRec(endV.get(BoxNum),15);
					// 해당 사각형 끝점 주위에 임시 사각형(끝점 중심으로 가로 30, 세로 30)생성
					// 임시 사각형(우측하단 모서리 근처) 안에 커서가 있을 경우
					if(tempRecSize.contains(new Point(e.getX(),e.getY())))
					{			
						//드래그 시작을 표시
						sizeDragged = true;
					}
					else
					{	
						//임시 사각형(크기조절용) 안에 X && 사각형 내부에 커서가 있을 경우
						//사각형 자체를 움직여야함으로, 편의를 위해 해당 사각형을 임시사각형에 저장해서 연산.
						rec = TransPoint.pointToRec(startV.get(BoxNum),endV.get(BoxNum)); //임시사각형
						if(rec.contains(new Point(e.getX(),e.getY())))
						{
							//상대 위치 저장
							moveP.x=e.getX() - rec.x;
							moveP.y=e.getY() - rec.y;
					
							//드래그 시작을 표시
							moveDragged = true;
						}
					}
				}
			}
		}


		public void mouseReleased(MouseEvent e)
		{
			startP = null;
			endP = null;// 릴리스하면 생성 시 그려질때 나오는 임시 사각형 삭제

			if(!ModeClick) // 그리기 모드, 커서 떼는 순간 현재 좌표를 end에 넣음
			{
				endV.add(e.getPoint());
			}
			//마우스 버튼이 릴리즈되면 드래그 모드 종료
			sizeDragged = false;
			moveDragged = false;	

			repaint();			
		}
		
		public void mouseDragged(MouseEvent e)
		{
			Point tempEndP;
			if(ModeClick)//선택모드
			{
				if(BoxNum!=0)
				{
					if(sizeDragged)//size 드래그 모드
					{
						tempEndP = new Point(e.getX(),e.getY());
						endV.set(BoxNum,tempEndP);
					}
					else if(moveDragged)//move 드래그 모드
					{
						//시작점, 끝점을 임시사각형에 넣었기 때문에, 시작점 x,y값만 바꾸면 나머지 좌표는 알아서 계산됨.
						rec.x = e.getX() - moveP.x;
						rec.y = e.getY() - moveP.y;
								
						startV.set(BoxNum,TransPoint.RecToStartPoint(rec));
						endV.set(BoxNum,TransPoint.RecToEndPoint(rec));
					}
				}					
			}
			else
				endP = e.getPoint(); // 생성 시 그려질때 나오는 임시 사각형
			repaint();
		}					
	}	
}
	
	