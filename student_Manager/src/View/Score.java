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
   
   //기능패널을 포함할 레이아웃 페넗
   JPanel reft = new JPanel();
   JPanel right = new JPanel();
   //레이아웃 페널에 부착될 기능 패널
   //reft
   JScrollPane scroll = new JScrollPane();
   //right
   JPanel panel =new JPanel();
      
   // 값이 들어가는 table
   //"이름", "학번", "중간", "기말", "퀴즈", "과제" 테이블
    static public JTable table1;
   //"총점", "학점"
    static public JTable table2;   
   
   // 성적을 출력해줄 버튼
   JButton btn_total = new JButton("총점");
   JButton btn_grade= new JButton("학점");
   
   //table 선언
   static public  tableM_score model_s = new tableM_score() /*{
	   public boolean isCellEditable(int i, int c) {
           return false;
       }
   }*/;

   static public tableM_totalgrade model_tg = new tableM_totalgrade();
   
    Score(StudentManager SM,DB_conneting DB){
    	
         ///////////////객체를 테이블에 추가 ////////////////////////////
         for(int i=0;i<SM.stu_L.size();i++) {
        	 model_s.addRow(SM.stu_L.get(i).rowing_s());
         }
	     table1 = new JTable(model_s);
	     
	     table1.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
	     table1.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
	    //////////////////////////////////////////////////////////
	     
        //총점 학점 테이블(버튼)
	     
	     for(int i=0;i<SM.stu_L.size();i++) {
        	 model_tg.addRow(SM.stu_L.get(i).rowing_tg());
         }
         table2 = new JTable(model_tg);
         
         table2.setTableHeader(null);
         
         ////////////////////////////////////////////////////////
         
         //layout
         setLayout(null);
         
         //reft에는 scroll 하나 만 부착되기 떄문에 하나짜리 그리드 레이아웃
         reft.setLayout(new GridLayout(1, 1));
         //right에는 위에는 버튼 아래에는 헤더가 없는 표를 부착 예정 north,ceter 이용
         right.setLayout(new BorderLayout());
         
         //reft와 right의 사이즈를 지정
         reft.setSize(new Dimension(680,700));
         right.setSize(new Dimension(320,700));
         
         //right의 위치를 지정
         right.setBounds(680,0,300,700);
         
         //table1의 헤더와 사이즈를 맞춤
         panel.setPreferredSize(new Dimension(100, 22));
         //버튼이 한줄로 2개가 들어가기 때문에 그리드레이아웃 1,2
         panel.setLayout(new GridLayout(1, 2));
         
         //횟수를 내주는 버튼들 부착
         btn_total.addActionListener(new Control().new totalListenner(SM,DB));
         btn_grade.addActionListener(new Control().new gradeListenner(SM,DB));
         
         
         panel.add(btn_total);
         panel.add(btn_grade);

         // reft,ringt에 하위 기능 패널 부착
         right.add(panel,BorderLayout.NORTH);
         right.add(new JScrollPane(table2),BorderLayout.CENTER);
         
         scroll.setViewportView(table1);
         reft.add(scroll);
         
         //전체 패널에 reft right부착
         add(reft);
         add(right);
    }
   
}

