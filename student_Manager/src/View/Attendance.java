package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import Controller.Control;
import DB.DB_conneting;
import Model.StudentManager;
import Model.tableM_attTotal;
import Model.tableM_attendance;

public class Attendance extends JPanel{
	//����г��� ������ ���̾ƿ� �䆠
	JPanel reft = new JPanel();
	JPanel right = new JPanel();
	
	//���̾ƿ� ��ο� ������ ��� �г�
	//reft
	JScrollPane scroll = new JScrollPane();
	//right
	JPanel panel =new JPanel();
	
	// ���� ���� table
	//16������ �⼮
	public static JTable table1;
	//�⼮��
	public static JTable table2;
	
	//table1�� �� �޺��ڽ�
	public static MycomboBox[] week_comboBox = new MycomboBox[16];
	public static TableColumn[] week = new TableColumn[16];
	
	// Ƚ���� ������� ��ư
	JButton btn_attendance = new JButton("�⼮");
    JButton btn_absent= new JButton("�Ἦ");
    JButton btn_lateness= new JButton("����");

    public static tableM_attendance model = new tableM_attendance();
    static public tableM_attTotal model_t = new tableM_attTotal();
    
	 Attendance(StudentManager SM,DB_conneting DB){
	      
	      //table1 = new JTable(data1,title1);
	      for(int i=0;i<SM.stu_L.size();i++) {
	        	 model.addRow(SM.stu_L.get(i).rowing_att());
	         }
	      table1 = new JTable(model);
	      
	      for(int i = 0 ; i < 16 ;i++) {
	    	// 3��° ������ 19��° ������ �� 16���� ����
	         week[i] = table1.getColumnModel().getColumn(i + 2);
	         // �޺��ڽ��� ����
	         week_comboBox[i] = new MycomboBox();
	        
	         // 3��° ������ �� ���� �޺��ڽ� �߰�
	         week[i].setCellEditor(new DefaultCellEditor(week_comboBox[i]));
	      }
	      
	      table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	      table1.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
		  table1.getTableHeader().setResizingAllowed(false); // �÷� ũ�� ���� �Ұ�
	      
	      for(int i=0;i<SM.stu_L.size();i++) {
	        	 model_t.addRow(SM.stu_L.get(i).rowing_attT());
	         }
	         table2 = new JTable(model_t);
	      table2.setTableHeader(null);
	      /////////////////////////////////////////////////////////////////////////////
	      
	      //layout
	      setLayout(null);
	      
	      //reft���� scroll �ϳ� �� �����Ǳ� ������ �ϳ�¥�� �׸��� ���̾ƿ�
	      reft.setLayout(new GridLayout(1, 1));
	      //right���� ������ ��ư �Ʒ����� ����� ���� ǥ�� ���� ���� north,ceter �̿�
	      right.setLayout(new BorderLayout());
	      
	      //reft�� right�� ����� ����
	      reft.setSize(new Dimension(700,700));
	      right.setSize(new Dimension(300,700));
	      
	      //right�� ��ġ�� ����
	      right.setBounds(700,0,270,700);
	      
	      //table1�� ����� ����� ����
	      panel.setPreferredSize(new Dimension(100, 22));
	      //��ư�� ���ٷ� 3���� ���� ������ �׸��巹�̾ƿ� 1,3
	      panel.setLayout(new GridLayout(1, 3));
	      
	      //Ƚ���� ���ִ� ��ư�� ����
	      btn_attendance.addActionListener(new Control().new attListenner(SM, DB));
	      btn_absent.addActionListener(new Control().new attListenner(SM, DB));
	      btn_lateness.addActionListener(new Control().new attListenner(SM, DB));
	      
	      panel.add(btn_attendance);
	      panel.add(btn_absent);
	      panel.add(btn_lateness);
	      
	      // reft,ringt�� ���� ��� �г� ����
	      right.add(panel,BorderLayout.NORTH);
	      right.add(new JScrollPane(table2),BorderLayout.CENTER);
	      
	      scroll.setViewportView(table1);
	      reft.add(scroll);
	      
	      //��ü �гο� reft right����
	      add(reft);
	      add(right);
	 }
	 
	 static public class MycomboBox extends JComboBox {
		   public MycomboBox() {
		      addItem("-");
		      addItem("��");
		      addItem("��");
		   }
	 }
	 
}
