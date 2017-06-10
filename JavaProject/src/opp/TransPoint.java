package opp;

import java.awt.*;
import java.util.ArrayList;

public class TransPoint // 유틸
{
	//좌표 입력하면 사각형으로 만들어줌
	public static Rectangle pointToRec(int sx, int sy, int ex, int ey){ 
		return new Rectangle(sx,sy,ex-sx,ey-sy);
	} 
	//포인트 입력하면 사각형으로 만들어줌
	public static Rectangle pointToRec(Point start, Point end){ 
		return new Rectangle(start.x,start.y,end.x-start.x,end.y-start.y);
	} 

	//사각형 입력하면 시작 좌표 만들어줌
	public static Point RecToStartPoint(Rectangle a){ 
		return new Point(a.x,a.y);
	} 

	 //사각형 입력하면 끝 좌표 만들어줌
	public static Point RecToEndPoint(Rectangle a){
		return new Point(a.x+a.width,a.y+a.height);
	} 


	// 우측 하단 끝점을 중심으로 하는 가로 i포인트, 세로 i포인트인 임시 사각형 생성(변환 가능 범위)
	public static Rectangle EndToTempRec(Point end,int i){ 
		return new Rectangle(end.x-i,end.y-i,i*2,i*2);
	}
	
	
	// 좌측 상단 끝점을 중심으로 하는 가로 i포인트, 세로 i포인트인 임시 원 생성(이동 가능 범위)
	public static Rectangle StartToTempOval(Point start,int i){ 
		return new Rectangle(start.x-i,start.y-i,i*2,i*2);
	}
	
	
	// choose변환용
	void ChangeChoose(int i)	{
		componetVer.Choose = i;
	}
	
	//viewText 변환용
	void ChangeviewText(String a) {
		componetVer.viewText = a;
	}
	
	// 모두 삭제용
	
	static void AllCompclear()
	{
		componetVer.startV.clear();
		componetVer.endV.clear();
		componetVer.clickV.clear();
		componetVer.TypeV.clear();
		componetVer.TextV.clear();
		
		componetVer.startV.add(new Point(10,10));
		componetVer.endV.add(new Point(10,10));
		componetVer.clickV.add(false);
		componetVer.TypeV.add(null);
		componetVer.TextV.add(null);

		Layout.comTemp.repaint();
		Layout.comTemp.BoxNum = 0;
		Layout.comTemp.OpenNum = false;

	}
	
	//박스 -> json용 배열로 변환
	public static void JsonToBox(ArrayList<JsonBox> j, BoxModel b)
	{
		j.clear();
		JsonBox temp;
		for(int i=0;i<b.ArrSize();i++)
		{
			if(i==0)
			{
				temp = new JsonBox(0,0,0,0,
						false,
						0,
						"null");
				j.add(temp);
			}
			else
			{
				temp = new JsonBox(
						(int)b.getRec(i).getX(), 
						(int)b.getRec(i).getY(), 
						(int)b.getRec(i).getWidth(), 
						(int)b.getRec(i).getHeight(),
						b.getClick(i),
						b.getType(i),
						b.getString(i));
				j.add(temp);
			}
		}		
	}
	
	
	//json 배열-> 박스 변환
	public static void BoxToJson(ArrayList<JsonBox> j, BoxModel b)
	{
		b.Box.clear();
		b.clickM.clear();
		b.ConType.clear();
		b.conText.clear();
		
		Rectangle temp;
	
		b.Box.add(new Rectangle(10,10,0,0));
		b.clickM.add(false);
		b.ConType.add(null);
		b.conText.add(null);
		
		for(int i=1;i<j.size();i++)
		{
			temp = new Rectangle(j.get(i).x,j.get(i).y,j.get(i).width,j.get(i).height);
			b.Box.add(temp);
			b.clickM.add(j.get(i).Cl);
			b.ConType.add(j.get(i).ComT);
			b.conText.add(j.get(i).ComText);
			
		}
		b.NowBoxNumM=0;
		componetVer.boxM.RodeArrayBox(componetVer.startV,componetVer.endV,componetVer.clickV, componetVer.TypeV,componetVer.TextV);

	}

}
