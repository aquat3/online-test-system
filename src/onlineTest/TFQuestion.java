// TrueOrFalseQuestion.java
package onlineTest;

public class TFQuestion extends Question{
   boolean ans;

   public TFQuestion(int examID, int questionNumber, String question, double points, boolean answer)
   {
       super(examID, questionNumber, question, points);
        
       this.ans = answer;
   }

   public boolean isAnswer(){
	  /* if(answer == true)
       return "True";
	   else 
		   return "False"; 
  */
   return ans;
   }

   public void setAnswer(boolean answer){
       this.ans = answer;
   }
   public double getScoreValue(TFQuestion other){  
       if(ans == other.isAnswer())
           return this.points;
      
       return 0;
   }
  
   public String toString(){      
       String answer2= ""+this.ans;
	   if(ans== true)
       answer2="True";
	   else 
		   answer2="False";
       String result=super.toString()+"Correct Answer: "+answer2+"\n";//isAnswer()+"\n";      
       return result;      
   }
}

