package View;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import DB.DB_conneting;
import Model.StudentManager;

public class myTappedPane extends JTabbedPane{
	 
	public myTappedPane(StudentManager SM,DB_conneting DB) {
		JPanel P_attendance = new Attendance(SM,DB);
		JPanel P_score = new Score(SM,DB);
		JPanel P_statistics = new Statistics(SM);
		 
		add("출석",P_attendance);
		add("성적",P_score);
		add("통계", P_statistics);
	}
}
