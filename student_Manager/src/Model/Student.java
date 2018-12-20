package Model;

public class Student implements Comparable<Student>{
	 
	   public String name; //�̸�
	   public int  stunum;//�й�
	   
	   public String grade; //���� 
	   public double total = 0; //����
	   
	   public int[] att;
	   //0�߰� ,1�⸻,2����,3����,4�⼮,5��ǥ,6����,7��Ÿ
	   public Score[] scores;
	   
	   //�����ڿ��� ������ �ʱ�ȭ�Ѵ�.
	   public Student(String name,int stunum) {
	      this.name = name;
	      this.stunum = stunum;
	      
	      this.total = 0;
	      this.grade = null;
	      
	      scores = new Score[8];
	      att = new int[16];
	   }
	   
	   //�л��� ������ ����Ѵ�.
	   public void calculate_score() {
		   total = 0;
	      for(int i=0; i<8; i++) {
	    	  if(scores[i] != null) {
	    		  total += scores[i].score * scores[i].rate;
	    	  }
	    	 
	      }
	   }
	   
	   //�л����� �����񱳸� ���� compaereTo �Լ�
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
	  
	   //��� ����
	   public void setGrade(String gr) {
	      this.grade = gr;
	   }
	   
	   // �л� ������ �迭ȭ �ؼ� ������ 
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
	   //�л� ������ ������ �迭ȭ �ؼ� ������.
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
			   //1�� �Ἦ ,2�� ���� ,3�� �⼮
			   if(att[i-2] == 1) {
				   temp[i] = "-";
			   }else if(att[i-2] == 2) {
				   temp[i] = "��";
			   }else if( att[i-2] == 3) {
				   temp[i] = "��";
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
			   //1�� �Ἦ ,2�� ���� ,3�� �⼮
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
		   //�Ἦ�� 5�� ���� 
		   scores[4].score -= (int)temp[1]*5;
		   
		   //���� 3���� 5�� ����
		   scores[4].score -= ((int)temp[2]/3)*5;
		   
		   System.out.println("ö�� ����:"+scores[4].score);
	   }
}
