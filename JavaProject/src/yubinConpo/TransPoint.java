package yubinConpo;

import java.awt.*;
import java.util.ArrayList;

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
	
	
	// ���� ��� ������ �߽����� �ϴ� ���� i����Ʈ, ���� i����Ʈ�� �ӽ� �� ����(�̵� ���� ����)
	public static Rectangle StartToTempOval(Point start,int i){ 
		return new Rectangle(start.x-i,start.y-i,i*2,i*2);
	}
	
	public static void JsonToBox(ArrayList<JsonBox> j, BoxModel b)
	{
		j.clear();
		JsonBox temp;
		for(int i=1;i<b.ArrSize();i++)
		{
			temp = new JsonBox(b.getRec(i),b.getClick(i),b.getType(i),b.getString(i));
			j.add(temp);
		}		
	}

}
