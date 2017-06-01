package kim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class FinalRecJPanel extends JPanel
{
	public ArrayList<Point> startV = new ArrayList<Point>(); // ������ ���� �迭 
	public ArrayList<Point> endV = new ArrayList<Point>(); // ���� ���� �迭 
	public ArrayList<Boolean> clickV = new ArrayList<Boolean>(); //Ŭ���Ǿ��� �簢�� Ȯ�ο�

	public int BoxNum = 0;
	// ���� ���õ� �ڽ� ��ȣ


	// �ܺ� ��ȸ��
//-------------------------------------------------------------------------------------------
	//���������θ� ����ϴ� ����
	boolean sizeDragged = false; //�����̰� ���� �簢�� �ȿ��� �������� true�� ����.
	boolean moveDragged = false; //ũ�⸦ �ٲٰ� ���� �簢���� �������� �������� true�� ����.
	boolean ModeClick = false; // false : �׸��� ���, true : ���ø��
	
	Point startP = new Point();
	Point endP = new Point();
	//������ �� �������� ���̰� ������ִ� �뵵

	//�����϶� ����ϴ� �簢�� ����ü�� Point����ü
	Rectangle rec;
	Point moveP = new Point();

	public FinalRecJPanel()
	{
		MouseListen ml = new MouseListen();
		this.addMouseListener(ml); 
		this.addMouseMotionListener(ml);
		//���콺 ������ ���

		//�ڽ�0. ����. �ƹ��͵� �� ����ų�� ����ϴ� Null ����
		startV.add(null);
		endV.add(null);
		clickV.add(false);
	}
	

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); 	

		for(int i=1;i<endV.size();i++)  // �迭�� ����� �� �簢���� �Ź� �ϳ��� ����
		{
			Point sp = startV.get(i);
			Point ep = endV.get(i);	

			//clickV �迭�� ����� ������ ����ؼ� ���° ���ڰ� ���õǾ����ִ��� Ȯ����. ���õ� ���ڴ� ������
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
			if(ModeClick)//���� ��� 
			{
				if(e.getButton()==1) // ���� ���콺 ���� �� = �����ϱ�
				{
					//���Ϳ� ����� �簢�� �ȿ� Ŀ���� �ִ� �� Ȯ��
					// ���Ϳ� ����� �� �簢���� �Ź� �׸�. ��, �ֱٿ� �׷��� �簢���� ���� ���� �˻�
					for(int i=(endV.size()-1);i>0;i--) 
					{
						Point sp = startV.get(i);
						Point ep = endV.get(i);	
						tempRecClick = TransPoint.pointToRec(sp,ep); // ����, ���� Ȱ���� �ӽû��� ����
						if(tempRecClick.contains(new Point(e.getX(),e.getY()))) // ���� �ȿ� Ŀ���� ������
						{	
							for(int j=1;j<endV.size();j++)
								clickV.set(j,false);
								//�ƹ� ���ڳ� Ŭ���ϸ� �켱 ��ü ���� �ʱ�ȭ ����
							clickV.set(i,true);
								// �ش� ���� ���� �ٽü���
							BoxNum = i;//���õǾ��� ���� ���� ��ȣ ����
							break;					
						}
						else // ��� Ŭ���ϸ� �ʱ�ȭ 
						{
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
						BoxNum=0; // ���õ� �ڽ� ����
					}
				}						
				repaint();	
			}

		}


		public void mousePressed(MouseEvent e)
		{
			Rectangle tempRecSize; 
			//������ ������ �� ���� �ӽ� �簢��

			if(e.getButton()==2) // ���콺 ��� ��ư ������ ����� ��� <-> ���ø�� ���� ����
			{
				if(ModeClick)
					ModeClick = false;
				else
					ModeClick = true;
			}
			else{}//���п� �Ű� ����


			if(!ModeClick)//�׸��� ���
			{
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
						rec = TransPoint.pointToRec(startV.get(BoxNum),endV.get(BoxNum)); //�ӽû簢��
						if(rec.contains(new Point(e.getX(),e.getY())))
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


		public void mouseReleased(MouseEvent e)
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
						tempEndP = new Point(e.getX(),e.getY());
						endV.set(BoxNum,tempEndP);
					}
					else if(moveDragged)//move �巡�� ���
					{
						//������, ������ �ӽû簢���� �־��� ������, ������ x,y���� �ٲٸ� ������ ��ǥ�� �˾Ƽ� ����.
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
	
	