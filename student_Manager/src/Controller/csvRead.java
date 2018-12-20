package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;

import DB.DB_conneting;
import Model.StudentManager;
import Model.tableM_attTotal;
import Model.tableM_attendance;
import Model.tableM_score;
import Model.tableM_totalgrade;
import View.Attendance;
import View.Attendance.MycomboBox;
import View.Score;

public class csvRead {

    public csvRead(String path,DB_conneting DB,StudentManager SM) {
	    try {
	        //���� db ���� ����
	    	DB.Delete();
	    	DB.DeleteAtt();
	    	//�޾ƿ� ����� ���� ��ü ����
	        File csv = new File(path);
	        //���� ������ �о��� ���͸��� ����
	        BufferedReader br = new BufferedReader(new FileReader(csv));
	        //������ ������ �ӽ� ���ڿ�
	        String line = "";
	        
	        //�о�� ���� db�� �ٷ� insert
	        while((line = br.readLine())!= null) {
	        	String[] arr =line.split(",");
	        	DB.Insert(arr);
	        	DB.InsertAtt(arr);
	        	for(int i=0;i<10;i++) {
	        		System.out.print(arr[i]);
	        	}
	        	System.out.println();
	        }
	       
	       //db�� ������ ��üȭ
	       DB.formDB(SM);
	       //���۸���  ����
	       br.close();
	       
	       //table 1�� 2�� ��������� refresh
	       //Score -> table
	       Score.model_s=null;
	       Score.model_s =new tableM_score();
	       Score.table1.setModel(Score.model_s);
	       for(int i=0;i<SM.stu_L.size();i++) {
	        	 Score.model_s.addRow(SM.stu_L.get(i).rowing_s());
	         }
	       Score.table1.setModel(Score.model_s);
	       Score.table1.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
	       Score.table1.getTableHeader().setResizingAllowed(false); // �÷� ũ�� ���� �Ұ�
	       //Score-> tg
	       Score.model_tg=null;
	       Score.model_tg =new tableM_totalgrade();
	       Score.table2.setModel(Score.model_tg);
	       for(int i=0;i<SM.stu_L.size();i++) {
	        	 Score.model_tg.addRow(SM.stu_L.get(i).rowing_tg());
	         }
	       Score.table2.setModel(Score.model_tg);
	       //att->table1
	       Attendance.model = null;
			Attendance.model = new tableM_attendance();
			Attendance.table1.setModel( Attendance.model);
			for(int i=0;i<SM.stu_L.size();i++) {
				 Attendance.model.addRow(SM.stu_L.get(i).rowing_att());
	         }
			for(int i = 0 ; i < 16 ;i++) {
		    	// 3��° ������ 19��° ������ �� 16���� ����
		         Attendance.week[i] = Attendance.table1.getColumnModel().getColumn(i + 2);
		         // �޺��ڽ��� ����
		         Attendance.week_comboBox[i] = new MycomboBox();
		        
		         // 3��° ������ �� ���� �޺��ڽ� �߰�
		         Attendance.week[i].setCellEditor(new DefaultCellEditor(Attendance.week_comboBox[i]));
		      }
		      
		     Attendance.table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			 Attendance.table1.setModel( Attendance.model);
			 Attendance.table1.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
			 Attendance.table1.getTableHeader().setResizingAllowed(false); // �÷� ũ�� ���� �Ұ�
			 
	       //att->table2
	       Attendance.model_t = null;
			Attendance.model_t = new tableM_attTotal();
			Attendance.table2.setModel( Attendance.model_t);
			for(int i=0;i<SM.stu_L.size();i++) {
				 Attendance.model_t.addRow(SM.stu_L.get(i).rowing_attT());
	         }
			 Attendance.table2.setModel( Attendance.model_t);
			 Attendance.table2.setTableHeader(null);
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    }
    }        

}
