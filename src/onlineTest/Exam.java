 
package onlineTest;
import java.io.*;
import java.util.*;

public class Exam implements Serializable
{
   int examID;
   String examTitle;
   Set<Question> questions;

   public Exam(){
       
       questions = new HashSet<Question>();
       examTitle = "";
       examID = 0;
       
   }

   public Exam(int examID, String examTitle){
       this.examID = examID;
       this.examTitle = examTitle;
       questions = new HashSet<Question>();
   }

   
   
   
   public void setExamID(int examID){
       this.examID = examID;
   }
  
   
   
   public int getExamID() {
       return examID;
   }

   
public void setExamTitle(String examTitle){
       this.examTitle = examTitle;
   }
   

public String getExamTitle(){
       return examTitle;
   }

   

   public void addQuestion(Question question) {
       questions.add(question);
   }

   public Question getQuestion(int examId){
       for (Question question : questions)
       {
           if (question.getExamID() == examId)
               return question;
       }
       return null;
   }
   
   public Set<Question> getQuestions(){
       return questions;
   }

   

   public boolean equals(Exam other){
       if (this.examID == other.getExamID())
       {
           if (this.examTitle.equals(other.getExamTitle()))
               return true;
           else
               return false;
       }
       else
           return false;
   }
   

}