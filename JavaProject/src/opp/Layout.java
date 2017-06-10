package opp;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;



public class Layout extends JFrame{
				
	private JPanel AbPanel;
	private JPanel EdPanel;
	private JPanel underPanel;

	//AttributePanel 변수
	private JLabel xl = new JLabel("x : ");
	private JLabel yl = new JLabel("y : ");	
	private JLabel hl = new JLabel("h : ");	
	private JLabel wl = new JLabel("w : ");
	private JLabel cl = new JLabel("텍스트 속성: ");
	private JLabel ctl = new JLabel("타입 : ");
	private JLabel cnl = new JLabel("변수명 : ");
	private JButton btn = new JButton("텍스트 수정"); 
	
	//AttributePanel 변수
	private JTextField  xt = new JTextField(4);
	private JTextField yt = new JTextField(4);
	private JTextField ht = new JTextField(4);
	private JTextField wt = new JTextField(4);
	private JTextField ct = new JTextField(2);
	private JComboBox  ctt = new JComboBox ();
	private JTextField cnt = new JTextField(4);
	
	//파일 처리 리스너 생성부분
	Save save = new Save();
	Open open = new Open();
	New New = new New(); 
	OverwriteSave OverwriteSave = new OverwriteSave();
	
	public boolean ModeClick = false; // false : 그리기 모드, true : 선택모드
	
	public static componetVer comTemp;
			
