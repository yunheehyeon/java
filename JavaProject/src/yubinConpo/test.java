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
        private JButton jbt_open = new JButton("����");
        private JButton jbt_save = new JButton("����");

       
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
                // ���� ����
                jfc.setMultiSelectionEnabled(false);//���� ���� �Ұ�
        }
 
        public void actionPerformed(ActionEvent e) {
                if(e.getSource() == jbt_open){
                        if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){ // showopendialog ���� â�� ���� Ȯ�� ��ư�� �������� Ȯ��
                        	
                        	
                        }
                }else if(e.getSource() == jbt_save)
                {
                        if(jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) // showSaveDialog ���� â�� ���� Ȯ�� ��ư�� �������� Ȯ��
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
        
        
    	
    	
    	//json�� ����� �迭 ����� �޼ҵ�, j�� ����
    	
    	
        
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


