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
	//데이터 베이스와 연결될 connetion객체 생성
	public Connection con =null;
	
	//String sql ="insert into test_table(name,email,text) values(?,?,?)";
	//String sql = "delete from test_table where id =?";
	String url = "jdbc:mysql://localhost/student?characterEncoding=UTF-8&serverTimezone=UTC";

	String sql = "SELECT * FROM stu_score";
	
	public DB_conneting(StudentManager SM) {
		try {
			//드라이버를 메모리에 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("db 연결중...");
			con = DriverManager.getConnection(url, "root", "0504");
			System.out.println("db 연결 성공!");
			
			//formDB(SM);
			
		}catch(SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}catch(ClassNotFoundException e1) {
			System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]");
		}
	}
	
	public void formDB(StudentManager SM) {
		try {
			//실생문은 위한 변수
			PreparedStatement pstmt = con.prepareStatement(sql);
			//실행문의 결과값을 객체로 만들어 저장
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
	
	//디비의 내용을 객체화
	public void ToStu(ResultSet rs,StudentManager SM) {
		try {
			SM.stu_L.clear();
		while(rs.next()) {
			//학생 객체 생성
			Student stu =new Student(rs.getString(1),rs.getInt(2));
			//학생의 성적 객체 생걱
	        for(int i=3;i<11;i++) {
	        	 if(rs.getInt(i)!= 0) {
	        		 stu.scores[i-3] = new Model.Score(rs.getInt(i));
	        	 }else {
	        		 stu.scores[i-3] = new Model.Score(0);
	        	 }
	        }
	        stu.total = rs.getDouble(11);
	        stu.grade = rs.getString(12);
	       //학생 리스트에 추가  
	       SM.stu_L.add(stu);
	       
		}
		}catch(SQLException e){
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}
	}
	
	//한사람의 내용이 담긴 문자열을 받아와 db에 넣어줌.
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
			System.out.println("입력 성공");
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
			System.out.println("입력 성공");
			stmt.close();
		}catch (SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}
	}

	//DB내용 클리어 
	public void Delete() {
		String sql ="delete from stu_score";
		
		System.out.println(sql);
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.execute();
			
			pstmt.close();
			
			System.out.println("모두 삭제되었습니다.");
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
			
			System.out.println("모두 삭제되었습니다.");
		}catch (SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}
	}
	
	//DB 출석 업데이트 
	public void update(int week,int value,int id) {
		String sql ="update stu_att set `"+week+"`";
		sql+="=\""+value+"\" ";
		sql+=" where stu_id="+id;
		
		System.out.println(sql);
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.execute();
			
			pstmt.close();
			
			System.out.println("제대로 입력");
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
			
			System.out.println("제대로 입력");
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
			
			System.out.println("제대로 입력");
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
			
			System.out.println("제대로 입력");
		}catch (SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}
	}
}
