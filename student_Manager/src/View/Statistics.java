package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import Controller.Control;
import Model.StudentManager;

public class Statistics extends JPanel{
	//기능패널을 포함할 레이아웃 페넗
	JPanel reft = new JPanel();
	JPanel right = new JPanel();
	
	//기능패널
	JPanel aver_p = new JPanel();
	JPanel selet_p = new JPanel();
	JPanel graph_p = new JPanel();
	
	//세부 기능  요소
	JTextArea jTextArea = new JTextArea();
	
	JButton btn_aver = new JButton("통계 출력");
	
	grape grapePanel;
	
	
	public Statistics(StudentManager SM) {
		for(int i=0;i<5;i++) {
			System.out.print(SM.dividing(0)[i]);
		}
		System.out.println();
		//reft : right 레이아웃 잡음
		setLayout(new GridLayout(1,2));
		
		//reft.setBorder(new LineBorder(Color.BLACK,5,true));
		//right.setBorder(new LineBorder(Color.BLACK,5,true));
		
		add(reft);
		add(right);
		
		//aver / select 로 레이아웃 잡음
		reft.setLayout(new GridLayout(2, 1));
		
		//aver_p.setBorder(new LineBorder(Color.blue,5,true));
		//selet_p.setBorder(new LineBorder(Color.blue,5,true));
		
		reft.add(aver_p);
		reft.add(selet_p);
		
		//aver 기능 생성
		aver_p.setLayout(null);
		btn_aver.setBounds(100, 80, 220, 50);
		
		jTextArea.setFont(new Font(null, Font.BOLD, 16));
	    jTextArea.setText("이 영역에 평균이 출력됩니다.");
		jTextArea.setEditable(false);
	    jTextArea.setBounds(100, 180, 250, 50);
	    
	    //이벤트 리스너 연결
	    btn_aver.addActionListener(new Control().new averListener(SM,jTextArea));
	    
		aver_p.add(btn_aver);
		aver_p.add(jTextArea);
		
		//selct 기능 생성
		selet_p.setLayout(null);
		
		JButton b2 = new JButton("그래프 출력");
		//b2.setBounds(100, 500, 200, 50);
		b2.setBounds(100, 50, 220, 50);
		selet_p.add(b2);
		
		final JRadioButton radio1 = new JRadioButton("중간");
		radio1.setBounds(100, 150, 59, 23);
		selet_p.add(radio1);
		
		final JRadioButton radio2 = new JRadioButton("과제");
		radio2.setBounds(160, 150, 59, 23);
		selet_p.add(radio2);
		
		final JRadioButton radio3 = new JRadioButton("보고서");
		radio3.setBounds(220, 150, 79, 23);
		selet_p.add(radio3);
		    
		final JRadioButton radio4 = new JRadioButton("기말");
		radio4.setBounds(100, 250, 59, 23);
		selet_p.add(radio4);
		
		final JRadioButton radio5 = new JRadioButton("출석");
		radio5.setBounds(160, 250, 59, 23);
		selet_p.add(radio5);
		
		final JRadioButton radio6 = new JRadioButton("발표");
		radio6.setBounds(220, 250, 59, 23);
		selet_p.add(radio6);
		   
		ButtonGroup group = new ButtonGroup();
		group.add(radio1); group.add(radio2);group.add(radio3);  group.add(radio4); group.add(radio5); group.add(radio6); 
		   
		radio1.setSelected(true);
		
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Enumeration<AbstractButton> enums = group.getElements();
				for(int i=0;enums.hasMoreElements();i++) { 
					 AbstractButton ab = enums.nextElement(); 
					 
					 JRadioButton jb = (JRadioButton)ab;
					 if(jb.isSelected()) {
						 grapePanel = new grape(SM.dividing(i));
						 right.removeAll();
						 right.add(grapePanel);
						 right.revalidate();
						 right.repaint();
					 }
				}
			}
		});
		//페널 색 변경 
		aver_p.setBorder(new LineBorder(Color.BLUE,5,true));
		selet_p.setBorder(new LineBorder(Color.BLUE,5,true));
		reft.setBorder(new LineBorder(Color.BLUE,5,true));
		right.setBorder(new LineBorder(Color.BLUE,5,true));
		right.setLayout(new GridLayout(1,1));
	}
}
