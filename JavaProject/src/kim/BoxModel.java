package kim;

import java.awt.*;
import java.util.ArrayList;


public class BoxModel
{
	String BoxSetName;
	private static ArrayList<Rectangle> Box = new ArrayList<Rectangle>();
	private ArrayList<Boolean> clickM = new ArrayList<Boolean>(); 
	int NowBoxNumM = 0;



	BoxModel(String BoxSetName)
	{
		this.BoxSetName = BoxSetName;
	}




	//배열 : 포인트 -> 상자로 변환 메소드
	void ArrayPointToRec(ArrayList<Point> start, ArrayList<Point> end, ArrayList<Boolean> click)
	{
		for(int i=0;i<end.size();i++)
		{
			Box.set(i,TransPoint.pointToRec(start.get(i),end.get(i)));
			clickM.set(i,click.get(i));
		}
	}

	//상자 불러올때 사용, startV,endV에 사용
	void RodeArrayBox(ArrayList<Point> start,ArrayList<Point> end, ArrayList<Boolean> click)
	{
		for(int i=0;i<Box.size();i++)
		{
			start.set(i,TransPoint.RecToStartPoint(Box.get(i)));
			end.set(i,TransPoint.RecToEndPoint(Box.get(i)));
			click.set(i,clickM.get(i));
		}
	}





//현재 선택된 상자 번호 로드
	int getNum(){
		return NowBoxNumM;
	}

	//번호 상자 로드
	Rectangle getRec(int i){
		return Box.get(i);
	}
	//현재 선택된 상자로드
	Rectangle getRec(){
		return getRec(NowBoxNumM);
	}


	//번호 상자 시작 Point
	Point getStartPoint(int i){
		return Box.get(i).getLocation();
	}
//현재 선택된  상자 시작 Point
	Point getStartPoint(){
		return getStartPoint(NowBoxNumM);
	}


	//번호 상자 높이 세로
	int getRecHeight(int i){
		return (int)Box.get(i).getHeight(); //getheight가 double리턴해서 편의를 위해 int로 강제형변환
	}
	//현재 선택된  상자 높이 세로
	int getRecHeight(){
		return getRecHeight(NowBoxNumM);
	}



	//번호 상자 너비 가로
	int getRecWidth(int i){
		return (int)Box.get(i).getWidth(); // 상동
	}
	//현재 선택된 상자 너비 가로
	int getRecWidth(){
		return getRecWidth(NowBoxNumM);
	}

}
