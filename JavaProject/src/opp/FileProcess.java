package opp;

import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class FileProcess {
	static public String temp;

}

class Open extends JFrame implements ActionListener{
	
	private JFileChooser jfc = new JFileChooser();

	Open(){
		this.start();
	}
 	
 	public void start(){
               
        jfc.setFileFilter(new FileNameExtensionFilter("json", "json"));
                // 파일 필터
        jfc.setMultiSelectionEnabled(false);//다중 선택 불가
    }
    
 	public void actionPerformed(ActionEvent e){

	   if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
	   { // showopendialog 열기 창을 열고 확인 버튼을 눌렀는지 확인
				try
                {
              		Gson gson = new Gson();                        		  
              		BufferedReader br = new BufferedReader(new FileReader(jfc.getSelectedFile().toString()));
              		ArrayList<JsonBox> jsonbox =  gson.fromJson(br,  new TypeToken<ArrayList<JsonBox>>(){}.getType());
              		TransPoint.BoxToJson(jsonbox,componetVer.boxM);
              		componetVer.boxM.RodeArrayBox(componetVer.startV,componetVer.endV,componetVer.clickV, componetVer.TypeV,componetVer.TextV);
               		componetVer.OpenNum=true;
                  
                    Layout.comTemp.repaint();	

              	}
              	catch(IOException g)
             	{
               		g.printStackTrace();
              	}                   
        }

	}
}

class Save extends JFrame implements ActionListener{
	
	private JFileChooser jfc = new JFileChooser();

	Save(){
	  	jfc.setFileFilter(new FileNameExtensionFilter("json", "json"));
                // 파일 필터
        jfc.setMultiSelectionEnabled(false);//다중 선택 불가
	}

	  public void actionPerformed(ActionEvent e) {
	  
	  		 if(jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) // showSaveDialog 저장 창을 열고 확인 버튼을 눌렀는지 확인
              { 
                  ArrayList<JsonBox> jsonbox = new ArrayList<JsonBox>();
                  TransPoint.JsonToBox(jsonbox,componetVer.boxM);
                        	
                   Gson gson = new Gson();
                   String json = gson.toJson(jsonbox);
                        	
                        	
                   try
                    {
                	   FileProcess.temp = jfc.getSelectedFile().toString() + "." + jfc.getFileFilter().getDescription();
                      	FileWriter fw = new FileWriter(FileProcess.temp); //jfc.getSelectedFile().toString()
                       	
                    	fw.write(json);
                    	fw.flush();
                      fw.close();
                     }
                     catch(IOException g)
                     {
                      	g.printStackTrace();
                     }
              }
	  
	  }
}

class OverwriteSave extends JFrame implements ActionListener{
	

	OverwriteSave(){
	  	
	}

	  public void actionPerformed(ActionEvent e) {
	  
	  		
                  ArrayList<JsonBox> jsonbox = new ArrayList<JsonBox>();
                  TransPoint.JsonToBox(jsonbox,componetVer.boxM);
                        	
                   Gson gson = new Gson();
                   String json = gson.toJson(jsonbox);
                        	
                        	
                   try
                    {
                      	FileWriter fw = new FileWriter(FileProcess.temp); //jfc.getSelectedFile().toString()
                       	
                    	fw.write(json);
                    	fw.flush();
                      fw.close();
                     }
                     catch(IOException g)
                     {
                      	g.printStackTrace();
                     }
              
	  
	  }
}


class New implements ActionListener { 
		
	public void actionPerformed(ActionEvent e)
	{	
		 TransPoint.AllCompclear();	  		 		
	}
 }