package kim;

import java.awt.*;
import java.util.ArrayList;


public class BoxModel
{
	public String BoxSetName;
	public ArrayList<Rectangle> Box = new ArrayList<Rectangle>();
	public ArrayList<Boolean> clickM = new ArrayList<Boolean>(); 
	public int NowBoxNumM = 0;



	BoxModel(String BoxSetName)
	{
		this.BoxSetName = BoxSetName;
		Box.add(null);
		clickM.add(false);

	}




	//�迭 : �ӽ� ����Ʈ�� �� ��ü�� ����
	void ArrayPointToRec(ArrayList<Point> start, ArrayList<Point> end, ArrayList<Boolean> click)
	{

		Box.clear();
		clickM.clear();
		Box.add(null);
		clickM.add(false);

		for(int i=1;i<end.size();i++)
		{
			Box.add(TransPoint.pointToRec(start.get(i),end.get(i)));
			clickM.add(click.get(i));				
			
			
			if(click.get(i)==true)
				NowBoxNumM = i;
		}
	}

	//���� �ҷ��ö� ���, �� �ڽ� ��ü�� startV,endV,clickV�� ȯ����.
	void RodeArrayBox(ArrayList<Point> start,ArrayList<Point> end, ArrayList<Boolean> click)
	{
		for(int i=0;i<Box.size();i++)
		{
			start.set(i,TransPoint.RecToStartPoint(Box.get(i)));
			end.set(i,TransPoint.RecToEndPoint(Box.get(i)));
			click.set(i,clickM.get(i));
		}
	}





	//control
	//���� ���õ� ���� ��ȣ �ε�
	public int getNum(){
		return NowBoxNumM;
	}
	//�迭 ������
	public int ArrSize(){
		return Box.size();
	}

	//��ȣ ���� Ŭ�� ���� �ε�
	public boolean getClick(int i){
		return (boolean) clickM.get(i);
	}

	//��ȣ ���� �ε�
	public Rectangle getRec(int i){
		return Box.get(i);
	}
	//���� ���õ� ���ڷε�
	public Rectangle getRec(){
		return getRec(NowBoxNumM);
	}


	//��ȣ ���� ���� Point
	public Point getStartPoint(int i){
		return Box.get(i).getLocation();
	}
	//���� ���õ�  ���� ���� Point
	public Point getStartPoint(){
		return getStartPoint(NowBoxNumM);
	}


	// ���� ���� x��, y��
	public int recgetX(int i)	{
		return (int)Box.get(i).getX();
	}
	public int recgetX()	{
		return (int)Box.get(NowBoxNumM).getX();
	}


	public int recgetY(int i)	{
		return (int)Box.get(i).getY();
	}
	public int recgetY()	{
		return (int)Box.get(NowBoxNumM).getY();
	}



	//��ȣ ���� �� Point
	public Point getEndPoint(int i){
		return TransPoint.RecToEndPoint(Box.get(i));
	}
	//���� ���õ�  ���� �� Point
	public Point getEndPoint(){
		return getEndPoint(NowBoxNumM);
	}



	//��ȣ ���� �ʺ� 
	public int getRecWidth(int i){
		return (int)Box.get(i).getWidth(); // ��
	}
	//���� ���õ� ���� �ʺ� 
	public int getRecWidth(){
		return getRecWidth(NowBoxNumM);
	}



	//��ȣ ���� ���� 
	public int getRecHeight(int i){
		return (int)Box.get(i).getHeight(); //getheight�� double�����ؼ� ���Ǹ� ���� int�� ��������ȯ
	}
	//���� ���õ�  ���� ���� 
	public int getRecHeight(){
		return getRecHeight(NowBoxNumM);
	}




	//������ �Է�


}
