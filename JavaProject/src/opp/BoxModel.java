package opp;

import java.awt.*;
import java.util.ArrayList;


public class BoxModel
{
	public ArrayList<Rectangle> Box = new ArrayList<Rectangle>();
	public ArrayList<Boolean> clickM = new ArrayList<Boolean>(); 
	public ArrayList<Integer> ConType = new ArrayList<Integer>();
	public ArrayList<String> conText = new ArrayList<String> ();
	public int NowBoxNumM = 0;

	
	
	public static int BUTTON = 1;
	public static int JLABLE = 2;
	public static int JSCROLL = 3;
	public static int JTEXTFIELD = 4;

	BoxModel( )
	{
		Box.add(null);
		clickM.add(false);
		ConType.add(null);
		conText.add(null);
	}


	//�迭 : �ӽ� ����Ʈ�� boxM�� ����
	void ArrayPointToRec(ArrayList<Point> start, ArrayList<Point> end, ArrayList<Boolean> click,  ArrayList<Integer> Type, ArrayList<String> Text)
	{
		Box.clear();
		clickM.clear();
		ConType.clear();
		conText.clear();

		Box.add(null);
		clickM.add(false);
		ConType.add(null);
		conText.add(null);
		
		for(int i=1;i<end.size();i++)
		{
			Box.add(TransPoint.pointToRec(start.get(i),end.get(i)));
			clickM.add(click.get(i));				
			ConType.add(Type.get(i));
			conText.add(Text.get(i));

			if(click.get(0)==true)
				NowBoxNumM = 0;
			if(click.get(i)==true)
				NowBoxNumM = i;
		}
	}

	
	//���� �ҷ��ö� ���, ����Ǿ��� �� �ڽ� ��ü ������ startV,endV,clickV, type, text�� ȯ����.
	void RodeArrayBox(ArrayList<Point> start,ArrayList<Point> end, ArrayList<Boolean> click,  ArrayList<Integer> Type, ArrayList<String> Text)
	{
		start.clear();
		end.clear();
		click.clear();
		Type.clear();
		Text.clear();
		
		
		start.add(new Point(10,10));
		end.add(new Point(10,10));
		click.add(false);
		Type.add(null);
		Text.add(null);
		
		for(int i=1;i<Box.size();i++)
		{			
			start.add(TransPoint.RecToStartPoint(Box.get(i)));
			end.add(TransPoint.RecToEndPoint(Box.get(i)));
			click.add(clickM.get(i));
			Type.add(ConType.get(i));
			Text.add(conText.get(i));
		}
	}
	
	
	//�ؽ�Ʈ ��ȸ��
	public String getString(int i){ 
		return conText.get(i);
	}
	
	 // Ÿ�� ��ȸ��
	public Integer getType(int i){
		return ConType.get(i);
	}


	
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


}