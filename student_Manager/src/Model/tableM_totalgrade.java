package Model;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class tableM_totalgrade extends DefaultTableModel{
	//col name
	static String[] title = {"√—¡°","¿ù¡°"};
	//data
	public Object[][] data = {};
	
	public tableM_totalgrade() {
		super(title,0);
	}
}
