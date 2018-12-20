package onlineTest;

import java.io.*;

public class Question implements Serializable{
   
  String question;
   int questionNum;
   double points;
   int examID;
   
   public Question(int examID, int questionNumber, String question, double points) {
   
	   this.question = question;
       this.points = points;
       
       this.questionNum = questionNumber;
      this.examID = examID;
       
      
   
   
   }

   
   public void setQuestion(String question){
       this.question = question;
   }
   public String getQuestion(){
       return question;
   }

   
 public void setPoints(double points){
       this.points = points;
   }
   public double getPoints(){
       return points;
   }

  
 public void setExamID(int examID){
       this.examID = examID;
   }
   public int getExamID(){
       return examID;
   }

   public void setQuestionNumber(int questionNumber){
       this.questionNum = questionNumber;
   }

   public int getQuestionNumber(){
       return questionNum;
   }

  
   
   public String toString(){
       String result = "Question Text: "+getQuestion()+"\n";
       result+="Points: "+getPoints()+"\n";
       return result;
   }
}