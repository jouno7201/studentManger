package Main;

import DB.DB_conneting;
import Model.StudentManager;
import View.myFrame;

public class Driver {

	public static void main(String[] args) {
		
		StudentManager SM =new StudentManager();
		
		DB_conneting DB = new DB_conneting(SM);
		
		new myFrame(SM,DB);
		
	}
}
