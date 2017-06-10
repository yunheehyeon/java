package opp;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;



public class Layout extends JFrame{
				
	private JPanel AbPanel;
	private JPanel EdPanel;
	private JPanel underPanel;

	//AttributePanel ����
	private JLabel xl = new JLabel("x : ");
	private JLabel yl = new JLabel("y : ");	
	private JLabel hl = new JLabel("h : ");	
	private JLabel wl = new JLabel("w : ");
	private JLabel cl = new JLabel("�ؽ�Ʈ �Ӽ�: ");
	private JLabel ctl = new JLabel("Ÿ�� : ");
	private JLabel cnl = new JLabel("������ : ");
	private JButton btn = new JButton("�ؽ�Ʈ ����"); 
	
	//AttributePanel ����
	private JTextField  xt = new JTextField(4);
	private JTextField yt = new JTextField(4);
	private JTextField ht = new JTextField(4);
	private JTextField wt = new JTextField(4);
	private JTextField ct = new JTextField(2);
	private JComboBox  ctt = new JComboBox ();
	private JTextField cnt = new JTextField(4);
	
	//���� ó�� ������ �����κ�
	Save save = new Save();
	Open open = new Open();
	New New = new New(); 
	OverwriteSave OverwriteSave = new OverwriteSave();
	
	public boolean ModeClick = false; // false : �׸��� ���, true : ���ø��
	
	public static componetVer comTemp;
			
	Layout(){
	
		setTitle("opp project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		creatmenuber(); 	//�޴��� ����
		creattoolbar(); 	//���� ����
		
		underPanel = new JPanel(new BorderLayout(3 ,3));	
		add(underPanel);
				
		comTemp = new componetVer();

		AbPanel = AttributePanel();		//�Ӽ� â ����
		EdPanel = comTemp;				//����Ʈ â ����
		
		
		AbPanel.setBorder(new TitledBorder(new SoftBevelBorder(SoftBevelBorder.RAISED),"Attribute"));
		EdPanel.setBorder(new TitledBorder(new SoftBevelBorder(SoftBevelBorder.RAISED),"Edit"));
		
		underPanel.add(EdPanel);			//���̱�
		underPanel.add("West" ,AbPanel);	//���̱�
		
		Layout.LayoutMouseListen Lml = new LayoutMouseListen();		//�Ӽ�â ǥ�ø� �ϱ����� ����Ʈ â Ŭ�� ����
		EdPanel.addMouseListener(Lml);
		
		ComboActionListener Combo = new ComboActionListener();		//�޺��ڽ��� ������ ������ ������Ʈ ����
		ctt.addActionListener(Combo); 
				
		TextActionListener Text = new TextActionListener();			//�ؽ�Ʈ ���� ������
		btn.addActionListener(Text);		
				
		setSize(500, 300);
		setVisible(true);
	}

	//�Ӽ�â ǥ�ø� �ϱ����� ����Ʈ â Ŭ�� ����
	class LayoutMouseListen extends MouseAdapter implements MouseMotionListener
	{
		public void mouseClicked(MouseEvent e)
		{
			if(ModeClick)//���� ��� �϶��� ���� 
			{
				if(e.getButton()==1){
									
					int i = componetVer.boxM.NowBoxNumM;
					
					if(componetVer.boxM.getRec(i)!=null){		
							//�� �κп� ���� ���õ� ��� �ֱ�
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
			if(e.getButton()==2) // ���콺 ��� ��ư ������ ����� ��� <-> ���ø�� ���� ����
			{
				if(ModeClick)
					ModeClick = false;
				else
					ModeClick = true;
			}
			else{}
		}
			
	}
	
	//�޺��ڽ��� ������ ������ ������Ʈ ����
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
 	
 	
 	//�޴��� ����� �޼ҵ�		
	private void creatmenuber(){
	
		JMenuBar menubar=new JMenuBar();
        
		setJMenuBar(menubar);
        
        JMenu menu1=new JMenu("����(F)");
       
        menu1.setMnemonic('F');
        menubar.add(menu1);
       	
       	JMenuItem m_new=new JMenuItem("���� �����");
        JMenuItem m_open=new JMenuItem("����");
        JMenuItem m_save=new JMenuItem("����");
        JMenuItem m_oversave=new JMenuItem("�����");

        
        
        m_open.addActionListener(open); 	// ���ٿ� ���� �ڵ� �ֱ�
        m_save.addActionListener(save);		// ���ٿ� ���� �ڵ� �ֱ�
     	m_new.addActionListener(New);		// ���ٿ� ���� �ڵ� �ֱ�
     	m_oversave.addActionListener(OverwriteSave);
     	
        menu1.add(m_new);
        menu1.add(m_open);
        menu1.add(m_save); 
        menu1.add(m_oversave);
             
	}
	
	//���� ����� �޼ҵ�
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
			
		jbt_open.addActionListener(open);		//�޴��ٿ� ���� ��� �ֱ�
        jbt_save.addActionListener(save);		//�޴��ٿ� ���� ��� �ֱ�
        jbt_new.addActionListener(New);			//�޴��ٿ� ���� ��� �ֱ�
        jbt_overnew.addActionListener(OverwriteSave);
		this.add(toolbar, BorderLayout.NORTH);

	}
	
	//�Ӽ�â �����
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
