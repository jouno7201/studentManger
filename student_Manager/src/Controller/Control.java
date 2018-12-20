package Controller;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DB.DB_conneting;
import Model.Student;
import Model.StudentManager;
import Model.tableM_attTotal;
import Model.tableM_attendance;
import Model.tableM_score;
import Model.tableM_totalgrade;
import View.Attendance;
import View.Score;
import View.Attendance.MycomboBox;

//���� ������ �� ����͸� ������ Ŭ����
public class Control{
	//�޴���
	/////////////////////////////////////////////////////////
	//FA�˸� ����� ������
	public class FA_ActionListenner implements ActionListener{
		StudentManager SM;
		public FA_ActionListenner(StudentManager SM) {
			this.SM = SM;
		}
		public void actionPerformed(ActionEvent e) {
			String Fstu="F �����:";
			for(int i=0;i<SM.stu_L.size();i++) {
				if(SM.stu_L.get(i).grade == "F") {
					Fstu += SM.stu_L.get(i).name+" ";
				}
			}
			
			JOptionPane.showMessageDialog(null, Fstu);
			System.out.println("click FN");
		}
	}
	
	//file read ����� ������
	public class fr_ActionListenner implements ActionListener{
		StudentManager SM;
		DB_conneting DB;
		public fr_ActionListenner(StudentManager SM,DB_conneting DB) {
			this.SM = SM;
			this.DB = DB;
		}
		public void actionPerformed(ActionEvent e) {
			//���� ���� ���� �⺻ ��� c:
			JFileChooser fileChooser = new JFileChooser("c:");
			
			int check = fileChooser.showSaveDialog(fileChooser);
			//���� ��� ���� ����
			String path = null;
			
			//���� ��ư ������ �� ������ ������� 
			if(check == 0) {
				path =fileChooser.getSelectedFile().getAbsolutePath();
				System.out.println(path);
				
				new csvRead(path,DB,SM);
			}
		}
	}
	
	//file write �� ������
	public class fw_ActionListenner implements ActionListener{
		StudentManager SM;
		
