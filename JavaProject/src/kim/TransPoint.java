package kim;

import java.awt.*;

public class TransPoint // ��ƿ
{
	//��ǥ �Է��ϸ� �簢������ �������
	public static Rectangle pointToRec(int sx, int sy, int ex, int ey){ 
		return new Rectangle(sx,sy,ex-sx,ey-sy);
	} 
	//����Ʈ �Է��ϸ� �簢������ �������
	public static Rectangle pointToRec(Point start, Point end){ 
		return new Rectangle(start.x,start.y,end.x-start.x,end.y-start.y);
	} 

	//�簢�� �Է��ϸ� ���� ��ǥ �������
	public static Point RecToStartPoint(Rectangle a){ 
		return new Point(a.x,a.y);
	} 

	 //�簢�� �Է��ϸ� �� ��ǥ �������
	public static Point RecToEndPoint(Rectangle a){
		return new Point(a.x+a.width,a.y+a.height);
	} 


	// ���� �ϴ� ������ �߽����� �ϴ� ���� i����Ʈ, ���� i����Ʈ�� �ӽ� �簢�� ����(��ȯ ���� ����)
	public static Rectangle EndToTempRec(Point end,int i){ 
		return new Rectangle(end.x-i,end.y-i,i*2,i*2);
	}

}
