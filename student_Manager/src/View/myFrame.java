package View;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;

import DB.DB_conneting;
import Model.StudentManager;

public class myFrame extends JFrame{

	public myFrame(StudentManager SM,DB_conneting DB){
		// 프로그램이름 
		setTitle("성적 처리 프로그램");
		
		//메뉴 생성
		setJMenuBar(new myMenu(SM,DB));
		
		//tapped pane 생성
		add(new myTappedPane(SM,DB));
		
		//test
		
		// x를 눌렀을 떄 프로그램도 같이 종료.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//배경을 하얀색으로
		getContentPane().setBackground(Color.WHITE);
		//사이즈 설정
		setSize(1000,800);
		//visible mode
		setVisible(true);
	}
}
