package kim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class FinalRecJPanel extends JPanel
{

	public ArrayList<Point> startV = new ArrayList<Point>(); // �떆�옉�젏 紐⑥쓬 諛곗뿴 
	public ArrayList<Point> endV = new ArrayList<Point>(); // �걹�젏 紐⑥쓬 諛곗뿴 
	public ArrayList<Boolean> clickV = new ArrayList<Boolean>(); //�겢由��릺�뼱吏� �궗媛곹삎 �솗�씤�슜

	public int BoxNum = 0;
	// �쁽�옱 �꽑�깮�맂 諛뺤뒪 踰덊샇



	// �쇅遺� 議고쉶�슜
//-------------------------------------------------------------------------------------------
	//�궡遺��쟻�쑝濡쒕쭔 �궗�슜�븯�뒗 蹂��닔
	boolean sizeDragged = false; //��吏곸씠怨� �떢�� �궗媛곹삎 �븞�뿉�꽌 �닃���쓣�븣 true濡� 蹂��븿.
	boolean moveDragged = false; //�겕湲곕�� 諛붽씀怨� �떢�� �궗媛곹삎�쓽 瑗�吏��젏�쓣 �닃���쓣�븣 true濡� 蹂��븿.
	boolean ModeClick = false; // false : 洹몃━湲� 紐⑤뱶, true : �꽑�깮紐⑤뱶
	
	Point startP = new Point();
	Point endP = new Point();
	//�깮�꽦�븷 �븣 ��吏곸엫�쓣 蹂댁씠寃� 留뚮뱾�뼱二쇰뒗 �슜�룄

	//��吏곸씪�븣 �궗�슜�븯�뒗 �궗媛곹삎 援ъ“泥댁� Point援ъ“泥�
	Rectangle rec;
	Point moveP = new Point();



		public FinalRecJPanel()
		{
			MouseListen ml = new MouseListen();
			this.addMouseListener(ml); 
			this.addMouseMotionListener(ml);
			//留덉슦�뒪 由ъ뒪�꼫 �벑濡�

			//諛뺤뒪0. �깮�꽦. �븘臾닿쾬�룄 �븞 媛�由ы궗�븣 �궗�슜�븯�뒗 Null �긽�옄
			startV.add(null);
			endV.add(null);
			clickV.add(false);
		}
		

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g); 	

			for(int i=1;i<endV.size();i++)  // 諛곗뿴�뿉 ���옣�맂 媛� �궗媛곹삎�쓣 留ㅻ쾲 �븯�굹�뵫 �쟾遺�
			{
				Point sp = startV.get(i);
				Point ep = endV.get(i);	

				//clickV 諛곗뿴�뿉 ���옣�맂 �젙蹂대�� �궗�슜�빐�꽌 紐뉖쾲吏� �긽�옄媛� �꽑�깮�릺�뼱�졇�엳�뒗吏� �솗�씤�븿. �꽑�깮�맂 �긽�옄�뒗 鍮④컙�깋
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
				if(ModeClick)//�꽑�깮 紐⑤뱶 
				{
					if(e.getButton()==1) // �쇊履� 留덉슦�뒪 �늻瑜� �븣 = �꽑�깮�븯湲�
					{
						//踰≫꽣�뿉 ���옣�맂 �궗媛곹삎 �븞�뿉 而ㅼ꽌媛� �엳�뒗 吏� �솗�씤
						// 踰≫꽣�뿉 ���옣�맂 媛� �궗媛곹삎�쓣 留ㅻ쾲 洹몃┝. �떒, 理쒓렐�뿉 洹몃젮吏� �궗媛곹삎�씠 媛��옣 癒쇱� 寃��궗
						for(int i=(endV.size()-1);i>0;i--) 
						{
							Point sp = startV.get(i);
							Point ep = endV.get(i);	
							tempRecClick = TransPoint.pointToRec(sp,ep); // �떆�옉, �걹�젏 �솢�슜�빐 �엫�떆�긽�옄 �깮�꽦
							if(tempRecClick.contains(new Point(e.getX(),e.getY()))) // �긽�옄 �븞�뿉 而ㅼ꽌媛� �엳�쑝硫�
							{	
								for(int j=1;j<endV.size();j++)
									clickV.set(j,false);
									//�븘臾� �긽�옄�굹 �겢由��븯硫� �슦�꽑 �쟾泥� �깋源� 珥덇린�솕 �븳�썑
								clickV.set(i,true);
									// �빐�떦 �긽�옄 �깋留� �떎�떆�꽕�젙
								BoxNum = i;//�꽑�깮�릺�뼱吏� �쁽�옱 �긽�옄 踰덊샇 ���옣
								break;					
							}
							else // 諛곌꼍 �겢由��븯硫� 珥덇린�솕 
							{
								for(int j=1;j<endV.size();j++)
								{
									clickV.set(j,false);
									BoxNum=0;
								}
							}
						}												
					}
					else if(e.getButton()==3) // �삤瑜몄そ 留덉슦�뒪 �늻瑜� �븣 = �꽑�깮�맂 �긽�옄 �궘�젣
					{
						if(BoxNum!=0) // �꽑�깮�맂 諛뺤뒪媛� �엳�쓣 �븣留� �떎�뻾
						{
							startV.remove(BoxNum);
							endV.remove(BoxNum);
							clickV.remove(BoxNum);
							BoxNum=0; // �꽑�깮�맂 諛뺤뒪 �빐�젣
						}
					}						
					repaint();	
				}

			}


			public void mousePressed(MouseEvent e)
			{
				Rectangle tempRecSize; 
				//�궗�씠利� 蹂�寃쏀븷 �븣 �벐�뒗 �엫�떆 �궗媛곹삎

				if(e.getButton()==2) // 留덉슦�뒪 媛��슫�뜲 諛뷀듉 �늻瑜대㈃ 留뚮뱾湲� 紐⑤뱶 <-> �꽑�깮紐⑤뱶 �꽑�깮 媛��뒫
				{
					if(ModeClick)
						ModeClick = false;
					else
						ModeClick = true;
				}
				else{}//援щ텇�슜 �떊寃� �꽩�꽩


				if(!ModeClick)//洹몃━湲� 紐⑤뱶
				{
					startP = e.getPoint();	 // �깮�꽦 �떆 洹몃젮吏덈븣 �굹�삤�뒗 �엫�떆 �궗媛곹삎	

					//�쁽�옱 而ㅼ꽌 醫뚰몴 ���옣 諛� clickV�뿉 �빐�떦 �룄�삎 踰덊샇 �깮�꽦�썑 false濡� 珥덇린�솕
					startV.add(e.getPoint());
					clickV.add(false);
				}
				else 		//�꽑�깮紐⑤뱶
				{
					if(BoxNum!=0) //�긽�옄媛� 1媛쒕씪�룄 �엳�뼱�빞 �옉�룞�븿.(0踰덉� Null�긽�옄�엫�쑝濡� �뾾�뒗 �뀍 移�)
					{
						tempRecSize = TransPoint.EndToTempRec(endV.get(BoxNum),15);
						// �빐�떦 �궗媛곹삎 �걹�젏 二쇱쐞�뿉 �엫�떆 �궗媛곹삎(�걹�젏 以묒떖�쑝濡� 媛�濡� 30, �꽭濡� 30)�깮�꽦
						// �엫�떆 �궗媛곹삎(�슦痢≫븯�떒 紐⑥꽌由� 洹쇱쿂) �븞�뿉 而ㅼ꽌媛� �엳�쓣 寃쎌슦
						if(tempRecSize.contains(new Point(e.getX(),e.getY())))
						{			
							//�뱶�옒洹� �떆�옉�쓣 �몴�떆
							sizeDragged = true;
						}
						else
						{	
							//�엫�떆 �궗媛곹삎(�겕湲곗“�젅�슜) �븞�뿉 X && �궗媛곹삎 �궡遺��뿉 而ㅼ꽌媛� �엳�쓣 寃쎌슦
							//�궗媛곹삎 �옄泥대�� ��吏곸뿬�빞�븿�쑝濡�, �렪�쓽瑜� �쐞�빐 �빐�떦 �궗媛곹삎�쓣 �엫�떆�궗媛곹삎�뿉 ���옣�빐�꽌 �뿰�궛.
							rec = TransPoint.pointToRec(startV.get(BoxNum),endV.get(BoxNum)); //�엫�떆�궗媛곹삎
							if(rec.contains(new Point(e.getX(),e.getY())))
							{
								//�긽�� �쐞移� ���옣
								moveP.x=e.getX() - rec.x;
								moveP.y=e.getY() - rec.y;
						
								//�뱶�옒洹� �떆�옉�쓣 �몴�떆
								moveDragged = true;
							}
						}
					}
				}
			}


			public void mouseReleased(MouseEvent e)
			{
				startP = null;
				endP = null;// 由대━�뒪�븯硫� �깮�꽦 �떆 洹몃젮吏덈븣 �굹�삤�뒗 �엫�떆 �궗媛곹삎 �궘�젣

				if(!ModeClick) // 洹몃━湲� 紐⑤뱶, 而ㅼ꽌 �뼹�뒗 �닚媛� �쁽�옱 醫뚰몴瑜� end�뿉 �꽔�쓬
				{
					endV.add(e.getPoint());
				}
				//留덉슦�뒪 踰꾪듉�씠 由대━利덈릺硫� �뱶�옒洹� 紐⑤뱶 醫낅즺
				sizeDragged = false;
				moveDragged = false;	

				repaint();			
			}
			
			public void mouseDragged(MouseEvent e)
			{
				Point tempEndP;
				if(ModeClick)//�꽑�깮紐⑤뱶
				{
					if(BoxNum!=0)
					{
						if(sizeDragged)//size �뱶�옒洹� 紐⑤뱶
						{
							tempEndP = new Point(e.getX(),e.getY());
							endV.set(BoxNum,tempEndP);
						}
						else if(moveDragged)//move �뱶�옒洹� 紐⑤뱶
						{
							//�떆�옉�젏, �걹�젏�쓣 �엫�떆�궗媛곹삎�뿉 �꽔�뿀湲� �븣臾몄뿉, �떆�옉�젏 x,y媛믩쭔 諛붽씀硫� �굹癒몄� 醫뚰몴�뒗 �븣�븘�꽌 怨꾩궛�맖.
							rec.x = e.getX() - moveP.x;
							rec.y = e.getY() - moveP.y;
									
							startV.set(BoxNum,TransPoint.RecToStartPoint(rec));
							endV.set(BoxNum,TransPoint.RecToEndPoint(rec));
						}
					}					
				}
				else
					endP = e.getPoint(); // �깮�꽦 �떆 洹몃젮吏덈븣 �굹�삤�뒗 �엫�떆 �궗媛곹삎
				repaint();
			}					
		}		
	}
	
	