package Model;

import javax.swing.table.DefaultTableModel;

public class tableM_score extends DefaultTableModel{
	//col name
	static String[] title = { "이름", "학번", "중간", "기말", "퀴즈", "과제","출석","발표","보고서","기타"};
	//data
	public Object[][] data = {};
	
	public tableM_score() {
		super(title,0);
	}

}
