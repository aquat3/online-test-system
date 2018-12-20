package onlineTest;

public class MultChoice extends Question
{
   String ans[];

   public MultChoice(int examID, int questionNumber, String questionText, double points, String answers[]){
       super(examID, questionNumber, questionText, points);
       this.ans = answers;
   }

  
   public void setAnswers(String[] answers){
       this.ans = answers;
   }
   public String[] getAnswers(){
       return ans;
   }

  
  
   public double getScoreValue(MultChoice other){
       String otheranswers[] = other.getAnswers();
       double score =0;
       for(int i =0; i<otheranswers.length; i++)
       {
           if(!(otheranswers[i].equals(ans[i]) )|| (otheranswers.length<ans.length))
    	    {
              score+=0;//score= 0;
           }
           else
        	   score=other.points;
        
       
       
       }
       
       return score;//this.points;
  
   
   }
 
   public String toString(){      
       String result=super.toString()+"Correct Answer: [";
       for(int i = 0; i<ans.length; i++)
       {
           if(i<ans.length-1)
           {
               result+= ans[i]+", ";
           }
           else
           {
               result+=ans[i]+"]\n";
           }
       }
       return result;      
   }
 

}