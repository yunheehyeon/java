package yubinConpo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class componetVer extends JPanel
{

	public static BoxModel boxM = new BoxModel("»óÀÚ");
	public boolean ModeClick = false; // false : ±×¸®±â ¸ğµå, true : ¼±ÅÃ¸ğµå
	
	int Choose = BUTTON; // ÀÌ °ªÀ» ¹Ù²Û ÈÄ »ı¼ºÇÏ¸é ÄÄÆ÷³ÍÆ® Á¾·ù ¹Ù²ñ
	String viewText = "net"; // ÀÌ °ªÀ» ¹Ù²Û ÈÄ »ı¼ºÇÏ¸é ÄÄÆ÷³ÍÆ® ÅÃ½ºÆ® ¹Ù²ñ
		
	// ¿ÜºÎ Á¶È¸¿ë
	//-------------------------------------------------------------------------------------------
	// ³»ºÎÀûÀ¸·Î¸¸ »ç¿ëÇÏ´Â º¯¼ö

	
	ArrayList<JComponent> buttons = new ArrayList<JComponent>(); //ÄÄÆ÷³ÍÆ®µéÀ» ÀúÀåÇÒ ¹è¿­. ´Ù¾çÇÑ ÄÄÆ÷³ÍÆ® ÀúÀåÀ» À§ÇØ ÃÖ»óÀ§ ½´ÆÛ Å¬·¡½º JComponent »ç¿ë
	ArrayList<Point> startV = new ArrayList<Point>(); // ½ÃÀÛÁ¡ ¸ğÀ½ ¹è¿­ 
	ArrayList<Point> endV = new ArrayList<Point>(); // ³¡Á¡ ¸ğÀ½ ¹è¿­ 
	ArrayList<Boolean> clickV = new ArrayList<Boolean>(); //Å¬¸¯µÇ¾îÁø »ç°¢Çü È®ÀÎ¿ë ±âº» : false, ¼±ÅÃ : true
	ArrayList<Integer> TypeV = new ArrayList<Integer>(); //Å¸ÀÔÀúÀå ¹è¿­
	ArrayList<String> TextV = new ArrayList<String> (); // ÅØ½ºÆ® ÀúÀå ¹è¿­
	
	
	// ÇöÀç ¼±ÅÃµÈ ¹Ú½º ¹øÈ£	
	int BoxNum = 0;

	
	//ÄÄÆ÷³ÍÆ® Á¾·ù
	public static int BUTTON = 1;
	public static int JPANEL = 2;
	public static int JSCROLL = 3;
	public static int JTEXTFIELD = 4;
	

	
	boolean sizeDragged = false; //¿òÁ÷ÀÌ°í ½ÍÀº »ç°¢Çü ¾È¿¡¼­ ´­·¶À»¶§ true·Î º¯ÇÔ.
	boolean moveDragged = false; //Å©±â¸¦ ¹Ù²Ù°í ½ÍÀº »ç°¢ÇüÀÇ ²ÀÁöÁ¡À» ´­·¶À»¶§ true·Î º¯ÇÔ.
	
	Point startP = new Point();
	Point endP = new Point();
	//»ı¼ºÇÒ ¶§ ¿òÁ÷ÀÓÀ» º¸ÀÌ°Ô ¸¸µé¾îÁÖ´Â ¿ëµµ

	//¿òÁ÷ÀÏ¶§ »ç¿ëÇÏ´Â »ç°¢Çü ±¸Á¶Ã¼¿Í Point±¸Á¶Ã¼
	Rectangle rec;
	Point moveP = new Point();
		
	

	public componetVer()
	{
		MouseListen ml = new MouseListen();
		this.addMouseListener(ml); 
		this.addMouseMotionListener(ml);
		//¸¶¿ì½º ¸®½º³Ê µî·Ï
		
		//¹Ú½º0. »ı¼º. ¾Æ¹«°Íµµ ¾È °¡¸®Å³¶§ »ç¿ëÇÏ´Â Null »óÀÚ
		startV.add(new Point(0,0));
		endV.add(new Point(0,0));
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

		
		buttons.clear(); //ÄÄÆ÷³ÍÆ® ÀüÃ¼ »èÁ¦ ÈÄ ´Ù½Ã»ı¼º
		buttons.add(null);
		
	

		
		//view
		for(int i=1;i<boxM.ArrSize();i++)  // ÄÄÆ÷³ÍÆ® »ı¼ºÈÄ 
		{
			JComponent j =null; // °¢ ÄÄÆ÷³ÍÆ®ÀÇ ½´ÆÛ Å¬·¡½ºÀÎ JComponent·Î ¼±¾ğ 
			
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
<<<<<<< HEAD
		for(int i=boxM.ArrSize()-1;i>0;i--)  // ¸ğµ¨ °´Ã¼¿¡ ÀúÀåµÈ °¢ »ç°¢ÇüÀ» ¸Å¹ø ÇÏ³ª¾¿ ÀüºÎ ±×¸²
			add(buttons.get(i));

=======
		
		for(int i=1;i<boxM.ArrSize();i++){  // ¼±ÅÃ ¹× ÀÌµ¿¿ë ¿ø
			g.setColor(Color.RED);
			g.drawOval(boxM.recgetX(i)-15,boxM.recgetY(i) -15, 30, 30);
		}
		
		for(int i=(boxM.ArrSize()-1);i>0;i--){ // ¸ğµ¨ °´Ã¼¿¡ ÀúÀåµÈ °¢ »ç°¢ÇüÀ» ¸Å¹ø ÇÏ³ª¾¿ ÀüºÎ ±×¸²(ÃÖ½ÅÀ» ¸Ç À§¿¡) 
			add(buttons.get(i));
		}		
>>>>>>> ì„ì‹œ
		
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
			
			if(ModeClick)//¼±ÅÃ ¸ğµå 
			{
				if(e.getButton()==1) // ¿ŞÂÊ ¸¶¿ì½º ´©¸¦ ¶§ = ¼±ÅÃÇÏ±â
				{
					//º¤ÅÍ¿¡ ÀúÀåµÈ »ç°¢Çü ¾È¿¡ Ä¿¼­°¡ ÀÖ´Â Áö È®ÀÎ
					// º¤ÅÍ¿¡ ÀúÀåµÈ °¢ »ç°¢ÇüÀ» ¸Å¹ø ±×¸². ´Ü, ÃÖ±Ù¿¡ ±×·ÁÁø »ç°¢ÇüÀÌ °¡Àå ¸ÕÀú °Ë»ç
					for(int i=(endV.size()-1);i>0;i--) 
					{							
						tempRecClick = TransPoint.StartToTempOval(startV.get(i),30);
						if(tempRecClick.contains(new Point(e.getX(),e.getY()))) // »óÀÚ ¾È¿¡ Ä¿¼­°¡ ÀÖÀ¸¸é
						{	
							for(int j=0;j<endV.size();j++)
								clickV.set(j,false);
								//¾Æ¹« »óÀÚ³ª Å¬¸¯ÇÏ¸é ¿ì¼± ÀüÃ¼ »ö±ò ÃÊ±âÈ­ ÇÑÈÄ
							clickV.set(i,true);
								// ÇØ´ç »óÀÚ »ö¸¸ ´Ù½Ã¼³Á¤
							BoxNum = i;//¼±ÅÃµÇ¾îÁø ÇöÀç »óÀÚ ¹øÈ£ ÀúÀå
							break;					
						}
						else // ¹è°æ Å¬¸¯ÇÏ¸é ÃÊ±âÈ­ 
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
				else if(e.getButton()==3) // ¿À¸¥ÂÊ ¸¶¿ì½º ´©¸¦ ¶§ = ¼±ÅÃµÈ »óÀÚ »èÁ¦
				{
					if(BoxNum!=0) // ¼±ÅÃµÈ ¹Ú½º°¡ ÀÖÀ» ¶§¸¸ ½ÇÇà
					{
						startV.remove(BoxNum);
						endV.remove(BoxNum);
						clickV.remove(BoxNum);
						BoxNum=0; // ¼±ÅÃµÈ ¹Ú½º ÇØÁ¦
					}
				}
				
				boxM.ArrayPointToRec(startV,endV,clickV, TypeV,TextV);
				//System.out.println(boxM.NowBoxNumM);
			}
			repaint();	
		}


		public void mousePressed(MouseEvent e)
		{
<<<<<<< HEAD
			Rectangle tempRecSize; 
			//»çÀÌÁî º¯°æÇÒ ¶§ ¾²´Â ÀÓ½Ã »ç°¢Çü

			if(e.getButton()==1)//°¡¿îµ¥ ¹öÆ° & ¿ŞÂÊ ¹öÆ°À¸·Î ±×¸± ¼ö ¾ø°Ô ¸·À½
			{
				if(!ModeClick)//±×¸®±â ¸ğµå
				{
					TypeV.add(Choose); // Å¸ÀÔ¼±ÅÃ¿ë
					TextV.add(viewText); // ÅØ½ºÆ® ÀÔ·Â¿ë
					
					startP = e.getPoint();	 // »ı¼º ½Ã ±×·ÁÁú¶§ ³ª¿À´Â ÀÓ½Ã »ç°¢Çü	

					//ÇöÀç Ä¿¼­ ÁÂÇ¥ ÀúÀå ¹× clickV¿¡ ÇØ´ç µµÇü ¹øÈ£ »ı¼ºÈÄ false·Î ÃÊ±âÈ­
					startV.add(e.getPoint());
					clickV.add(false);
				}
				else 		//¼±ÅÃ¸ğµå
				{
					if(BoxNum!=0) //»óÀÚ°¡ 1°³¶óµµ ÀÖ¾î¾ß ÀÛµ¿ÇÔ.(0¹øÀº Null»óÀÚÀÓÀ¸·Î ¾ø´Â ¼À Ä§)
					{
						tempRecSize = TransPoint.EndToTempRec(endV.get(BoxNum),15);
						// ÇØ´ç »ç°¢Çü ³¡Á¡ ÁÖÀ§¿¡ ÀÓ½Ã »ç°¢Çü(³¡Á¡ Áß½ÉÀ¸·Î °¡·Î 30, ¼¼·Î 30)»ı¼º
						// ÀÓ½Ã »ç°¢Çü(¿ìÃøÇÏ´Ü ¸ğ¼­¸® ±ÙÃ³) ¾È¿¡ Ä¿¼­°¡ ÀÖÀ» °æ¿ì
						if(tempRecSize.contains(new Point(e.getX(),e.getY())))
						{			
							//µå·¡±× ½ÃÀÛÀ» Ç¥½Ã
							sizeDragged = true;
						}
						else
						{	
							//ÀÓ½Ã »ç°¢Çü(Å©±âÁ¶Àı¿ë) ¾È¿¡ X && »ç°¢Çü ³»ºÎ¿¡ Ä¿¼­°¡ ÀÖÀ» °æ¿ì
							//»ç°¢Çü ÀÚÃ¼¸¦ ¿òÁ÷¿©¾ßÇÔÀ¸·Î, ÆíÀÇ¸¦ À§ÇØ ÇØ´ç »ç°¢ÇüÀ» ÀÓ½Ã»ç°¢Çü¿¡ ÀúÀåÇØ¼­ ¿¬»ê.
							Rectangle tempRec = TransPoint.StartToTempOval(startV.get(BoxNum),30);
							rec = TransPoint.pointToRec(startV.get(BoxNum),endV.get(BoxNum)); //ÀÓ½Ã»ç°¢Çü
							if(tempRec.contains(new Point(e.getX(),e.getY())))
							{
								//»ó´ë À§Ä¡ ÀúÀå
								moveP.x=e.getX() - rec.x;
								moveP.y=e.getY() - rec.y;
						
								//µå·¡±× ½ÃÀÛÀ» Ç¥½Ã
								moveDragged = true;
							}
						}
					}
				}
			}
			
=======
>>>>>>> ì„ì‹œ
			if(e.getButton()==2) // ¸¶¿ì½º °¡¿îµ¥ ¹ÙÆ° ´©¸£¸é ¸¸µé±â ¸ğµå <-> ¼±ÅÃ¸ğµå ¼±ÅÃ °¡´É
			{
				if(ModeClick)
					ModeClick = false;
				else
					ModeClick = true;
			}
<<<<<<< HEAD
			else{}//±¸ºĞ¿ë ½Å°æ ¤¤¤¤


=======
			Rectangle tempRecSize; 
			//»çÀÌÁî º¯°æÇÒ ¶§ ¾²´Â ÀÓ½Ã »ç°¢Çü
			
			if(!ModeClick)//±×¸®±â ¸ğµå
			{
				TypeV.add(Choose); // Å¸ÀÔ¼±ÅÃ¿ë
				TextV.add(viewText); // ÅØ½ºÆ® ÀÔ·Â¿ë
				
				startP = e.getPoint();	 // »ı¼º ½Ã ±×·ÁÁú¶§ ³ª¿À´Â ÀÓ½Ã »ç°¢Çü	

				//ÇöÀç Ä¿¼­ ÁÂÇ¥ ÀúÀå ¹× clickV¿¡ ÇØ´ç µµÇü ¹øÈ£ »ı¼ºÈÄ false·Î ÃÊ±âÈ­
				startV.add(e.getPoint());
				clickV.add(false);
			}
			else 		//¼±ÅÃ¸ğµå
			{
				if(BoxNum!=0) //»óÀÚ°¡ 1°³¶óµµ ÀÖ¾î¾ß ÀÛµ¿ÇÔ.(0¹øÀº Null»óÀÚÀÓÀ¸·Î ¾ø´Â ¼À Ä§)
				{
					tempRecSize = TransPoint.EndToTempRec(endV.get(BoxNum),15);
					// ÇØ´ç »ç°¢Çü ³¡Á¡ ÁÖÀ§¿¡ ÀÓ½Ã »ç°¢Çü(³¡Á¡ Áß½ÉÀ¸·Î °¡·Î 30, ¼¼·Î 30)»ı¼º
					// ÀÓ½Ã »ç°¢Çü(¿ìÃøÇÏ´Ü ¸ğ¼­¸® ±ÙÃ³) ¾È¿¡ Ä¿¼­°¡ ÀÖÀ» °æ¿ì
					if(tempRecSize.contains(new Point(e.getX(),e.getY())))
					{			
						//µå·¡±× ½ÃÀÛÀ» Ç¥½Ã
						sizeDragged = true;
					}
					else
					{	
						//ÀÓ½Ã »ç°¢Çü(Å©±âÁ¶Àı¿ë) ¾È¿¡ X && »ç°¢Çü ³»ºÎ¿¡ Ä¿¼­°¡ ÀÖÀ» °æ¿ì
						//»ç°¢Çü ÀÚÃ¼¸¦ ¿òÁ÷¿©¾ßÇÔÀ¸·Î, ÆíÀÇ¸¦ À§ÇØ ÇØ´ç »ç°¢ÇüÀ» ÀÓ½Ã»ç°¢Çü¿¡ ÀúÀåÇØ¼­ ¿¬»ê.
						Rectangle tempRec = TransPoint.StartToTempOval(startV.get(BoxNum),30);
						rec = TransPoint.pointToRec(startV.get(BoxNum),endV.get(BoxNum)); //ÀÓ½Ã»ç°¢Çü
						if(tempRec.contains(new Point(e.getX(),e.getY())))
						{
							//»ó´ë À§Ä¡ ÀúÀå
							moveP.x=e.getX() - rec.x;
							moveP.y=e.getY() - rec.y;
					
							//µå·¡±× ½ÃÀÛÀ» Ç¥½Ã
							moveDragged = true;
						}
					}
				}			
			}	
>>>>>>> ì„ì‹œ
		}


		public void mouseReleased(MouseEvent e)
<<<<<<< HEAD
		{
			if(e.getButton()==1)
=======
		{			
			startP = null;
			endP = null;// ¸±¸®½ºÇÏ¸é »ı¼º ½Ã ±×·ÁÁú¶§ ³ª¿À´Â ÀÓ½Ã »ç°¢Çü »èÁ¦

			if(!ModeClick) // ±×¸®±â ¸ğµå, Ä¿¼­ ¶¼´Â ¼ø°£ ÇöÀç ÁÂÇ¥¸¦ end¿¡ ³ÖÀ½
>>>>>>> ì„ì‹œ
			{
				startP = null;
				endP = null;// ¸±¸®½ºÇÏ¸é »ı¼º ½Ã ±×·ÁÁú¶§ ³ª¿À´Â ÀÓ½Ã »ç°¢Çü »èÁ¦

				if(!ModeClick) // ±×¸®±â ¸ğµå, Ä¿¼­ ¶¼´Â ¼ø°£ ÇöÀç ÁÂÇ¥¸¦ end¿¡ ³ÖÀ½
				{
					endV.add(e.getPoint());
				}
				//¸¶¿ì½º ¹öÆ°ÀÌ ¸±¸®ÁîµÇ¸é µå·¡±× ¸ğµå Á¾·á
				sizeDragged = false;
				moveDragged = false;	

			}	
			repaint();		

		}
		
		public void mouseDragged(MouseEvent e)
		{
			Point tempEndP;
			if(ModeClick)//¼±ÅÃ¸ğµå
			{
				if(BoxNum!=0)
				{
					if(sizeDragged)//size µå·¡±× ¸ğµå
					{
						JTextField j = new JTextField(); 

						j.setSize(boxM.getRecWidth(BoxNum)-buttons.get(BoxNum).getX(), boxM.getRecHeight(BoxNum)-buttons.get(BoxNum).getY());
						
						buttons.set(BoxNum, j);
						
						tempEndP = new Point(e.getX(),e.getY());
						endV.set(BoxNum,tempEndP);
					}
					else if(moveDragged)//move µå·¡±× ¸ğµå
					{
						//½ÃÀÛÁ¡, ³¡Á¡À» ÀÓ½Ã»ç°¢Çü¿¡ ³Ö¾ú±â ¶§¹®¿¡, ½ÃÀÛÁ¡ x,y°ª¸¸ ¹Ù²Ù¸é ³ª¸ÓÁö ÁÂÇ¥´Â ¾Ë¾Æ¼­ °è»êµÊ.
						//j.setSize(e.getX() - moveP.x,rec.y = e.getY() - moveP.y)
						rec.x = e.getX() - moveP.x;
						rec.y = e.getY() - moveP.y;
						startV.set(BoxNum,TransPoint.RecToStartPoint(rec));
						endV.set(BoxNum,TransPoint.RecToEndPoint(rec));
					}
				}					
			}
			else
				endP = e.getPoint(); // »ı¼º ½Ã ±×·ÁÁú¶§ ³ª¿À´Â ÀÓ½Ã »ç°¢Çü
			repaint();
		}					
	}
}
	
	