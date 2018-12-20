package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Student;
import Model.StudentManager;

public class DB_conneting {
	//������ ���̽��� ����� connetion��ü ����
	public Connection con =null;
	
	//String sql ="insert into test_table(name,email,text) values(?,?,?)";
	//String sql = "delete from test_table where id =?";
	String url = "jdbc:mysql://localhost/student?characterEncoding=UTF-8&serverTimezone=UTC";

	String sql = "SELECT * FROM stu_score";
	
	public DB_conneting(StudentManager SM) {
		try {
			//����̹��� �޸𸮿� �ε�
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("db ������...");
			con = DriverManager.getConnection(url, "root", "0504");
			System.out.println("db ���� ����!");
			
			//formDB(SM);
			
		}catch(SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}catch(ClassNotFoundException e1) {
			System.out.println("[JDBC Connector Driver ���� : " + e1.getMessage() + "]");
		}
	}
	
	public void formDB(StudentManager SM) {
		try {
			//�ǻ����� ���� ����
			PreparedStatement pstmt = con.prepareStatement(sql);
			//���๮�� ������� ��ü�� ����� ����
			ResultSet rs = pstmt.executeQuery();
			
			ToStu(rs, SM);
			rs.close();
			pstmt.close();
			pstmt = con.prepareStatement("SELECT * FROM stu_att");
			rs = pstmt.executeQuery();
			
			for(int i=0;rs.next();i++) {
				for(int j=0;j<16;j++) {
					//System.out.println(rs.getInt(j+3));
					SM.stu_L.get(i).att[j] = rs.getInt(j+3);
				}
			}
			rs.close();
			
			pstmt.close();
		}catch(SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}
	}
	
	//����� ������ ��üȭ
	public void ToStu(ResultSet rs,StudentManager SM) {
		try {
			SM.stu_L.clear();
		while(rs.next()) {
			//�л� ��ü ����
			Student stu =new Student(rs.getString(1),rs.getInt(2));
			//�л��� ���� ��ü ����
	        for(int i=3;i<11;i++) {
	        	 if(rs.getInt(i)!= 0) {
	        		 stu.scores[i-3] = new Model.Score(rs.getInt(i));
	        	 }else {
	        		 stu.scores[i-3] = new Model.Score(0);
	        	 }
	        }
	        stu.total = rs.getDouble(11);
	        stu.grade = rs.getString(12);
	       //�л� ����Ʈ�� �߰�  
	       SM.stu_L.add(stu);
	       
		}
		}catch(SQLException e){
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}
	}
	
	//�ѻ���� ������ ��� ���ڿ��� �޾ƿ� db�� �־���.
	public void Insert(String stu[]) {
		
		String sql ="insert into stu_score(name,stu_id,midterm,final,quiz,assignment,attendance,announcement,report,others) values(";
		sql +="\""+stu[0]+"\",";
		for(int i=1;i<9;i++) {
			sql+=stu[i]+",";
		}
		sql +=stu[9]+")";
		
		System.out.println(sql);
		
		try {
			Statement stmt =con.createStatement();
			
			stmt.execute(sql);
			System.out.println("�Է� ����");
			stmt.close();
		}catch (SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}
	}
	
	public void InsertAtt(String stu[]) {
		
		String sql ="insert into stu_att(name,stu_id) values(";
		sql +="\""+stu[0]+"\",";
		sql +=stu[1]+")";
		
		System.out.println(sql);
		
		try {
			Statement stmt =con.createStatement();
			
			stmt.execute(sql);
			System.out.println("�Է� ����");
			stmt.close();
		}catch (SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}
	}

	//DB���� Ŭ���� 
	public void Delete() {
		String sql ="delete from stu_score";
		
		System.out.println(sql);
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.execute();
			
			pstmt.close();
			
			System.out.println("��� �����Ǿ����ϴ�.");
		}catch (SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}
	}
	public void DeleteAtt() {
		String sql ="delete from stu_att";
		
		System.out.println(sql);
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.execute();
			
			pstmt.close();
			
			System.out.println("��� �����Ǿ����ϴ�.");
		}catch (SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}
	}
	
	//DB �⼮ ������Ʈ 
	public void update(int week,int value,int id) {
		String sql ="update stu_att set `"+week+"`";
		sql+="=\""+value+"\" ";
		sql+=" where stu_id="+id;
		
		System.out.println(sql);
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.execute();
			
			pstmt.close();
			
			System.out.println("����� �Է�");
		}catch (SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}
	}
	
	public void updateTotla(double value,int id) {
		String sql ="update stu_score set total ";
		sql+="=\""+value+"\" ";
		sql+=" where stu_id="+id;
		
		System.out.println(sql);
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.execute();
			
			pstmt.close();
			
			System.out.println("����� �Է�");
		}catch (SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}
	}
	
	public void updateGrade(String value,int id) {
		String sql ="update stu_score set grade ";
		sql+="=\""+value+"\" ";
		sql+=" where stu_id="+id;
		
		System.out.println(sql);
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.execute();
			
			pstmt.close();
			
			System.out.println("����� �Է�");
		}catch (SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}
	}
	
	public void updateScore(String col,int value,int id) {
		String sql ="update stu_score set ";
		sql += col+" ";
		sql+="=\""+value+"\" ";
		sql+=" where stu_id="+id;
		
		System.out.println(sql);
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.execute();
			
			pstmt.close();
			
			System.out.println("����� �Է�");
		}catch (SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}
	}
}
