package Model;

import javax.swing.table.DefaultTableModel;

public class tableM_attendance extends DefaultTableModel{
	//col name
	static String[] title = { "�̸�", "�й�", "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", 
            "9��", "10��", "11��", "12��", "13��", "14��", "15��", "16��"};
	//data
	public Object[][] data = {};
	
	public tableM_attendance(){
		super(title,0);
	}
}