	Layout(){
	
		setTitle("opp project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		creatmenuber(); 	//메뉴바 생성
		creattoolbar(); 	//툴바 생성
		
		underPanel = new JPanel(new BorderLayout(3 ,3));	
		add(underPanel);
				
		comTemp = new componetVer();

		AbPanel = AttributePanel();		//속성 창 생성
		EdPanel = comTemp;				//에디트 창 생성
		
		
		AbPanel.setBorder(new TitledBorder(new SoftBevelBorder(SoftBevelBorder.RAISED),"Attribute"));
		EdPanel.setBorder(new TitledBorder(new SoftBevelBorder(SoftBevelBorder.RAISED),"Edit"));
		
		underPanel.add(EdPanel);			//붙이기
		underPanel.add("West" ,AbPanel);	//붙이기
		
		Layout.LayoutMouseListen Lml = new LayoutMouseListen();		//속성창 표시를 하기위해 에디트 창 클릭 감지
		EdPanel.addMouseListener(Lml);
		
		ComboActionListener Combo = new ComboActionListener();		//콤보박스로 다음에 생성할 컴포넌트 설정
		ctt.addActionListener(Combo); 
				
		TextActionListener Text = new TextActionListener();			//텍스트 수정 리스너
		btn.addActionListener(Text);		
				
		setSize(500, 300);
		setVisible(true);
	}

	//속성창 표시를 하기위해 에디트 창 클릭 감지
	class LayoutMouseListen extends MouseAdapter implements MouseMotionListener
	{
		public void mouseClicked(MouseEvent e)
		{
			if(ModeClick)//선택 모드 일때만 반응 
			{
				if(e.getButton()==1){
									
					int i = componetVer.boxM.NowBoxNumM;
					
					if(componetVer.boxM.getRec(i)!=null){		
							//각 부분에 현재 선택된 요소 넣기
							xt.setText(Integer.toString(componetVer.boxM.recgetX()));
							yt.setText(Integer.toString(componetVer.boxM.recgetY()));
							wt.setText(Integer.toString(componetVer.boxM.getRecWidth()));
							ht.setText(Integer.toString(componetVer.boxM.getRecHeight()));
							ct.setText(componetVer.boxM.getString(i));
						 	
							switch(componetVer.boxM.getType(i).intValue()){
								case 1 : cnt.setText("BUTTEN");
											break;
								case 2 : cnt.setText("JPANEL");
											break;
								case 3 : cnt.setText("JSCROLL");
											break;
								case 4 : cnt.setText("JTEXTFIELD");
											break;
							}
					}
					else if(componetVer.boxM.getRec(i)==null){
					
							xt.setText("null");
							yt.setText("null");
							wt.setText("null");
							ht.setText("null");
							ct.setText("null");
							cnt.setText("null");				
					}		
		
				}
			}
		}
		
		public void mousePressed(MouseEvent e)
		{
			if(e.getButton()==2) // 마우스 가운데 바튼 누르면 만들기 모드 <-> 선택모드 선택 가능
			{
				if(ModeClick)
					ModeClick = false;
				else
					ModeClick = true;
			}
			else{}
		}
			
	}
	
	//콤보박스로 다음에 생성할 컴포넌트 설정
	class ComboActionListener implements ActionListener { 
		
		public void actionPerformed(ActionEvent e)
		{	
		 	  String str = (String)ctt.getSelectedItem();
		 	   
		 	  if(str.equals("BUTTON")){System.out.println(1); componetVer.Choose = 1;} 
		 	  else if(str.equals("JPANEL")){System.out.println(2); componetVer.Choose = 2;}
		 	  else if(str.equals("JSCROLL")){System.out.println(3); componetVer.Choose = 3;}
		 	  else if(str.equals("JTEXTFIELD")){System.out.println(4); componetVer.Choose = 4;}
		}
 	}
 	
 	class TextActionListener implements ActionListener { 
		
		public void actionPerformed(ActionEvent e)
		{	
		 	
		 	String str = (String)ct.getText();
		 	System.out.println(str);
		 	componetVer.boxM.conText.set( componetVer.boxM.NowBoxNumM ,str);
		 	componetVer.TextV.set(componetVer.boxM.NowBoxNumM ,str); 
		 	
		}
 	}
 	
 	
 	//메뉴바 만드는 메소드		
	private void creatmenuber(){
	
		JMenuBar menubar=new JMenuBar();
        
		setJMenuBar(menubar);
        
        JMenu menu1=new JMenu("파일(F)");
       
        menu1.setMnemonic('F');
        menubar.add(menu1);
       	
       	JMenuItem m_new=new JMenuItem("새로 만들기");
        JMenuItem m_open=new JMenuItem("열기");
        JMenuItem m_save=new JMenuItem("저장");
        JMenuItem m_oversave=new JMenuItem("덮어쓰기");

        
        
        m_open.addActionListener(open); 	// 툴바와 같은 코드 넣기
        m_save.addActionListener(save);		// 툴바와 같은 코드 넣기
     	m_new.addActionListener(New);		// 툴바와 같은 코드 넣기
     	m_oversave.addActionListener(OverwriteSave);
     	
        menu1.add(m_new);
        menu1.add(m_open);
        menu1.add(m_save); 
        menu1.add(m_oversave);
             
	}
	
	//툴바 만드는 메소드
	private void creattoolbar(){
		
		JToolBar toolbar = new JToolBar();
		
		JButton jbt_open = new JButton("OPEN");
		JButton jbt_save = new JButton("SAVE");
		JButton jbt_new = new JButton("NEW");
		JButton jbt_overnew = new JButton("overNEW");

		toolbar.add(jbt_new);
		toolbar.add(jbt_open);
		toolbar.add(jbt_save);
		toolbar.add(jbt_overnew);
			
		jbt_open.addActionListener(open);		//메뉴바와 같은 기능 넣기
        jbt_save.addActionListener(save);		//메뉴바와 같은 기능 넣기
        jbt_new.addActionListener(New);			//메뉴바와 같은 기능 넣기
        jbt_overnew.addActionListener(OverwriteSave);
		this.add(toolbar, BorderLayout.NORTH);

	}
	
	//속성창 만들기
	JPanel AttributePanel(){
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout(2,2));
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel Lbpanel = new JPanel();
		Lbpanel.setLayout(new GridLayout(6, 1));
		JPanel Tpanel = new JPanel();
		Tpanel.setLayout(new GridLayout(6, 1));
		JPanel Cpanel = new JPanel();
		Cpanel.setLayout(new GridLayout(1, 2));
		
		Lbpanel.add(xl);
		Tpanel.add(xt);
		Lbpanel.add(yl);
		Tpanel.add(yt);
		Lbpanel.add(hl);
		Tpanel.add(ht);
		Lbpanel.add(wl);
		Tpanel.add(wt);
		Lbpanel.add(cl);
		Tpanel.add(ct);
		Lbpanel.add(cnl);
		Tpanel.add(cnt);
		
		
		Cpanel.add(ctl);
		Cpanel.add(ctt);
		ctt.addItem("BUTTON");
		ctt.addItem("JPANEL");
		ctt.addItem("JSCROLL");
		ctt.addItem("JTEXTFIELD");
		
		panel.add("West", Lbpanel);
		panel.add("Center", Tpanel);
		panel.add("South",btn);
		
		panel2.add("Center", panel);
		panel2.add("South", Cpanel);
		
		return panel2;
	}

	public static void main(String[] args) {
		new Layout();	
	}
}
