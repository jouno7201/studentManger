package Model;

import javax.swing.table.DefaultTableModel;

public class tableM_score extends DefaultTableModel{
	//col name
	static String[] title = { "�̸�", "�й�", "�߰�", "�⸻", "����", "����","�⼮","��ǥ","����","��Ÿ"};
	//data
	public Object[][] data = {};
	
	public tableM_score() {
		super(title,0);
	}

}
