package yubinConpo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class componetVer extends JPanel
{

	public static BoxModel boxM = new BoxModel("상자");
	public boolean ModeClick = false; // false : 그리기 모드, true : 선택모드
	
	int Choose = BUTTON; // 이 값을 바꾼 후 생성하면 컴포넌트 종류 바뀜
	String viewText = "net"; // 이 값을 바꾼 후 생성하면 컴포넌트 택스트 바뀜
		
	// 외부 조회용
	//-------------------------------------------------------------------------------------------
	// 내부적으로만 사용하는 변수

	
	ArrayList<JComponent> buttons = new ArrayList<JComponent>(); //컴포넌트들을 저장할 배열. 다양한 컴포넌트 저장을 위해 최상위 슈퍼 클래스 JComponent 사용
	ArrayList<Point> startV = new ArrayList<Point>(); // 시작점 모음 배열 
	ArrayList<Point> endV = new ArrayList<Point>(); // 끝점 모음 배열 
	ArrayList<Boolean> clickV = new ArrayList<Boolean>(); //클릭되어진 사각형 확인용 기본 : false, 선택 : true
	ArrayList<Integer> TypeV = new ArrayList<Integer>(); //타입저장 배열
	ArrayList<String> TextV = new ArrayList<String> (); // 텍스트 저장 배열
	
	
	// 현재 선택된 박스 번호	
	int BoxNum = 0;

	
	//컴포넌트 종류
	public static int BUTTON = 1;
	public static int JPANEL = 2;
	public static int JSCROLL = 3;
	public static int JTEXTFIELD = 4;
	

	
	boolean sizeDragged = false; //움직이고 싶은 사각형 안에서 눌렀을때 true로 변함.
	boolean moveDragged = false; //크기를 바꾸고 싶은 사각형의 꼭지점을 눌렀을때 true로 변함.
	
	Point startP = new Point();
	Point endP = new Point();
	//생성할 때 움직임을 보이게 만들어주는 용도

	//움직일때 사용하는 사각형 구조체와 Point구조체
	Rectangle rec;
	Point moveP = new Point();
		
	

	public componetVer()
	{
		MouseListen ml = new MouseListen();
		this.addMouseListener(ml); 
		this.addMouseMotionListener(ml);
		//마우스 리스너 등록
		
		//박스0. 생성. 아무것도 안 가리킬때 사용하는 Null 상자
		startV.add(new Point(10,10));
		endV.add(new Point(10,10));
		clickV.add(false);
		TypeV.add(null);
		TextV.add(null);
	
	}
	 
	//view
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); 	
			
		removeAll();

		//control -> model
		boxM.ArrayPointToRec(startV,endV,clickV, TypeV,TextV);

		
		buttons.clear(); //컴포넌트 전체 삭제 후 다시생성
		buttons.add(null);
		
		for(int i=1;i<boxM.ArrSize();i++)  // 선택, 이동용 원
		{
			g.setColor(Color.RED);
			g.drawOval(boxM.recgetX(i)-15,boxM.recgetY(i) -15, 30, 30);
		}

		
		//view
		for(int i=1;i<boxM.ArrSize();i++)  // 모델 객체에 저장된 각 사각형을 매번 하나씩 전부 그림
		{
			JComponent j =null;
			
			switch(boxM.getType(i))
			{
			case 1 :
				j = new JButton(boxM.getString(i));
				break;
			case 2 :
				j = new JPanel();
				break;
			case 3 :
				j = new JScrollPane();
				break;
			case 4 :
				j = new JTextField(boxM.getString(i));
				break;
			}
			
			j.setLocation(boxM.recgetX(i),boxM.recgetY(i));  
			j.setSize(boxM.getRecWidth(i), boxM.getRecHeight(i)); 
			j.setEnabled(true);
			j.setFocusable(true);
			
			if(boxM.getClick(i))
				j.setBackground(Color.RED);
			else
				j.setBackground(Color.WHITE);
			
			buttons.add(j);
			add(buttons.get(i));
		}		
		if(startP != null)
		{
			g.setColor(Color.BLACK);
			g.drawRect(startP.x, startP.y, endP.x-startP.x, endP.y-startP.y);
		}	
	}
	
	
	//control
	class MouseListen extends MouseAdapter implements MouseMotionListener
	{
		public void mouseClicked(MouseEvent e)
		{
			Rectangle tempRecClick;
			if(ModeClick)//선택 모드 
			{
				if(e.getButton()==1) // 왼쪽 마우스 누를 때 = 선택하기
				{
					//removeAll();

					//벡터에 저장된 사각형 안에 커서가 있는 지 확인
					// 벡터에 저장된 각 사각형을 매번 그림. 단, 최근에 그려진 사각형이 가장 먼저 검사
					for(int i=(endV.size()-1);i>0;i--) 
					{							
						tempRecClick = TransPoint.StartToTempOval(startV.get(i),30);
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
			}
			repaint();	

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
				TypeV.add(Choose); // 타입선택용
				TextV.add(viewText); // 텍스트 입력용
				
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
						Rectangle tempRec = TransPoint.StartToTempOval(startV.get(BoxNum),30);
						rec = TransPoint.pointToRec(startV.get(BoxNum),endV.get(BoxNum)); //임시사각형
						if(tempRec.contains(new Point(e.getX(),e.getY())))
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
						JTextField j = new JTextField(); 

						j.setSize(boxM.getRecWidth(BoxNum)-buttons.get(BoxNum).getX(), boxM.getRecHeight(BoxNum)-buttons.get(BoxNum).getY());
						
						buttons.set(BoxNum, j);
						
						tempEndP = new Point(e.getX(),e.getY());
						endV.set(BoxNum,tempEndP);
					}
					else if(moveDragged)//move 드래그 모드
					{
						//시작점, 끝점을 임시사각형에 넣었기 때문에, 시작점 x,y값만 바꾸면 나머지 좌표는 알아서 계산됨.
						//j.setSize(e.getX() - moveP.x,rec.y = e.getY() - moveP.y)
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
	
	