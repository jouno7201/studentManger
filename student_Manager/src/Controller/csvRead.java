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
	        //기존 db 내용 삭제
	    	DB.Delete();
	    	DB.DeleteAtt();
	    	//받아온 경로의 파일 객체 생성
	        File csv = new File(path);
	        //파일 내용을 읽어줄 버터리더 생성
	        BufferedReader br = new BufferedReader(new FileReader(csv));
	        //한줄을 저장할 임시 문자열
	        String line = "";
	        
	        //읽어온 값을 db로 바로 insert
	        while((line = br.readLine())!= null) {
	        	String[] arr =line.split(",");
	        	DB.Insert(arr);
	        	DB.InsertAtt(arr);
	        	for(int i=0;i<10;i++) {
	        		System.out.print(arr[i]);
	        	}
	        	System.out.println();
	        }
	       
	       //db의 내용을 객체화
	       DB.formDB(SM);
	       //버퍼리더  종료
	       br.close();
	       
	       //table 1과 2의 변경사항을 refresh
	       //Score -> table
	       Score.model_s=null;
	       Score.model_s =new tableM_score();
	       Score.table1.setModel(Score.model_s);
	       for(int i=0;i<SM.stu_L.size();i++) {
	        	 Score.model_s.addRow(SM.stu_L.get(i).rowing_s());
	         }
	       Score.table1.setModel(Score.model_s);
	       Score.table1.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
	       Score.table1.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
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
		    	// 3번째 열부터 19번째 열까지 총 16개의 열에
		         Attendance.week[i] = Attendance.table1.getColumnModel().getColumn(i + 2);
		         // 콤보박스를 만들어서
		         Attendance.week_comboBox[i] = new MycomboBox();
		        
		         // 3번째 열부터 각 열에 콤보박스 추가
		         Attendance.week[i].setCellEditor(new DefaultCellEditor(Attendance.week_comboBox[i]));
		      }
		      
		     Attendance.table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			 Attendance.table1.setModel( Attendance.model);
			 Attendance.table1.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
			 Attendance.table1.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
			 
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
