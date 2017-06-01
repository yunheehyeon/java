package kim;

import java.awt.*;

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

}
