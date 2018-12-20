package Model;

//학생들의 성적을 계산 및 관리하는 클래스
public class Score {
	   public int score = 0; //성적 변수
	   public int rate; //반영 비율
	   
	   public Score(int score) {
	      this.score = score; 
	      rate =1;
	   }
	   
	   public int getScore() {
		   return  score;
	   }
}
