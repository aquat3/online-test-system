 
package onlineTest;

public class FillinBlank extends Question
{

   String ans[];
 
   public void setAnswer(String[] answers){
       this.ans = answers;
   }
   public String[] getAnswers(){
       return ans;
   }

  

   public FillinBlank(int examID, int questionNumber, String question, double points, String answers[]){
       super(examID, questionNumber, question, points);
       
       this.ans = answers;
   }
  //PROBLEM!!
   public double getScoreValue(FillinBlank other) {
       String otheranswers[] = other.getAnswers();
       double score =0;
       for(int i =0; i<otheranswers.length; i++)
       {
           if(!(otheranswers[i].equals(ans[i])))
           {
               score+= 0;
           }
     
        score+=2.0;//(points/2); //  otheranswers.length
      //other.points==6.0 &&
      //  if(otheranswers[i]=="boolean") {//otheranswers.length<answers.length)//
        
    	 // score+=4.0;//
        
       // } 
        
       }
      
       return score;//this.points;
   }
  
   public String toString(){      
       String result=super.toString()+"Correct Answer: [";
       for(int i = ans.length-1; i>=0; i--)
       {
           if(i>0)
           {
               result+= ans[i]+", ";
           }
           else
           {
                result+=ans[i]+"]\n";
           }
      
        
       }
      // result+="]\n";//
       return result;      
   }
}