package yubinConpo;

import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

 
public class test {
        public static void main (String [] args) {
        	SaveOpen tf = new SaveOpen();
        }
}
                       
class SaveOpen extends JFrame implements ActionListener{
       
        private JFileChooser jfc = new JFileChooser();
        private JButton jbt_open = new JButton("열기");
        private JButton jbt_save = new JButton("저장");

       
        public SaveOpen(){
                super("test");
                this.init();
                this.start();
                this.setSize(400,200);
                this.setVisible(true);
                
        }
        public void init(){
                getContentPane().setLayout(new FlowLayout());
                add(jbt_open);
                add(jbt_save);
        }
        public void start(){
                jbt_open.addActionListener(this);
                jbt_save.addActionListener(this);
 
                jfc.setFileFilter(new FileNameExtensionFilter("json", "json"));
                // 파일 필터
                jfc.setMultiSelectionEnabled(false);//다중 선택 불가
        }
 
        public void actionPerformed(ActionEvent e) {
                if(e.getSource() == jbt_open){
                        if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){ // showopendialog 열기 창을 열고 확인 버튼을 눌렀는지 확인
                        	
                        	
                        }
                }else if(e.getSource() == jbt_save)
                {
                        if(jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) // showSaveDialog 저장 창을 열고 확인 버튼을 눌렀는지 확인
                        { 
                        	ArrayList<JsonBox> jsonbox = new ArrayList<JsonBox>();
                        	TransPoint.JsonToBox(jsonbox,componetVer.boxM);
                        	
                        	Gson gson = new Gson();
                        	String json = gson.toJson(jsonbox);
                        	
                        	
                        	try
                        	{
                            	FileWriter fw = new FileWriter(jfc.getSelectedFile().toString() + "." + jfc.getFileFilter().getDescription()); //jfc.getSelectedFile().toString()
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
        
        
    	
    	
    	//json에 저장될 배열 만드는 메소드, j에 저장
    	
    	
        
}

class jsonSave
{
	Gson gson = new Gson();
	String json = gson.toJson(componetVer.boxM);
	
	
}




class JsonBox
{
	Rectangle B;
	boolean Cl;
	int ComT;
	String	ComText;
	JsonBox(Rectangle B, boolean Cl,int ComT, String ComText)
	{
		this.B=B;
		this.Cl=Cl;
		this.ComT=ComT;
		this.ComText=ComText;
	}
	
	
	
}


