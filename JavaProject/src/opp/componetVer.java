package opp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class componetVer extends JPanel
{

	public static BoxModel boxM = new BoxModel();
	public boolean ModeClick = false; // false : �׸��� ���, true : ���ø��
	
	public static int Choose = 2; // �� ���� �ٲ� �� �����ϸ� ������Ʈ ���� �ٲ�
	public static String viewText = "net"; // �� ���� �ٲ� �� �����ϸ� ������Ʈ �ý�Ʈ �ٲ�
		
	// �ܺ� ��ȸ��
	//-------------------------------------------------------------------------------------------
	// ���������θ� ����ϴ� ����

	
	ArrayList<JComponent> buttons = new ArrayList<JComponent>(); //������Ʈ���� ������ �迭. �پ��� ������Ʈ ������ ���� �ֻ��� ���� Ŭ���� JComponent ���
	public static ArrayList<Point> startV = new ArrayList<Point>(); // ������ ���� �迭 
	public static ArrayList<Point> endV = new ArrayList<Point>(); // ���� ���� �迭 
	public static ArrayList<Boolean> clickV = new ArrayList<Boolean>(); //Ŭ���Ǿ��� �簢�� Ȯ�ο� �⺻ : false, ���� : true
	public static ArrayList<Integer> TypeV = new ArrayList<Integer>(); //Ÿ������ �迭
	public static ArrayList<String> TextV = new ArrayList<String> (); // �ؽ�Ʈ ���� �迭
	
	public static boolean OpenNum = false;
	public static boolean ClearNum = true; //true�� ���, false�� Ŭ����

	
	// ���� ���õ� �ڽ� ��ȣ	
	public static int BoxNum = 0;

	
	//������Ʈ ����
	public static int BUTTON = 1;
	public static int JPANEL = 2;
	public static int JSCROLL = 3;
	public static int JTEXTFIELD = 4;
	

	
	boolean sizeDragged = false; //�����̰� ���� �簢�� �ȿ��� �������� true�� ����.
	boolean moveDragged = false; //ũ�⸦ �ٲٰ� ���� �簢���� �������� �������� true�� ����.
	
	Point startP = new Point();
	Point endP = new Point();
	//������ �� �������� ���̰� ������ִ� �뵵

	//�����϶� ����ϴ� �簢�� ����ü�� Point����ü
	Rectangle rec;
	Point moveP = new Point();
		
	

	public componetVer()
	{
		MouseListen ml = new MouseListen();
		this.addMouseListener(ml); 
		this.addMouseMotionListener(ml);
		//���콺 ������ ���
		
		//�ڽ�0. ����. �ƹ��͵� �� ����ų�� ����ϴ� Null ����
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
		
			
			
		if(OpenNum)
		{
    		boxM.RodeArrayBox(startV,endV,clickV,TypeV,TextV);
    		OpenNum=false;
		}
		else
		{
			boxM.ArrayPointToRec(startV,endV,clickV, TypeV,TextV);		
		}

		
		buttons.clear(); //������Ʈ ��ü ���� �� �ٽû���
		buttons.add(null);
		
		if(ClearNum)
		{
			for(int i=1;i<boxM.ArrSize();i++)  // ����, �̵��� ��
			{
				g.setColor(Color.RED);
				g.drawOval(boxM.recgetX(i)-15,boxM.recgetY(i) -15, 30, 30);
			}

			
			//view
			for(int i=1;i<boxM.ArrSize();i++)  // �� ��ü�� ����� �� �簢���� �Ź� �ϳ��� ���� �׸�
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
			}		
			for(int i=boxM.ArrSize()-1;i>0;i--)
				add(buttons.get(i));

			
			if(startP != null)
			{
				g.setColor(Color.BLACK);
				g.drawRect(startP.x, startP.y, endP.x-startP.x, endP.y-startP.y);
			}
		
		}
	}
	
	
	//control
	class MouseListen extends MouseAdapter implements MouseMotionListener
	{
		public void mouseClicked(MouseEvent e)
		{
			Rectangle tempRecClick;
			Rectangle tempRecClick2;
			
			if(e.getButton()==2) // ���콺 ��� ��ư ������ ����� ��� <-> ���ø�� ���� ����
			{
				if(ModeClick)
					ModeClick = false;
				else
					ModeClick = true;
			}
			
			if(ModeClick)//���� ��� 
			{
				if(e.getButton()==1) // ���� ���콺 ���� �� = �����ϱ�
				{
					//removeAll();

					//���Ϳ� ����� �簢�� �ȿ� Ŀ���� �ִ� �� Ȯ��
					// ���Ϳ� ����� �� �簢���� �Ź� �׸�. ��, �ֱٿ� �׷��� �簢���� ���� ���� �˻�
					for(int i=(endV.size()-1);i>0;i--) 
					{	
						
						tempRecClick = TransPoint.StartToTempOval(startV.get(i),30);

						Point sp = startV.get(i);
						Point ep = endV.get(i);	
						tempRecClick2 = TransPoint.pointToRec(sp,ep); // ����, ���� Ȱ���� �ӽû��� ����
						
						if(tempRecClick2.contains(new Point(e.getX(),e.getY()))||tempRecClick.contains(new Point(e.getX(),e.getY()))) // ���� �ȿ� Ŀ���� ������
						{	
							for(int j=0;j<endV.size();j++)
								clickV.set(j,false);
								//�ƹ� ���ڳ� Ŭ���ϸ� �켱 ��ü ���� �ʱ�ȭ ����
							clickV.set(i,true);
								// �ش� ���� ���� �ٽü���
							BoxNum = i;//���õǾ��� ���� ���� ��ȣ ����
							break;					
						}
						else // ��� Ŭ���ϸ� �ʱ�ȭ 
						{
                            clickV.set(0,true);
							for(int j=1;j<endV.size();j++)
							{
								clickV.set(j,false);
								BoxNum=0;
							}
						}
					}												
				}
				else if(e.getButton()==3) // ������ ���콺 ���� �� = ���õ� ���� ����
				{
					if(BoxNum!=0) // ���õ� �ڽ��� ���� ���� ����
					{
						startV.remove(BoxNum);
						endV.remove(BoxNum);
						clickV.remove(BoxNum);
						TypeV.remove(BoxNum); //Ÿ������ �迭
						TextV.remove(BoxNum);
						BoxNum=0; // ���õ� �ڽ� ����
					}
				}						
			}			
			repaint();	

		}


		public void mousePressed(MouseEvent e)
		{
			Rectangle tempRecSize; 
			//������ ������ �� ���� �ӽ� �簢��

			if(e.getButton()==1)
			{
				if(!ModeClick)//�׸��� ���
				{
					TypeV.add(Choose); // Ÿ�Լ��ÿ�
					TextV.add(viewText); // �ؽ�Ʈ �Է¿�
					
					startP = e.getPoint();	 // ���� �� �׷����� ������ �ӽ� �簢��	

					//���� Ŀ�� ��ǥ ���� �� clickV�� �ش� ���� ��ȣ ������ false�� �ʱ�ȭ
					startV.add(e.getPoint());
					clickV.add(false);
				}
				else 		//���ø��
				{
					if(BoxNum!=0) //���ڰ� 1���� �־�� �۵���.(0���� Null���������� ���� �� ħ)
					{
						tempRecSize = TransPoint.EndToTempRec(endV.get(BoxNum),15);
						// �ش� �簢�� ���� ������ �ӽ� �簢��(���� �߽����� ���� 30, ���� 30)����
						// �ӽ� �簢��(�����ϴ� �𼭸� ��ó) �ȿ� Ŀ���� ���� ���
						if(tempRecSize.contains(new Point(e.getX(),e.getY())))
						{			
							//�巡�� ������ ǥ��
							sizeDragged = true;
						}
						else
						{	
							//�ӽ� �簢��(ũ��������) �ȿ� X && �簢�� ���ο� Ŀ���� ���� ���
							//�簢�� ��ü�� ��������������, ���Ǹ� ���� �ش� �簢���� �ӽû簢���� �����ؼ� ����.
							Rectangle tempRec = TransPoint.StartToTempOval(startV.get(BoxNum),30);
							rec = TransPoint.pointToRec(startV.get(BoxNum),endV.get(BoxNum)); //�ӽû簢��
							if(rec.contains(new Point(e.getX(),e.getY()))||tempRec.contains(new Point(e.getX(),e.getY())))
							{
								//��� ��ġ ����
								moveP.x=e.getX() - rec.x;
								moveP.y=e.getY() - rec.y;
						
								//�巡�� ������ ǥ��
								moveDragged = true;
							}
						}
					}
				}

			}
		}


		public void mouseReleased(MouseEvent e)
		{
			if(e.getButton()==1)
			{
				startP = null;
				endP = null;// �������ϸ� ���� �� �׷����� ������ �ӽ� �簢�� ����

				if(!ModeClick) // �׸��� ���, Ŀ�� ���� ���� ���� ��ǥ�� end�� ����
				{
					endV.add(e.getPoint());
				}
				//���콺 ��ư�� ������Ǹ� �巡�� ��� ����
				sizeDragged = false;
				moveDragged = false;

			}
			repaint();			
		}
		
		public void mouseDragged(MouseEvent e)
		{
			Point tempEndP;
			if(ModeClick)//���ø��
			{
				if(BoxNum!=0)
				{
					if(sizeDragged)//size �巡�� ���
					{
						JTextField j = new JTextField(); 

						j.setSize(boxM.getRecWidth(BoxNum)-buttons.get(BoxNum).getX(), boxM.getRecHeight(BoxNum)-buttons.get(BoxNum).getY());
						
						buttons.set(BoxNum, j);
						
						tempEndP = new Point(e.getX(),e.getY());
						endV.set(BoxNum,tempEndP);
					}
					else if(moveDragged)//move �巡�� ���
					{
						//������, ������ �ӽû簢���� �־��� ������, ������ x,y���� �ٲٸ� ������ ��ǥ�� �˾Ƽ� ����.
						//j.setSize(e.getX() - moveP.x,rec.y = e.getY() - moveP.y)
						rec.x = e.getX() - moveP.x;
						rec.y = e.getY() - moveP.y;
						startV.set(BoxNum,TransPoint.RecToStartPoint(rec));
						endV.set(BoxNum,TransPoint.RecToEndPoint(rec));
					}
				}					
			}
			else
				endP = e.getPoint(); // ���� �� �׷����� ������ �ӽ� �簢��
			repaint();
		}					
	}
}
	
	