package Model;

import javax.swing.table.DefaultTableModel;

public class tableM_attTotal extends DefaultTableModel{
	//col name
		static String[] title = { "출석","결석","지각"};
		//data
		public int[][] data = {};
		
		public tableM_attTotal(){
			super(title,0);
		}

}
