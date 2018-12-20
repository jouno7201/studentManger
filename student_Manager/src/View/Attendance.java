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
	//기능패널을 포함할 레이아웃 페넗
	JPanel reft = new JPanel();
	JPanel right = new JPanel();
	
	//레이아웃 페널에 부착될 기능 패널
	//reft
	JScrollPane scroll = new JScrollPane();
	//right
	JPanel panel =new JPanel();
	
	// 값이 들어가는 table
	//16동안의 출석
	public static JTable table1;
	//출석수
	public static JTable table2;
	
	//table1에 들어갈 콤보박스
	public static MycomboBox[] week_comboBox = new MycomboBox[16];
	public static TableColumn[] week = new TableColumn[16];
	
	// 횟수를 출력해줄 버튼
	JButton btn_attendance = new JButton("출석");
    JButton btn_absent= new JButton("결석");
    JButton btn_lateness= new JButton("지각");

    public static tableM_attendance model = new tableM_attendance();
    static public tableM_attTotal model_t = new tableM_attTotal();
    
	 Attendance(StudentManager SM,DB_conneting DB){
	      
	      //table1 = new JTable(data1,title1);
	      for(int i=0;i<SM.stu_L.size();i++) {
	        	 model.addRow(SM.stu_L.get(i).rowing_att());
	         }
	      table1 = new JTable(model);
	      
	      for(int i = 0 ; i < 16 ;i++) {
	    	// 3번째 열부터 19번째 열까지 총 16개의 열에
	         week[i] = table1.getColumnModel().getColumn(i + 2);
	         // 콤보박스를 만들어서
	         week_comboBox[i] = new MycomboBox();
	        
	         // 3번째 열부터 각 열에 콤보박스 추가
	         week[i].setCellEditor(new DefaultCellEditor(week_comboBox[i]));
	      }
	      
	      table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	      table1.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		  table1.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
	      
	      for(int i=0;i<SM.stu_L.size();i++) {
	        	 model_t.addRow(SM.stu_L.get(i).rowing_attT());
	         }
	         table2 = new JTable(model_t);
	      table2.setTableHeader(null);
	      /////////////////////////////////////////////////////////////////////////////
	      
	      //layout
	      setLayout(null);
	      
	      //reft에는 scroll 하나 만 부착되기 떄문에 하나짜리 그리드 레이아웃
	      reft.setLayout(new GridLayout(1, 1));
	      //right에는 위에는 버튼 아래에는 헤더가 없는 표를 부착 예정 north,ceter 이용
	      right.setLayout(new BorderLayout());
	      
	      //reft와 right의 사이즈를 지정
	      reft.setSize(new Dimension(700,700));
	      right.setSize(new Dimension(300,700));
	      
	      //right의 위치를 지정
	      right.setBounds(700,0,270,700);
	      
	      //table1의 헤더와 사이즈를 맞춤
	      panel.setPreferredSize(new Dimension(100, 22));
	      //버튼이 한줄로 3개가 들어가기 때문에 그리드레이아웃 1,3
	      panel.setLayout(new GridLayout(1, 3));
	      
	      //횟수를 내주는 버튼들 부착
	      btn_attendance.addActionListener(new Control().new attListenner(SM, DB));
	      btn_absent.addActionListener(new Control().new attListenner(SM, DB));
	      btn_lateness.addActionListener(new Control().new attListenner(SM, DB));
	      
	      panel.add(btn_attendance);
	      panel.add(btn_absent);
	      panel.add(btn_lateness);
	      
	      // reft,ringt에 하위 기능 패널 부착
	      right.add(panel,BorderLayout.NORTH);
	      right.add(new JScrollPane(table2),BorderLayout.CENTER);
	      
	      scroll.setViewportView(table1);
	      reft.add(scroll);
	      
	      //전체 패널에 reft right부착
	      add(reft);
	      add(right);
	 }
	 
	 static public class MycomboBox extends JComboBox {
		   public MycomboBox() {
		      addItem("-");
		      addItem("△");
		      addItem("○");
		   }
	 }
	 
}
