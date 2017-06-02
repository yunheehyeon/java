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




	//배열 : 임시 포인트를 모델 객체에 저장
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

	//상자 불러올때 사용, 모델 박스 객체를 startV,endV,clickV로 환원함.
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




	//시작점 입력


}
