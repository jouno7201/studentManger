package Model;

public class Student implements Comparable<Student>{
	 
	   public String name; //이름
	   public int  stunum;//학번
	   
	   public String grade; //학점 
	   public double total = 0; //총점
	   
	   public int[] att;
	   //0중간 ,1기말,2퀴즈,3과제,4출석,5발표,6보고서,7기타
	   public Score[] scores;
	   
	   //생성자에서 변수를 초기화한다.
	   public Student(String name,int stunum) {
	      this.name = name;
	      this.stunum = stunum;
	      
	      this.total = 0;
	      this.grade = null;
	      
	      scores = new Score[8];
	      att = new int[16];
	   }
	   
	   //학생의 총점을 계산한다.
	   public void calculate_score() {
		   total = 0;
	      for(int i=0; i<8; i++) {
	    	  if(scores[i] != null) {
	    		  total += scores[i].score * scores[i].rate;
	    	  }
	    	 
	      }
	   }
	   
	   //학생들의 점수비교를 위한 compaereTo 함수
	   @Override
	   public int compareTo(Student stu2) {
	      if (this.total < stu2.total) {
	         return 1;
	      } else if (this.total == stu2.total) {
	         return 0;
	      } else {
	         return -1;
	       }
	   }
	  
	   //등급 설정
	   public void setGrade(String gr) {
	      this.grade = gr;
	   }
	   
	   // 학생 정보를 배열화 해서 돌려줌 
	   public Object[] rowing_s(){
		   Object[] temp = new Object[14];
		   temp[0] = name;
		   temp[1] = stunum;
		   
		   for(int i=0;i<8;i++) {
			   if(scores[i] != null ) {
				   temp[i+2] = scores[i].score;
			   }
		   }
		   
		   return temp;
	   }
	   //학생 학점과 총점을 배열화 해서 돌려줌.
	   public Object[] rowing_tg(){
		   Object[] temp = new Object[2];
		   temp[0] = total;
		   temp[1] = grade;
		   
		   return temp;
	   }
	   ///
	   public Object[] rowing_att(){
		   Object[] temp = new Object[18];
		   temp[0] = name;
		   temp[1] = stunum;
		   
		   for(int i=2;i<18;i++) {
			   //1은 결석 ,2는 지각 ,3은 출석
			   if(att[i-2] == 1) {
				   temp[i] = "-";
			   }else if(att[i-2] == 2) {
				   temp[i] = "△";
			   }else if( att[i-2] == 3) {
				   temp[i] = "○";
			   }else {
				   temp[i] = null;
			   }
		   }
		   return temp;
	   }
	   public Object[] rowing_attT(){
		   Object[] temp = new Object[3];
		   int[] numtemp = new int[3];
		   
		   for(int i=2;i<18;i++) {
			   //1은 결석 ,2는 지각 ,3은 출석
			   if(att[i-2] == 3) {
				   numtemp[0] ++;
			   }else if(att[i-2] == 1) {
				   numtemp[1]++;
			   }else if( att[i-2] == 2) {
				   numtemp[2]++;
			   }
		   }
		   
		   for(int i=0;i<3;i++) {
			   temp[i]=numtemp[i];
		   }
		   
		   return temp;
	   }
	   public void attCal() {
		   Object[] temp = new Object[3];
		   
		   temp = rowing_attT();
		   //결석시 5점 감점 
		   scores[4].score -= (int)temp[1]*5;
		   
		   //지각 3번에 5점 감점
		   scores[4].score -= ((int)temp[2]/3)*5;
		   
		   System.out.println("철석 점수:"+scores[4].score);
	   }
}