		public fw_ActionListenner(StudentManager SM) {
			this.SM = SM;
		}
		public void actionPerformed(ActionEvent e) {
			//���� ���� ���� �⺻ ��� c:
			JFileChooser fileChooser = new JFileChooser("c:");
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			int check = fileChooser.showSaveDialog(fileChooser);
			//���� ��� ���� ����
			String path = null;
			
			//���� ��ư ������ �� ������ ������� 
			if(check == 0) {
				path =fileChooser.getSelectedFile().getAbsolutePath();
				System.out.println(path);
				
				new csvWrite(path ,"student",SM);
				System.out.println("�������� ����");
			}
		}
	}
	
	
	//�л� ���� ��� 
	public class sd_ActionListenner implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			
		}
	}
	//���� ���� ����
	public class rate_ActionListenner implements ActionListener{
		StudentManager SM;
		
		public rate_ActionListenner(StudentManager SM) {
			this.SM = SM;
		}
		public void actionPerformed(ActionEvent e) {
			JFrame j = new JFrame();
			j.setTitle("���� ���� �Է�");
			j.setVisible(true);
			j.setSize(340,120);
			
			JPanel P = new JPanel();
			P.setLayout(new GridLayout(2, 1));
			JPanel top = new JPanel();
			top.setLayout(new GridLayout(2,4));
			
			JPanel bottom = new JPanel();
			//P.setLayout(null);
			
			JPanel[] score_rate = new JPanel[8];
			JTextField[] score_value = new JTextField[8];
			for(int i=0; i<8;i++) {
				score_rate[i] =new JPanel();
				score_value[i] = new JTextField(2);
				score_rate[i].setLayout(new GridLayout(1, 2));
			}
			score_rate[0].add(new Label("�߰�"));
			score_rate[1].add(new Label("�⸻"));
			score_rate[2].add(new Label("����"));
			score_rate[3].add(new Label("����"));
			score_rate[4].add(new Label("�⼮"));
			score_rate[5].add(new Label("��ǥ"));
			score_rate[6].add(new Label("����"));
			score_rate[7].add(new Label("��Ÿ"));
			
			for(int i=0;i<8;i++) {
				score_rate[i].add(score_value[i]);
				top.add(score_rate[i]);
			}
			JButton btn = new JButton("�Է�");
			bottom.add(btn);
			
			P.add(top);
			P.add(bottom);
			
			j.add(P);
			
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(int j=0;j<SM.stu_L.size();j++) {
						for(int i=0;i<8;i++) {
							System.out.print(SM.stu_L.get(j).scores[i].rate+","+score_value[i].getText()+"\n");
							SM.stu_L.get(j).scores[i].rate = Integer.parseInt(score_value[i].getText()); 
						}
					}
					j.dispose();
				}
			});
		}
	}
	//���� ���� 
	public class rateG_ActionListenner implements ActionListener{
		StudentManager SM;
		
		public rateG_ActionListenner(StudentManager SM) {
			this.SM = SM;
		}
		public void actionPerformed(ActionEvent e) {
			JFrame j = new JFrame();
			j.setTitle("���������Է�");
			j.setVisible(true);
			j.setSize(380,100);
			
			JPanel P = new JPanel();
			P.setLayout(new BorderLayout());
			JPanel top = new JPanel();
			top.setLayout(new GridLayout(1,6));
			
			JPanel bottom = new JPanel();
			//P.setLayout(null);
			
			JPanel[] score_rate = new JPanel[6];
			JTextField[] score_value = new JTextField[6];
			for(int i=0; i<6;i++) {
				score_rate[i] =new JPanel();
				score_value[i] = new JTextField(2);
				score_rate[i].setLayout(new BorderLayout());
			}
			score_rate[0].add(new Label("A+"),BorderLayout.WEST);
			score_rate[1].add(new Label("A0"),BorderLayout.WEST);
			score_rate[2].add(new Label("B+"),BorderLayout.WEST);
			score_rate[3].add(new Label("B0"),BorderLayout.WEST);
			score_rate[4].add(new Label("C+"),BorderLayout.WEST);
			score_rate[5].add(new Label("C0"),BorderLayout.WEST);
			
			for(int i=0;i<6;i++) {
				score_rate[i].add(score_value[i]);
				top.add(score_rate[i]);
			}
			JButton btn = new JButton("�Է�");
			bottom.add(btn);
			
			P.add(top,BorderLayout.CENTER);
			P.add(bottom,BorderLayout.SOUTH);
			
			j.add(P);
			
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//System.out.println(Double.parseDouble(score_value[0].getText()));
					SM.Aplus_rate = Double.parseDouble(score_value[0].getText());
					SM.Azero_rate = Double.parseDouble(score_value[1].getText());
					SM.Bplus_rate = Double.parseDouble(score_value[2].getText());
					SM.Bzero_rate = Double.parseDouble(score_value[3].getText());
					SM.Cplus_rate = Double.parseDouble(score_value[4].getText());
					SM.Czero_rate = Double.parseDouble(score_value[5].getText());
					
					j.dispose();
				}
			});
		}
	}
	//�̸� ����
	public class namesort_ActionListenner implements ActionListener{
		StudentManager SM;
		public namesort_ActionListenner(StudentManager SM) {
			this.SM =SM;
		}
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("�̸� �� ����");
			Collections.sort(SM.stu_L,new Comparator<Student>() {
	            public int compare(Student s1, Student s2) {
	                return s1.name.compareTo(s2.name);
	            }
			});
			
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
		
		
	}
	//���� ����
	public class totalsort_ActionListenner implements ActionListener{
		StudentManager SM;
		public totalsort_ActionListenner(StudentManager SM) {
			this.SM =SM;
		}
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("���� �� ����");
			Collections.sort(SM.stu_L);
			
			 //Score -> table
		       Score.model_s=null;
		       Score.model_s =new tableM_score();
		       Score.table1.setModel(Score.model_s);
		       for(int i=0;i<SM.stu_L.size();i++) {
		        	 Score.model_s.addRow(SM.stu_L.get(i).rowing_s());
		         }
		       Score.table1.setModel(Score.model_s);
		       //Score-> tg
		       Score.model_tg=null;
		       Score.model_tg =new tableM_totalgrade();
		       Score.table2.setModel(Score.model_tg);
		       for(int i=0;i<SM.stu_L.size();i++) {
		        	 Score.model_tg.addRow(SM.stu_L.get(i).rowing_tg());
		         }
		       Score.table2.setModel(Score.model_tg);
		       Score.table1.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
		       Score.table1.getTableHeader().setResizingAllowed(false); // �÷� ũ�� ���� �Ұ�
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
		
	}
	// �й� ����
	public class idsort_ActionListenner implements ActionListener{
		StudentManager SM;
		public idsort_ActionListenner(StudentManager SM) {
			this.SM =SM;
		}
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("�й� �� ����");
			Collections.sort(SM.stu_L,new Comparator<Student>() {
	            public int compare(Student s1, Student s2) {
	            	  if (s1.stunum < s2.stunum) {
	                      return -1;
	                  } else if (s1.stunum > s2.stunum) {
	                      return 1;
	                  }
	                  return 0;
	            }
			});
			
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
		
	}
	//������ ������
	public class same_ActionListenner implements ActionListener{
		StudentManager SM;
		public same_ActionListenner(StudentManager SM) {
			this.SM =SM;
		}
		
		public void actionPerformed(ActionEvent e) {
			for(int i=0;i<SM.stu_L.size();i++) {
				for(int j=i+1;j<SM.stu_L.size();j++) {
					if(SM.stu_L.get(i).total==SM.stu_L.get(j).total) {
						String str = "������ \n"+SM.stu_L.get(i).name +","+SM.stu_L.get(j).name+" "+SM.stu_L.get(j).total+"�� ����";
						if(SM.stu_L.get(j).grade != SM.stu_L.get(i).grade) {
							str+="\n��������  ������ �ٸ��ϴ�.";
						}
						JOptionPane.showMessageDialog(null,str );
					}
				}
			}
		}
		
	}
	
	//statistics
	/////////////////////////////////////////////////////////
	//������� �������� ����� ������ 
	public class averListener implements ActionListener{
		JTextArea jTextArea;
		StudentManager SM;
		public double x = 0.0;
		public averListener(StudentManager SM, JTextArea jTextArea) {
			this.SM = SM;
			this.jTextArea = jTextArea;
		}
		public void actionPerformed(ActionEvent e) {
//			 double x = SM.total;
			for(int i=0;i<SM.stu_L.size();i++) {
				x += SM.stu_L.get(i).total;
			}
			x = x/(SM.stu_L.size());
	        jTextArea.setFont(new Font(null,Font.BOLD, 28));
	        jTextArea.setText(" "+"�� ��� : "+x);
		}
	}
	
	//Score
	//////////////////////////////////////////////////////////
	public class totalListenner implements ActionListener{
		StudentManager SM;
		DB_conneting DB;
		public totalListenner(StudentManager SM,DB_conneting DB) {
			this.SM = SM;
			this.DB = DB;
		}
		public void actionPerformed(ActionEvent e) {
			//������ ���� �ٸ� ���� �ִ��� ã��
			for(int i=0;i<SM.stu_L.size();i++) {
				//System.out.print(SM.stu_L.get(i).name);
				//System.out.println(Score.model_s.getValueAt(i,0));
				for(int j=2;j<10;j++) {
					int temp = Integer.parseInt(Score.model_s.getValueAt(i, j).toString());
					if(temp != SM.stu_L.get(i).scores[j-2].score) {
						//��ü �� �ٲٰ�
						String[] colName = new String[8];
						colName[0] ="midterm";
						colName[1] ="final";
						colName[2] ="quiz";
						colName[3] ="assignment";
						colName[4] ="attendance";
						colName[5] ="announcement";
						colName[6] ="report";
						colName[7] ="others";
						
						SM.stu_L.get(i).scores[j-2].score = temp;
						//��� �� ������Ʈ
						DB.updateScore(colName[j-2], temp, SM.stu_L.get(i).stunum);
					}
				}
			}
			//���� ���
			for(int i=0;i<SM.stu_L.size();i++) {
				//���� ���
				SM.stu_L.get(i).calculate_score();
				//���� ���̺� ����.
				Score.model_tg.setValueAt(SM.stu_L.get(i).total, i, 0);
				//���� ��� ����.
				DB.updateTotla(SM.stu_L.get(i).total, SM.stu_L.get(i).stunum);
			}
			
			//�缳��
			Score.table2.setModel(Score.model_tg);
			
			
			
		}
	}
	
	public class gradeListenner implements ActionListener{
		StudentManager SM;
		DB_conneting DB;
		public gradeListenner(StudentManager SM,DB_conneting DB) {
			this.SM = SM;
			this.DB = DB;
		}
		public void actionPerformed(ActionEvent e) {
			//���� ���(���ĵ�)
			SM.calculate_grade();
			  Score.model_s=null;
		       Score.model_s =new tableM_score();
		       Score.table1.setModel(Score.model_s);
		       for(int i=0;i<SM.stu_L.size();i++) {
		    	   System.out.println(SM.stu_L.get(i).scores[4].score);
		        	 Score.model_s.addRow(SM.stu_L.get(i).rowing_s());
		         }
		       Score.table1.setModel(Score.model_s);
		       Score.table1.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
		       Score.table1.getTableHeader().setResizingAllowed(false); // �÷� ũ�� ���� �Ұ�
			 Score.model_tg=null;
		       Score.model_tg =new tableM_totalgrade();
		       Score.table2.setModel(Score.model_tg);
		       for(int i=0;i<SM.stu_L.size();i++) {
		        	 Score.model_tg.addRow(SM.stu_L.get(i).rowing_tg());
		        	 DB.updateGrade(SM.stu_L.get(i).grade, SM.stu_L.get(i).stunum);
		         }
		       Score.table2.setModel(Score.model_tg);
		}
	}
		
		//attendance
		//////////////////////////////////////////////////////////////
		public class attListenner implements ActionListener{
			StudentManager SM;
			DB_conneting DB;
			public attListenner(StudentManager SM,DB_conneting DB) {
				this.SM = SM;
				this.DB = DB;
			}
			public void actionPerformed(ActionEvent e) {
				//������ ���̺� ������ ���� ������ ��ü�� �������� ������ִ� �Լ� ����
			//��ü ->���
			for(int i=0;i<SM.stu_L.size();i++) {
				for(int j=2;j<18;j++) {
					if((int) Attendance.model.getValueAt(i, 1) == SM.stu_L.get(i).stunum) {
						String temp;
						if(SM.stu_L.get(i).att[j-2] ==1) {
							temp="-";
						}else if(SM.stu_L.get(i).att[j-2] ==2) {
							temp="��";
						}else if(SM.stu_L.get(i).att[j-2] ==3) {
							temp="��";
						}else {
							temp =null;
						}
						
						if( Attendance.model.getValueAt(i,j) != temp) {
							System.out.println( Attendance.model.getValueAt(i,0)+"�� "+(j-1)+"���� �����Ͱ� �ٸ�");
							if( Attendance.model.getValueAt(i,j)=="-") {
								SM.stu_L.get(i).att[j-2]=1;
							}else if( Attendance.model.getValueAt(i,j)=="��") {
								SM.stu_L.get(i).att[j-2]=2;
							}else if( Attendance.model.getValueAt(i,j)=="��") {
								SM.stu_L.get(i).att[j-2]=3;
							}else {
								SM.stu_L.get(i).att[j-2]=0;
							}
							//db ������Ʈ
							DB.update(j-1, SM.stu_L.get(i).att[j-2], SM.stu_L.get(i).stunum);
						}
					}else {
						System.out.println("���̺��� �̸��̶� ��ü�� �̸��� �ٸ�.");
					}
				}
			}
			for(int i=0;i<SM.stu_L.size();i++) {
				SM.stu_L.get(i).attCal();
				DB.updateScore("attendance",SM.stu_L.get(i).scores[4].score , SM.stu_L.get(i).stunum);
			}
			
			 Attendance.model_t = null;
			 Attendance.model_t = new tableM_attTotal();
			 for(int i=0;i<SM.stu_L.size();i++) {
				 Attendance.model_t.addRow(SM.stu_L.get(i).rowing_attT());
	         }
			 Attendance.table2.setModel( Attendance.model_t);
			 Attendance.table2.setTableHeader(null);
			 
		}
		
	}
}

