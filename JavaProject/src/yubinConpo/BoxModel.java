package yubinConpo;

import java.awt.*;
import java.util.ArrayList;


public class BoxModel
{
	public String BoxSetName;
	public ArrayList<Rectangle> Box = new ArrayList<Rectangle>();
	public ArrayList<Boolean> clickM = new ArrayList<Boolean>(); 
	public int NowBoxNumM = 0;
	public ArrayList<Integer> ConType = new ArrayList<Integer>();
	public ArrayList<String> conText = new ArrayList<String> ();

	
	
	public static int BUTTON = 1;
	public static int JLABLE = 2;
	public static int JSCROLL = 3;
	public static int JTEXTFIELD = 4;
	



	BoxModel(String BoxSetName)
	{
		this.BoxSetName = BoxSetName;
		Box.add(null);
		clickM.add(false);
		ConType.add(null);
		conText.add(null);
	}




	//배열 : 임시 포인트를 모델 객체에 저장
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

	
	//상자 불러올때 사용, 저장되었던 모델 박스 객체 정보를 startV,endV,clickV, type, text로 환원함.
	void RodeArrayBox(ArrayList<Point> start,ArrayList<Point> end, ArrayList<Boolean> click,  ArrayList<Integer> Type, ArrayList<String> Text)
	{
		for(int i=0;i<Box.size();i++)
		{
			start.set(i,TransPoint.RecToStartPoint(Box.get(i)));
			end.set(i,TransPoint.RecToEndPoint(Box.get(i)));
			click.set(i,clickM.get(i));
			Type.set(i, ConType.get(i));
			Text.set(i, conText.get(i));
		}
	}



	//텍스트 조회용
	public String getString(int i){ 
		return conText.get(i);
	}
	
	 // 타입 조회용
	public Integer getType(int i){
		return ConType.get(i);
	}


	
	//현재 선택된 상자 번호 로드
	public int getNum(){
		return NowBoxNumM;
	}
	//배열 사이즈
	public int ArrSize(){
		return Box.size();
	}

	//번호 상자 클릭 여부 로드
	public boolean getClick(int i){
		return (boolean) clickM.get(i);
	}

	//번호 상자 로드
	public Rectangle getRec(int i){
		return Box.get(i);
	}
	//현재 선택된 상자로드
	public Rectangle getRec(){
		return getRec(NowBoxNumM);
	}


	//번호 상자 시작 Point
	public Point getStartPoint(int i){
		return Box.get(i).getLocation();
	}
	//현재 선택된  상자 시작 Point
	public Point getStartPoint(){
		return getStartPoint(NowBoxNumM);
	}


	// 상자 시작 x값, y값
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



	//번호 상자 끝 Point
	public Point getEndPoint(int i){
		return TransPoint.RecToEndPoint(Box.get(i));
	}
	//현재 선택된  상자 끝 Point
	public Point getEndPoint(){
		return getEndPoint(NowBoxNumM);
	}



	//번호 상자 너비 
	public int getRecWidth(int i){
		return (int)Box.get(i).getWidth(); // 상동
	}
	//현재 선택된 상자 너비 
	public int getRecWidth(){
		return getRecWidth(NowBoxNumM);
	}



	//번호 상자 높이 
	public int getRecHeight(int i){
		return (int)Box.get(i).getHeight(); //getheight가 double리턴해서 편의를 위해 int로 강제형변환
	}
	//현재 선택된  상자 높이 
	public int getRecHeight(){
		return getRecHeight(NowBoxNumM);
	}


}
