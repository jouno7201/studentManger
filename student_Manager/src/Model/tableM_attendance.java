package Model;

import javax.swing.table.DefaultTableModel;

public class tableM_attendance extends DefaultTableModel{
	//col name
	static String[] title = { "捞抚", "切锅", "1林", "2林", "3林", "4林", "5林", "6林", "7林", "8林", 
            "9林", "10林", "11林", "12林", "13林", "14林", "15林", "16林"};
	//data
	public Object[][] data = {};
	
	public tableM_attendance(){
		super(title,0);
	}
}
