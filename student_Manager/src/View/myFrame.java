package View;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;

import DB.DB_conneting;
import Model.StudentManager;

public class myFrame extends JFrame{

	public myFrame(StudentManager SM,DB_conneting DB){
		// ���α׷��̸� 
		setTitle("���� ó�� ���α׷�");
		
		//�޴� ����
		setJMenuBar(new myMenu(SM,DB));
		
		//tapped pane ����
		add(new myTappedPane(SM,DB));
		
		//test
		
		// x�� ������ �� ���α׷��� ���� ����.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//����� �Ͼ������
		getContentPane().setBackground(Color.WHITE);
		//������ ����
		setSize(1000,800);
		//visible mode
		setVisible(true);
	}
}
