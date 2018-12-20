package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentManager{
   // 학생 객체 참조변수 
   public List<Student> stu_L =new ArrayList<Student>();
   
   //등급비율  
   public static double Aplus_rate = 0.15;
   public static double Azero_rate = 0.3;
   public static double Bplus_rate = 0.45;
   public static double Bzero_rate = 0.6;
   public static double Cplus_rate = 0.7;
   public static double Czero_rate = 0.8;
   
   public StudentManager(){
	   
   }

   
   //학생의 등급이 계산한다.
   public void calculate_grade() {
      Collections.sort(stu_L);
      
      for(int i=0;i<stu_L.size();i++) {
    	  if(stu_L.size()*Aplus_rate>i) {
    		  stu_L.get(i).grade = "A+";
    	  }else if(stu_L.size()*Azero_rate>i) {
    		  stu_L.get(i).grade = "A0";
    	  }else if(stu_L.size()*Bplus_rate>i) {
    		  stu_L.get(i).grade = "B+";
    	  }else if(stu_L.size()*Bzero_rate>i) {
    		  stu_L.get(i).grade = "B0";
    	  }else if(stu_L.size()*Cplus_rate>i) {
    		  stu_L.get(i).grade = "C+";
    	  }else if(stu_L.size()*Czero_rate>i) {
    		  stu_L.get(i).grade = "C0";
    	  }else {
    		  stu_L.get(i).grade = "F";
    	  }
    	  
    	  System.out.println(stu_L.get(i).grade);
      }
      
   }
   
   //점수 구간 대별로 반환
   public int[] dividing(int subj) {
	   int[] temp = new int[5];
	   for(int i=0;i<5;i++) { temp[i]=0;}
	   
	   for(int i=0;i<stu_L.size();i++) {
		   //System.out.println(stu_L.get(i).scores[subj].score);
		   if(stu_L.get(i).scores[subj].score <60) {
			   temp[0]++;
		   }else if(stu_L.get(i).scores[subj].score <70) {
			   temp[1]++;
		   }else if(stu_L.get(i).scores[subj].score <80) {
			   temp[2]++;
		   }else if(stu_L.get(i).scores[subj].score <90) {
			   temp[3]++;
		   }else {
			   temp[4]++;
		   }
	   }
	   
	   return temp;
   }
   
}
