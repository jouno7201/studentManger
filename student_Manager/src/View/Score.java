package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Controller.Control;
import Controller.Control.sd_ActionListenner;
import DB.DB_conneting;
import Model.StudentManager;
import Model.tableM_score;
import Model.tableM_totalgrade;


public class Score extends JPanel{
   
   //����г��� ������ ���̾ƿ� �䆠
   JPanel reft = new JPanel();
   JPanel right = new JPanel();
   //���̾ƿ� ��ο� ������ ��� �г�
   //reft
   JScrollPane scroll = new JScrollPane();
   //right
   JPanel panel =new JPanel();
      
   // ���� ���� table
   //"�̸�", "�й�", "�߰�", "�⸻", "����", "����" ���̺�
    static public JTable table1;
   //"����", "����"
    static public JTable table2;   
   
   // ������ ������� ��ư
   JButton btn_total = new JButton("����");
   JButton btn_grade= new JButton("����");
   
   //table ����
   static public  tableM_score model_s = new tableM_score() /*{
	   public boolean isCellEditable(int i, int c) {
           return false;
       }
   }*/;

   static public tableM_totalgrade model_tg = new tableM_totalgrade();
   
    Score(StudentManager SM,DB_conneting DB){
    	
         ///////////////��ü�� ���̺� �߰� ////////////////////////////
         for(int i=0;i<SM.stu_L.size();i++) {
        	 model_s.addRow(SM.stu_L.get(i).rowing_s());
         }
	     table1 = new JTable(model_s);
	     
	     table1.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
	     table1.getTableHeader().setResizingAllowed(false); // �÷� ũ�� ���� �Ұ�
	    //////////////////////////////////////////////////////////
	     
        //���� ���� ���̺�(��ư)
	     
	     for(int i=0;i<SM.stu_L.size();i++) {
        	 model_tg.addRow(SM.stu_L.get(i).rowing_tg());
         }
         table2 = new JTable(model_tg);
         
         table2.setTableHeader(null);
         
         ////////////////////////////////////////////////////////
         
         //layout
         setLayout(null);
         
         //reft���� scroll �ϳ� �� �����Ǳ� ������ �ϳ�¥�� �׸��� ���̾ƿ�
         reft.setLayout(new GridLayout(1, 1));
         //right���� ������ ��ư �Ʒ����� ����� ���� ǥ�� ���� ���� north,ceter �̿�
         right.setLayout(new BorderLayout());
         
         //reft�� right�� ����� ����
         reft.setSize(new Dimension(680,700));
         right.setSize(new Dimension(320,700));
         
         //right�� ��ġ�� ����
         right.setBounds(680,0,300,700);
         
         //table1�� ����� ����� ����
         panel.setPreferredSize(new Dimension(100, 22));
         //��ư�� ���ٷ� 2���� ���� ������ �׸��巹�̾ƿ� 1,2
         panel.setLayout(new GridLayout(1, 2));
         
         //Ƚ���� ���ִ� ��ư�� ����
         btn_total.addActionListener(new Control().new totalListenner(SM,DB));
         btn_grade.addActionListener(new Control().new gradeListenner(SM,DB));
         
         
         panel.add(btn_total);
         panel.add(btn_grade);

         // reft,ringt�� ���� ��� �г� ����
         right.add(panel,BorderLayout.NORTH);
         right.add(new JScrollPane(table2),BorderLayout.CENTER);
         
         scroll.setViewportView(table1);
         reft.add(scroll);
         
         //��ü �гο� reft right����
         add(reft);
         add(right);
    }
   
}

