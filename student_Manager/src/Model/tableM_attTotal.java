package Model;

import javax.swing.table.DefaultTableModel;

public class tableM_attTotal extends DefaultTableModel{
	//col name
		static String[] title = { "�⼮","�Ἦ","����"};
		//data
		public int[][] data = {};
		
		public tableM_attTotal(){
			super(title,0);
		}

}
